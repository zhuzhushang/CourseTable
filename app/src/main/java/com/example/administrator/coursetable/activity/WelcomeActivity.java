package com.example.administrator.coursetable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.administrator.coursetable.R;

/**
 * Created by Administrator on 2016/12/10.
 */

public class WelcomeActivity extends BaseActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle arg0) {

        super.onCreate(arg0);
        setContentView(R.layout.activity_welcome);

        mHandler = new Handler();

        mHandler.postDelayed(runnable,2000);

    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            WelcomeActivity.this.finish();

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacks(runnable);


    }
}
