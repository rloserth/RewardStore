package com.cs6340.rewardsapp;

import java.util.ArrayList;
import java.util.HashMap;

import com.cs6340.rewardsapp.DBTools;
import com.cs6340.rewardsapp.NewReward;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;

import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MainActivity extends ListActivity {

	// The Intent is used to issue that an operation should
	// be performed

	Intent intent;
	TextView rewardId;

	// The object that allows me to manipulate the database

	DBTools dbTools = new DBTools(this);

	// Called when the Activity is first called

	protected void onCreate(Bundle savedInstanceState) {
		// Get saved data if there is any

		super.onCreate(savedInstanceState);

		// Designate that edit_reward.xml is the interface used
		// is activity_main.xml
		
		setContentView(R.layout.activity_main);

		// Gets all the data from the database and stores it
		// in an ArrayList

		ArrayList<HashMap<String, String>> rewardList =  dbTools.getAllRewards();

		// Check to make sure there are rewards to display

		if(rewardList.size()!=0) {
			
			// Get the ListView and assign an event handler to it
			
			ListView listView = getListView();
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					// When an item is clicked get the TextView
					// with a matching checkId
					
					rewardId = (TextView) view.findViewById(R.id.rewardId);
					
					// Convert that rewardId into a String
					
					String rewardIdValue = rewardId.getText().toString();	
					
					// Signals an intention to do something
					// getApplication() returns the application that owns
					// this activity
					
					Intent  theIndent = new Intent(getApplication(),EditReward.class);
					
					// Put additional data in for EditReward to use
					
					theIndent.putExtra("rewardId", rewardIdValue); 
					
					// Calls for EditReward
					
					startActivity(theIndent); 
				}
			}); 
			
			// A list adapter is used bridge between a ListView and
			// the ListViews data
			
			// The SimpleAdapter connects the data in an ArrayList
			// to the XML file
			
			// First we pass in a Context to provide information needed
			// about the application
			// The ArrayList of data is next followed by the xml resource
			// Then we have the names of the data in String format and
			// their specific resource ids
			
			ListAdapter adapter = new SimpleAdapter( MainActivity.this,rewardList, R.layout.reward_entry, new String[] { "rewardId","accountNum", "rewardName"}, new int[] {R.id.rewardId, R.id.accountNum, R.id.rewardName});
			
			// setListAdapter provides the Cursor for the ListView
			// The Cursor provides access to the database data
			
			setListAdapter(adapter);
		}
	}
	
	// When showAddReward is called with a click the Activity 
	// NewReward is called
	
	public void showAddReward(View view) {
		Intent theIntent = new Intent(getApplicationContext(), NewReward.class);
		startActivity(theIntent);
	}
}
