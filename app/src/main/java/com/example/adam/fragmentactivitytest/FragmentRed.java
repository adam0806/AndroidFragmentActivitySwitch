package com.example.adam.fragmentactivitytest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentRed extends MyFragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        view.findViewById(R.id.linearLayout).setBackgroundColor(Color.RED);
        view.findViewById(R.id.buttonNext1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, new FragmentLightRed(), false, false);
            }
        });
        view.findViewById(R.id.buttonNext2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, new FragmentLightRed(), true, false);
            }
        });
        view.findViewById(R.id.buttonNext3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, new FragmentLightRed(), false, true);
            }
        });
        view.findViewById(R.id.buttonNext4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.switchFragment(getActivity(), R.id.fragmentContent, new FragmentLightRed(), true, true);
            }
        });
        Log.d("adam", "onCreateView, "+getClass().getSimpleName());

        return view;
    }
}
