package customAnnotation;

import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Author: ZhouAnYan
 * @Date: 2022/1/17 21:34
 */
public class AnnotationTest {

    @Test
    public void test1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AnnotationVO user1 = getUser0005();
        System.out.println(user1);
        String s = JSONObject.toJSONString(user1);
        AnnotationVO user = JSONObject.parseObject(s, AnnotationVO.class);

        Field[] field = user.getClass().getDeclaredFields();
        for(int j=0 ; j<field.length ; j++){ //遍历所有属性
            String name = field[j].getName(); //获取属性的名字
//            System.out.println("attribute name:"+name);
            name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
//            String type = field[j].getGenericType().toString(); //获取属性的类型
            Class<?> type = field[j].getType();//获取属性的类型
            if( type == BigDecimal.class){
                Method method = user.getClass().getMethod("get" + name);
                BigDecimal bigDecimal = (BigDecimal)method.invoke(user);
                System.out.println(bigDecimal);
                bigDecimal = CommonUtils.getBigDecimal(bigDecimal);
                Method setMethod = user.getClass().getMethod("set" + name, BigDecimal.class);
                setMethod.invoke(user, bigDecimal);
            }
//            System.out.println(type == String.class);
//            if(type.equals("class java.lang.String")){ //如果type是类类型，则前面包含"class "，后面跟类名
//                System.out.println("aaaaaaaaa");
//            }
        }
    }

    public static AnnotationVO getUser0005(){
        AnnotationVO user = new AnnotationVO();
        return user;
    }
}
