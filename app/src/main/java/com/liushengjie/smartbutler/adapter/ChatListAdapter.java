package com.liushengjie.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.entity.ChatListData;

import java.util.List;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.adapter
 * 文件名：ChatListAdapter
 * Created by liushengjie on 2017/10/8.
 * 描述：对话Adapter
 */

public class ChatListAdapter extends BaseAdapter {

    /*
    * Item类型，int型，必须从0开始依次递增
    * */
    //左边的type
    public static final int VALUE_LEFT_TEXT = 0;
    //右边的type
    public static final int VALUE_RIGHT_TEXT = 1;

    //Item Type 的数量
    private static final int ITEM_TYPE_COUNT = 2;

    /*
    * 数据
    * */
    private Context mContext;
    private LayoutInflater inflater;
    private ChatListData data;
    private List<ChatListData> mList;

    public ChatListAdapter(Context mContext, List<ChatListData> mList) {

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

        //不同类型的ViewHolder
        ViewHolderLeftText viewHolderLeftText = null;
        ViewHolderRightText viewHolderRightText = null;

        //获取当前要显示的type，根据这个type来区分数据的加载
        int type = getItemViewType(i);
        if (view == null) {
            //设置缓存
            switch (type) {
                case VALUE_LEFT_TEXT:
                    viewHolderLeftText = new ViewHolderLeftText();
                    view = inflater.inflate(R.layout.left_item, null);
                    viewHolderLeftText.tv_left_text = view.findViewById(R.id.tv_left_text);
                    view.setTag(viewHolderLeftText);
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRightText = new ViewHolderRightText();
                    view = inflater.inflate(R.layout.right_item, null);
                    viewHolderRightText.tv_right_text = view.findViewById(R.id.tv_right_text);
                    view.setTag(viewHolderRightText);
                    break;
            }
        } else {
            //获取缓存
            switch (type) {
                case VALUE_LEFT_TEXT:
                    viewHolderLeftText = (ViewHolderLeftText) view.getTag();
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRightText = (ViewHolderRightText) view.getTag();
                    break;
            }
        }

        //赋值
        data = mList.get(i);
        switch (type) {
            case VALUE_LEFT_TEXT:
                viewHolderLeftText.tv_left_text.setText(data.getText());
                break;
            case VALUE_RIGHT_TEXT:
                viewHolderRightText.tv_right_text.setText(data.getText());
                break;
        }

        return view;
    }

    //根据数据源的position返回要显示的item类型
    @Override
    public int getItemViewType(int position) {
        ChatListData data = mList.get(position);
        int type = data.getType();
        return type;
    }

    //返回 Item Type 的总数
    @Override
    public int getViewTypeCount() {
        return ITEM_TYPE_COUNT;
    }

    //左边的文本
    class ViewHolderLeftText {
        private TextView tv_left_text;
    }

    //右边的文本
    class ViewHolderRightText {
        private TextView tv_right_text;
    }


}
