package example;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DateTest {
    public static void main(String[] args) throws ParseException, InterruptedException {
/*        Date date = new Date(116, 2, 18);
        System.out.println(date);*/

/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int x = 0; x < 100; x++) {
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse("20160505");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }*/

//        testLocalDate();
//        testLocalTime();
//        combineLocalDateAndTime();
//        testInstant();
//        testDuration();
//        testPeriod();
//        testDateFormat();
//        testDateParse();
        testTemporalAdjuster();
    }

    private static void testLocalDate() {
        LocalDate localDate = LocalDate.of(2016, 11, 13);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());

        localDate.get(ChronoField.DAY_OF_MONTH);
    }

    private static void testLocalTime() {
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    private static void combineLocalDateAndTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
        System.out.println(localDateTime.toString());
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());
    }

    private static void testDuration() {
        LocalTime time = LocalTime.now();
        LocalTime beforeTime = time.minusHours(1);
        Duration duration = Duration.between(time, beforeTime);
        System.out.println(duration.toHours());
    }

    private static void testPeriod() {
        Period period = Period.between(LocalDate.of(2014, 1, 10), LocalDate.of(2016, 1, 10));
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getYears());
    }

    private static void testDateFormat() {
        LocalDate localDate = LocalDate.now();
        String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
//        String format2 = localDate.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(format1);
//        System.out.println(format2);

        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = localDate.format(mySelfFormatter);
        System.out.println(format);
    }

    private static void testDateParse() {
        String date1 = "20161113";
        LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);
        
        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date2 = "2016-11-13";
        LocalDate localDate2 = LocalDate.parse(date2, mySelfFormatter);
        System.out.println(localDate2);
    }

    private static void testTemporalAdjuster(){
        LocalDateTime now = LocalDateTime.now();
        //获取当月第一天
        System.out.println("当月第一天："+now.with(TemporalAdjusters.firstDayOfMonth()));
        //获取下月第一天
        System.out.println("下月第一天："+now.with(TemporalAdjusters.firstDayOfNextMonth()));
        //获取明年第一天
        System.out.println("明年第一天："+now.with(TemporalAdjusters.firstDayOfNextYear()));
        //获取本年第一天
        System.out.println("本年第一天："+now.with(TemporalAdjusters.firstDayOfYear()));
        //获取当月最后一天
        System.out.println("当月最后一天："+now.with(TemporalAdjusters.lastDayOfMonth()));
        //获取本年最后一天
        System.out.println("本年最后一天："+now.with(TemporalAdjusters.lastDayOfYear()));
        //获取当月第三周星期五
        System.out.println("当月第三周星期五："+now.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY)));
        //获取上周一
        System.out.println("上周一："+now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));
        //获取下周日
        System.out.println("下周日："+now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
    }
}