package com.example.administrator.coursetable.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.constellation.FortuneActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2016/12/11.
 */

public class TabFourFragment extends BaseFragment{


    private GridView gridView;
    //图片容器
    private List<Map<String, Object>> iconList;
    // 图片封装为一个数组
    public   static  int[] icon = {R.mipmap.constellation_normal_03,R.mipmap.constellation_normal_05, R.mipmap.constellation_normal07,  R.mipmap.constellation_normal_12,
            R.mipmap.constellation_normal_13,R.mipmap.constellation_normal_14, R.mipmap.constellation_normal_18,  R.mipmap.constellation_normal_19,
            R.mipmap.constellation_normal20,R.mipmap.constellation_normal_24, R.mipmap.constellation_normal_25,  R.mipmap.constellation_normal_26};

    private String from[] = {"img"};
    private int[] to = {R.id.img};
   SimpleAdapter simpleAdapter;

    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

        getData();



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.constellation,container,false);
        gridView = (GridView) view.findViewById(R.id.gridView);
        simpleAdapter = new SimpleAdapter(context, iconList, R.layout.constellation_item, from, to);
        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), FortuneActivity.class);
                intent.putExtra("index", position);

                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void getData(){
        iconList = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < icon.length; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            iconList.add(map);
        }
    }



}
