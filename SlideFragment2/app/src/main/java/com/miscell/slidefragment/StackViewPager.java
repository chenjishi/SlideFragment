package com.miscell.slidefragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jishichen on 2017/6/27.
 */
public class StackViewPager extends ViewPager {

    private Drawable mShadowDrawable;

    private int mShadowWidth;

    public StackViewPager(Context context) {
        super(context);
        initStackViewPager();
    }

    public StackViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStackViewPager();
    }

    private void initStackViewPager() {
        if (Build.VERSION.SDK_INT >= 11) {
            setPageTransformer(false, new StackPageTransformer());
            mShadowDrawable = getResources().getDrawable(R.drawable.sliding_back_shadow);
            mShadowWidth = mShadowDrawable.getIntrinsicWidth();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) return;

        int right = getCurrentItem() * w;
        int left = right - mShadowWidth;

        mShadowDrawable.setBounds(left, 0, right, h);
        mShadowDrawable.draw(canvas);
    }

    @SuppressWarnings("NewApi")
    private class StackPageTransformer implements PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();

            if (position >= -1 && position <= 0) {
                page.setTranslationX(-position * pageWidth * 2 / 3);
            } else if (position > 0 && position <= 1) {
                page.setTranslationX(0);
            }
        }
    }
}
