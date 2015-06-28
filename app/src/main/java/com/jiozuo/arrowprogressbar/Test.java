package com.jiozuo.arrowprogressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Weity on 2015/6/27.
 * test
 */
public class Test extends ListView {
    public Test(Context context) {
        super(context);
    }

    public Test(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
