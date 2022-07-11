package com.zay.service.impl;

import com.zay.entity.StudentEntity;
import com.zay.mapper.StudentMapper;
import com.zay.pojo.R;
import com.zay.service.HelloWorldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zay
 * @date 2021/6/2 15:37
 */
@Service
@AllArgsConstructor
public class HelloWorldServiceImpl implements HelloWorldService {

    StudentMapper studentMapper;

    @Override
    public R getAllStudent(String type) {
        List<StudentEntity> list = studentMapper.selectList(null);
        System.out.println(type);
        return R.ok(list);
    }
}
