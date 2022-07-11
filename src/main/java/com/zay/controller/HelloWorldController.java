package com.zay.controller;

import com.zay.pojo.R;
import com.zay.service.HelloWorldService;
import com.zay.test01.Coffee;
import com.zay.test01.CoffeeFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zay
 * @date 2021/6/2 15:16
 */
@Controller
@ResponseBody
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloWorldController {

    HelloWorldService helloWorldService;

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
    public Integer test03(String type) {
        Coffee coffee = CoffeeFactory.buildCoffee(Integer.parseInt(type));
        System.out.println(coffee.getPrice());
        return coffee.getPrice();
    }

    @RequestMapping("test04")
    public R test04(String type) {
        return helloWorldService.getAllStudent(type);
    }
}
