package fgh.idd.mvp.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.potato.library.util.L;
import com.potato.library.util.StringUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DateTimePicker;
import fgh.idd.R;
import fgh.idd.chips.base.BaseActivity;
import fgh.idd.chips.util.DateUtil;
import fgh.idd.chips.util.TimeUtil;
import fgh.idd.chips.util.UIUtils;

import static cn.qqtheme.framework.picker.DateTimePicker.YEAR_MONTH_DAY;

public class AlarmActivity extends BaseActivity implements Alarm.V {

    public static final String TAG = AlarmActivity.class.getSimpleName();

    @Inject                 AlarmPresenter presenter;
    @Bind(R.id.bt_start)    Button         btStart;
    @Bind(R.id.tv_time)     TextView       tvTime;
    @Bind(R.id.ll_time)     LinearLayout   llTime;
    @Bind(R.id.tv_interval) TextView       tvInterval;
    @Bind(R.id.ll_interval) LinearLayout   llInterval;

    long mAlarmTs    = 0;
    long mIntervalTS = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);

        DaggerAlarm_C
                .builder()
                .module(new Alarm.Module(this))
                .build()
                .inject(this);

        String message = this
                .getIntent()
                .getStringExtra("msg");
        showDialogInBroadcastReceiver(this.getIntent(), message);
    }

    @Override
    public void render(Object obj) {
        UIUtils.toast(mContext, obj.toString());
    }

    @Override
    public void startAlarm() {
        L.i("startTime=" + TimeUtil.getTimeYYYYMMDDHHmm(mAlarmTs + ""));
        L.i("间隔=" + DateUtil.stringForTime(mIntervalTS));
        AlarmManagerUtil.setAlarm(mContext, mAlarmTs * 1000, mIntervalTS * 1000);
    }

    @OnClick({R.id.bt_start, R.id.ll_time, R.id.ll_interval})
    public void onViewClicked(View view) {
        Calendar calendar = Calendar.getInstance();
        // 获取当前对应的年、月、日的信息
        int year = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        int d = calendar.get(Calendar.DATE);
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);
        switch (view.getId()) {
            case R.id.bt_start:
                startAlarm();
                btStart.setText("已经开始，from:+" + TimeUtil.getTimeYYYYMMDDHHmm(mAlarmTs + "") + "间隔：" + DateUtil.stringForTime(mIntervalTS));
                UIUtils.toast(mContext, "已经开始");


                break;
            case R.id.ll_time:
                DateTimePicker datePicker = new DateTimePicker(AlarmActivity.this, YEAR_MONTH_DAY);

                datePicker.setRange(year, year + 10);
//                datePicker.setSelectedItem();
                datePicker.setSelectedItem(year, m, d, hh, mm);//时间选择的控件，有个bug，设置选择的时间，小时需要补零，但是只能给int，所以没机会补零。初始化的时候，会默认显示当前时间，而且做了补零操作。
                datePicker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        String birthday = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                        mAlarmTs = TimeUtil.getTSLong(birthday, "yyyy-MM-dd HH:mm");
                        tvTime.setText(birthday);
//                        mYear = year;
//                        mMonth = month;
//                        mDay = day;
                    }
                });
                datePicker.setTextColor(getResources().getColor(R.color.ifsee_blue));
                datePicker.show();

                break;
            case R.id.ll_interval:
                if (mAlarmTs == 0) {
                    UIUtils.toast(mContext, "请先选择开始时间");
                    return;
                }

                DateTimePicker picker = new DateTimePicker(AlarmActivity.this, YEAR_MONTH_DAY);
                final long startTs = mAlarmTs;
                picker.setRange(year, year + 10);
//                datePicker.setSelectedItem();
                picker.setSelectedItem(year, m, d, hh, mm);//时间选择的控件，有个bug，设置选择的时间，小时需要补零，但是只能给int，所以没机会补零。初始化的时候，会默认显示当前时间，而且做了补零操作。
                picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        String birthday = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                        mIntervalTS = TimeUtil.getTSLong(birthday, "yyyy-MM-dd HH:mm") - startTs;
                        String 时间 = DateUtil.stringForTime(mIntervalTS);
                        tvInterval.setText(时间);

                    }
                });
                picker.setTextColor(getResources().getColor(R.color.ifsee_blue));
                picker.show();
                break;
        }


    }

    private void showDialogInBroadcastReceiver(Intent intent, String message) {

        if (TextUtils.isEmpty(message)) {
            return;
        }

        UIUtils.toast(this, message);
        long startTime = intent.getLongExtra("startTime", System.currentTimeMillis());
        long intervalMillis = intent.getLongExtra("intervalMillis", 0);

        tvTime.setText(TimeUtil.getTimeYYYYMMDDHHmm(startTime / 1000 + ""));
        tvInterval.setText(DateUtil.stringForTime(intervalMillis / 1000));
        mAlarmTs = startTime;
        mIntervalTS = intervalMillis;

        AVObject product = new AVObject("IddAlarmMsg");
        product.put("time", StringUtils.convertSecondsToYYMMDDStringHHmm(System.currentTimeMillis()));
        product.put("startTime", TimeUtil.getTimeYYYYMMDDHHmm(startTime / 1000 + ""));
        product.put("intervalMillis", DateUtil.stringForTime(intervalMillis / 1000));

        product.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
            }
        });

        String dingdingpkg = "com.alibaba.android.rimet";
        Intent it = this
                .getPackageManager()
                .getLaunchIntentForPackage(dingdingpkg);
        this.startActivity(it);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = this
                .getIntent()
                .getStringExtra("msg");
        showDialogInBroadcastReceiver(this.getIntent(), message);
    }


}