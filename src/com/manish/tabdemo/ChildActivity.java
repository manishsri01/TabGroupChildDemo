package com.manish.tabdemo;

import android.app.Activity;
import android.os.Bundle;

public class ChildActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_child);
	}
}