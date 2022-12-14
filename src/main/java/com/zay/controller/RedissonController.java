package com.zay.controller;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.zay.pojo.R;
import com.zay.utils.Functions;
import com.zay.utils.RedissonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-09-02 15:41
 */
@Controller
@ResponseBody
@RequestMapping("/redisson")
@AllArgsConstructor
@Slf4j
public class RedissonController {

    RedissonClient redissonClient;
    RedissonUtils redissonUtils;

    @GetMapping("/test01")
    public R test01(String str) throws InterruptedException {
/*        return redissonUtils.tryLock(str, () -> {
            System.out.println("我反手就执行一个方法14");
            return R.ok();
        });*/
        return R.ok();
    }
    @GetMapping("/test02")
    public R test02(String str) throws InterruptedException {
/*        return redissonUtils.tryLock(str, () -> {
            System.out.println("我反手就执行一个方法15");
            return R.ok();
        });*/
        return R.ok();
    }

    @GetMapping("/test03")
    public String test03() throws InterruptedException {
        test0304("03");
        return "test03";
    }
    @GetMapping("/test04")
    public String test04() throws InterruptedException {
        test0304("04");
        return "test04";
    }

    /**
     * 10秒到期释放锁
     * @param str
     * @throws InterruptedException
     */
    public void test0304(String str) throws InterruptedException {
        //1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("my-lock");
        // 尝试加锁，最多等待30秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(30, 10, TimeUnit.SECONDS);
        if (res) {
            try {
                log.info(Thread.currentThread().getId() + ",我抢到了一个锁."+str);
                Thread.sleep(5000);
            } finally {
                log.info(Thread.currentThread().getId() + "释放锁");
                lock.unlock();
            }
        }
    }

    @GetMapping("/test05")
    public String test05() throws InterruptedException {
        zay01(() -> {

        });

        zay01(() -> {
            String aa = "01";
            System.out.println(aa);
            return aa;
        });

//        test0304("04");
        return "test05";
    }

    private void zay01(Functions func) {
        func.execute();
    }

    private void zay01(Supplier func) {
        func.get();
    }

/*    private String zay02(String str) {
        return "str";
    }
    private void zay02(String str) {

    }*/

    @GetMapping("/test06")
    public String test06() throws InterruptedException {
/*        redissonUtils.tryLock("lockKey",() -> {

        }, new RuntimeException(""));*/

        return "test06";
    }
}
