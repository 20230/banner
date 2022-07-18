package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ContentPagerAdapter  extends FragmentStateAdapter {
    private List<ContentFragment> datas;

    public ContentPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<ContentFragment> datas) {
        super(fragmentActivity);
        this.datas = datas;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}

