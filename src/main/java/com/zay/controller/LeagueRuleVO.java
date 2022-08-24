package com.zay.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-07-22 15:37
 */
@Data
public class LeagueRuleVO {
    @ApiModelProperty(value = "公会ID",required = true)
    private Long leagueId;

    @ApiModelProperty(value = "公会名称",required = true)
    private String leagueName;
    @ApiModelProperty(value = "公会描述",required = true)
    private String leagueDesc;
    @ApiModelProperty(value = "公会头像URL",required = true)
    private String leagueIcon;
    @ApiModelProperty(value = "公会是否已被认证",required = true)
    private Boolean leagueAuthFlag;

    @ApiModelProperty(value = "是否开通认证")
    private Boolean enabledAuth;
    @ApiModelProperty(value = "认证价格")
    private BigDecimal authPrice;
    @ApiModelProperty(value = "写推荐报告价格")
    private BigDecimal reportPrice;
    @ApiModelProperty(value = "聊天价格")
    private BigDecimal chatPrice;
    @ApiModelProperty(value = "是否允许用户加入")
    private Boolean enabledUserJoin;
    @ApiModelProperty(value = "用户加入价格")
    private BigDecimal userJoinPrice;
}
