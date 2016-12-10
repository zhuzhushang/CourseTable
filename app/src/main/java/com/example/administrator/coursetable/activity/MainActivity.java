package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.TabFiveFragment;
import com.example.administrator.coursetable.fragment.TabFourFragment;
import com.example.administrator.coursetable.fragment.TabOneFragment;
import com.example.administrator.coursetable.fragment.TabThreeFragment;
import com.example.administrator.coursetable.fragment.TabTwoFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{


    private TabOneFragment mTabOneFragment;
    private TabTwoFragment mTabTwoFragment;
    private TabThreeFragment mTabThreeFragment;
    private TabFourFragment mTabFourFragment;
    private TabFiveFragment mTabFiveFragment;

    private Fragment fragmentArray[];

    private RadioGroup hp_rg;

    /**当前选中的id*/
    private int currentCheckId = R.id.homepage_tab_2;
    /**当前选中的下标*/
    private int currentIndex = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewInit();
        dataInit();
        eventInit();

    }

    private void ViewInit() {

        hp_rg = (RadioGroup) findViewById(R.id.hp_rg);

    }

    private void dataInit() {

        mTabOneFragment = new TabOneFragment();
        mTabTwoFragment = new TabTwoFragment();
        mTabThreeFragment = new TabThreeFragment();
        mTabFourFragment = new TabFourFragment();
        mTabFiveFragment = new TabFiveFragment();

        fragmentArray = new Fragment[]{mTabOneFragment,mTabTwoFragment,mTabThreeFragment,mTabFourFragment,mTabFiveFragment};

        FragmentTransaction transAction = getSupportFragmentManager().beginTransaction();

        for (int i = 0; i < fragmentArray.length; i++) {

            transAction.add(R.id.comtainer,fragmentArray[i]);
            transAction.hide(fragmentArray[i]);

        }

        transAction.show(fragmentArray[2]);
        transAction.commit();

    }

    private void eventInit() {


        hp_rg.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if(currentCheckId == i)
        {
            return;
        }

        FragmentTransaction transAction = getSupportFragmentManager().beginTransaction();

        int index = 2;

        switch (i)
        {
            case R.id.homepage_tab_1:

                index = 0;

                break;
            case R.id.homepage_tab_2:

                index = 1;
                break;
            case R.id.homepage_tab_3:
                index = 2;

                break;
            case R.id.homepage_tab_4:

                index = 3;

                break;
            case R.id.homepage_tab_5:

                index = 4;
                break;
        }


        transAction.show(fragmentArray[index]).hide(fragmentArray[currentIndex]).commit();
        currentIndex = index;
        currentCheckId = i;

    }
}

