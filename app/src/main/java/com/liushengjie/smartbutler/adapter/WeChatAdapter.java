package com.liushengjie.smartbutler.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.entity.WeChatData;
import com.liushengjie.smartbutler.utils.L;
import com.liushengjie.smartbutler.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.adapter
 * 文件名：WeChatAdapter
 * Created by liushengjie on 2017/10/11.
 * 描述：微信精选
 */

public class WeChatAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WeChatData> mList;
    private WeChatData data;
    private int width, height;
    private WindowManager wm;

    public WeChatAdapter(Context mContext, List<WeChatData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        L.i("width: " + width + "height: " + height);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.wechat_item, null);
            viewHolder.iv_img = view.findViewById(R.id.iv_img);
            viewHolder.tv_title = view.findViewById(R.id.tv_title);
            viewHolder.tv_source = view.findViewById(R.id.tv_source);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        data = mList.get(i);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_source.setText(data.getSource());

        if (!TextUtils.isEmpty(data.getImgUrl())) {
            //加载图片
            PicassoUtils.loadImageViewSize(mContext, data.getImgUrl(), width / 3, 250, viewHolder.iv_img);
        }


        return view;
    }

    class ViewHolder {
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_source;

    }
}
