package com.example.adam.fragmentactivitytest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.adam.fragmentactivitytest.Utility.popFragment;
import static com.example.adam.fragmentactivitytest.Utility.switchFragment;

public class FragmentLightRed extends MyFragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        view.findViewById(R.id.linearLayout).setBackgroundColor(Color.parseColor("#FFaaaa"));
        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFragment(getActivity());
            }
        });
        Log.d("adam", "onCreateView, "+getClass().getSimpleName());

        return view;
    }
}
