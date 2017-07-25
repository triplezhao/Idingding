package fgh.idd.data.result;

import org.json.JSONArray;
import org.json.JSONObject;

import fgh.idd.chips.api.QICallback;
import fgh.idd.data.bean.QIImageBean;

public class QIImageEntity extends QICallback.QIResultEntity {

    public int nowpage = 1;

    @Override
    public QICallback.QIResultEntity parse(String json) {
        try {
            JSONObject root = new JSONObject(json);

            code = root.optInt("code");
            message = root.optString("msg");

            if (isSucc()){
                JSONObject data = root.getJSONObject("data");
                total = data.optInt("total");
                nowpage = data.optInt("nowpage");
                JSONArray array = data.getJSONArray("list");
                list = QIImageBean.jsonArray2BeanList(array);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = -1;
        }
        return this;
    }
}
