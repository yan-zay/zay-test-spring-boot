package test0004;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 10:00
 */
@Component
public class Latte implements Coffee, InitializingBean {
    @Override
    public int getPrice() {
        return 0003;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CoffeeFactory.register(3,this);
    }
}
