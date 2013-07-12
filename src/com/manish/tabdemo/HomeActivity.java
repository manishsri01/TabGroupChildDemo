package com.manish.tabdemo;

import com.manish.util.AnimatedActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity{
	Button buttonNext;
	private boolean isClicked=true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		buttonNext=(Button)findViewById(R.id.button_nxt);
		buttonNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Log.e("test", "test");
				AnimatedActivity pActivity = (AnimatedActivity)HomeActivity.this.getParent();                    
		            Intent intent = new Intent(HomeActivity.this, ChildActivity.class);
		            pActivity.startChildActivity("home_screen", intent);
	            }
		});
	}
	@Override
	public void onBackPressed() {
		System.out.println("***back*");
		HomeActivity.super.onBackPressed();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		System.out.println("****event****"+event+"****"+keyCode);
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{

               finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}