package test0004;

import org.junit.Test;
import test0003.User0003;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:21
 * @Version 1.0
 */
public class Test0004 {

    @Test
    public void test6() {
//        Object o = new Object();
//        getzay01(o);
        System.out.println("");
        CoffeeFactory cF = new CoffeeFactory();
        Coffee coffee = CoffeeFactory.buildCoffee(1);
        System.out.println(coffee.getPrice());
    }
}
