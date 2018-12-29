package com.example.adam.fragmentactivitytest;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
    public static int REQUEST_ACTIVITY_RED =  100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContent, new FragmentMain()).commitAllowingStateLoss();


        //設列表
        setBehindContentView(R.layout.list_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentPink()).commitAllowingStateLoss();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                MyFragment fragment = Utility.getBackLastFragment(MainActivity.this);
                if(fragment != null){
                    fragment.onResume();
                    fragment.refreshChildFragment();
                    Log.d("adam", "onBackStackChanged, "+fragment.getClass().getSimpleName()+", last: "+Utility.lastFragment.getClass().getSimpleName()+", replace: "+Utility.replace);
                }
            }
        });
        SlidingMenu sm = getSlidingMenu();
        sm.setOnOpenListener(new SlidingMenu.OnOpenListener() {//完整打開側邊攔頁, 在onOpened之前
            @Override
            public void onOpen() {
                MyFragment fragment = (MyFragment)getSupportFragmentManager().findFragmentById(R.id.frame);
                fragment.onResume();
                Log.d("adam", "onOpen, "+fragment.getClass().getSimpleName());
            }
        });
        sm.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {//有拉開側邊攔頁就算
            @Override
            public void onOpened() {
                MyFragment fragment = (MyFragment)getSupportFragmentManager().findFragmentById(R.id.frame);
                Log.d("adam", "onOpened, "+fragment.getClass().getSimpleName());
            }
        });
        sm.setOnCloseListener(new SlidingMenu.OnCloseListener() {
            @Override
            public void onClose() {
                MyFragment fragment = (MyFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContent);
                fragment.onResume();
                Log.d("adam", "onClose, "+fragment.getClass().getSimpleName());
            }
        });
        sm.setOnClosedListener(new SlidingMenu.OnClosedListener() {
            @Override
            public void onClosed() {
                MyFragment fragment = (MyFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContent);
                Log.d("adam", "onClosed, "+fragment.getClass().getSimpleName());
            }
        });
        ((Button)findViewById(R.id.buttonDoublePress)).setText((Utility.isDoubleBackPressedLeaveApp?"enable":"disable")+" double back press leave app");

        findViewById(R.id.buttonDoublePress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utility.isDoubleBackPressedLeaveApp){
                    Utility.isDoubleBackPressedLeaveApp = false;
                }else {
                    Utility.isDoubleBackPressedLeaveApp = true;
                }
                ((Button)findViewById(R.id.buttonDoublePress)).setText((Utility.isDoubleBackPressedLeaveApp?"enable":"disable")+" double back press leave app");
            }
        });
        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(ActivityRed.class, new Person("Adam"));
            }
        });
    }
    private void switchActivity(Class c, Parcelable p){
        Intent intent = new Intent(this, c);
        intent.putExtra("data",p);
        startActivityForResult(intent, REQUEST_ACTIVITY_RED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("adam", "onActivityResult, requestCode: "+requestCode+", resultCode: "+resultCode+", data: "+data);
        if (requestCode == REQUEST_ACTIVITY_RED) {
            if(resultCode == RESULT_OK){
                Person person = data.getParcelableExtra("data");
                Log.d("adam", "onActivityResult, ok, person: "+person.name);
            }
        }
    }

    static class Person implements Parcelable {
        public Person(String mName) {
            name = mName;
        }

        String name;

        protected Person(Parcel in) {
            name = in.readString();
        }

        public final static Creator<Person> CREATOR = new Creator<Person>() {
            @Override
            public Person createFromParcel(Parcel in) {
                return new Person(in);
            }

            @Override
            public Person[] newArray(int size) {
                return new Person[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("adam", "onStart, "+getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("adam", "onResume, "+getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("adam", "onPause, "+getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("adam", "onStop, "+getClass().getSimpleName());
    }

    @Override
    public void onBackPressed() {
        if(Utility.isDoubleBackPressedLeaveApp){
            Utility.backPressedLeaveAppCount++;

            if(getSupportFragmentManager().getBackStackEntryCount() == 0){//到最後一頁
                if(Utility.backPressedLeaveAppCount > 1){//連點兩次擇離開
                    Utility.backPressedLeaveAppCount = 0;
                    finish();
                }else {//否則跳alert告知要連點兩次
                    new Thread(new Runnable() {//500毫秒不再次backpress則count清為0
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                                Utility.backPressedLeaveAppCount = 0;
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    Utility.showToast(this, "double press to leave", Toast.LENGTH_SHORT);
                }
            }else{//不在最後一頁則照常back
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }
    }
}