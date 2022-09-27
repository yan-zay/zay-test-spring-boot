package test0006;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import test0004.User09;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:21
 */
@Slf4j
public class Test0002 {

    @Test
    public void test1() {
        DayEnum dayEnum = EnumUtils.valueOf(4, DayEnum.class);
        System.out.println(dayEnum);
    }

    @EnumKey
    public int test01() {
        return 1;
    }

    @Test
    public void test2() {
//        DayEnum dayEnum = EnumUtils.valueOf(2, DayEnum.class);
        DayEnum day_1 = Enum.valueOf(DayEnum.class, "DAY_1");
        DayEnum day_11 = DayEnum.valueOf("DAY_1");
        System.out.println();
    }

    @Test
    public void test3() {
        System.out.println(DayEnum.DAY_1.getValue());
    }

    @Test
    public void test4() {
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime now2 = LocalDateTime.now().minusSeconds(1);
        System.out.println(now1.isAfter(now2));
    }

    @Test
    public void test5() {
        User09 u1 = new User09("01", new BigDecimal(1));
        User09 u2 = new User09().setAmount(new BigDecimal(2));
//        String[] nullPropertyNames = ;
        BeanUtils.copyProperties(u2, u1, MyBeanUtils.getNullPropertyNames(u2));
        System.out.println();
    }

    @Test
    public void test6() {
        long a = 123456;
        Long b = new Long(123456);
        System.out.println(a == b);
        System.out.println();
    }

    private static Map<DayEnum, String> map = new HashMap<>();

    @Test
    public void test7() {
        map.put(DayEnum.DAY_1,"1");
        map.put(DayEnum.DAY_3,"3");
        System.out.println();
        System.out.println();
    }
}
