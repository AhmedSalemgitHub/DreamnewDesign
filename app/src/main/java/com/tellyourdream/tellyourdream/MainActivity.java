package com.tellyourdream.tellyourdream;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView mProfileLabel;
    private TextView mDreamsLabel;
    private TextView mNotificationLabel;

    private ViewPager mMainPage;

    private PagerViewAdapter mPagerViewAdapter;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null)
        {
            Intent toLog = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(toLog);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        /*Declare the views in the main page*/

        mProfileLabel = (TextView) findViewById(R.id.profile_label_textView);
        mDreamsLabel = (TextView) findViewById(R.id.dream_label_textView);
        mNotificationLabel = (TextView) findViewById(R.id.notification_label_textView);

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

    private void changeTabes(int i) {
        if (i == 0)
        {
            mProfileLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            mProfileLabel.setTextSize(24);

            mDreamsLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mDreamsLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(16);
        }
        else if (i == 1)
        {
            mProfileLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mDreamsLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            mDreamsLabel.setTextSize(24);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(16);
        }
        else if ( i == 2)
        {
            mProfileLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mDreamsLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            mDreamsLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            mNotificationLabel.setTextSize(26);
        }
    }
}
