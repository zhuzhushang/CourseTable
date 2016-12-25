package com.example.administrator.coursetable.fragment.constellation;


import android.os.Bundle;

import com.example.administrator.coursetable.activity.BaseActivity;

/**
 * Created by garma on 2016/12/24.
 */

public class FortuneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       if(savedInstanceState == null){
            // During initial setup, plug in the FortuneFragment
            FortuneFragment fortuneFragment = new FortuneFragment();
            fortuneFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fortuneFragment).commit();
       }
    }
}
