package com.zay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 15:57
 * @Version 1.0
 */
@Data
@TableName("student")
public class StudentEntity {
    private String id;
    private String name;
    private String sex;
    private String age;
}
