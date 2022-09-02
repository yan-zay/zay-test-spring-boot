package com.zay.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-09-02 11:05
 */
@Slf4j
@Component
public class RedissonUtils {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 可重入锁
     * @param
     * @throws InterruptedException
     */
    public <R> R tryLock(String lockKey, Supplier<R> sup) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);
        // 尝试加锁，最多等待30秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(30, 10, TimeUnit.SECONDS);
        if (res) {
            try {
                System.out.println(Thread.currentThread().getId() + ",我抢到了一个锁！"+ lockKey);
                return sup.get();
            } finally {
                lock.unlock();
            }
        }
        log.info("没抢到锁,当前线程:{},lockKey:{}", Thread.currentThread().getId(), lockKey);
        throw new RuntimeException("没抢到锁 111111111111111111111111111111");
    }
}
