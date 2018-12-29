package com.example.adam.fragmentactivitytest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("adam", "onAttach, "+getClass().getSimpleName());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("adam", "onAttach, "+getClass().getSimpleName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("adam", "onAttach, "+getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("adam", "onStart, "+getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("adam", "onResume, "+getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("adam", "onPause, "+getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("adam", "onStop, "+getClass().getSimpleName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("adam", "setUserVisibleHint, "+isVisibleToUser+", "+getClass().getSimpleName());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("adam", "onCreate, "+getClass().getSimpleName());

    }

    public MyFragment() {
        super();
        Log.d("adam", "Fragment(), "+getClass().getSimpleName());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("adam", "onHiddenChanged, "+hidden);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("adam", "onActivityResult, requestCode: "+requestCode+", resultCode: "+resultCode+", data: "+data+", "+getClass().getSimpleName());
    }
    public void refreshChildFragment(){

    }
}
