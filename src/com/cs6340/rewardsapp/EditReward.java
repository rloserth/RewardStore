package com.cs6340.rewardsapp;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditReward extends Activity{

	EditText rewardName;
	EditText accountNum;
	EditText category;
	EditText notes;

	DBTools dbTools = new DBTools(this);

	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_reward);
		rewardName = (EditText) findViewById(R.id.rewardName);
		accountNum = (EditText) findViewById(R.id.accountNum);
		category = (EditText) findViewById(R.id.category);
		notes = (EditText) findViewById(R.id.notes);

		Intent theIntent = getIntent();

		String rewardId = theIntent.getStringExtra("rewardId");

		HashMap<String, String> rewardList = dbTools.getRewardInfo(rewardId);

		if(rewardList.size() != 0){

			rewardName.setText(rewardList.get("rewardName"));
			accountNum.setText(rewardList.get("accountNum"));
			category.setText(rewardList.get("category"));
			notes.setText(rewardList.get("notes"));

		}
	}

	public void editReward(View view){

		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		rewardName = (EditText) findViewById(R.id.rewardName);
		accountNum = (EditText) findViewById(R.id.accountNum);
		category = (EditText) findViewById(R.id.category);
		notes = (EditText) findViewById(R.id.notes);

		Intent theIntent = getIntent();

		String rewardId = theIntent.getStringExtra("rewardId");

		queryValuesMap.put("rewardId", rewardId);
		queryValuesMap.put("rewardName", rewardName.getText().toString());
		queryValuesMap.put("accountNum", accountNum.getText().toString());
		queryValuesMap.put("category", category.getText().toString());
		queryValuesMap.put("notes", notes.getText().toString());

		dbTools.updateReward(queryValuesMap);
		this.callMainActivity(view);

	}

	public void removeReward(final View view){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditReward.this);

		// Setting Dialog Message
		alertDialog.setMessage("Are you sure you want delete this reward?");


		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				Intent theIntent = getIntent();
				String rewardId = theIntent.getStringExtra("rewardId");
				dbTools.deleteReward(rewardId);
				callMainActivity(view);
			}
		});

		alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	public void callMainActivity(View view){
		Intent objIntent = new Intent(getApplication(), MainActivity.class);
		startActivity(objIntent);

	}

}






