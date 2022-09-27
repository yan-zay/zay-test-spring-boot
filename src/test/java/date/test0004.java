package date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author: ZhouAnYan
 * @Date: 2022/1/17 21:34
 */
public class test0004 {

    @Test
    public void test7() {
        String day = getDay("202202");
        System.out.println(day);
    }

    public static String getDay(String startTime){
        Calendar calendar = new GregorianCalendar(Integer.parseInt(startTime.substring(0,4)), Integer.parseInt(startTime.substring(4,6)), Calendar.SUNDAY);
        calendar.roll(Calendar.MONTH, -1);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    @Test
    public void test6() throws ParseException {
        String str = "20220130";
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        Date parse = sim.parse(str);
        System.out.println(parse.toString());
        System.out.println(parse.getDay());
    }

    @Test
    public void test5() throws ParseException {
        //创建SimpleDateFormat对象实例并定义好转换格式
/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("把当前时间转换成字符串：" + sdf.format(new Date()));

        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse("2005-12-15 15:30:23");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("字符串转换成时间:" + date);*/

/*        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dd;
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        dd = simpleDateFormat.format(cale.getTime());
        System.out.println(dd);
        System.out.println();*/

//        System.out.println(simpleDateFormat.format(str));

//        Date date = new Date(2021,01,17);
//        System.out.println(date.getTime());
//        System.out.println(date.getMonth());
//        System.out.println(date.getDay());
//        System.out.println(simpleDateFormat.format(date));
    }

    @Test
    public void test4() throws ParseException {
        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();

        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        int dow = cale.get(Calendar.DAY_OF_WEEK);
        int dom = cale.get(Calendar.DAY_OF_MONTH);
        int doy = cale.get(Calendar.DAY_OF_YEAR);

        System.out.println("Current Date: " + cale.getTime());
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
        System.out.println("Second: " + second);
        System.out.println("Day of Week: " + dow);
        System.out.println("Day of Month: " + dom);
        System.out.println("Day of Year: " + doy);

        // 获取当月第一天和最后一天

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String firstday, lastday;

        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());

        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);

        // 获取当前日期字符串
        Date d = new Date();
        System.out.println("当前日期字符串1：" + format.format(d));
        System.out.println("当前日期字符串2：" + year + "/" + month + "/" + day + " "+ hour + ":" + minute + ":" + second);
    }
}
