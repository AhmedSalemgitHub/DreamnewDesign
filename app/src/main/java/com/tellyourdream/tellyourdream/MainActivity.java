package com.tellyourdream.tellyourdream;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView mProfileLabel;
    private TextView mDreamsLabel;
    private TextView mNotificationLabel;
    private Button mAddDreamButton;

    private ViewPager mMainPage;

    private PagerViewAdapter mPagerViewAdapter;

    private FirebaseAuth mAuth;
    public static String prefname, prefage, prefemail, prefgender, prefmarital;
    String mAuthemail;
    private String TAG = "tester";

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuthemail = currentUser.getEmail();

        if (currentUser == null)
        {
            Intent toLog = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(toLog);
            finish();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
        SharedPreferences pref = this.getSharedPreferences(mAuthemail, MODE_PRIVATE);
        prefname = pref.getString("name", "error");
        prefemail = pref.getString("email", "error");
        prefage = pref.getString("age", "error");
        prefmarital = pref.getString("marital", "error");
        prefgender = pref.getString("gender", "error");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        /*Declare the views in the main page*/

        mProfileLabel = (TextView) findViewById(R.id.profile_label_textView);
        mDreamsLabel = (TextView) findViewById(R.id.dream_label_textView);
        mNotificationLabel = (TextView) findViewById(R.id.notification_label_textView);

        mAddDreamButton = (Button) findViewById(R.id.btn_add_dream);

        mAddDreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addDream.class);
                startActivity(intent);
            }
        });

        mMainPage = (ViewPager) findViewById(R.id.mainPager);
        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mMainPage.setAdapter(mPagerViewAdapter);

        /*click listener for the custom bar*/

        mProfileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPage.setCurrentItem(0);
            }
        });

        mDreamsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPage.setCurrentItem(1);
            }
        });

        mNotificationLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { mMainPage.setCurrentItem(2);
            }
        });

        mMainPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                changeTabes(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
//        mAuth = FirebaseAuth.getInstance();
//        mAuth.signOut();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
//        mAuth = FirebaseAuth.getInstance();
//        mAuth.signOut();
    }

    private void changeTabes(int i) {
        if (i == 0)
        {
            mProfileLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            mProfileLabel.setTextSize(20);

            mDreamsLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mDreamsLabel.setTextSize(14);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(14);
        }
        else if (i == 1)
        {
            mProfileLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(14);

            mDreamsLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            mDreamsLabel.setTextSize(20);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(14);
        }
        else if ( i == 2)
        {
            mProfileLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(14);

            mDreamsLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mDreamsLabel.setTextSize(14);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            mNotificationLabel.setTextSize(20);
        }
    }
}
