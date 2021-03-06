package fgh.idd.data.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fgh.idd.chips.api.JiongtuCallback;
import fgh.idd.data.bean.JiongtuPhoto;

/**
 * 囧图-图片列表数据解析器
 *
 * @author zhaobingfeng
 */
public class JiongtuPhotoListEntity extends JiongtuCallback.JiongtuResultEntity {

    public long total;
    public ArrayList<String> picUrls = new ArrayList<String>();

    @Override
    public JiongtuPhotoListEntity parse(String json) {
        try {
            picUrls.clear();
            JSONObject obj = new JSONObject(json);
            code = obj.optInt("Code");
            message = obj.optString("Message");
            JSONObject objData = obj.getJSONObject("Data");
            total = objData.optLong("Total");
            JSONArray array = objData.getJSONArray("List");
            int count = array.length();
            for (int i = 0; i < count; i++) {
                JSONObject jsonObj = array.optJSONObject(i);
                JiongtuPhoto entity = JiongtuPhoto.createFromJSON(jsonObj);
                list.add(entity);
                picUrls.add(entity.getBigUrl());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
}