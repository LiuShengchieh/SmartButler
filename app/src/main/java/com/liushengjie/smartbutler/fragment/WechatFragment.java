package com.liushengjie.smartbutler.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.adapter.WeChatAdapter;
import com.liushengjie.smartbutler.entity.WeChatData;
import com.liushengjie.smartbutler.ui.WebViewActivity;
import com.liushengjie.smartbutler.utils.L;
import com.liushengjie.smartbutler.utils.StaticClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 * 微信精选
 */
public class WechatFragment extends Fragment {

    private ListView mListView;

    private List<WeChatData> mList = new ArrayList<>();

    //标题
    private List<String> mListTitle = new ArrayList<>();
    //地址
    private List<String> mListUrl = new ArrayList<>();

    public WechatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView = view.findViewById(R.id.mListView);

        //点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                L.i("position: " + i);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", mListTitle.get(i));
                intent.putExtra("url", mListUrl.get(i));
                startActivity(intent);
               /* Bundle bundle = new Bundle();
                bundle.putString("key", "value");
                intent.putExtras(bundle);*/
            }
        });

        //获取json数据
        String url = "http://v.juhe.cn/weixin/query?key=" + StaticClass.WECHAT_KEY;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                L.i("Json: " + t);
                parsingJson(t);
            }
        });

    }

    private void parsingJson(String t) {

        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                WeChatData data = new WeChatData();

                String title = json.getString("title");
                String url = json.getString("url");

                data.setTitle(title);
                data.setSource(json.getString("source"));
                data.setImgUrl(json.getString("firstImg"));
                mList.add(data);

                mListTitle.add(title);
                mListUrl.add(url);
            }
            WeChatAdapter adapter = new WeChatAdapter(getActivity(), mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
