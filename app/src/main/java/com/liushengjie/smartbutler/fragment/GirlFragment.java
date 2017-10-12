package com.liushengjie.smartbutler.fragment;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.adapter.GridAdapter;
import com.liushengjie.smartbutler.entity.GirlData;
import com.liushengjie.smartbutler.utils.L;
import com.liushengjie.smartbutler.utils.PicassoUtils;
import com.liushengjie.smartbutler.utils.StaticClass;
import com.liushengjie.smartbutler.view.CustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 妹子图
 */
public class GirlFragment extends Fragment {

    private GridView mGridView;
    private List<GirlData> mList = new ArrayList<>();
    private GridAdapter adapter;
    //提示框
    private CustomDialog dialog;
    //图片地址的数据
    private List<String> mListUrl = new ArrayList<>();
    //PhotoView
    private PhotoView photo_view;

    /**
     * 1.监听点击事件
     * 2.提示框
     * 3.加载图片
     * 4.PhotoView
     */


    public GirlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mGridView = view.findViewById(R.id.mGridView);

        //初始化提示框
        dialog = new CustomDialog(getActivity(), LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, R.layout.dialog_girl,
                R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);

        //通过提示框展示图片
        photo_view = dialog.findViewById(R.id.photo_view);

        //获取json数据
        RxVolley.get(StaticClass.GIRL_URL_KEY, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("Json: " + t);
                parsingJson(t);
            }
        });

        //监听点击事件
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //解析图片
                PicassoUtils.loadImageView(getActivity(), mListUrl.get(i), photo_view);
                dialog.show();
            }
        });
    }

    //解析json数据
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String url = json.getString("url");
                mListUrl.add(url);
                GirlData data = new GirlData();
                data.setImgUrl(url);
                mList.add(data);
            }
            adapter = new GridAdapter(getActivity(), mList);
            mGridView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
