package test0001;

import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;
import org.springframework.util.StringUtils;
import test0001.User0002;
import test0001.User0003;
import test0001.User0004;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: ZhouAnYan
 * @Date: 2021/12/28 10:40
 * @Version 1.0
 */
public class Test0003 {



    @Test
    public void test3(){
        List<Object> list = getUserList();
        User0002 u2 = (User0002) list.get(0);
        User0003 u3 = (User0003) list.get(1);
        User0004 u4 = (User0004) list.get(2);
        System.out.println(JSONObject.toJSONString(u2));
        System.out.println(JSONObject.toJSONString(u3));
        System.out.println(JSONObject.toJSONString(u4));
    }

    List<Object> getUserList(){
        User0002 usr2 = new User0002();
        usr2.setName("1");
        usr2.setAge("2");
        usr2.setIsSex("3");
        usr2.setIsSucc("4");
        User0003 usr3 = new User0003();
        usr3.setName("1");
        usr3.setAge("2");
        usr3.setIssex("3");
        usr3.setIssucc("4");
        User0004 usr4 = new User0004();
        usr4.setName("1");
        usr4.setAge("2");
        usr4.setSex("3");
        usr4.setSucc("4");
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(usr2);
        obj.add(usr3);
        obj.add(usr4);
        return obj;
    }

    @Test
    public void test1(){
        String str = "private String a_bb_ccc";
        StringBuilder builder = new StringBuilder();
        Arrays.asList(str.split("_")).forEach(temp -> builder.append(StringUtils.capitalize(temp)));
        System.out.println(builder);
    }

    @Test
    public void test2(){
        System.out.println(new java.text.DecimalFormat("#.00").format(3.1415926));
        System.out.println(new java.text.DecimalFormat("#.00").format(3.1145));
        System.out.println(new java.text.DecimalFormat("#.00").format(3.00));
    }
}
