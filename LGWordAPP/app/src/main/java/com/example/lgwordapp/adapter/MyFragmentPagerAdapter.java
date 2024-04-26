package com.example.lgwordapp.adapter;

/*import androidx.annotation.NonNull;*/
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lgwordapp.MyFragment1;
import com.example.lgwordapp.MyFragment2;
import com.example.lgwordapp.MyFragment3;
import com.example.lgwordapp.MyFragment4;
import com.example.lgwordapp.Viewpager;

import java.util.ArrayList;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private MyFragment1 myFragment1;
    private MyFragment2 myFragment2;
    private MyFragment3 myFragment3;
    private MyFragment4 myFragment4;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
        myFragment3 = new MyFragment3();
        myFragment4 = new MyFragment4();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Viewpager.PAGE_ONE:
                fragment = myFragment1;
                break;
            case Viewpager.PAGE_TWO:
                fragment = myFragment2;
                break;
            case Viewpager.PAGE_THREE:
                fragment = myFragment3;
                break;
            case Viewpager.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }
    
}



