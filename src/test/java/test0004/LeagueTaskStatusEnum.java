package test0004;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-07-25 9:29
 */
@AllArgsConstructor
public enum LeagueTaskStatusEnum {
    /** 公会规则枚举 */
    TASK_STATUS_1(1, "未接单"),
    TASK_STATUS_2(2, "已接单"),
    TASK_STATUS_3(3, "已完成");
    @EnumValue
    private int value;
    private String desc;
}
