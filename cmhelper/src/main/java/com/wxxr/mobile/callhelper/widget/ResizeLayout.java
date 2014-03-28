package com.wxxr.mobile.callhelper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
/**
 * 
 * 自定义的view，为了监听高度的变化，来判断键盘的显示和隐藏
 *
 * @since 1.0
 */
public class ResizeLayout extends LinearLayout{  
    private OnResizeListener mListener;
    private static final int BIGGER = 1;
    private static final int SMALLER = 2;
    private static final int MSG_RESIZE = 1;
    public interface OnResizeListener {
        void OnResize(int w, int h, int oldw, int oldh);
    }
     
    public void setOnResizeListener(OnResizeListener l) {
        mListener = l;
    }
     
    public ResizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
     
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {     
        super.onSizeChanged(w, h, oldw, oldh);
         
        if (mListener != null) {
            mListener.OnResize(w, h, oldw, oldh);
        }
    }
} 