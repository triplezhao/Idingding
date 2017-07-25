package fgh.idd.mvp.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.potato.library.util.L;

import java.util.Calendar;

import fgh.idd.chips.util.DateUtil;
import fgh.idd.chips.util.TimeUtil;

import static android.R.attr.id;

/**
 * Created by loonggg on 2016/3/21.
 */
public class AlarmManagerUtil {
    public static final String ALARM_ACTION = "DINGDING_ALARM";


    public static void cancelAlarm(Context context, String action, int id) {
        Intent intent = new Intent(action);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }

    /**
     * @param startTime      开始时间
     * @param intervalMillis 重复间隔
     */
    public static void setAlarm(Context context, long startTime, long intervalMillis) {
        L.i("startTime=" + TimeUtil.getTimeYYYYMMDDHHmm(startTime / 1000 + ""));
        L.i("间隔=" + DateUtil.stringForTime(intervalMillis / 1000));
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        Intent intent = new Intent(ALARM_ACTION);
        intent.putExtra("intervalMillis", intervalMillis);
        intent.putExtra("startTime", startTime);
        intent.putExtra("msg", "startTime=" + TimeUtil.getTimeYYYYMMDDHHmm(startTime / 1000 + ""));
        PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setWindow(AlarmManager.RTC_WAKEUP, startTime, intervalMillis, sender);
        } else {
            if (intervalMillis == 0) {
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
            } else {
                am.setRepeating(AlarmManager.RTC_WAKEUP, startTime, intervalMillis, sender);
            }
        }
    }


}
