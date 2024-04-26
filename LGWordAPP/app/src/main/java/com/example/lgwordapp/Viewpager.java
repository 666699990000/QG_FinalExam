package com.example.lgwordapp;


import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.lgwordapp.adapter.MyFragmentPagerAdapter;


//根据状态决定
public class Viewpager extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {
    private static final String TAG = "Viewpager";

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;


//    private TextView titleBarText;
    private RadioGroup rgTabBar;
    private RadioButton rbChannel;
    private RadioButton rbMessage;
    private RadioButton rbBetter;
    private RadioButton rbSetting;
    private ViewPager viewpager;
    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        initViews();
    }

    private void initViews() {

//        titleBarText = (TextView) findViewById(R.id.title_bar_text);
        rgTabBar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rbChannel = (RadioButton) findViewById(R.id.rb_channel);
        rbMessage = (RadioButton) findViewById(R.id.rb_message);
        rbBetter = (RadioButton) findViewById(R.id.rb_better);
        rbSetting = (RadioButton) findViewById(R.id.rb_setting);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        rgTabBar.setOnCheckedChangeListener(this);
        viewpager.setAdapter(mAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(this);
        rbChannel.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_channel) {
            viewpager.setCurrentItem(PAGE_ONE);
        } else if (checkedId == R.id.rb_message) {
            viewpager.setCurrentItem(PAGE_TWO);
        } else if (checkedId == R.id.rb_better) {
            viewpager.setCurrentItem(PAGE_THREE);
        } else if (checkedId == R.id.rb_setting) {
            viewpager.setCurrentItem(PAGE_FOUR);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {


        Log.i(TAG, "onPageScrollStateChanged: state::" + state);
        if (state == 2) {
            int currentItemPosition = viewpager.getCurrentItem();
            Log.w(TAG, "onPageScrollStateChanged: currentItemPosition::" + currentItemPosition);
            switch (currentItemPosition) {
                case PAGE_ONE:
                    rbChannel.setChecked(true);
                    break;
                case PAGE_TWO:
                    rbMessage.setChecked(true);
                    break;
                case PAGE_THREE:
                    rbBetter.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rbSetting.setChecked(true);
                    break;
            }
        }
    }

}
