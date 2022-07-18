package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    List<ContentFragment> datas;

    ViewGroup group;
    ImageView[] imageViews;
    ImageView imageView;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.vp);
        group=findViewById(R.id.viewGroup);
        initPointer();

        datas = new ArrayList<>();
        datas.add(new ContentFragment(R.drawable.t1));
        datas.add(new ContentFragment(R.drawable.t2));
        datas.add(new ContentFragment(R.drawable.t3));

        ContentPagerAdapter adapter= new ContentPagerAdapter(this,datas);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[position].setBackgroundResource(R.mipmap.page_indicator_focused);
                    if (position != i) {
                        imageViews[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                while(isRunning){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int cur_item=viewPager.getCurrentItem();
                            cur_item=(cur_item+1)%imageViews.length;
                            viewPager.setCurrentItem(cur_item);
                        }
                    });
                }
            }
        }).start();
    }
    
    public void initPointer() {
        imageViews = new ImageView[3];
        for (int i = 0; i < imageViews.length; i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayoutCompat.LayoutParams(85, 85));
            imageView.setPadding(20, 0, 20, 0);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            }
            group.addView(imageViews[i]);
        }
    }
}