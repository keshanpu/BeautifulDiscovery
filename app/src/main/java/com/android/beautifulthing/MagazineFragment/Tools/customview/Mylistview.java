package com.android.beautifulthing.MagazineFragment.Tools.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by keshanpu on 16/8/18.
 */
public class Mylistview extends ListView {
    public Mylistview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public Mylistview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Mylistview(Context context) {
        this(context, null);
    }

    /**
     * 重写此方法让listview或者gridview匹配scrollview的高度
     *
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
