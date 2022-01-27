package uuboy.scy.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    public static String today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5));
        return String.format("%02d%02d%02d", calendar.get(1) - 2000, calendar.get(2) + 1, calendar.get(5));
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

    public static String getSiteDate() {
        Calendar calendar = Calendar.getInstance();
        return String.format("%04d-%02d-%02d", calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    public static String getSiteSomeDay(String day) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        Date date = simpleDateFormat.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5));
        return String.format("%04d-%02d-%02d", calendar.get(1), calendar.get(2) + 1, calendar.get(5));
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
        System.out.println(new Timestamp(longtoDateScale(1555689599L)));
    }
}
