package com.example.adam.fragmentactivitytest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.adam.fragmentactivitytest.Utility.popFragment;

public class FragmentBlue extends MyFragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        view.findViewById(R.id.linearLayout).setBackgroundColor(Color.BLUE);
        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Person person = (MainActivity.Person) getArguments().get(Utility.KEY_DATA);
                person.name = "BobAfterBlue";
                FragmentMain.data = person;//when back to last fragment, save data

                popFragment(getActivity());
            }
        });
        Log.d("adam", "onCreateView, "+getClass().getSimpleName());


        return view;
    }
}
