package com.zay.controller;

import com.zay.entity.Student03;
import com.zay.mapper.TestMapper;
import com.zay.pojo.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zay
 * @date 2021/6/2 15:16
 */
@Controller
@ResponseBody
@RequestMapping("/test")
@AllArgsConstructor
@Slf4j
@Api("test")
public class TestController0001 {

    PlatformTransactionManager transactionManager;//JdbcTransactionManager
//    DataSourceTransactionManager
//    TransactionDefinition
    TestMapper testMapper;

    @GetMapping("test01")
    public R test01(String str) {
        Student03 student03 = new Student03();
        student03.setName("0001");
        testMapper.insert(student03);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);//新发起一个事务
        TransactionStatus defStatus = transactionManager.getTransaction(def);
        // 业务代码
        try {
            student03.setId(13L);
            student03.setName("0002");
            testMapper.insert(student03);
            throw new RuntimeException("1111111111111111111111");
        }catch (RuntimeException e) {

        }finally {
            System.out.println("finally");
            transactionManager.rollback(defStatus);
        }
        transactionManager.commit(defStatus);// 手动提交事务
        return R.ok();
    }

    @GetMapping("test02")
    public R test02() {
        testMapper.insert(null);
        return R.ok();
    }

    @GetMapping("test03")
    public R test03() {
        Student03 student03 = new Student03();
        testMapper.insert(student03);
        return R.ok(student03);
    }

    @GetMapping("test04")
    public R test04() {
        Student03 student03 = new Student03();
        student03.setName("0001");
        testMapper.insert(student03);
        return R.ok(student03);
    }


/*    public CommonResult test() {
        int insert = tkc.insert(new TaskKeyChatDO().setTaskId(2L).setTaskKey("22"));
        if (insert == 1)
            throw new RuntimeException();
        return null;
    }

    @GetMapping("/testEnums")
    @ApiOperation("测试枚举工具类接口 前端忽略")
    public CommonResult<DayEnum> testEnums(Integer value) {
        //第一个参数value 传1234，第二个参数 填枚举的类型
        DayEnum dayEnum = EnumUtils.valueOf(value, DayEnum.class);
        return success(dayEnum);
    }

    @PostMapping("/testEnums")
    @ApiOperation("测试枚举2")
    public CommonResult<TaskChatVO2> testEnums2(@RequestBody TaskChatVO2 task) {
        //第一个参数value 传1234，第二个参数 填枚举的类型
//        DayEnum dayEnum = EnumUtils.valueOf(value, DayEnum.class);
        System.out.println(task);
        return success(task);
    }

    @GetMapping("/test2")
    @ApiOperation("test2")
    public CommonResult<Page<LeagueNoticeDO>> test2(Long value) {
        System.out.println(taskUtils);
//        System.out.println(taskUtils.taskProperties.reportExpiresTime);
//        System.out.println(taskUtils.taskProperties.reportExpiresTimeFormat);
//        System.out.println(taskUtils.chat);
        Page page = leagueNoticeMapper.selectPage(new Page<>(1, 10), value);
        return success(page);
    }

    @GetMapping("/test3")
    @ApiOperation("test3")
    @DistributedLocked(lockName = "'test3'")
    public CommonResult test3(Long value) {
//        System.out.println(redisson01);
        return success(null);
    }

    @Resource
    RedissonUtils redissonUtils;

    @GetMapping("/test4")
    @ApiOperation("test4")
    public CommonResult test4(String str) {
        boolean lockKey = redissonUtils.tryLock("lockKey", () -> {
            String aa = ":"+str;
            System.out.println(aa);
            System.out.println("执行了代码");
        });
        return success(lockKey);
    }*/
}
