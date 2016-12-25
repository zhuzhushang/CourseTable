package com.example.administrator.coursetable.fragment.constellation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.TabFourFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by garma on 2016/12/13.
 */

public  class FortuneFragment extends Fragment {


    GridView gridView;
    SimpleAdapter simpleAdapter;
    /**星星容器**/
    private List<Map<String, Object>> list;
    private Context context;

    private String from[] = {"img"};
    private int[] to = {R.id.img};

    private ListView listView;
    private MyDataAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        getData();
    }


    public int getShownIndex(){
        return getArguments().getInt("index", 0);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fortune, container, false);

        ImageView img_View = (ImageView) view.findViewById(R.id.imgView);
        img_View.setImageResource(TabFourFragment.icon[getShownIndex()]);
        gridView = (GridView) view.findViewById(R.id.gridView);
        simpleAdapter = new SimpleAdapter(context, list, R.layout.constellation_item, from, to);
        gridView.setAdapter(simpleAdapter);

        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new MyDataAdapter();
        listView.setAdapter(adapter);
        return view;
    }

    private void getData(){
        list = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < 20; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img",R.mipmap.star);
            list.add(map);
        }
    }

    private class MyDataAdapter extends BaseAdapter{

        String[] stris = {"今日运势", "明日运势", "本周运势", "本月运势", "本年运势", "爱情运势"};
        @Override
        public int getCount() {
            return stris.length + 3;
        }

        @Override
        public Object getItem(int i) {

            return stris[i];

        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            View rootView = null;
            //如果没有可重用的控件
            if(view == null ){
                LayoutInflater inflater = LayoutInflater.from(context);
                rootView = inflater.inflate(R.layout.fortune_item, viewGroup, false);
                holder = new ViewHolder();
                rootView.setTag(holder);
                holder.textView = (TextView) rootView.findViewById(R.id.text);
                holder.imageButton = (ImageButton) rootView.findViewById(R.id.show);
            }else{
                holder = (ViewHolder) rootView.getTag();
            }

            if(i < 5) {
                holder.textView.setText(stris[i]);
            }else{
                holder.textView.setText("");
                holder.imageButton.setVisibility(View.GONE);
            }


            return rootView;
        }
    }


    private class ViewHolder{
        private TextView textView;
        private ImageButton imageButton;
    }
}
