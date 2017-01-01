package com.example.administrator.coursetable.fragment.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.BaseFragment;

/**
 * Created by garma on 2016/12/31.
 */

public class shengxiaoFragment extends BaseFragment {

    ImageButton drop_down_gril,drop_down_boy;

    String[]list = {"鼠","牛","虎","兔","龙","蛇",
            "马","羊","猴","鸡","狗","猪"};



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
            MyDialog dialog = new MyDialog(getActivity(), list);

            dialog.show();


        }
    };


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return list[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.constell_list_item, null);
            TextView textView = (TextView) view.findViewById(R.id.text1);
            textView.setText(list[position]);

            return view;
        }
    }
}
