package com.manish.tabdemo;


import com.manish.util.AnimatedActivity;

import android.content.Intent;
import android.os.Bundle;

public class HomeGroupActivity extends AnimatedActivity
{
	public static HomeGroupActivity HomeGroupStack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        HomeGroupStack = HomeGroupActivity.this;
        
       startChildActivity("HomeGroupActivity", new Intent(this, HomeActivity.class));
    }
} 