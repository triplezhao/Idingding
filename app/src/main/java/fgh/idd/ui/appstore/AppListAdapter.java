package fgh.idd.ui.appstore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mozillaonline.downloadprovider.DownLoadUtil;
import com.mozillaonline.providers.downloads.ui.DownloadListActivity;
import com.potato.library.adapter.PotatoBaseRecyclerViewAdapter;
import com.potato.library.util.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import fgh.idd.R;
import fgh.idd.chips.util.ImageLoaderUtil;
import fgh.idd.data.bean.AppBean;

/**
 * Created by ztw on 2015/9/21.
 */
public class AppListAdapter extends PotatoBaseRecyclerViewAdapter<AppListAdapter.VH> {

    public AppListAdapter(Context context) {
        super(context);
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(
                R.layout.item_app_list,
                parent,
                false);
        VH holder = new VH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        final AppBean bean = (AppBean) mData.get(position);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, DownloadListActivity.class);
                mContext.startActivity(intent);
            }
        });
        vh.tv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                DownLoadUtil.startDownload(context, bean.getApkUrl(), bean.getTitle());
            }
        });
        L.i("with", bean.getIcon());

        ImageLoaderUtil.displayImage(bean.getIcon(), vh.iv_pic, R.drawable.def_gray_small);
    }


    public static class VH extends ViewHolder {

        @Bind(R.id.iv_pic)
        RoundedImageView iv_pic;
        @Bind(R.id.tv_download)
        TextView tv_download;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
