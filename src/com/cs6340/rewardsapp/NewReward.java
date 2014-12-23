package com.cs6340.rewardsapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;


public class NewReward extends Activity{

	// The EditText objects

	EditText rewardName;
	EditText accountNum;
	EditText category;
	EditText notes;

	DBTools dbTools = new DBTools(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {

		// Get saved data if there is any

		super.onCreate(savedInstanceState);

		// Designate that add_new_reward.xml is the interface used
		
		setContentView(R.layout.add_new_reward);

		// Initialize the EditText objects
		
		rewardName = (EditText) findViewById(R.id.rewardName);
		accountNum = (EditText) findViewById(R.id.accountNum);
		category = (EditText) findViewById(R.id.category);
		notes = (EditText) findViewById(R.id.notes);

	}
	public void addNewReward(View view) {
		
		// Will hold the HashMap of values 
		
		HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

		// Get the values from the EditText boxes
		
		queryValuesMap.put("rewardName", rewardName.getText().toString());
		queryValuesMap.put("accountNum", accountNum.getText().toString());
		queryValuesMap.put("pnoneNumber", category.getText().toString());
		queryValuesMap.put("notes", notes.getText().toString());

		// Call for the HashMap to be added to the database
		
		dbTools.insertReward(queryValuesMap);
		
		// Call for MainActivity to execute
		
		this.callMainActivity(view);
	}
	public void callMainActivity(View view) {
		Intent theIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(theIntent);
	}	
}
