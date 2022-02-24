package uuboy.scy.common.util;

import org.apache.avro.generic.GenericData;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Project: uuboycommon
 * Package: uuboy.scy.common.util
 * DateUtils.java
 * Description:
 * User: uuboyscy
 * Created Date: 1/27/22
 * Version: 0.0.1
 */

public class DateUtils {
    public DateUtils() {
    }

    public static String today(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.format(calendar.getTime());
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String today() {
        return today("yyyyMMdd");
    }

    public static String dateFormatTransformation(String dateStr, String inputFmt, String outputFmt) throws ParseException {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFmt);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFmt);
        return outputDateFormat.format(
                inputDateFormat.parse(dateStr)
        );
    }

    public static List<String> duration2List(String startDateStr, String endDateStr, String inputFmt, String outputFmt) {
        List<String> outputList = new ArrayList<>();
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFmt);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFmt);
        return null;
    }

    public static String someDay(String day, int nDay) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        Date date = simpleDateFormat.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(5, calendar.get(5) - nDay);
        return String.format("%02d%02d%02d", calendar.get(1) - 2000, calendar.get(2) + 1, calendar.get(5));
    }

    public static String yesterday() throws ParseException {
        return someDay(today(), 1);
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        return String.format("%02d%02d%02d", calendar.get(1) - 2000, calendar.get(2) + 1, calendar.get(5));
    }

    public static String getMonth() {
        Calendar calendar = Calendar.getInstance();
        return String.format("%02d%02d", calendar.get(1) - 2000, calendar.get(2) + 1);
    }

    public static ArrayList<String> parseDataRange(String dataRange, String seperator, String dataFormat) {
        String[] start_end = dataRange.split(seperator);
        ArrayList<String> dates = new ArrayList();
        if (start_end.length < 2) {
            dates.add(dataRange);
            return dates;
        } else {
            String startday = start_end[0];
            String endday = start_end[1];
            SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
            Date startdate = null;
            Date enddate = null;

            try {
                startdate = sdf.parse(startday);
                enddate = sdf.parse(endday);
                Calendar cal = Calendar.getInstance();
                cal.setTime(startdate);
                long difftime = enddate.getTime() - startdate.getTime();
                int diffdays = (int)(difftime / 86400000L);

                for(int i = 0; i <= diffdays; ++i) {
                    dates.add(sdf.format(cal.getTime()));
                    cal.add(5, 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return dates;
        }
    }

    public static Long longtoDateScale(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        if (timestamp.toString().length() == 10) {
            calendar.setTimeInMillis(timestamp * 1000L);
        } else {
            calendar.setTimeInMillis(timestamp);
        }

        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        return calendar.getTimeInMillis();
    }

    public static Long getTodayTimestamp() {
        return getNdayBeforeTimestamp(0);
    }

    public static Long getYesterdayTimestamp() {
        return getNdayBeforeTimestamp(1);
    }

    public static Long getNdayBeforeTimestamp(int N) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(5, -1 * N);
        return calendar.getTimeInMillis();
    }

    public static Long getDayTimestamp(String day) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        Date date = simpleDateFormat.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5));
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(10, 0);
        return calendar.getTimeInMillis();
    }

    public static Date getNdayBefore(int N) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -N);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(10, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        System.out.println(String.format("YEAR: %d", calendar.get(Calendar.YEAR))); // 1
        System.out.println(String.format("MONTH: %d", calendar.get(Calendar.MONTH))); // 2
        System.out.println(String.format("WEEK_OF_YEAR: %d", calendar.get(Calendar.WEEK_OF_YEAR))); // 3
        System.out.println(String.format("WEEK_OF_MONTH: %d", calendar.get(Calendar.WEEK_OF_MONTH))); // 4
        System.out.println(String.format("DATE: %d", calendar.get(Calendar.DATE))); // 5
        System.out.println(String.format("DAY_OF_MONTH: %d", calendar.get(Calendar.DAY_OF_MONTH))); // 5
        System.out.println(String.format("DAY_OF_YEAR: %d", calendar.get(Calendar.DAY_OF_YEAR))); // 6
        System.out.println(String.format("DAY_OF_WEEK: %d", calendar.get(Calendar.DAY_OF_WEEK))); // 7
        System.out.println(String.format("DAY_OF_WEEK_IN_MONTH: %d", calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH))); // 8
        System.out.println(String.format("AM_PM: %d", calendar.get(Calendar.AM_PM))); // 9
        System.out.println(String.format("HOUR: %d", calendar.get(Calendar.HOUR))); // 10
        System.out.println(String.format("HOUR_OF_DAY: %d", calendar.get(Calendar.HOUR_OF_DAY))); // 11
        System.out.println(String.format("MINUTE: %d", calendar.get(Calendar.MINUTE))); // 12
        System.out.println(String.format("SECOND: %d", calendar.get(Calendar.SECOND))); // 13
        System.out.println(String.format("MILLISECOND: %d", calendar.get(Calendar.MILLISECOND))); // 14

        System.out.println("===");

        System.out.println(DateUtils.today());
        System.out.println(DateUtils.today("yyyy-MM-dd"));
        System.out.println(new Timestamp(longtoDateScale(1555689599L)));
        System.out.println(new Date().getTime());

        System.out.println("===");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println(calendar.getTime().toString());
        Date date = simpleDateFormat.parse("20220222");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        System.out.println(date.toString());

    }
}
