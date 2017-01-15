package com.example.administrator.coursetable.fragment.constellation;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.administrator.coursetable.activity.BaseActivity;
import com.example.administrator.coursetable.fragment.TabFourFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by garma on 2016/12/24.
 */

public class FortuneActivity extends BaseActivity {

    int index;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获得选中的图片
        getData();
        context = this;

       setContentView(R.layout.fortune);

       index = getIntent().getIntExtra("index", 0);

        ImageView img_View = (ImageView)findViewById(R.id.imgView);
        img_View.setImageResource(TabFourFragment.icon[index]);
        gridView = (GridView)findViewById(R.id.gridView);
        simpleAdapter = new SimpleAdapter(context, list, R.layout.constellation_item, from, to);
        gridView.setAdapter(simpleAdapter);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyDataAdapter();
        listView.setAdapter(adapter);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        return true;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    private void getData(){
        list = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < 20; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img",R.mipmap.star);
            list.add(map);
        }
    }


    private class MyDataAdapter extends BaseAdapter {

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

            View rootView = null;

                LayoutInflater inflater = LayoutInflater.from(context);
                rootView = inflater.inflate(R.layout.fortune_item, viewGroup, false);

               TextView textView = (TextView) rootView.findViewById(R.id.text);
               ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.show);


            if(i < 5) {
               textView.setText(stris[i]);
            }else{
               textView.setText("");
               imageButton.setVisibility(View.GONE);
            }


            return rootView;
        }
    }


    private class ViewHolder{
        private TextView textView;
        private ImageButton imageButton;
    }
}
