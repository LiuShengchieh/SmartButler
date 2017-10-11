package com.liushengjie.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.entity.CourierData;

import java.util.List;
import java.util.zip.Inflater;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.adapter
 * 文件名：CourierAdapter
 * Created by liushengjie on 2017/10/6.
 * 描述：快递查询适配器
 */

public class CourierAdapter extends BaseAdapter{

    //上下文
    private Context mContext;
    //布局加载器
    private LayoutInflater inflater;
    //实体类
    private CourierData data;
    //数据
    private List<CourierData> mList;


    public CourierAdapter(Context mContext, List<CourierData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        //第一次加载
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_courier_item, null);
            viewHolder.tv_remark = view.findViewById(R.id.tv_remark);
            viewHolder.tv_zone = view.findViewById(R.id.tv_zone);
            viewHolder.tv_datetime = view.findViewById(R.id.tv_datetime);
            //设置缓存
            view.setTag(viewHolder);
        } else {
            //获取缓存
            viewHolder = (ViewHolder) view.getTag();
        }
        //设置数据
        data = mList.get(i);
        viewHolder.tv_remark.setText(data.getRemark());
        viewHolder.tv_zone.setText(data.getZone());
        viewHolder.tv_datetime.setText(data.getDatetime());

        //返回View
        return view;
    }

    //内部类ViewHolder，用于对控件的实例进行缓存
    class ViewHolder {
        private TextView tv_remark;
        private TextView tv_zone;
        private TextView tv_datetime;
    }
}
