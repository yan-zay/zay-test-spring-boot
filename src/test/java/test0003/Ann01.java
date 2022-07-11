package test0003;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: ZhouAnYan
 * @Date: 2022/2/17 15:37
 * @Version 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann01 {
    public String value();
    String name2() default "333";
//    int[] array();
}