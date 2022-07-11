package com.zay.test01;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:56
 * @Version 1.0
 */
@Component
public class Cappuccino implements Coffee, InitializingBean {

    @Override
    public int getPrice() {
        return 2;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CoffeeFactory.register(1,this);
    }
}
