package com.example.administrator.coursetable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.HideWeekModel;
import com.example.administrator.coursetable.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by ShaoQuanwei on 2017/2/13.
 */

public class HideWeekAdapter extends RecyclerView.Adapter<HideWeekAdapter.ViewHolder>{

    private Context context;
    private List<HideWeekModel>  list;
    private String whatWeekArray[];
    private String ww = "1,1,1,1,1,1,1";
    private String TAG = "HideWeekAdapter";


    public HideWeekAdapter(Context context, List<HideWeekModel> list) {
        this.context = context;
        this.list = list;

        String str = (String) SharedPreferencesUtils.getParam(context, Constants.HIDE_WEEK_DATA,ww);

        whatWeekArray = str.split(",");

    }

    @Override
    public HideWeekAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_hide_week,null);
        HideWeekAdapter.ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HideWeekAdapter.ViewHolder holder, int position) {

        HideWeekModel model = list.get(position);

        holder.what_week.setText(model.getWhatDay());

        if(model.getIsCheck().trim().equals("1"))
        {
            holder.checkbox.setChecked(true);
        }else
        {
            holder.checkbox.setChecked(false);
        }

        holder.checkbox.setOnCheckedChangeListener(new MyCheckChangeListener(position));


    }

    @Override
    public int getItemCount() {
        return list != null ? list.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView what_week;
        private CheckBox checkbox;


        public ViewHolder(View itemView) {
            super(itemView);

            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            what_week = (TextView) itemView.findViewById(R.id.what_week);
        }
    }

    public class MyCheckChangeListener implements CompoundButton.OnCheckedChangeListener
    {
        private int position;

        public MyCheckChangeListener(int position) {
            this.position = position;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(isChecked)
            {
                list.get(position).setIsCheck("1");
            }else
            {
                list.get(position).setIsCheck("0");
            }

            String data = getCheckData();
            Log.e(TAG, "onCheckedChanged: "+position+"     "+data );

            SharedPreferencesUtils.putParam(context,Constants.HIDE_WEEK_DATA,data);
        }
    }


    /**
     * 把数据变成字符串
     */
    private String getCheckData()
    {
        StringBuffer buffer = new StringBuffer();
        for(HideWeekModel model:list){

            buffer.append(model.getIsCheck()+",");

        }

        buffer.delete(buffer.length() -1 ,buffer.length());

        return buffer.toString();
    }

}
