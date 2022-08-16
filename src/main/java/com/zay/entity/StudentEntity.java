package com.zay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 15:57
 */
@Data
@TableName("student")
public class StudentEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String sex;
    private String age;
    private Date createTime;
    private Date updateTime;
}
