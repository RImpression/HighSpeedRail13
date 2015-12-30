package com.rail.myview;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 稻香下 on 2015/11/10.
 */
public class MyLinearLayout extends ViewGroup{
    private  int mCellWidth;
    private  int mCellHeight;
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyLinearLayout(Context context) {
        super(context);
    }
    public void setmCellWidth(int w) {
        mCellWidth = w;
        requestLayout();
    }

    public void setmCellHeight(int h) {
        mCellHeight = h;
        requestLayout();
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int cellWidth = mCellWidth;
        int cellHeight = mCellHeight;
        int columns = (r - l) / cellWidth;
        if (columns < 0) {
            columns = 1;
        }
        int x = 0;
        int y = 0;
        int i = 0;
        int count = getChildCount();
        for (int j = 0; j < count; j++) {
            final View childView = getChildAt(j);
            int w = childView.getMeasuredWidth();
            int h = childView.getMeasuredHeight();
            int left = x + ((cellWidth - w) / 2);
            int top = y + ((cellHeight - h) / 2);
            childView.layout(left, top, left + w, top + h);
            if (i >= (columns - 1)) {
                i = 0;
                x = 0;
                y += cellHeight;
            } else {
                i++;
                x += cellWidth;
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cellWidthSpec = MeasureSpec.makeMeasureSpec(mCellWidth, MeasureSpec.EXACTLY);
        int cellHeightSpec = MeasureSpec.makeMeasureSpec(mCellHeight, MeasureSpec.EXACTLY);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            childView.measure(cellWidthSpec, cellHeightSpec);
        }
        int num;
        if(count>5){
            num=2;
        }else{
            num=1;
        }
        setMeasuredDimension(resolveSize(mCellWidth * count, widthMeasureSpec),
                resolveSize(mCellHeight * num, heightMeasureSpec));
    }
}
