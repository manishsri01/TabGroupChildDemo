package com.manish.tabdemo;


import com.manish.util.AnimatedActivity;

import android.content.Intent;
import android.os.Bundle;

public class ContactGroupActivity extends AnimatedActivity
{
	public static ContactGroupActivity ContactGroupStack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        ContactGroupStack = ContactGroupActivity.this;
        
       startChildActivity("ContactGroupActivity", new Intent(this, ContactActivity.class));
    }
} 