package com.zay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 15:57
 */
@Data
@TableName("student04")
public class Student03 {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String sex;
    private String age;

    //    @TableField(fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private LocalDateTime createTime;
    //    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
