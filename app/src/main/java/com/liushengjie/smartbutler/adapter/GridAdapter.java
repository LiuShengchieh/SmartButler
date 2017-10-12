package com.liushengjie.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.entity.GirlData;
import com.liushengjie.smartbutler.utils.PicassoUtils;

import java.util.List;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.adapter
 * 文件名：GridAdapter
 * Created by liushengjie on 2017/10/12.
 * 描述：妹子的适配器
 */

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<GirlData> mList;
    private GirlData data;
    private WindowManager wm;
    private int width;

    public GridAdapter(Context mContext, List<GirlData> mList) {

        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();

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
            view = inflater.inflate(R.layout.girl_item, null);
            viewHolder.imageView = view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        data = mList.get(i);
        //解析图片
        String url = data.getImgUrl();
        PicassoUtils.loadImageViewSize(mContext, url, width/2, 300, viewHolder.imageView);

        return view;
    }

    class ViewHolder {
        private ImageView imageView;
    }

}
