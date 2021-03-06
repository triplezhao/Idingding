package fgh.idd.data.result;

import org.json.JSONArray;
import org.json.JSONObject;

import fgh.idd.chips.api.YKCallback;
import fgh.idd.data.bean.YKVideo;

public class YKVideosByUserEntity extends YKCallback.YKResultEntity {

    public String last_item;
    public int page;
    public int count;

    @Override
    public YKCallback.YKResultEntity parse(String json) {
        try {
            JSONObject root = new JSONObject(json);

            code = 0;

            if (root.has("error")) {
                code = root.optJSONObject("error").optInt("code");
                message = root.optJSONObject("error").optString("description");
            }

            total = root.optInt("total");
            page = root.optInt("page");
            count = root.optInt("count");
            last_item = root.optString("last_item");
            JSONArray array = root.getJSONArray("videos");
            list = YKVideo.createFromJSONArray(array);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = -1;
        }
        return this;
    }
}
