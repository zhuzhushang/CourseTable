package com.example.administrator.coursetable.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.administrator.coursetable.R;

import java.util.ArrayList;
import java.util.HashMap;
import  java.util.Map;
import java.util.List;
/**
 * Created by Administrator on 2016/12/11.
 */

public class TabOneFragment  extends BaseFragment{


    private GridView gridView;
    //图片容器
    private List<Map<String, Object>> iconList;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.constellation_normal_03,R.mipmap.constellation_normal_05, R.mipmap.constellation_normal07,  R.mipmap.constellation_normal_12,
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
