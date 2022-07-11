package com.zay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * @author zay
 * @date 2021/6/2 15:15
 */
@SpringBootApplication
public class SpringBoot {
    public static void main(String[] args) throws ParseException {
        /*int a = 3;
        int b = 8;
        int i = a / b;
        System.out.println(i);*/
                SpringApplication.run(SpringBoot.class,args);

/*        for (int i = 0; i < 24; i++) {
            if(i<10){
                System.out.println("{title: \"0"+i+":00\", dataIndex: \"z0000"+i+"1\", width: 100},");
                System.out.println("{title: \"0"+i+":15\", dataIndex: \"z0000"+i+"2\", width: 100},");
                System.out.println("{title: \"0"+i+":30\", dataIndex: \"z0000"+i+"3\", width: 100},");
                System.out.println("{title: \"0"+i+":45\", dataIndex: \"z0000"+i+"4\", width: 100},");
            }else{
                System.out.println("{title: \""+i+":00\", dataIndex: \"z0000"+i+"1\", width: 100},");
                System.out.println("{title: \""+i+":15\", dataIndex: \"z0000"+i+"2\", width: 100},");
                System.out.println("{title: \""+i+":30\", dataIndex: \"z0000"+i+"3\", width: 100},");
                System.out.println("{title: \""+i+":45\", dataIndex: \"z0000"+i+"4\", width: 100},");
            }
        }*/
/*        for (int i = 0; i < 24; i++) {
            if(i<10){
                System.out.println("{title: \"0"+i+":00\", dataIndex: \"z0000"+i+"1\", width: 100},");
                System.out.println("{title: \"0"+i+":30\", dataIndex: \"z0000"+i+"2\", width: 100},");
            }else{
                System.out.println("{title: \""+i+":00\", dataIndex: \"z0000"+i+"1\", width: 100},");
                System.out.println("{title: \""+i+":30\", dataIndex: \"z0000"+i+"2\", width: 100},");
            }
        }*/

/*        for (int i = 0; i < 24; i++) {
            if(i<10){
                System.out.println("{title: \"0"+i+":00\", dataIndex: \"z0000"+i+"1\", width: 100},");
            }else{
                System.out.println("{title: \""+i+":00\", dataIndex: \"z0000"+i+"1\", width: 100},");
            }
        }*/

/*        String stat="20:30";
        String end ="18:00";
        int stats = Integer.parseInt(stat);
        int ends = Integer.parseInt(end);
        System.out.println(stats-ends);
        Date date1  = new Date(stat);
        Date date2  = new Date(end);
        System.out.println(date1.compareTo(date2));*/

/*        String startTime = "03:00";
        String endTime = "01:30";
        SimpleDateFormat df = new SimpleDateFormat("HH:ss");
        long t1 = df.parse(startTime).getTime();
        long t2 = df.parse(endTime).getTime();
        double t3 = (t2-t1)/1000;
        System.out.println( (int)(t3/60/60)+(t3%60/60) );*/

        /*long seconds = System.currentTimeMillis()/1000;
        System.out.println(seconds);
        Map map = new HashMap<>();
        map.put("createTime",new Integer("1624351206"));
        System.out.println(((Integer)map.get("createTime")).longValue()/60);*/
    }
}
