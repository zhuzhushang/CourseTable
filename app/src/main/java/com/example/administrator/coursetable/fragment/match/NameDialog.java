package com.example.administrator.coursetable.fragment.match;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.administrator.coursetable.R;

/**
 * Created by garma on 2017/1/1.
 */

public class NameDialog extends Dialog{
    Context context;



    public NameDialog(Context context) {
        super(context);
        this.context = context;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.text);
        dialogInit();

    }

    private void dialogInit(){
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.BOTTOM;
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        //获得窗体的属性
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.y = 500;//设置Dialog距离底部的距离
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
////       将属性设置给窗体
//        getWindow().setAttributes(lp);
//        setCanceledOnTouchOutside(true);

    }


    public void animation(int duration){

        ObjectAnimator.ofFloat(this, "translationY", -1000,360 ).setDuration(duration).start();


    }
}
