package com.example.administrator.coursetable.fragment.match;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;

/**
 * Created by garma on 2016/12/31.
 */

public class MyDialog extends Dialog {
    Context context;
    String[] list;
    public MyDialog(Context context, String[] list ) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.constell_list);

        ListView listView = (ListView) this.findViewById(R.id.listView1);
         MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

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
            View view = LayoutInflater.from(context).inflate(R.layout.constell_list_item, null);
            TextView textView = (TextView) view.findViewById(R.id.text1);
            textView.setText(list[position]);

            return view;
        }
    }
}
