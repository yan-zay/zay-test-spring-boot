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

    private final static String LOCK_KEY_PREFIX = "lock:";

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
     *
     * @param lockKey key
     * @param func     Function
     * @return 自定义返回值
     * @throws InterruptedException
     */
    private void tryLock(String lockKey, Functions func, RuntimeException e) throws InterruptedException {
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + lockKey);
        lock(lockKey, lock, func, e);
    }

    public void tryLock(String lockKey, Functions func) throws InterruptedException {
        tryLock(lockKey, func, new RuntimeException("没抢到锁 抛出一个异常"));
    }

    public void add(String lockKey, Functions func) throws InterruptedException {
        tryLock(lockKey, func, new RuntimeException("没抢到锁 抛出一个异常"));
    }

    /**
     * 公平锁
     *
     * @param lockKey
     * @param func
     * @param e
     * @return
     * @throws InterruptedException
     */
    private void fairLock(String lockKey, Functions func, RuntimeException e) throws InterruptedException {
        RLock lock = redissonClient.getFairLock(LOCK_KEY_PREFIX + lockKey);
        lock(lockKey, lock, func, e);
    }

    /**
     * 读写锁  写锁
     *
     * @param lockKey
     * @param func
     * @param e
     * @return
     * @throws InterruptedException
     */
    private void writeLock(String lockKey, Functions func, RuntimeException e) throws InterruptedException {
        RReadWriteLock lock = redissonClient.getReadWriteLock(LOCK_KEY_PREFIX + lockKey);
        RLock rLock = lock.writeLock();
        lock(lockKey, rLock, func, e);
    }

    /**
     * 读写锁  读锁
     *
     * @param lockKey
     * @param func
     * @param e
     * @return
     * @throws InterruptedException
     */
    private void readLock(String lockKey, Functions func, RuntimeException e) throws InterruptedException {
        RReadWriteLock lock = redissonClient.getReadWriteLock(LOCK_KEY_PREFIX + lockKey);
        RLock rLock = lock.readLock();
        lock(lockKey, rLock, func, e);
    }

    private void lock(String lockKey, RLock lock, Functions func, RuntimeException e) throws InterruptedException {
        // 尝试加锁，最多等待30秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(waitTime, leaseTime, unit);
        if (res) {
            try {
                log.info(Thread.currentThread().getId() + ",我抢到了一个锁." + lockKey);
                func.execute();
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
     *
     * @param lockKey
     * @param func
     * @param e
     * @return
     * @throws InterruptedException
     */
    private void semaphore(String lockKey, Functions func, RuntimeException e) throws InterruptedException {
        RSemaphore semaphore = redissonClient.getSemaphore(LOCK_KEY_PREFIX + lockKey);
        // 尝试加锁，最多等待30秒，上锁以后10秒自动解锁
        boolean acquire = semaphore.tryAcquire(waitTime, unit);
        if (acquire) {
            try {
                log.info(Thread.currentThread().getId() + ",我抢到了一个锁." + lockKey);
                func.execute();
            } finally {
                semaphore.release();
            }
        }
        log.info("tryAcquire 没抢到锁,当前线程:{},lockKey:{}", Thread.currentThread().getId(), lockKey);
        throw e;
    }
}
