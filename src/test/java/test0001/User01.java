package test0001;

import com.alibaba.fastjson2.JSONObject;

/**
 * @Author: ZhouAnYan
 * @Date: 2021/12/24 15:50
 * @Version 1.0
 */
public class User01 {

    public JSONObject json = new JSONObject();

    public User01(){
        this.json.put("aa","aa");
        this.json.put("bb","bb");
    }

    public User01 addStr(String k, String v){
        this.json.put(k, v);
        return this;
    }

    public JSONObject getObj(){
        return this.json;
    }
}
