package test0004;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import test0003.User0003;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class Test0004 {

    @Test
    public void test6() {
//        Object o = new Object();
//        getzay01(o);
        System.out.println("");
        CoffeeFactory cF = new CoffeeFactory();
        Coffee coffee = CoffeeFactory.buildCoffee(1);
        System.out.println(coffee.getPrice());
    }

    @Test
    public void test7() {
        String UTC_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter fmt = DateTimeFormat.forPattern(UTC_FORMATTER_PATTERN);
        DateTime now = DateTime.now(DateTimeZone.UTC);
        String nowStr = fmt.print(now);

        Date date = new Date(nowStr);
//        fmt.
        log.info("nowStr" + date);
        System.out.println();
    }

    @Test
    public void test8() {
        Stream<Long> stream1 = Stream.of(1L,2L,4L,2L,4L,3L);

        Stream<Long> stream = stream1.distinct();

        stream.forEach(System.out::print);
        System.out.println();
    }

    @Test
    public void test9() {
        /**
         * 过滤和加起来
         */
        User09 u0 = new User09("6",new BigDecimal(0));
        User09 u1 = new User09("1",new BigDecimal(1));
        User09 u2 = new User09("2",new BigDecimal(2));
        User09 u3 = new User09("3",new BigDecimal(3));
        User09 u4 = new User09("4",new BigDecimal(2));
        User09 u5 = new User09("5",new BigDecimal(3));
        User09 u6 = new User09("6",new BigDecimal(4));
        User09 u7 = new User09("6",new BigDecimal(0));
        List<User09> list = Arrays.asList(u0,u1,u2,u3,u4,u5,u6,u7);

        System.out.println(list);
//        Stream<Long> stream = stream1.distinct();
        List<User09> list2 = list.stream().filter(o ->
                o.getAmount().equals(BigDecimal.ZERO)
        ).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(list2);

/*        ArrayList<PatentDto> collect1 = patentDtoList.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(p->p.getPatentName() + ";" + p.getLevel()))), ArrayList::new)*/
    }

    @Test
    public void test10() {
        System.out.println(LeagueTaskStatusEnum.TASK_STATUS_3.equals(LeagueTaskStatusEnum.TASK_STATUS_3));
        System.out.println(LeagueTaskStatusEnum.TASK_STATUS_3.compareTo(LeagueTaskStatusEnum.TASK_STATUS_3));

        System.out.println(LeagueTaskStatusEnum.TASK_STATUS_3.equals(3L));
//        System.out.println(LeagueTaskStatusEnum.TASK_STATUS_3.compareTo(3L));

        System.out.println(LeagueTaskStatusEnum.TASK_STATUS_3.equals(3L));
//        System.out.println(LeagueTaskStatusEnum.TASK_STATUS_3.equals(LeagueNoticeStatusEnum.NOTICE_STATUS_3));
    }

    @Test
    public void test11() {
        System.out.println(LocalDateTime.now().minusDays(-7));
    }

    @Test
    public void test12() {

    }
}
