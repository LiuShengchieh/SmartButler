package com.liushengjie.smartbutler.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liushengjie.smartbutler.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GirlFragment extends Fragment {


    public GirlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, null);
        return view;
    }

}
