package com.tellyourdream.tellyourdream;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tellyourdream.tellyourdream.profileFragment;

class PagerViewAdapter extends FragmentPagerAdapter {

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
        case 0:
            profileFragment profileFragment = new profileFragment();
            return profileFragment;
        case 1:
            DreamsFragment dreamsFragment = new DreamsFragment();
            return dreamsFragment;
        case 2:
            NotificationFragment notificationFragment = new NotificationFragment();
            return notificationFragment;
        default:
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
