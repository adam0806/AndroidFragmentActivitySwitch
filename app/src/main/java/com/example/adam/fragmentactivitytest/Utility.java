package com.example.adam.fragmentactivitytest;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class Utility {
    public static boolean isDoubleBackPressedLeaveApp = true;
    public static int backPressedLeaveAppCount = 0;
    public static MyFragment lastFragment = null;
    public static boolean replace = false;
    public static String KEY_DATA = "data";
    public static void switchFragment(FragmentActivity activity, int layoutId, final Fragment fragment, boolean isAddToBackStack, boolean isReplace){
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        String tag = fragment.getClass().getSimpleName();
        if (isAddToBackStack) {
            Log.d("adam", "switchFragment isAddToBackStack, fragment: " + tag);
            fragmentTransaction.addToBackStack(TextUtils.isEmpty(tag) ? null : tag);
        }
        if (isReplace) {
            Log.d("adam", "switchFragment isReplace, fragment: " + tag);
            fragmentTransaction.replace(layoutId, fragment);
        } else {
            if (!fragment.isAdded()) {//新增/顯示一個fragment(頁面)
                Log.d("adam", "switchFragment add, fragment: " + tag);
                fragmentTransaction.add(layoutId, fragment, tag);
            } else {
                Log.d("adam", "switchFragment show, fragment: " + tag);
                fragmentTransaction.show(fragment);
            }
        }
        replace = isReplace;
        lastFragment = (MyFragment) fragment;
        fragmentTransaction.commitAllowingStateLoss();
    }
    public static void popFragment(final FragmentActivity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
    public static MyFragment getBackLastFragment(MyFragment nowPageFragment){
        MyFragment fragment = (MyFragment)nowPageFragment.getFragmentManager().findFragmentById(R.id.fragmentContent);
        if(!fragment.getClass().getSimpleName().equals(Utility.lastFragment.getClass().getSimpleName()) && !Utility.replace) {
            return fragment;
        }
        return null;
    }
    public static MyFragment getBackLastFragment(MainActivity activity){
        if(Utility.lastFragment!=null) {
            MyFragment fragment = (MyFragment) activity.getSupportFragmentManager().findFragmentById(R.id.fragmentContent);
            if (!fragment.getClass().getSimpleName().equals(Utility.lastFragment.getClass().getSimpleName()) && !Utility.replace) {
                return fragment;
            }
        }
        return null;
    }
    public static void showToast(Context context, final String string, int duration){
        Toast toast = null;
        try{
            toast = Toast.makeText(context, string, duration);
            toast.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
