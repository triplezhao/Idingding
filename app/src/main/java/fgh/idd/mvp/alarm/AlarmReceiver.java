package fgh.idd.mvp.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub  
        Log.d("MyTag", "onclock......................");
        String msg = intent.getStringExtra("msg");
//        long startTime = intent.getLongExtra("startTime", System.currentTimeMillis());
        long startTime = System.currentTimeMillis();
        long intervalMillis = intent.getLongExtra("intervalMillis", 0);
        if (intervalMillis != 0) {
            AlarmManagerUtil.setAlarm(context, System.currentTimeMillis() + intervalMillis, intervalMillis);
        }
        Intent clockIntent = new Intent(context, AlarmActivity.class);
        clockIntent.putExtra("msg", msg);
        clockIntent.putExtra("startTime", startTime);
        clockIntent.putExtra("intervalMillis", intervalMillis);
        clockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(clockIntent);


//String packageName = "com.alibaba.android.rimet";//要打开应用的包名,以钉钉为例
//        Intent intent1 = new Intent(Intent.ACTION_MAIN);
//        intent1.addCategory(Intent.CATEGORY_LAUNCHER);
//        ComponentName cn = new ComponentName("com.alibaba.android.rimet", "");
//        intent1.setComponent(cn);
//        context.startActivity(intent);

    }

}  