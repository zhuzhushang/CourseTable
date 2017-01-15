package com.example.administrator.coursetable.fragment.match;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.BaseFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.coursetable.R.id.listView1;

/**
 * Created by garma on 2016/12/31.
 */

public class shengxiaoFragment extends BaseFragment {

    ImageButton drop_down_gril,drop_down_boy;

    String[]list = {"鼠","牛","虎","兔","龙","蛇",
            "马","羊","猴","鸡","狗","猪"};

    int[] attachedViewLocation = new int[2];
    boolean isboy;
    TextView edit_boy, edit_gril;

    int raw[] = {R.raw.rat, R.raw.cattle,R.raw.tiger, R.raw.rabbit, R.raw.dragon, R.raw.snake,
                 R.raw.horse, R.raw.sheep, R.raw.mon, R.raw.chicken, R.raw.dog, R.raw.pig};

    List<String> file = new ArrayList<>();

    /***读文件内容***/
    List<List<String>> vector = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < raw.length; i++ ){
            readFile(i);
        }


    }

    private void readFile(int a){

        InputStream is =null;
        try {
            is = getActivity().getResources().openRawResource(raw[a]);
            if (is == null) {
                throw new IOException("星座配对规则文件不存在");
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String line = null;

            ArrayList<String> ary = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                ary.add(line);

            }

            vector.add(ary);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.constell_match,container,false);
        drop_down_boy = (ImageButton) view.findViewById(R.id.allow_boy);
        drop_down_gril = (ImageButton) view.findViewById(R.id.allow_gril);
        drop_down_gril.setOnClickListener(listener);
        drop_down_boy.setOnClickListener(listener);

        edit_boy = (TextView) view.findViewById(R.id.edit_boy);
        edit_gril = (TextView) view.findViewById(R.id.edit_gril);

        final ImageButton  match = (ImageButton) view.findViewById(R.id.match);
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                file.clear();
                /***找到匹配的内容**/
                readFile();

                TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(getActivity(),file.toString());
                //showAtLocation(View parent, int gravity, int x, int y)
                takePhotoPopWin.showAtLocation(match, Gravity.BOTTOM, 0, 0);
            }
        });


        return view;
    }



    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Dialog dialog = new Dialog(getActivity(), R.style.dialog);
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.constell_list,null);
            dialog.setContentView(view);

            if(drop_down_gril.isPressed()) {
                drop_down_gril.getLocationOnScreen(attachedViewLocation);
                isboy = false;
            }
            else if(drop_down_boy.isPressed()) {
                drop_down_boy.getLocationOnScreen(attachedViewLocation);
                isboy = true;
            }


            ListView listView = (ListView)view.findViewById(listView1);
           MyAdapter adapter = new MyAdapter();
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(onItemClickListener);

            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);


            lp.x = attachedViewLocation[0] +8; // 新位置X坐标
            lp.y = attachedViewLocation[1] -10; // 新位置Y坐标
            lp.width = 60; // 宽度
            lp.height = 360; // 高度


            // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
            // dialog.onWindowAttributesChanged(lp);
            dialogWindow.setAttributes(lp);
            dialog.show();


        }
    };


    /**
     * 找到匹配内容
     */
    private void readFile(){

        List<String> cp = null;
        String boy = edit_boy.getText().toString();

        for( int i = 0; i < list.length; i++) {


            if (boy.equals(list[i]))
                cp = vector.get(i);

            }


        for(int j = 0; j < cp.size(); j++ ){
            String line = cp.get(j);
            String gril = edit_gril.getText().toString();
            String temp = line.substring(line.length() - 1);
            if (line.contains("女士生肖：") && gril.equals(temp)){
                file.add(cp.get(j+1));

            }
        }



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
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.constell_list_item, null);
            TextView textView = (TextView) view.findViewById(R.id.text1);
            textView.setText(list[position]);

            return view;
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(!isboy)
                edit_gril.setText(list[position]);
            else
                edit_boy.setText(list[position]);

        }
    };
}
