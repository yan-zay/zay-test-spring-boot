package com.zay.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:52
 */
public class CoffeeFactory {

    private static Map<Integer, Coffee> coffeeMap = new HashMap<>();

    public static Coffee buildCoffee(int type) {
        return coffeeMap.get(type);
    }

    public static void register(int type, Coffee coffee) {
        coffeeMap.put(type, coffee);
    }
}
