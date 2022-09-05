package com.zay.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-09-02 11:05
 * 不启用看门狗机制，手动设置释放锁时间
 * 1. 可重入锁（Reentrant Lock）  已实现
 * 2. 公平锁（Fair Lock）    已实现
 * 3. 联锁（MultiLock） 不实现
 * 4. 红锁（RedLock）   不实现
 * 5. 读写锁（ReadWriteLock）    已实现
 * 6. 信号量（Semaphore）    已实现，可实现限流
 * 7. 可过期性信号量（PermitExpirableSemaphore） 不实现，限流可实现 无单个信号量过期需求
 * 8. 闭锁（CountDownLatch）    不实现
 */
@Slf4j
@Component
public class RedissonUtils {

    @Resource
    private RedissonClient redissonClient;

    @Value("${spring.redisson.lock.wait-time}")
    private long waitTime;
    @Value("${spring.redisson.lock.lease-time}")
    private long leaseTime;
    private TimeUnit unit = TimeUnit.SECONDS;

    /**
     * 可重入锁
     * 迭代版本：可重载抛出自定义异常
     * 但是如果遇到需要其他进程也能解锁的情况，请使用分布式信号量Semaphore 对象
     * @param lockKey key
     * @param sup     Function
     * @param <R>     自定义返回值
     * @return 自定义返回值
     * @throws InterruptedException
     */
    private <R> R tryLock(String lockKey, Supplier<R> sup, RuntimeException e) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);
       return lock(lockKey, lock, sup, e);
    }

    public <R> R tryLock(String lockKey, Supplier<R> sup) throws InterruptedException {
        return tryLock(lockKey, sup, new RuntimeException("没抢到锁 抛出一个异常"));
    }

    /**
     * 公平锁
     * @param lockKey
     * @param sup
     * @param e
     * @param <R>
     * @return
     * @throws InterruptedException
     */
    private <R> R fairLock(String lockKey, Supplier<R> sup, RuntimeException e) throws InterruptedException {
        RLock lock = redissonClient.getFairLock(lockKey);
        return lock(lockKey, lock, sup, e);
    }

    /**
     * 读写锁  写锁
     * @param lockKey
     * @param sup
     * @param e
     * @param <R>
     * @return
     * @throws InterruptedException
     */
    private <R> R writeLock(String lockKey, Supplier<R> sup, RuntimeException e) throws InterruptedException {
        RReadWriteLock lock = redissonClient.getReadWriteLock(lockKey);
        RLock rLock = lock.writeLock();
        return lock(lockKey, rLock, sup, e);
    }

    /**
     * 读写锁  读锁
     * @param lockKey
     * @param sup
     * @param e
     * @param <R>
     * @return
     * @throws InterruptedException
     */
    private <R> R readLock(String lockKey, Supplier<R> sup, RuntimeException e) throws InterruptedException {
        RReadWriteLock lock = redissonClient.getReadWriteLock(lockKey);
        RLock rLock = lock.readLock();
        return lock(lockKey, rLock, sup, e);
    }

    private  <R> R lock(String lockKey, RLock lock, Supplier<R> sup, RuntimeException e) throws InterruptedException {
        // 尝试加锁，最多等待30秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(waitTime, leaseTime, unit);
        if (res) {
            try {
                log.info(Thread.currentThread().getId() + ",我抢到了一个锁." + lockKey);
                return sup.get();
            } finally {
                lock.unlock();
            }
        }
        log.info("tryLock 没抢到锁,当前线程:{},lockKey:{}", Thread.currentThread().getId(), lockKey);
        throw e;
    }

    /**
     * 分布式信号量
     * 信号量:也可以用作限流
     * @param lockKey
     * @param sup
     * @param e
     * @param <R>
     * @return
     * @throws InterruptedException
     */
    private  <R> R semaphore(String lockKey, Supplier<R> sup, RuntimeException e) throws InterruptedException {
        RSemaphore semaphore = redissonClient.getSemaphore(lockKey);
        // 尝试加锁，最多等待30秒，上锁以后10秒自动解锁
        boolean acquire = semaphore.tryAcquire(waitTime, unit);
        if (acquire) {
            try {
                log.info(Thread.currentThread().getId() + ",我抢到了一个锁." + lockKey);
                return sup.get();
            } finally {
                semaphore.release();
            }
        }
        log.info("tryAcquire 没抢到锁,当前线程:{},lockKey:{}", Thread.currentThread().getId(), lockKey);
        throw e;
    }
}
