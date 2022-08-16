package com.zay.factory;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-07-25 10:22
 * 公会规则条件过滤 plan B 策略模式+工厂模式 (请勿过度设计)
 */
public class LeagueRuleFactory {
    private static Map<Integer, LeagueRuleTemplate> leagueRuleMap = new HashMap<>();

    public static LeagueRuleTemplate buildLeagueRule(int type) {
        return leagueRuleMap.get(type);
    }

    public static void register(int type, LeagueRuleTemplate leagueRule) {
        leagueRuleMap.put(type, leagueRule);
    }
}
