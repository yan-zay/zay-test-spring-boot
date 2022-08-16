package com.zay.factory;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 公会规则 DO
 *
 * @author 芋道源码
 */
@TableName("duke_league_rule")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueRuleDO {

    /**
     * 公会规则表ID
     */
    @TableId
    private Long id;
    /**
     * 公会ID
     */
    private Long leagueId;
    /**
     * 种类 关联公会规则模板表
     */
    private Integer type;
    /**
     * 规则开启状态 1是 0 否
     */
    private String status;
    /**
     * 内容
     */
    private String content;
    /**
     * 更新人
     */
    private Long updateUserId;

    @TableField(exist = false)
    private String creator;
    @TableField(exist = false)
    private String updater;
    @TableField(exist = false)
    private Boolean deleted;

}
