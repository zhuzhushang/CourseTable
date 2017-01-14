package com.example.administrator.coursetable.fragment.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.BaseFragment;

/**
 * Created by garma on 2016/12/29.
 */

public class constellMatchFragment extends BaseFragment {

    ImageButton drop_down_gril,drop_down_boy;

    String[]list = {"白羊","金牛","双子","巨蟹","狮子","处女",
    "天秤","天蝎","射手","摩羯","水瓶","双鱼"};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.constell_match,container,false);
        drop_down_boy = (ImageButton) view.findViewById(R.id.allow_boy);
        drop_down_gril = (ImageButton) view.findViewById(R.id.allow_gril);
        drop_down_gril.setOnClickListener(listener);
        drop_down_boy.setOnClickListener(listener);

        return view;
    }



    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            MyDialog dialog = new MyDialog(getActivity(), list);
//
//            dialog.show();


        }
    };



}









































