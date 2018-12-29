package com.example.adam.fragmentactivitytest;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
    public String name = this.getClass().getSimpleName();

    private boolean scrollble = true;


    public MyViewPager(Context context)
    {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble)
            return false;
        else
            return super.onTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (!scrollble)
            return false;
        else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    public boolean isScrollble()
    {
        return scrollble;
    }

    public void setScrollble(boolean scrollble)
    {
        this.scrollble = scrollble;
    }
}