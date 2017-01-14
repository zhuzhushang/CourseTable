package com.example.administrator.coursetable.fragment.match;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import android.widget.Toast;

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
 * Created by garma on 2016/12/29.
 */

public class constellMatchFragment extends BaseFragment {

    ImageButton drop_down_gril,drop_down_boy;

    String[]list = {"白羊","金牛","双子","巨蟹","狮子","处女",
    "天秤","天蝎","射手","摩羯","水瓶","双鱼"};

    int raw[] = {R.raw.baiyangboy, R.raw.jinniuboy, R.raw.shuangziboy, R.raw.juxieboy, R.raw.shiziboy, R.raw.chunvboy,
    R.raw.tianchengboy, R.raw.tianxieboy, R.raw.sheshouboy, R.raw.mojieboy, R.raw.shuipingboy, R.raw.shuangyuboy};

   /***读文件内容***/
    List<List<String>> vector = new ArrayList<>();
  /***找到匹配文件**/
    List<String> content = new ArrayList<>();


    int[] attachedViewLocation = new int[2];

    TextView edit_boy, edit_gril;
    boolean isboy;

    ImageButton  match;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < raw.length; i++) {
            readFile(i);
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


        match = (ImageButton) view.findViewById(R.id.match);
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judge();

                /***找到匹配的内容**/
               List<String> temp =  match();

                TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(getActivity(),temp.toString());
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
            lp.width = 100; // 宽度
            lp.height = 360; // 高度


            // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
            // dialog.onWindowAttributesChanged(lp);
            dialogWindow.setAttributes(lp);
            dialog.show();

        }
    };


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

            Toast.makeText(getActivity(),"你选中了"+ list[position] + "座", Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 读星座文件
     */
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

    /**
     * 判断读哪个星座文件
     */
    private void judge(){

        String boy = edit_boy.getText().toString();

        for(int i = 0; i < list.length; i++) {


            if (boy.equals(list[i]))
            {
                content = vector.get(i);
                Log.e("666","你读的是"+list[i]+"文件");
                break;
            }
        }
    }

    boolean find;
    private List<String>  match() {
        String edit = edit_gril.getText().toString();
        /***匹配内容**/
        ArrayList<String> temp = new ArrayList<>();

        for (int i = 0; i < content.size(); i++) {

            if(find){
                temp.add(content.get(i));
                if(content.get(i+1).contains("【")) {
                    Log.e("666",temp.toString());
                    find = false;
                    return temp;
                }
            }
            if(content.get(i).contains("【") && content.get(i).contains(edit)) {
                find = true;
            }



        }
       return null;
    }
}









































