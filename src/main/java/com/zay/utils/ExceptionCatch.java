package com.zay.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-03-30 17:59
 * @Version 1.0
 */
@ControllerAdvice//控制器增强
@Log4j2
public class ExceptionCatch {

    //捕获Exception此类异常
    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseBody
    public String exception(Exception exception) {
        System.out.println("zay------------------------------");
        exception.printStackTrace();
        //记录日志打印
        log.error("catch exception:{}", exception.getMessage());
        //返回自己定义的通用异常
        return "ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR)";
    }
}
