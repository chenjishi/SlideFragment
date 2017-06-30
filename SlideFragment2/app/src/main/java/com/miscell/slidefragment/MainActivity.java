package com.miscell.slidefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StackViewPager viewPager = (StackViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));
    }

    private class ColorPagerAdapter extends FragmentPagerAdapter {

        public ColorPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt("index", position);

            return Fragment.instantiate(MainActivity.this, ColorFragment.class.getName(), args);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
