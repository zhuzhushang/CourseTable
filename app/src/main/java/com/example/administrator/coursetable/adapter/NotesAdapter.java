package com.example.administrator.coursetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.model.NoteModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 */

public class NotesAdapter   extends BaseAdapter {



    private String weekArray[] = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日",};

    private Context context;
    private LayoutInflater inflater;
    private List<NoteModel> list;
    private int layoutID;

    public  NotesAdapter(Context context,List<NoteModel> list,int layoutID)
    {
        this.context = context;
        this.list = list;
        this.layoutID = layoutID;
        inflater = LayoutInflater.from(this.context);

    }


    public List<NoteModel> getList() {
        return list;
    }


    public void setList(List<NoteModel> list) {
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
        NoteModel model = list.get(position);

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

        viewHolder.time.setText(getYMD(model.getTime())+"  星期"+getDayOfWeek(model.getTime()));
        viewHolder.note.setText(""+model.getNote());

        if(model.isExpan())
        {
            viewHolder.note.setSingleLine(false);
        }else
        {
            viewHolder.note.setSingleLine(true);
        }

        viewHolder.arrow.setOnClickListener(new ExpandClick(viewHolder.note,position));



        return convertView;
    }


    public class ExpandClick implements View.OnClickListener
    {
        private TextView tv;
        private int position;

        public ExpandClick(TextView tv, int position) {
            this.tv = tv;
            this.position = position;
        }

        @Override
        public void onClick(View view) {

            if(tv.getLineCount() == 1)
            {
                tv.setSingleLine(false);
                list.get(position).setExpan(true);
            }else
            {
                tv.setSingleLine(true);
                list.get(position).setExpan(false);
            }
        }
    }




    public class ViewHolder
    {
        private TextView time;
        private TextView note;
        private ImageView arrow;


        private void initView(View convertView)
        {

            time = (TextView) convertView.findViewById(R.id.time);
            note = (TextView) convertView.findViewById(R.id.note);
            arrow = (ImageView) convertView.findViewById(R.id.arrow);

        }

    }


    /**
     * @return  得到星期几
     */
    private String getDayOfWeek(long time )
    {
        Calendar calendar =  Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);


        String wText = "一";

        switch (dayOfWeek) {

            case 2:
                wText = "一";
                break;
            case 3:
                wText = "二";
                break;
            case 4:
                wText = "三";
                break;
            case 5:
                wText = "四";
                break;
            case 6:
                wText = "五";
                break;
            case 7:
                wText = "六";
                break;
            case 1:
                wText = "日";
                break;

        }
        return wText;
    }


    private String getYMD(long time)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));

    }

}