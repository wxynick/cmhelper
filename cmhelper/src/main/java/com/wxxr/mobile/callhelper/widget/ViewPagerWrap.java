/**
 * 
 */
package com.wxxr.mobile.callhelper.widget;


import java.util.ArrayList;

import com.wxxr.mobile.callhelper.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * @author fudapeng
 *
 */
public class ViewPagerWrap extends ViewPager {
	
	final int[] navigation_res = { R.drawable.welcomebj_01, R.drawable.welcomebj_02, R.drawable.welcomebj_03};
	
	private ArrayList<View> pageViews;
	
	public ViewPagerWrap(Context context) {
		super(context);
		constructView(context);
	}
	
	
	public ViewPagerWrap(Context context, AttributeSet attrs) {
		super(context,attrs);
		constructView(context);
	}
	
	
	public void constructView(Context context){
		pageViews = new ArrayList<View>(); 
		for(int i=0;i<navigation_res.length;i++){
			LinearLayout out=new LinearLayout(context);
			ImageView a = new ImageView(context);
			a.setBackgroundResource(navigation_res[i]);
			LinearLayout.LayoutParams     params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		    out.addView(a, params);
		    pageViews.add(out);  
		}
		
		ImaAdapter adapter = new ImaAdapter(navigation_res, 0, false, true);
		setAdapter(adapter);
		setCurrentItem(0);
		setTag(navigation_res.length);
	}
	
	
	
	public class ImaAdapter extends PagerAdapter  {
		private int res[];

		ImaAdapter(int[] pres, int rowindex, boolean ploop, boolean pisnav) {
			res = pres;
		}

		@Override
		public int getCount() {
			return res.length;
		}

		@Override    
		      public void destroyItem(View arg0, int arg1, Object arg2) {    
		          ((ViewPager) arg0).removeView(pageViews.get(arg1));    
		      }    
		  
		      @Override    
		      public Object instantiateItem(View arg0, int arg1) {    
		          ((ViewPager) arg0).addView(pageViews.get(arg1));    
		          return pageViews.get(arg1);    
		      }  
		

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
	}
	
	
	
}
