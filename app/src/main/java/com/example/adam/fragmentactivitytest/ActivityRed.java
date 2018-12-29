package com.example.adam.fragmentactivitytest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityRed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        findViewById(R.id.linearLayout).setBackgroundColor(Color.RED);
        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Person person = (MainActivity.Person)getIntent().getExtras().get("data");
                Intent intent = new Intent();
                person.name = "AdamRed";
                intent.putExtra("data", person);
                setResult(MainActivity.RESULT_OK, intent);
                finish();
            }
        });
    }

}