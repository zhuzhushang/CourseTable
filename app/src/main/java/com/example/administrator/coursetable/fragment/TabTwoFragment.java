package com.example.administrator.coursetable.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.fragment.match.MyFragmentAdapter;
import com.example.administrator.coursetable.fragment.match.NameMatchFragment;
import com.example.administrator.coursetable.fragment.match.constellMatchFragment;
import com.example.administrator.coursetable.fragment.match.shengxiaoFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/11.
 */

public class TabTwoFragment  extends BaseFragment{




    /***t1姓名，t2星座, t3生肖**/
    TextView t1, t2, t3;

    private ViewPager mViewPager;
    private ArrayList<Fragment> fragments;
    int currindex;
    ImageView img1, img2, img3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.select_card,container,false);


        initViewPager(view);
        initTextView(view);
        initImage(view);











        return view;
    }

    /**
     * 重写TextView点击事件
     */
   public class txtListener implements View.OnClickListener{
        private int index = 0;

        public txtListener(int i ){
            index = i;
        }
        @Override
        public void onClick(View v) {
           mViewPager.setCurrentItem(index);
            switch (index){
                case 0:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    break;
                case 1:
                    img2.setVisibility(View.VISIBLE);
                    img1.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    break;
                case 2:
                    img3.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    img1.setVisibility(View.GONE);
                    break;
            }
        }
    }


    private void initViewPager(View rootView){
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        fragments = new ArrayList<>();

        constellMatchFragment b = new constellMatchFragment();
        NameMatchFragment a = new NameMatchFragment();

        shengxiaoFragment c = new shengxiaoFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

       fragments.add(a);

        fragments.add(b);

        fragments.add(c);

        mViewPager.setAdapter(new MyFragmentAdapter(getFragmentManager(),fragments));
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new myOnPageChangeListener());
    }

    private void initTextView(View rootView){
        t1 = (TextView) rootView.findViewById(R.id.t1);
        t2 = (TextView) rootView.findViewById(R.id.t2);
        t3 = (TextView) rootView.findViewById(R.id.t3);


        t1.setOnClickListener(new txtListener(0));
        t2.setOnClickListener(new txtListener(1));
        t3.setOnClickListener(new txtListener(2));
    }


    private void initImage(View rootView){
     img1= (ImageView) rootView.findViewById(R.id.img1);
     img2= (ImageView) rootView.findViewById(R.id.img2);
     img3= (ImageView) rootView.findViewById(R.id.img3);

    }

    public class myOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int index) {
            switch (index){
                case 0:
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    break;
                case 1:
                    img2.setVisibility(View.VISIBLE);
                    img1.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    break;
                case 2:
                    img3.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    img1.setVisibility(View.GONE);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
