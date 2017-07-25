package fgh.idd.chips.util;

import android.text.TextUtils;

import com.potato.library.util.L;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class TimeUtil {
    public static String getTime(String time) {
        String result = "";
        try {
            long l = Long.parseLong(time);
            result = StringUtils.convertSecondsToYYMMDDString(l * 1000);

        } catch (Exception e) {
            L.d("time convert fail");
        }
        return result;
    }

    public static String getTS(String time, String format) {
        String ts = new Date().getTime() / 1000 + "";
        try {
            Date date = stringtoDate(time, format);
            ts = date.getTime() / 1000 + "";
        } catch (Exception e) {

        }
        return ts;
    }

    public static long getTSLong(String time, String format) {
        long ts = new Date().getTime() / 1000;
        try {
            Date date = stringtoDate(time, format);
            ts = date.getTime() / 1000;
        } catch (Exception e) {

        }
        return ts;
    }

    //

    /**
     * SimpleDateFormat fn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     */
    public static String getTime(String time, SimpleDateFormat format) {
        String result = "";
        try {
            long l = Long.parseLong(time);
            result = format.format(new Date(l * 1000));
        } catch (Exception e) {
            L.d("time convert fail");
        }
        return result;
    }

    public static String getTimeYYYYMMDD(String time) {
        if (TextUtils.isEmpty(time) || time.equals("0")) {
            return "0";
        }
        String result = "";
        try {
            long l = Long.parseLong(time);
            result = StringUtils.convertSecondsToYYMMDDString(l * 1000);

        } catch (Exception e) {
            L.d("time convert fail");
        }
        return result;
    }

    public static String getTimeYYYYMMDDHHmm(String time) {
        if (TextUtils.isEmpty(time) || time.equals("0")) {
            return "0";
        }
        String result = "";
        try {
            long l = Long.parseLong(time);
            result = StringUtils.convertSecondsToYYMMDDStringHHmm(l * 1000);

        } catch (Exception e) {
            L.d("time convert fail");
        }
        return result;
    }

    public static String getTimeHHMM(String time) {
        String result = "";
        try {
            long l = Long.parseLong(time);
            result = StringUtils.convertSecondsToHHMMString(l * 1000);

        } catch (Exception e) {
            L.d("time convert fail");
        }
        return result;
    }


    /**
     * 把符合日期格式的字符串转换为日期类型
     *
     * @param yyyymmdd
     * @return
     */
    public static String getAge(String yyyymmdd) {
        Date date = stringtoDate(yyyymmdd, "yyyy-mm-dd");
        return getAge(date) + "";
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     *
     * @param dateStr
     * @return
     */
    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = new Date(System.currentTimeMillis());
        }
        return d;
    }

    /**
     * 把日期转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    public static int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }

    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public static void main(String args[]) {
        Date date = TimeUtil.stringtoDate("2017-3-28", "yyyy-MM-dd");
        System.out.println(date);
    }
}
