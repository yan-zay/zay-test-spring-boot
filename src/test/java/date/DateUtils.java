package date;

//import com.coin.cloud.common.core.entity.vo.StatusCode;
//import com.coin.cloud.common.core.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final String DATEFORMAT_YMD = "yyyy-MM-dd";

    public static final SimpleDateFormat shortSdf
            = new SimpleDateFormat(DATEFORMAT_YMD);
    private static final Pattern TIME_P = Pattern.compile("(\\d+)时(\\d+)分(\\d+)秒(\\d+)毫秒");
    static String[] parsePatterns = {"yyyy-MM-dd", "yyyy/MM/dd"};
    public static final String YMD_HM = "yyyy-MM-dd HH:mm";
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyyMMdd";

    public static String getTimeStampS() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String getTimeStampMS() {
        return String.valueOf(System.currentTimeMillis());
    }


    /**
     * 返回一天的最开始的时候,00:00:00
     *
     * @param date
     * @return
     */
    public static Date getBeginningOfADay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 返回一天的最后的时候, 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndOfADay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 格式化输出日期
     *
     * @param date   日期
     * @param format 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        return format(date, format, Locale.getDefault());
    }

    /**
     * 格式化输出日期 支持Locale
     *
     * @param date   日期
     * @param format 格式
     * @param locale 返回字符型日期
     * @return
     */
    public static String format(Date date, String format, Locale locale) {
        try {
            if (null != date) {
                DateFormat df = new SimpleDateFormat(format, locale);
                return df.format(date);
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 返回月初时间
     *
     * @param date
     * @return
     */
    public static Date getBeginingOfAMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取月末时间
     *
     * @param date
     * @return
     */
    public static Date getEndOfAMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取当前日期周一
     *
     * @param date
     * @return
     */
    public static Date getBeginingOfAWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (2 == dayWeek) {
            return date;
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        return cal.getTime();
    }

    /**
     * 获取当前日期周末
     *
     * @param date
     * @return
     */
    public static Date getEndOfAWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            return date;
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.DATE, 8 - dayWeek);
        return cal.getTime();
    }

    public static Date getOneMonthLater(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    public static String DEFAULT_DATE_PATTERN = getDatePattern();

    public static final String HMS_DATE_PATTERN = "HH:mm:ss";

    public static final String YMSHMS_DATE_PATTERN = getYMD_HMSDatePattern();

    public static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat ymd2 = new SimpleDateFormat("yyyy/MM/dd");
    public static SimpleDateFormat ymd3 = new SimpleDateFormat("yyMMdd");
    public static SimpleDateFormat ymd4 = new SimpleDateFormat("MMdd");

    public static SimpleDateFormat ymdh = new SimpleDateFormat("yyyyMMddHH");

    public static SimpleDateFormat dmy = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat dmy2 = new SimpleDateFormat("dd/MM/yyyy");

    public static SimpleDateFormat dmy_hms = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    public static SimpleDateFormat dmy_hms2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    public static SimpleDateFormat dmy_hm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat dmy_hm2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public static SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");

    public static SimpleDateFormat hm = new SimpleDateFormat("HH:mm");

    public static SimpleDateFormat ymd_hms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat ymd_hms2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static SimpleDateFormat ymd_hm = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    //使用这个变量保证SimpleDateFormat线程安全
    public static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static SimpleDateFormat ymd_hm2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public static SimpleDateFormat yy = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat wp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Calendar cale = Calendar.getInstance();

    private static final String YMD_KEY = "ymd";
    private static final String YMD_KEY2 = "ymd2";
    private static final String YMD_KEY3 = "ymd3";

    private static final String DMY_KEY = "dmy";
    private static final String DMY_KEY2 = "dmy2";

    private static final String DMY_HMS_KEY = "dmy_hms";
    private static final String DMY_HMS_KEY2 = "dmy_hms2";

    private static final String DMY_HM_KEY = "dmy_hm";
    private static final String DMY_HM_KEY2 = "dmy_hm2";

    private static final String HMS_KEY = "hms";

    private static final String YMD_HMS_KEY = "ymd_hms";
    private static final String YMD_HMS_KEY2 = "ymd_hms2";

    private static final String YMD_HM_KEY = "ymd_hm";
    private static final String YMD_HM_KEY2 = "ymd_hm2";

    private static final String YY = "yy";

    public static final String ymdPattern = "^\\d{4}[-]\\d{1,2}[-]\\d{1,2}$";
    public static final String ymdPattern2 = "^\\d{4}[/]\\d{1,2}[/]\\d{1,2}$";
    public static final String ymdPattern3 = "^\\d{4}\\d{2}\\d{2}$";

    public static final String dmyPattern = "^\\d{1,2}[-]\\d{1,2}[-]\\d{4}$";
    public static final String dmyPattern2 = "^\\d{1,2}[/]\\d{1,2}[/]\\d{4}$";

    public static final String dmy_hmsPattern = "^\\d{1,2}[-]\\d{1,2}[-]\\d{4}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";
    public static final String dmy_hmsPattern2 = "^\\d{1,2}[/]\\d{1,2}[/]\\d{4}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";

    public static final String dmy_hmPattern = "^\\d{1,2}[-]\\d{1,2}[-]\\d{4}\\s\\d{1,2}[:]\\d{1,2}$";
    public static final String dmy_hmPattern2 = "^\\d{1,2}[/]\\d{1,2}[/]\\d{4}\\s\\d{1,2}[:]\\d{1,2}$";


    public static final String hmsPattern = "^\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";

    public static final String ymd_hmsPattern = "^\\d{4}[-]\\d{1,2}[-]\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";
    public static final String ymd_hmsPattern2 = "^\\d{4}[/]\\d{1,2}[/]\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";

    public static final String ymd_hmPattern = "^\\d{4}[-]\\d{1,2}[-]\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}$";
    public static final String ymd_hmPattern2 = "^\\d{4}[/]\\d{1,2}[/]\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}$";

    public static final String yyPattern = "^\\d{8}$";

    public final static String MONTH = "MONTH";

    public final static String WEEK = "WEEK";

    public final static String DAY = "DAY";

    public final static String HOUR = "HOUR";

    public final static String MINUTE = "MINUTE";

    public final static String SECOND = "SECOND";

    public static Map<String, SimpleDateFormat> formatPattern = new HashMap<String, SimpleDateFormat>();

    public static DateFormat getDateFormatPattern(String str) {
        if (str.matches(ymdPattern)) {
            return formatPattern.get(YMD_KEY);
        } else if (str.matches(ymdPattern2)) {
            return formatPattern.get(YMD_KEY2);
        } else if (str.matches(hmsPattern)) {
            return formatPattern.get(HMS_KEY);
        } else if (str.matches(ymd_hmsPattern)) {
            return formatPattern.get(YMD_HMS_KEY);
        } else if (str.matches(ymd_hmsPattern2)) {
            return formatPattern.get(YMD_HMS_KEY2);
        } else if (str.matches(ymd_hmPattern)) {
            return formatPattern.get(YMD_HM_KEY);
        } else if (str.matches(ymd_hmPattern2)) {
            return formatPattern.get(YMD_HM_KEY2);
        } else if (str.matches(yyPattern)) {
            return formatPattern.get(YY);
        } else if (str.matches(dmyPattern)) {
            return formatPattern.get(DMY_KEY);
        } else if (str.matches(dmyPattern2)) {
            return formatPattern.get(DMY_KEY2);
        } else if (str.matches(dmy_hmsPattern)) {
            return formatPattern.get(DMY_HMS_KEY);
        } else if (str.matches(dmy_hmsPattern2)) {
            return formatPattern.get(DMY_HMS_KEY2);
        } else if (str.matches(dmy_hmPattern)) {
            return formatPattern.get(DMY_HM_KEY);
        } else if (str.matches(dmy_hmPattern2)) {
            return formatPattern.get(DMY_HM_KEY2);
        }
        return new SimpleDateFormat();
    }

    /**
     * 验证日期格式
     *
     * @param dateStr
     * @param pattern
     */
    public static boolean verifyDateFormat(String dateStr, String pattern) {
        if (dateStr.matches(pattern)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /*
     * 日期型字符串转化为日期 格式（"yyyy-MM-dd","yyyy/MM/dd"）
     */
/*    public static Date parseDate(String str, String... pattern) throws ParseException {
        throw new ApiException(StatusCode.FAIL.getCode(), "Not Implemented");
    }*/

    /*
     * 月份相加
     */
    public static Date addMonths(Date date, int m) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, +m);
        return c.getTime();
    }


    public static Date getDateFormat(String str) {
        if (str.matches(ymdPattern)) {
            return formatStrToDateYMD(str);
        } else if (str.matches(hmsPattern)) {
            return formatDateHMS(str);
        } else if (str.matches(ymd_hmsPattern)) {
            return formatStrToDate(str);
        } else if (str.matches(ymd_hmPattern)) {
            return formatDateYMD_HM(str);
        } else if (str.matches(yyPattern)) {
            return formatDate(str);
        }
        return new Date();
    }

    public static Date formatDate(String str) {
        try {
            return yy.parse(str);
        } catch (Exception e) {
            log.debug("DateUtil.formatDate():" + e.getMessage());
            return null;
        }
    }

    public static Date formatDateWp(String str) {
        try {
            str = str.replace("T", " ");
            return wp.parse(str);
        } catch (Exception e) {
            log.debug("DateUtil.formatDate():" + e.getMessage());
            return null;
        }
    }

    public static Date formatDateByPattern(String dateString) {
        try {
            if (dateString.equals("0000/00/00")) {
                return null;
            }
            DateFormat format = getDateFormatPattern(dateString);
            return format.parse(dateString);
        } catch (Exception e) {
            log.debug("DateUtil.formatDate():" + e.getMessage());
            return null;
        }
    }

    /**
     * @param str     符串时期格式形式
     * @param pattern 格式规格
     * @return 格式后的字符
     */
    public static String formatDateByPattern(String str, String pattern) {
        try {
            if (StringUtils.isEmpty(pattern)) {
                return str;
            }
            return formatPattern.get(pattern).format(formatStrToDate(str));
        } catch (Exception e) {
            log.debug("DateUtil.formatDateByPattern():" + e.getMessage());
            return "";
        }
    }

    /**
     * 返回默认的日期格式
     */
    public static synchronized String getDatePattern() {
        DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
        return DEFAULT_DATE_PATTERN;
    }

    public static String getYMD_HMSDatePattern() {
        return DEFAULT_DATE_PATTERN + " " + HMS_DATE_PATTERN;
    }

    /**
     * 传入格式为日期的字符, 格式为：yyyy-MM-dd 的日期字符串形式返回
     */
    public static Date formatStrToDateYMD(String str) {
        try {
            return ymd.parse(str);
        } catch (Exception e) {
            log.debug("DateUtil.formatStrToDateYMD():" + e.getMessage());
            return null;
        }

    }

    /**
     * 传入格式为日期的字符, 格式为：yyMMDD 的日期字符串形式返回
     */
    public static String formatStrToDateyyMMdd(Date date) {
        try {
            return ymd3.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatStrToDateYMD():" + e.getMessage());
            return null;
        }

    }
    public static Date formatStrToDateyyMMdd(String date) {
        try {
            return ymd3.parse(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatStrToDateYMD():" + e.getMessage());
            return null;
        }

    }

    public static String formatStrToDateMMdd(Date date) {
        try {
            return ymd4.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatStrToDateYMD():" + e.getMessage());
            return null;
        }

    }
    public static Date formatStrToDateMMdd(String date) {
        try {
            return ymd4.parse(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatStrToDateYMD():" + e.getMessage());
            return null;
        }

    }

    public static String formatDateYMDToStr(Date date) {
        if (date == null)
            return null;
        try {
            return ymd.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateYMDToStr():" + e.getMessage());
            return null;
        }

    }

    public static String formatDateYMDHToStr(Date date) {
        if (date == null)
            return null;
        try {
            return ymdh.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateYMDToStr():" + e.getMessage());
            return null;
        }

    }

    /**
     * 传入格式为日期的字符, 格式为：yyyy-MM-dd HH:mm:ss 的日期字符串形式返回
     */
    public static Date formatStrToDate(String str) {
        try {
            return ymd_hms.parse(str);
        } catch (Exception e) {
            log.debug("DateUtil.formatStrToDate():" + e.getMessage());
            return null;
        }

    }

    public static String formatDateToStr(Date date) {
        try {
            return ymd_hms.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateToStr():" + e.getMessage());
            return null;
        }

    }
    public static String formatDateYMSToStr(Date date) {
        try {
            return yy.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateToStr():" + e.getMessage());
            return null;
        }

    }
    public static String formatDateToYMD_HM(Date date) {
        try {
            return ymd_hm.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateToYMD_HM():" + e.getMessage());
            return null;
        }

    }

    /**
     * 传入格式为日期的字符, 格式为：yyyy-MM-dd HH:mm 的日期字符串形式返回
     */
    public static Date formatDateYMD_HM(String str) {
        try {
            return ymd_hm.parse(str);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateYMD_HM():" + e.getMessage());
            return null;
        }

    }

    /**
     * 并发字符转date格式（保证线程安全）
     *
     * @param str
     * @return
     */
    public static Date concurrentFormatDateYMD_HMS(String str) {
        try {
            return df.get().parse(str);
        } catch (Exception e) {
            log.debug("test0002.DateUtils.formatDateYMD_HMS():" + e.getMessage());
            return null;
        }

    }

    /**
     * 传入格式为日期的字符, 格式为：HH:mm:ss 的日期字符串形式返回
     */
    public static Date formatDateHMS(String str) {
        try {
            return hms.parse(str);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateHMS:" + e.getMessage());
            return null;
        }

    }

    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
     */
    public static String getDateTime() {
        try {
            return ymd_hms.format(cale.getTime());
        } catch (Exception e) {
            log.debug("DateUtil.getDateTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
     */
    public static String getDate() {
        try {
            return ymd.format(cale.getTime());
        } catch (Exception e) {
            log.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
     */
    public static String getTime() {
        String temp = " ";
        try {
            temp += hms.format(cale.getTime());
            return temp;
        } catch (Exception e) {
            log.debug("DateUtil.getTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期的年份
     */
    public static String getYear() {
        try {
            return String.valueOf(cale.get(Calendar.YEAR));
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("DateUtil.getYear():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期的月份
     */
    public static String getMonth() {
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.applyPattern("00;00");
            return df.format((cale.get(Calendar.MONTH) + 1));
        } catch (Exception e) {
            log.debug("DateUtil.getMonth():" + e.getMessage());
            return "";
        }
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 获得服务器在当前月中天数
     */
    public static String getDay() {
        try {
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            log.debug("DateUtil.getDay():" + e.getMessage());
            return "";
        }
    }

    /**
     * 比较两个日期相差的天数
     */
    public static int getMargin(String date1, String date2) {
        int margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = ymd.parse(date1, pos);
            Date dt2 = ymd.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的天数
     */
    public static double getDoubleMargin(String date1, String date2) {
        double margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = ymd_hms.parse(date1, pos);
            Date dt2 = ymd_hms.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (24 * 60 * 60 * 1000.00));
            return margin;
        } catch (Exception e) {
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的月数
     */
    public static int getMonthMargin(String date1, String date2) {
        int margin;
        try {
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer
                    .parseInt(date1.substring(0, 4))) * 12;
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",
                    "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll(
                    "-0", "-")));
            return margin;
        } catch (Exception e) {
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 获得last距now多少年
     *
     * @param last
     * @param now
     * @return
     */
    public static int getYearMargin(Date last, Date now) {
        Calendar cal = Calendar.getInstance();
        if (now != null)
            cal.setTime(now);
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
//        int monthNow = cal.get(Calendar.MONTH);
//        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        //将日期设置为出生日期
        cal.setTime(last);
        //取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
//        int monthBirth = cal.get(Calendar.MONTH);
//        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth;
        //如果是同年.则是一年
        if (age == 0) {
            age = 1;
        }
        return age;
    }

    /**
     * 初始化
     */
    private static void addFormatPattern() {
        formatPattern.put(YMD_KEY, ymd);
        formatPattern.put(YMD_KEY2, ymd2);
        formatPattern.put(YMD_KEY3, ymd3);

        formatPattern.put(HMS_KEY, hms);
        formatPattern.put(YMD_HMS_KEY, ymd_hms);
        formatPattern.put(YMD_HMS_KEY2, ymd_hms2);
        formatPattern.put(YMD_HM_KEY, ymd_hm);
        formatPattern.put(YMD_HM_KEY2, ymd_hm2);
        formatPattern.put(YY, yy);
        formatPattern.put(DMY_KEY, dmy);
        formatPattern.put(DMY_KEY2, dmy2);
        formatPattern.put(DMY_HMS_KEY, dmy_hms);
        formatPattern.put(DMY_HMS_KEY2, dmy_hms2);
        formatPattern.put(DMY_HM_KEY, dmy_hm);
        formatPattern.put(DMY_HM_KEY2, dmy_hm2);
    }

    static {
        addFormatPattern();
    }

    /*
     * DAY, HOUR, MINUTE,SECOND 多种方式增加时间
     *
     * @see com.order007.server.util.EasyDate#increaseDate(java.util.Date,
     *      java.lang.String, int)
     */
    public static Date increaseDate(Date date, String increaseType, int delta) {

        Calendar cal = new GregorianCalendar();
        try {
            cal.setTime(date);

            if (increaseType.equals(MONTH)) {
                cal.add(Calendar.MONTH, delta);
            }

            if (increaseType.equals(WEEK)) {
                cal.add(Calendar.WEEK_OF_MONTH, delta);
            }

            if (increaseType.equals(DAY)) {
                cal.add(Calendar.DATE, delta);
            }

            if (increaseType.equals(HOUR)) {
                cal.add(Calendar.HOUR, delta);
            }

            if (increaseType.equals(MINUTE)) {
                cal.add(Calendar.MINUTE, delta);
            }

            if (increaseType.equals(SECOND)) {
                cal.add(Calendar.SECOND, delta);
            }

            return cal.getTime();

        } catch (Exception e) {

            throw new RuntimeException("date format error");
        }
    }

    /*
     * 将一个 “1999-12-01 12:22:00" 的字符串转换为Date 类型返回
     */
    public static Date getDate(String sDate) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(sDate);
        } catch (ParseException e) {

            throw new RuntimeException("date format error");
        }
    }

    /**
     * 将指定格式的字符串转为Date
     *
     * @param sDate
     * @param format 遵循 simpleDateFormat 规范
     * @return
     */
    public static Date getDate(String sDate, String format) {

        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(sDate);
        } catch (ParseException e) {

            throw new RuntimeException("date format error");
        }
    }

    /**
     * 用于产生指定日期的时间对。 从当天0:00:00 秒 到第二天 0:0:0 秒
     *
     * @param date
     * @return Date[0] 起始时间， Date[1] 终止时间
     */
    public static Date[] getOneDayRange(Date date) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date[] d = new Date[]{null, null};
        Calendar cal = new GregorianCalendar();
        try {
            d[0] = df.parse(df.format(date));
            cal.setTime(df.parse(df.format(date)));
            cal.add(Calendar.DATE, 1);
            d[1] = cal.getTime();
        } catch (Exception e) {

            throw new RuntimeException("convert date error");
        }

        return d;
    }

    /**
     * 把一个Date 按照指定格式转换为String
     *
     * @param date
     * @return
     */
    public static String format2(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 将使用hh:mm 描述的时间，转换为相对于0点的秒数
     *
     * @param st
     */
    public static Integer getSeconds(String st) {

        if (st == null || st.length() < 3)
            return 0;

        int seperator = st.indexOf(":");
        if (seperator <= 0) {
            return 0;
        }

        String h = st.substring(0, seperator);
        String m = st.substring(seperator + 1);

        return (Integer.parseInt(h) * 60 + Integer.parseInt(m)) * 60;
    }

    public static int getMinute(double d) {
        return (int) Math.ceil(d * 60);
    }

    /**
     * 将使用Date 描述的时间，转换为相对于当天 0点的秒数
     *
     * @param d
     */
    public static Integer getIntTime(Date d) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        return (cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE))
                * 60 + cal.get(Calendar.SECOND);
    }

    /**
     * 判断两个date 是否同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean isSameDay(Date d1, Date d2) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d1);
        int id1 = cal.get(Calendar.DAY_OF_YEAR);
        int iy1 = cal.get(Calendar.YEAR);
        cal.setTime(d2);
        int id2 = cal.get(Calendar.DAY_OF_YEAR);
        int iy2 = cal.get(Calendar.YEAR);

        return (id1 == id2 && iy1 == iy2);
    }

    /**
     * 将一个java.sql.Timestamp 型的时间转换为一个 java.util.Date 的时间
     *
     * @param t Date
     * @return
     */
    public static Date timeStamp2Date(Date t) {

        Date d = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm ss");

        try {
            d = df.parse(df.format(t));
        } catch (Exception e) {
            // do nothing
        }

        return d;
    }

    /**
     * 获取明天凌晨零点的日期
     *
     * @return Date
     */
    public static Date getTomorrowBegin() {

        Date[] d = DateUtils.getTodayRange();

        return d[1];
    }

    /**
     * 获取今天凌晨零点的日期
     *
     * @return Date
     */
    public static Date getTodayBegin() {

        Date[] d = DateUtils.getTodayRange();

        return d[0];
    }

    /**
     * 获得当天的范围
     *
     * @return d[0] 今天凌晨0点， d[1] 明天凌晨零点
     */
    public static Date[] getTodayRange() {

        return DateUtils.getOneDayRange(new Date());
    }


    /**
     * 获取当前时间算上一个月的日期
     *
     * @return
     */
    public static String getPreMonthStr() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        Date day = c.getTime();
        String str = new SimpleDateFormat("yyyy-MM-dd").format(day);

        return str;
    }

    /**
     * 获取当前时间算上一个月的日期
     *
     * @return
     */
    public static Date getPreMonthDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        Date day = c.getTime();

        return day;
    }

    /**
     * 获取当前时间下一个月的日期
     *
     * @return
     */
    public static Date getNextMonthDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        Date day = c.getTime();

        return day;
    }

    /**
     * 获取当前时间算上一个月的日期
     *
     * @return
     */
    public static Date getPreMonthDate(Date curDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        Date day = c.getTime();

        return day;
    }

    // 获得当前日期与本周日相差的天数
    private static int getWeekPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static Date getFirstHourOFDay() {
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(Calendar.HOUR_OF_DAY, 0);
        firstDate.set(Calendar.MINUTE, 0);
        firstDate.set(Calendar.SECOND, 0);
        return firstDate.getTime();
    }

    // 获取当月第一天
    public static Date getFirstDayOfMonth() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.set(Calendar.HOUR_OF_DAY, 0);
        lastDate.set(Calendar.MINUTE, 0);
        lastDate.set(Calendar.SECOND, 0);
        return lastDate.getTime();
    }

    // 获得本周日的日期
    public static Date getSundayOFWeek() {
        int mondayPlus = getWeekPlus() - 1;
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        currentDate.set(GregorianCalendar.HOUR_OF_DAY, 0);
        currentDate.set(GregorianCalendar.MINUTE, 0);
        currentDate.set(GregorianCalendar.SECOND, 0);
        return currentDate.getTime();
    }

    // 获得本周一的日期
    public static Date getMondayOFWeek() {
        int mondayPlus = getWeekPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        currentDate.set(GregorianCalendar.HOUR_OF_DAY, 0);
        currentDate.set(GregorianCalendar.MINUTE, 0);
        currentDate.set(GregorianCalendar.SECOND, 0);
        return currentDate.getTime();
    }

    // 获得当前日期与本周日相差的天数
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    // 获得下周星期一的日期
    public static Date getNextMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return currentDate.getTime();
    }

    //获得下一天的时间
    public static Date getNextDay() {
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DAY_OF_MONTH, 1);
        return currentDate.getTime();
    }

    //获得上一天的时间
    public static Date getAscendDay() {
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DAY_OF_MONTH, -1);
        return currentDate.getTime();
    }

    // 获得下周星期日的日期
    public static Date getNextSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return currentDate.getTime();
    }

    /**
     * 获得特定时间一年后的时间
     *
     * @param date
     * @return
     */
    public static Date getNexYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, +1);
        return calendar.getTime();
    }

    /**
     * 时间计算
     */
    public static Date addHourToDate(Date date, double hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, (int) Math.ceil(hour * 60));
        return c.getTime();
    }

    public static Date addMinuteToDate(Date date, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, min);
        return c.getTime();
    }

    public static Date addDayToDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    public static Date addYearToDate(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        return c.getTime();
    }

    public static Date addDayToDateByDouble(Date date, double day) {
        Calendar c = Calendar.getInstance();
        int days = (int) day;
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    public static Date subDayToDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -day);
        return c.getTime();
    }

    /**
     * Date时间去除时分秒
     *
     * @param date
     * @return
     * @throws ParseException
     */
