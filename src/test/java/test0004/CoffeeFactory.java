package test0004;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:52
 * @Version 1.0
 */
@Component
public class CoffeeFactory {

    private static Map<Integer, Coffee> coffeeMap = new HashMap<>();

    public static Coffee buildCoffee(int type) {
        return coffeeMap.get(type);
    }

    public static void register(int type, Coffee coffee) {
        coffeeMap.put(type, coffee);
    }
}
