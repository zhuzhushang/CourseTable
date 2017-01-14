package com.example.administrator.coursetable.fragment.match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.BaseFragment;
import com.example.administrator.coursetable.hanzi.HanDict;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by garma on 2016/12/28.
 */

public class NameMatchFragment extends BaseFragment{

    Map<Integer, String> one = new HashMap<>();
    String gril, boy;
    String[] hanzi = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一",
            "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "二十一", "二十二"};

    /***
     * 姓名匹配规则文件容器
     ***/
    List<String> rule = new ArrayList<>();

    /***匹配结果**/
    public String content;

    ImageButton match;

    EditText edit_gril, edit_boy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         Bundle bundle = getArguments();
//         gril =bundle.getString("grilName");
//         boy = bundle.getString("boyName");

          initData();


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match, container, false);
        edit_boy = (EditText) view.findViewById(R.id.edit_boy);
        edit_gril = (EditText) view.findViewById(R.id.edit_gril);





         match = (ImageButton) view.findViewById(R.id.match);

         match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boy = edit_boy.getText().toString();
                gril = edit_gril.getText().toString();

                match();

                TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(getActivity(),content);
                //showAtLocation(View parent, int gravity, int x, int y)
                takePhotoPopWin.showAtLocation(match, Gravity.BOTTOM, 0, 0);



            }
        });



        return view;


    }



    private void initData() {
        HanDict.getInstance(getActivity());

        loadNameRule();

        for (int i = 0; i < hanzi.length; i++) {
            one.put(i, hanzi[i]);
        }


    }

    private void match() {
        int count = 0;
        for (int i = 0; i < gril.length(); i++) {
            char ch = gril.charAt(i);
            count += HanDict.getBH(ch).length();
        }

        int temp = 0;
        for (int i = 0; i < boy.length(); i++) {
            char ch = boy.charAt(i);
            temp += HanDict.getBH(ch).length();
        }

        int num = Math.abs(count - temp);
        Log.e("妮娜", num + "");

        String bihua = one.get(num);

        content = findFileForBIHUA(bihua);


    }


/****查找相对应得笔画数**/
    private String findFileForBIHUA(String bihua){
        for(int i = 0 ; i < rule.size(); i+=2){

            int index = rule.get(i).indexOf("画");
            String temp = rule.get(i).substring(0, index);
            if (temp.contains(bihua)){
                Log.e("111111111",rule.get(i+1));
                return rule.get(i + 1);

            }
        }

        return null;
    }

    private void loadNameRule() {


        InputStream is =null;
        try {
             is = getActivity().getResources().openRawResource(R.raw.name_match_rule);
            if (is == null) {
                throw new IOException("姓名配对规则文件不存在");
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String line = null;

            while ((line = br.readLine()) != null) {
                rule.add(line);
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





}