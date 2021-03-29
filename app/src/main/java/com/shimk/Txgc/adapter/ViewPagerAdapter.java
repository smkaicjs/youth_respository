package com.shimk.Txgc.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {


    private List<Fragment> mFragnebtList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        if (null==mFragnebtList){
            mFragnebtList = new ArrayList<>();
        }
    }


    public void addFragment(int index,Fragment fragment){
        mFragnebtList.add(index,fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return mFragnebtList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragnebtList.size();
    }


}
