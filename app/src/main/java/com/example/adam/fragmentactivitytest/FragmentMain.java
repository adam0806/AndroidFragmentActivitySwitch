package com.example.adam.fragmentactivitytest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentMain extends MyFragment{
    FragmentBlue blueFragment = new FragmentBlue();
    public static Object data;//if you want receive data from next fragment, save data when leave next fragment, when ondestroy, null it
    ArrayList<MyFragment> fragmentList = new ArrayList<>();
    MyFragment nowfragment;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        view.findViewById(R.id.linearLayout).setBackgroundColor(Color.GREEN);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Utility.KEY_DATA,new MainActivity.Person("Bob"));
        blueFragment.setArguments(bundle);
        view.findViewById(R.id.buttonNext1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, blueFragment, false, false);
            }
        });
        view.findViewById(R.id.buttonNext2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, blueFragment, true, false);
            }
        });
        view.findViewById(R.id.buttonNext3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, blueFragment, false, true);
            }
        });
        view.findViewById(R.id.buttonNext4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, blueFragment, true, true);
            }
        });
        view.findViewById(R.id.textView).setVisibility(View.VISIBLE);
        fragmentList.add(new FragmentRed());
        fragmentList.add(new FragmentGray());
        fragmentList.add(new FragmentBlack());
        nowfragment = fragmentList.get(0);
        final MyViewPager viewPager = view.findViewById(R.id.viewPager);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Log.d("adam", "onViewAttachedToWindow, "+getClass().getSimpleName());
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Log.d("adam", "onViewDetachedFromWindow, "+getClass().getSimpleName());
            }
        });
        viewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.d("adam", "onLayoutChange, "+getClass().getSimpleName());
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int mI, float mV, int mI1) {
                Log.d("adam", "onPageScrolled, "+mI+","+getClass().getSimpleName());
            }

            @Override
            public void onPageSelected(int mI) {
                nowfragment = fragmentList.get(mI);
                Log.d("adam", "onPageSelected, "+mI+","+getClass().getSimpleName());
            }

            @Override
            public void onPageScrollStateChanged(int mI) {
                Log.d("adam", "onPageScrollStateChanged, "+mI+","+getClass().getSimpleName());

            }
        });
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(0);

        Log.d("adam", "onCreateView, "+getClass().getSimpleName());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(data !=null){//when back from last fragment, you can receive data
            MainActivity.Person person = (MainActivity.Person) data;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        data = null;
    }

    public void refreshChildFragment(){
        if(nowfragment!=null) {
            nowfragment.onResume();
        }
    }
}
