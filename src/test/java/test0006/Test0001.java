package test0006;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:21
 */
@Slf4j
public class Test0001 {

    /**
     * 枚举小工具是这个方法
     */
    @Test
    public void test1() {
        DayEnum dayEnum = EnumUtils.valueOf(4, DayEnum.class);
        /** 会输出星期四，可以打断点看下 */
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
}
