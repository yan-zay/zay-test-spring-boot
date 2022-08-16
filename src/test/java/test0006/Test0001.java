package test0006;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import test0004.Coffee;
import test0004.CoffeeFactory;
import test0004.LeagueTaskStatusEnum;
import test0004.User09;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:21
 */
@Slf4j
public class Test0001 {

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
}
