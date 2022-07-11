package test0001;

import com.alibaba.fastjson2.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: ZhouAnYan
 * @Date: 2021/12/24 15:33
 * @Version 1.0
 */
public class Test0002 {

    @Test
    public void test() {
/*        JSONObject json = new JSONObject();
        json.put("0001", "z0001");
        json.put("0002", "z0002");
        json.put("0003", "z0003");
        JSONObject json2 = test01(json);
        System.out.println(json);
        System.out.println(json2);
        System.out.println(json==json2);*/
        User01 user01 = new User01();
        System.out.println(user01);
        user01.addStr("cc","cc");
        user01.addStr("dd", "dd");
        System.out.println(user01);
        User01 user02 = user01.addStr("qq", "qq").addStr("ww", "ww").addStr("ww", "ww").addStr("ww", "ww");
        System.out.println(user02);
        System.out.println(user02.getObj());
        System.out.println(user01==user02);
        System.out.println();
    }



    private JSONObject test01(JSONObject json) {
        json.put("0004", "04");
        return json;
    }

    @Before
    public void before() { }

    @After
    public void after() { }

}
