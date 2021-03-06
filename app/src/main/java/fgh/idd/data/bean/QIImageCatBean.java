package fgh.idd.data.bean;

//import

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import fgh.idd.chips.base.BaseBean;
import fgh.idd.data.db.ICImageCatBeanDao;

public class QIImageCatBean extends BaseBean implements Serializable {

    //keypslist
    private String cid;
    private String cat_name;
    private String cat_idname;
    private String cat_remark;
    private String cat_status;

    //other
    //createFromCursor
    public static QIImageCatBean cursor2Bean(Cursor cursor) {
        if (cursor == null) return null;
        QIImageCatBean bean = new QIImageCatBean();
        bean.setCid(cursor.getString(cursor.getColumnIndex(ICImageCatBeanDao.Columns.cid)));
        bean.setCat_name(cursor.getString(cursor.getColumnIndex(ICImageCatBeanDao.Columns.cat_name)));
        bean.setCat_idname(cursor.getString(cursor.getColumnIndex(ICImageCatBeanDao.Columns.cat_idname)));
        bean.setCat_remark(cursor.getString(cursor.getColumnIndex(ICImageCatBeanDao.Columns.cat_remark)));
        bean.setCat_status(cursor.getString(cursor.getColumnIndex(ICImageCatBeanDao.Columns.cat_status)));
        return bean;
    }

    //createFromJSON
    public static QIImageCatBean json2Bean(JSONObject json) throws JSONException {
        if (json == null) return null;
        QIImageCatBean bean = new QIImageCatBean();
        bean.setCid(json.optString("cid"));
        bean.setCat_name(json.optString("cat_name"));
        bean.setCat_idname(json.optString("cat_idname"));
        bean.setCat_remark(json.optString("cat_remark"));
        bean.setCat_status(json.optString("cat_status"));
        return bean;
    }

    //createFromJSONArray
    public static ArrayList<QIImageCatBean> jsonArray2BeanList(JSONArray jsonArray) throws JSONException {

        if (jsonArray == null) return null;

        ArrayList<QIImageCatBean> list = new ArrayList<QIImageCatBean>();

        int count = jsonArray.length();
        for (int i = 0; i < count; i++) {
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            QIImageCatBean entity = QIImageCatBean.json2Bean(jsonObj);
            list.add(entity);
        }
        return list;
    }

    //buildContentValues
    public static ContentValues bean2CV(BaseBean baseBean) {
        QIImageCatBean bean = new QIImageCatBean();

        if (baseBean != null) {
            bean = (QIImageCatBean) baseBean;
        }
        ContentValues values = new ContentValues();
        values.put(ICImageCatBeanDao.Columns.cid, bean.getCid());
        values.put(ICImageCatBeanDao.Columns.cat_name, bean.getCat_name());
        values.put(ICImageCatBeanDao.Columns.cat_idname, bean.getCat_idname());
        values.put(ICImageCatBeanDao.Columns.cat_remark, bean.getCat_remark());
        values.put(ICImageCatBeanDao.Columns.cat_status, bean.getCat_status());
        return values;
    }

    public String getCid() {
        return this.cid;
    }

    //set get list
    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCat_name() {
        return this.cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_idname() {
        return this.cat_idname;
    }

    public void setCat_idname(String cat_idname) {
        this.cat_idname = cat_idname;
    }

    public String getCat_remark() {
        return this.cat_remark;
    }

    public void setCat_remark(String cat_remark) {
        this.cat_remark = cat_remark;
    }

    public String getCat_status() {
        return this.cat_status;
    }

    public void setCat_status(String cat_status) {
        this.cat_status = cat_status;
    }

}