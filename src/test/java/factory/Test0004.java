package factory;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;
import java.util.stream.Stream;

/**
 * @Author: ZhouAnYan
 * @Date: 2022-07-11 9:21
 */
@Slf4j
public class Test0004 {

    @Test
    public void test6() {
        System.out.println("");
        CoffeeFactory factory = new CoffeeFactory();
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
}
