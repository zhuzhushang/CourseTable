package com.example.administrator.coursetable.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.coursetable.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/11.
 */

public class TabFiveFragment extends BaseFragment{

    /***
     * 笑话内容容器
     ***/

    static List<String> file = new ArrayList<>();


    int[] rawId= {R.raw.aiqing,R.raw.dongwu,R.raw.ertong,
            R.raw.jieri,R.raw.lengxiaohua,R.raw.shehua,
            R.raw.xiaoyuan, R.raw.yingshi, R.raw.zonghe};

    /***随机资源文件***/
    private Random rand  = new Random();

    TextView textView1, textView2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.xiaohua,container,false);

         textView1 = (TextView) view.findViewById(R.id.xiaohua1);
         textView2 = (TextView) view.findViewById(R.id.xiaohua2);


        textView1.setText(file.get(rand.nextInt(file.size())));


        textView2.setText(file.get(rand.nextInt(file.size())));
        return view;
    }

    /**
     * 读取笑话文件
     */
    private void loadxiaohuaFile(int raw) {


        InputStream is =null;
        try {
            is = getActivity().getResources().openRawResource(raw);
            if (is == null) {
                throw new IOException("笑话文件不存在");
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String line = null;
            int index = 0;
            while ((line = br.readLine()) != null) {
                file.add(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            ;

        }
    }


    private void initData() {
        // 1、直接new 一个线程类，传入参数实现Runnable接口的对象（new Runnable），相当于方法二
        new Thread(new Runnable() {
          @Override
          public void run() {
                // 读文件
                for (int i = 0; i < rawId.length; i++){
                    loadxiaohuaFile(rawId[i]);
                }
           }
       }).start();



    }


    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
          //向过滤器中添加action
         filter.addAction("com.eee");
        getActivity().registerReceiver(receiver, filter);
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {




        int n = rand.nextInt(file.size() -1);
    //    textView1.setText(file.get(n));
        Log.e("11111",file.get(n)+"111111");
         n  = rand.nextInt(file.size());
//        textView2.setText(file.get(n));
        Log.e("22222",file.get(n)+"");
    }
};

    @Override
    public void onDestroy() {

        getActivity().unregisterReceiver(receiver);
        super.onDestroy();
    }



}
