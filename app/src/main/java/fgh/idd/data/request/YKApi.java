package fgh.idd.data.request;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;

import fgh.idd.chips.api.ApiUrls;
import fgh.idd.chips.api.YKCallback;
import fgh.idd.chips.app.GlobleConstant;
import fgh.idd.data.result.YKVideoCategoryEntity;
import fgh.idd.data.result.YKVideosByCategoryEntity;
import fgh.idd.data.result.YKVideosByUserEntity;

public class YKApi implements ApiUrls {
    //获取用户发布的视频. 命名，取api接口的后两位单词
    public static final String videos_by_user = BaseYoukuURL
            + "/videos/by_user.json";
    //获取视频分类
    public static final String videos_category = BaseYoukuURL
            + "/schemas/video/category.json";
    //根据分类获取视频
    public static final String videos_by_category = BaseYoukuURL
            + "/videos/by_category.json";

    /**
     * http://open.youku.com/docs?id=49
     * 除了两个id，其他都可以给"";
     * openapi.youku.com/v2/videos/by_user.json?client_id=e4da0658e508bb69&user_name=superhebefans
     */
    public static void videosByUser(CacheMode cacheMode, String client_id, String user_id, String user_name, String orderby,
                                    String page, String count, String last_item, YKCallback<YKVideosByUserEntity> callback) {
        callback.setEntity(new YKVideosByUserEntity());
        OkHttpUtils.get(videos_by_user)//
                .tag("videosByUser")//
                .cacheKey(videos_by_user + client_id + user_id + user_name + orderby + page + count + last_item)//
                .cacheMode(cacheMode)//
                .params("client_id", client_id)
                .params("user_id", user_id)
                .params("user_name", user_name)
                .params("orderby", orderby)
                .params("page", page)
                .params("count", count)
                .params("last_item", last_item)
                .execute(callback);
    }

    public static void videosCategory(CacheMode cacheMode, YKCallback<YKVideoCategoryEntity> callback) {
        String client_id = GlobleConstant.youku_client_id;
        callback.setEntity(new YKVideoCategoryEntity());
        OkHttpUtils.get(videos_category)//
                .tag("videosByUser")//
                .cacheKey(videos_category + client_id)//
                .cacheMode(cacheMode)//
                .params("client_id", client_id)
                .execute(callback);
    }

    public static void videosByCategory(CacheMode cacheMode, String category, String page, String count, YKCallback<YKVideosByCategoryEntity> callback) {
        String client_id = GlobleConstant.youku_client_id;
        callback.setEntity(new YKVideosByCategoryEntity());
        OkHttpUtils.get(videos_by_category)//
                .tag("videosByUser")//
                .cacheKey(videos_by_category + category + client_id + page + count)//
                .cacheMode(cacheMode)//
                .params("client_id", client_id)
                .params("category", category)
                .params("page", page)
                .params("count", count)
                .execute(callback);
    }

}
