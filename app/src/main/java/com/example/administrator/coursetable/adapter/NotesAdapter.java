package com.example.administrator.coursetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.model.NotesModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 */

public class NotesAdapter   extends BaseAdapter {



    private String weekArray[] = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日",};

    private Context context;
    private LayoutInflater inflater;
    private List<NotesModel> list;
    private int layoutID;

    public  NotesAdapter(Context context,List<NotesModel> list,int layoutID)
    {
        this.context = context;
        this.list = list;
        this.layoutID = layoutID;
        inflater = LayoutInflater.from(this.context);

    }


    public List<NotesModel> getList() {
        return list;
    }


    public void setList(List<NotesModel> list) {
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
        NotesModel model = list.get(position);

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

        viewHolder.time.setText(""+model.getYTD()+"    "+weekArray[model.getWeekPosition()]);
        viewHolder.note.setText(""+model.getNotepad());

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



}