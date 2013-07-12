package com.manish.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ViewAnimator;

public class AnimatedActivity extends ActivityGroup {

    public Stack<String> mIdList;
    public Map<String, Intent> mIntents;
    private LocalActivityManager mActivityManager;
    public ViewAnimator mAnimator;
    private int mSerial;
    private int mLongAnimTime;
    String strClassName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        mIdList = new Stack<String>();
        mIntents = new HashMap<String, Intent>();
        mAnimator = new ViewAnimator(this);
        setContentView(mAnimator);
        mActivityManager = getLocalActivityManager();
        mLongAnimTime = 260;        
    }
    
    @Override
    public void startActivity(Intent intent) 
    {
        System.out.println("starting " + intent);
        String id = "id" + mSerial++;
        mIdList.push(id);
        mIntents.put(id, intent);
        System.out.println("adding " + intent);

        View view = mActivityManager.startActivity(id, intent).getDecorView();
        int size = mIdList.size();
        mAnimator.setInAnimation(size > 1? getAnimation(intent, true, true) : null);
        mAnimator.setOutAnimation(size > 1? getAnimation(intent, true, false) : null);
        mAnimator.addView(view);
        mAnimator.setDisplayedChild(mIdList.size() - 1);
    }
    
    public void startChildActivity(String id1,Intent intent)
    {
    	System.out.println("starting " + intent);
    	String id = "id" + mSerial++;
        mIdList.push(id);
        mIntents.put(id, intent);
        System.out.println("adding " + intent);

        View view = mActivityManager.startActivity(id, intent).getDecorView();
        
        int size = mIdList.size();
        mAnimator.setInAnimation(size > 1? getAnimation(intent, true, true) : null);
        mAnimator.setOutAnimation(size > 1? getAnimation(intent, true, false) : null);
        mAnimator.addView(view);
        mAnimator.setDisplayedChild(mIdList.size() - 1);
    }
    public void finishChildActivity(Intent intent1)
    {
    	int size = mIdList.size();
        if (size > 1) 
        {
            String topId = mIdList.pop();
            Intent intent = mIntents.get(topId);
            mAnimator.setInAnimation(getAnimation(intent, false, true));
            Animation outAnim = getAnimation(intent, false, false);
            outAnim.setAnimationListener(new BackAnimationListener(topId));
            mAnimator.setOutAnimation(outAnim);
            mAnimator.setDisplayedChild(mIdList.size() - 1);
            mAnimator.invalidate();
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) 
        {        	
        	int size = mIdList.size();
            if (size > 1) 
            {
            	try
            	{
            		System.gc();
            	}
            	catch (Exception e) 
            	{
    				
    			}
            	
            	String topId = mIdList.pop();
                Intent intent = mIntents.get(topId);
                mAnimator.setInAnimation(getAnimation(intent, false, true));
                Animation outAnim = getAnimation(intent, false, false);
                outAnim.setAnimationListener(new BackAnimationListener(topId));
                mAnimator.setOutAnimation(outAnim);
                mAnimator.setDisplayedChild(mIdList.size() - 1);
                mAnimator.invalidate();
                return true;
            }
            else
            {
            	//code to handle last page
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Returns page transition animation.
     *
     * @param intent intent to be used
     * @param forward true if starting new intent
     * @param inAnimation see {@link ViewAnimator#setInAnimation(Animation)}
     */
    protected Animation getAnimation(Intent intent, boolean forward, boolean inAnimation) 
    {
        Animation animation;
        if (forward) 
        {
            if (inAnimation) 
            {
                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
            } 
            else 
            {
                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
            }
        } 
        else 
        {
            if (inAnimation) 
            {
                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
            } 
            else 
            {
                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
            }
        }
        animation.setDuration(mLongAnimTime);
        return animation;
    }

    class BackAnimationListener implements AnimationListener 
    {
        String mId;
        public BackAnimationListener(String id) 
        {
            mId = id;
        }
        
        public void onAnimationEnd(Animation animation) 
        {            
            strClassName =  mIntents.get(mId).getComponent().getClassName();
            
            mIntents.remove(mId);
            View view = mActivityManager.destroyActivity(mId, true).getDecorView();
            mAnimator.removeView(view);
            

                                    
                		
    		
        }
    
        public void onAnimationRepeat(Animation animation) 
        {
        
        }
    
        public void onAnimationStart(Animation animation) 
        {
        	
        }
    }
}
