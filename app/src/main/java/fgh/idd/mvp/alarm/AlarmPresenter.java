package fgh.idd.mvp.alarm;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import javax.inject.Inject;

final class AlarmPresenter implements Alarm.P {


    private Alarm.V view;

    /**
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    AlarmPresenter(Alarm.V view) {
        this.view = view;
    }


    @Override
    public void start() {

    }


    @Override
    public void loadData(String time, String count) {
        AVObject product = new AVObject("Product");
        product.put("time", time);
        product.put("count", count);
        product.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                view.render("succ");
            }
        });

    }
}