package com.android.beautifulthing.MagazineFragment.Tools.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by keshanpu on 16/8/18.
 */
public class Mygridview extends GridView {
    public Mygridview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Mygridview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Mygridview(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
