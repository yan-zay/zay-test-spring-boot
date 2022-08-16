package com.zay.factory;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-07-25 9:29
 */
public enum LeagueRuleTypeEnum {
    /** 公会规则枚举 */

    NOTICE_TYPE_1(1),
    NOTICE_TYPE_2(2),
    NOTICE_TYPE_3(3),
    NOTICE_TYPE_4(4),
    NOTICE_TYPE_5(5),
    NOTICE_TYPE_6(6);

    private int value;

    LeagueRuleTypeEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
