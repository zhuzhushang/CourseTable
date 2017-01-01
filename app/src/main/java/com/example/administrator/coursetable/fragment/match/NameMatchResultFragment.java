package com.example.administrator.coursetable.fragment.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.BaseFragment;

/**
 * Created by garma on 2016/12/31.
 */

public class NameMatchResultFragment extends BaseFragment{

    EditText edit_gril, edit_boy;
    ImageButton match;

    TextView match_result;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.match_result_for_name);
//
////        edit_boy = (EditText) this.findViewById(R.id.edit_boy);
////        edit_gril = (EditText)this.findViewById(R.id.edit_gril);
////        match = (ImageButton) this.findViewById(R.id.match);
////        match_result = (TextView) this.findViewById(R.id.result);
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_, container, false);

        return view;
    }
}
