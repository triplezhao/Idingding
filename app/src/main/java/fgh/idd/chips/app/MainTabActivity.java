package fgh.idd.chips.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import fgh.idd.R;
import fgh.idd.chips.base.BaseTabHostActivity;
import fgh.idd.mvp.alarm.AlarmActivity;
import fgh.idd.mvp.baidu.BaiduHomeActivity;
import fgh.idd.ui.ab.BActivity;
import fgh.idd.ui.appstore.AppStoreActivity;

public class MainTabActivity extends BaseTabHostActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public TabItem getTabItemView(int position) {

        TabItem tabItem = new TabItem();
        View tabItemView = mLayoutflater.inflate(R.layout.item_main_tab, null);
        ImageView iv_icon = (ImageView) tabItemView.findViewById(R.id.iv_icon);
        switch (position) {
            case 0:
                iv_icon.setImageResource(R.drawable.selector_nav_home);
                tabItem.setTitle("" + position);
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), AlarmActivity.class));
                break;

            case 1:

                iv_icon.setImageResource(R.drawable.selector_nav_explore);
                tabItem.setTitle("" + position);
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), BaiduHomeActivity.class));
                break;
            case 2:

                iv_icon.setImageResource(R.drawable.selector_nav_workout);

                tabItem.setTitle("" + position);
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), AppStoreActivity.class));
                break;
            case 3:

                iv_icon.setImageResource(R.drawable.selector_nav_contact);
                tabItem.setTitle("" + position);
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), BaiduHomeActivity.class));
                break;
            case 4:

                iv_icon.setImageResource(R.drawable.selector_nav_profile);
                tabItem.setTitle("" + position);
                tabItem.setView(tabItemView);
                tabItem.setIntent(new Intent(getApplication(), BActivity.class));
                break;
        }

        return tabItem;
    }

    @Override
    public int getTabItemCount() {
        return 1;
    }

    @Override
    public void onTabChanged(String s) {

    }
}
