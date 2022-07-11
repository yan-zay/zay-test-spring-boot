package test0003;

import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;
import test0001.User01;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZhouAnYan
 * @Date: 2022/2/17 15:38
 * @Version 1.0
 */
public class Test0003 {



    @Test
    public void test6() {
//        Object o = new Object();
//        getzay01(o);
        User0003 user0003 = new User0003();
        getzay01(user0003);
    }
//    decimal places
    private void getzay01(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field:fields) {
            Ann01 annotation = field.getAnnotation(Ann01.class);
            annotation.value();
            System.out.println(annotation);
        }
        System.out.println();

        List<Object> aa = aa(new ArrayList<User0003>());
    }

    
    public <T> List<T> aa(List<? extends User0003> list) {
        return null;
    }
}
