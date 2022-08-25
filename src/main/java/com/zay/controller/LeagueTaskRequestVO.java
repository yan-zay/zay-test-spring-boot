package com.zay.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-07-22 15:37
 */
@Data
public class LeagueTaskRequestVO {

    /** 任务金额 */
    @ApiModelProperty(value = "任务金额",required = true)
    private BigDecimal money;

    /** 任务表ID */
    @ApiModelProperty(value = "任务表ID",required = true)
    private List<String> ids;
}
