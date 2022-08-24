package com.zay.controller;

import com.zay.entity.StudentEntity;
import com.zay.factory.LeagueRule;
import com.zay.factory.LeagueRuleFactory;
import com.zay.factory.LeagueRuleTemplate;
import com.zay.mapper.StudentMapper;
import com.zay.pojo.R;
import com.zay.service.HelloWorldService;
import com.zay.test01.Coffee;
import com.zay.test01.CoffeeFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

    @RequestMapping("helloWorld")
    public String helloWorld(String id) {
        return "HelloWorld!321, 我是葫芦娃的大娃！" + id;
    }

    @RequestMapping("test01")
    public String test01(String test01) {
        System.out.println("test01 0001");
        if (true) {
            throw new RuntimeException();
        }
        System.out.println("test01 0020");
        return "HelloWorld!321, 我是葫芦娃的大娃！";
    }

    @RequestMapping("test03")
    @ApiOperation("工厂模式+策略模式")
    public Integer test03(String type) {
        Coffee coffee = CoffeeFactory.buildCoffee(Integer.parseInt(type));
        log.info("获取coffee价格：" + coffee.getPrice());
        log.error("获取coffee价格：" + coffee.getPrice());
        return coffee.getPrice();
    }

    @RequestMapping("test04")
    public R test04(String type) {
        return helloWorldService.getAllStudent(type);
    }

    @RequestMapping("test05")
    public R test05(String type) {
        StudentEntity stu = new StudentEntity();
        stu.setName(type);
        stu.setCreateTime(new Date());
        stu.setUpdateTime(new Date());
        int insert = studentMapper.insert(stu);
        log.info("insert" + insert);
        return R.ok();
    }

    @RequestMapping("test06")
    public R test06(String type) {
        String UTC_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter fmt = DateTimeFormat.forPattern(UTC_FORMATTER_PATTERN);
        DateTime now = DateTime.now(DateTimeZone.UTC);
        String nowStr = fmt.print(now);

//        Date date = new Date();
        log.info("nowStr" + nowStr);
        return R.ok(nowStr);
    }

    @PostMapping("/test07")
    @ApiOperation("测试任务")
    @ApiImplicitParam(name = "id", value = "公会id", required = true, example = "1024", dataTypeClass = Long.class)
    public R test07(@RequestBody LeagueTaskRequestVO vo) {
//        leagueRuleService.test(id);
        System.out.println("");
        LeagueRuleTemplate leagueRuleTemplate = LeagueRuleFactory.buildLeagueRule(1);
        return R.ok(leagueRuleTemplate);
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

    private R checkLeagueRule(LeagueRuleVO vo) {
        if (Objects.isNull(vo.getEnabledAuth())) {
            return R.failed("非法参数1");
        }
        if (!(vo.getEnabledAuth()
                && vo.getAuthPrice().compareTo(BigDecimal.ZERO) >= 0 && vo.getAuthPrice().doubleValue()%100==0)) {
            return R.failed("非法参数2");
        }
        if (vo.getReportPrice().compareTo(new BigDecimal(10)) < 0) {
            return R.failed("非法参数3");
        }
        if (vo.getChatPrice().compareTo(new BigDecimal(10)) < 0) {
            return R.failed("非法参数4");
        }
        BigDecimal base = new BigDecimal(10L);
        return R.ok("", "执行成功");
    }
}