/*    public static Date timeOfSeparation(Date date) throws Exception {
        if (date == null) {
            throw new ApiException(StatusCode.FAIL.getCode(), "入参时间不能为空");

        }
        String s = ymd.format(date);
        Date time = ymd.parse(s);
        return time;
    }*/


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Date getFirstHourOFDay(Date date) {
        Calendar firstDate = Calendar.getInstance();
        firstDate.setTime(date);
        firstDate.set(Calendar.HOUR_OF_DAY, 0);
        firstDate.set(Calendar.MINUTE, 0);
        firstDate.set(Calendar.SECOND, 0);
        return firstDate.getTime();
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        // 再转换为时间
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static int getWeekNumber(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int week = cd.get(Calendar.DAY_OF_WEEK);
        return week == 1 ? week + 6 : week - 1;
    }

    public static int getDayNumber(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将使用hh:mm 描述的时间，转换为相对于0点的分钟数
     *
     * @param st
     */
    public static Integer getMinute(String st) {
        if (st == null || st.length() < 3)
            return 0;

        int seperator = st.indexOf(":");
        if (seperator <= 0) {
            return 0;
        }

        String h = st.substring(0, seperator);
        String m = st.substring(seperator + 1);

        return (Integer.parseInt(h.trim()) * 60 + Integer.parseInt(m.trim()));
    }

    public static Date setMinuteToDate(Date date, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, min);
        return c.getTime();
    }

    /**
     * hh:mm
     *
     * @param date
     * @param min
     * @return
     */
    public static Date setMinuteToDate(Date date, String min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, getMinute(min));
        return c.getTime();
    }

    public static Integer getSeconds(Date date) {
        String str = hm.format(date);
        return getSeconds(str);
    }

    /**
     * 相对于0点的分钟数
     *
     * @param date
     * @return
     */
    public static Integer getMinute(Date date) {
        String str = hm.format(date);
        return getMinute(str);
    }

	/*public static void main(String[] args) {
		String str = "00:00";
		System.out.println(test0002.DateUtils.getMinute(str));
		System.out.println(test0002.DateUtils.getMinute("23:59"));
	}*/

    /**
     * 获得日期字符串数组
     *
     * @param calendarType 日期跨度的类型，
     */

    public static Date[] getDateArrays(Date start, Date end, int calendarType) {
        ArrayList<Date> ret = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Date tmpDate = calendar.getTime();
        long endTime = end.getTime();
        while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
            ret.add(calendar.getTime());
            calendar.add(calendarType, 1);
            tmpDate = calendar.getTime();
        }

        Date[] dates = new Date[ret.size()];
        return ret.toArray(dates);
    }

    public static String formatDateHMToStr(Date date) {
        try {
            return hm.format(date);
        } catch (Exception e) {
            log.debug("DateUtil.formatDateYMDToStr():" + e.getMessage());
            return null;
        }

    }

