package factory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:59
 */
@Component
public class AmericanoCafe implements Coffee, InitializingBean {
    @Override
    public int getPrice() {
        return 0002;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CoffeeFactory.register(2,this);
    }
}
