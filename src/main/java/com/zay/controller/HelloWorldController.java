package com.zay.controller;

import com.zay.entity.StudentEntity;
import com.zay.mapper.StudentMapper;
import com.zay.pojo.R;
import com.zay.service.HelloWorldService;
import com.zay.factory.Coffee;
import com.zay.factory.CoffeeFactory;
import com.zay.utils.RedisUtils;
import com.zay.utils.RedissonUtils;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zay
 * @date 2021/6/2 15:16
 */
@Controller
@ResponseBody
@RequestMapping("/hello")
@AllArgsConstructor
@Slf4j
public class HelloWorldController {

    HelloWorldService helloWorldService;
    StudentMapper studentMapper;

    @GetMapping("helloWorld")
    public String helloWorld(String id) {
        return "HelloWorld!321, 我是葫芦娃的大娃！" + id;
    }

    @GetMapping("test01")
    public String test01(String test01) {
        System.out.println("test01 0001");
        if (true) {
            throw new RuntimeException();
        }
        System.out.println("test01 0020");
        return "HelloWorld!321, 我是葫芦娃的大娃！";
    }

    @GetMapping("test03")
    @ApiOperation("工厂模式+策略模式")
    public Integer test03(String type) {
        Coffee coffee = CoffeeFactory.buildCoffee(Integer.parseInt(type));
        log.info("获取coffee价格：" + coffee.getPrice());
        log.error("获取coffee价格：" + coffee.getPrice());
        return coffee.getPrice();
    }

    @GetMapping("test04")
    public R test04(String type) {
        return helloWorldService.getAllStudent(type);
    }

    @GetMapping("test05")
    public R test05(String type) {
        StudentEntity stu = new StudentEntity();
        stu.setName(type);
        stu.setCreateTime(new Date());
        stu.setUpdateTime(new Date());
        int insert = studentMapper.insert(stu);
        log.info("insert" + insert);
        return R.ok();
    }

    @GetMapping("test06")
    public R test06(String type) {
        String UTC_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter fmt = DateTimeFormat.forPattern(UTC_FORMATTER_PATTERN);
        DateTime now = DateTime.now(DateTimeZone.UTC);
        String nowStr = fmt.print(now);

//        Date date = new Date();
        log.info("nowStr" + nowStr);
        return R.ok(nowStr);
    }

    @GetMapping("/test08")
    public R test08(String type) {
        System.out.println(type);
        return R.ok(type);
    }

    @PostMapping("/test09")
    public R test09(@RequestBody LeagueRuleVO vo) {
        return checkLeagueRule(vo);
    }

    /**
     * 校验公会规则参数
     * @param vo
     */
    private R checkLeagueRule(LeagueRuleVO vo) {
        //必传参数不能为空
        if (Objects.isNull(vo.getEnabledAuth()) || Objects.isNull(vo.getReportPrice()) || Objects.isNull(vo.getChatPrice())) {
            return R.failed("非法参数 111");
        }
        BigDecimal authPrice = vo.getAuthPrice();
        //公会认证价格为0 或 大于100且是100的整数倍
        if (vo.getEnabledAuth()) {
            if (Objects.isNull(authPrice)) {
                return R.failed("非法参数 222");
            }
            if (authPrice.compareTo(BigDecimal.ZERO) < 0 || authPrice.doubleValue()%10 != 0) {
                return R.failed("非法参数 333");
            }
        }
        //推荐报告价格大于10
        if (vo.getReportPrice().compareTo(new BigDecimal(10)) < 0) {
            return R.failed("非法参数 444");
        }
        //聊天价格大于10
        if (vo.getChatPrice().compareTo(new BigDecimal(10)) < 0) {
            return R.failed("非法参数 555");
        }
        return R.ok();
    }

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/test10")
    public R test10(String str) {
        String result = redisUtils.get(str);
        System.out.println(result);
        return R.ok(result);
    }

    @GetMapping("/test11")
    public R test11(String str) throws InterruptedException {
        zay01(str,"1");
        return R.ok();
    }
    @GetMapping("/test12")
    public R test12(String str) throws InterruptedException {
        zay01(str,"2");
        return R.ok();
    }

    /**
     * 可重入锁
     * @param str
     * @param str2
     * @throws InterruptedException
     */
    private void zay01(String str, String str2) throws InterruptedException {
        RLock lock = redissonClient.getLock(str);
        // 最常见的使用方法
//        lock.lock();
        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(100, 6, TimeUnit.SECONDS);
        if (res) {
            try {
                System.out.println(Thread.currentThread().getId() + ","+str2+",我抢到了一个锁！"+ str);
//                Thread.sleep(15000);
            } finally {
                System.out.println("执行到了");
//                lock.unlock();
            }
        }
    }

    @GetMapping("/test13")
    public R test13(String str) throws InterruptedException {
        zay02(str);
        return R.ok();
    }
    private void zay02(String str) throws InterruptedException {
        RLock lock = redissonClient.getLock(str);
        // 最常见的使用方法
        lock.lock(20, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getId()+"执行到了");
    }

    @Resource
    RedissonUtils redissonUtils;
    @GetMapping("/test14")
    public R test14(String str) throws InterruptedException {
        return redissonUtils.tryLock(str, () -> {
            System.out.println("我反手就执行一个方法14");
            return R.ok();
        });
    }
    @GetMapping("/test15")
    public R test15(String str) throws InterruptedException {
        return redissonUtils.tryLock(str, () -> {
            System.out.println("我反手就执行一个方法15");
            return R.ok();
        });
    }
}
