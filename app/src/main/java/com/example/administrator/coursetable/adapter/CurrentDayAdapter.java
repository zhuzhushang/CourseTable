package com.example.administrator.coursetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.model.CurrentDayModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 */

public class CurrentDayAdapter   extends BaseAdapter {




    private Context context;
    private LayoutInflater inflater;
    private List<CurrentDayModel> list;
    private int layoutID;

    public  CurrentDayAdapter(Context context,List<CurrentDayModel> list,int layoutID)
    {
        this.context = context;
        this.list = list;
        this.layoutID = layoutID;
        inflater = LayoutInflater.from(this.context);

    }


    public List<CurrentDayModel> getList() {
        return list;
    }


    public void setList(List<CurrentDayModel> list) {
        this.list = list;

        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return null == list?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null == list?null:list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        ViewHolder viewHolder;
        CurrentDayModel model = list.get(position);

        if(convertView == null)
        {
            convertView = inflater.inflate(layoutID, null);
            viewHolder = new ViewHolder();
            viewHolder.initView(convertView);


            convertView.setTag(viewHolder);

        }else
        {


            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.class_name.setText(""+model.getClass_name());
        viewHolder.up_class_place.setText("上课地点:"+model.getUp_class_place());
        viewHolder.up_class_time.setText(model.getStartTime()+"-"+model.getEndTime());



        return convertView;
    }


    public class ViewHolder
    {


        private TextView class_name,up_class_place,up_class_time;


        private void initView(View convertView)
        {

            class_name = (TextView) convertView.findViewById(R.id.up_class_time);
            up_class_place = (TextView) convertView.findViewById(R.id.up_class_place);
            up_class_time = (TextView) convertView.findViewById(R.id.up_class_time);

        }

    }

}