//	public static String formatDateYMDToStr(Date date) {
//		if (date == null)
//			return null;
//		try {
//			return dmy_hm.format(date);
//		} catch (Exception e) {
//			log.debug("DateUtil.formatDateYMDToStr():" + e.getMessage());
//			return null;
//		}
//
//	}
//
//	/*
//	 * 得到当前日期字符串 格式（yyyy-MM-dd）
//	 */
//	public static String getDate() {
//		return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
//	}

    /*
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (null == date)
            return formatDate;

        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /*

         * 得到当前时间字符串 格式（HH:mm:ss）

        public static String getTime() {
            return (new DateTime()).toString("HH:mm:ss");
        }


        /*
         * 天数相加
         */
    public static Date addDay(Date date, int d) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, d);
    }

    /*
     * 月份相加
     */
    public static Date addMonth(Date date, int m) {
        return DateUtils.addMonths(date, m);
    }

    /*
     * 日期型字符串转化为日期 格式（"yyyy-MM-dd","yyyy/MM/dd"）
     */
    public static Date parseDate(String str, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(str);
    }

    /**
     * 获取日期在一年当中是的第几天
     *
     * @param date
     * @return
     */
    public static int daysOfDate(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        return aCalendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * @param time
     * @return Long
     * @Title: toMillisecond
     * @Description: 将 xx时xx分xx秒xx毫秒 化成 毫秒
     */
    public static Long toMillisecond(String time) {
        String orgTime = time;
        if (StringUtils.isEmpty(time))
            return null;
        if (!time.contains("分"))
            time = "0分" + time;
        if (!time.contains("时"))
            time = "0时" + time;
        if (!time.contains("毫秒"))
            time = time + "毫秒";
        Matcher m = TIME_P.matcher(time);
        if (m.matches()) {
            Long l = 0L;
            String h = m.group(1);
            l = l + Long.parseLong(h) * 60 * 60 * 1000;
            String min = m.group(2);
            l = l + Long.parseLong(min) * 60 * 1000;
            String s = m.group(3);
            l = l + Long.parseLong(s) * 1000;
            String ms = m.group(4);
            l = l + Long.parseLong(ms);
            return l;
        } else {
            throw new IllegalArgumentException("无效的时间格式：" + orgTime);
        }
    }

    public static Timestamp getTimestamp(Date... dates) {
        if (dates == null || dates.length == 0) {
            return new Timestamp(new Date().getTime());
        } else {
            return new Timestamp(dates[0].getTime());
        }
    }

    /**
     * 比较两个日期相差的天数
     */
    public static int getDiffDays(String date1, String date2) {
        int margin;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf.parse(date1, pos);
            Date dt2 = sdf.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getDiffDays(Date dt1, Date dt2) {
        int margin;
        try {
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            if (margin == 0) {
                margin = dt1.getDay() - dt2.getDay();
            }
            return margin;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 比较两个时间相差多少分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDiffMinutes(Date date1, Date date2) {
        long margin;
        try {
            long l = date1.getTime() - date2.getTime();
            margin = l / (60 * 1000);
            return margin;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getDiffYeas(Date dt1, Date dt2) {
        int margin;
        try {
            margin = dt1.getYear() - dt2.getYear();
            return margin;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param d1
     * @param d2
     * @return boolean
     * @Title: isAfter
     * @Description: d2 在 d1 后则返回true
     */
    public static boolean isAfter(Date d1, Date d2) {
        if (d1 != null && d2 != null)
            return d1.before(d2);
        else
            return false;
    }

    //判断选择的日期是否是本周
    public static boolean isThisWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(date);
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return paramWeek == currentWeek;
    }


    //判断选择的日期是否是本月
    public static boolean isThisMonth(Date date) {
        return isThisTime(date, "yyyy-MM");
    }

    public static boolean isThisTime(Date date, String pattern) {
        String param = format(date, pattern);
        String now = format(new Date(), pattern);//当前时间
        return param.equals(now);
    }


    public static Date splitDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * String转换成Date的yyyy-MM-dd格式
     *
     * @param strDate
     * @return
     */
    public static Date getShortTimeDate(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        Date date = null;
        try {
            date = shortSdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        String favoriteRaces = "aaa,vvv,";
        if (!StringUtils.isEmpty(favoriteRaces)) {
            favoriteRaces = favoriteRaces.substring(0, favoriteRaces.length() - 1);
        }
        System.out.println(favoriteRaces);

        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        Date now = new Date();
        Date date = getBeginningOfADay(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        date = getEndOfADay(now);
        System.out.println(dateFormat.format(date));

        System.out.println(dateFormat.format(getBeginingOfAMonth(now)));
        System.out.println(dateFormat.format(getEndOfAMonth(now)));


        System.out.println(DateUtils.format(DateUtils.getOneMonthLater(date), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(getPreMonthStr());
        System.out.println(DateUtils.format(getPreMonthDate(), "yyyy-MM-dd"));
        System.out.println(DateUtils.format(getPreMonthDate(subDayToDate(new Date(), 2)), "yyyy-MM-dd"));
        System.out.println(DateUtils.format(DateUtils.subDayToDate(new Date(), 2), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateUtils.format(new Date(), "yyyy-MM"));

        Date cur = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(cur);
        int month = c.get(Calendar.MONTH) + 1;
        System.out.println(month);
    }
}
