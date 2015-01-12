package com.cs6340.rewardsapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper {
	
	public DBTools(Context applicationContext){
		
		super(applicationContext, "rewardbook.db", null, 1);
		/* Just adding a comment for testing */
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		String query = "CREATE TABLE rewards ( rewardId INTEGER PRIMARY KEY, rewardName TEXT, " +
		"accountNum TEXT, category TEXT, notes TEXT)";
		
		database.execSQL(query);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		
		String query = "DROP TABLE IF EXISTS rewards";
		
		database.execSQL(query);
		onCreate(database);
		
	}
	
	public void insertReward(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("rewardName", queryValues.get("rewardName"));
		values.put("accountNum", queryValues.get("accountNum"));
		values.put("category", queryValues.get("category"));
		values.put("notes", queryValues.get("notes"));
		
		database.insert("rewards", null, values);
		
		database.close();
		
	}
	
	public int updateReward(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("rewardName", queryValues.get("rewardName"));
		values.put("accountNum", queryValues.get("accountNum"));
		values.put("category", queryValues.get("category"));
		values.put("notes", queryValues.get("notes"));
		
		return database.update("rewards", values, 
				"rewardId" + " = ?", new String[] {queryValues.get("rewardId") });
		
	}
	
	public void deleteReward(String id){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		String deleteQuery = "DELETE FROM rewards WHERE rewardId='" + id + "'";
		
		database.execSQL(deleteQuery);
		
	}
	
	public ArrayList<HashMap<String, String>> getAllRewards(){
		
		ArrayList<HashMap<String, String>> rewardsArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT * FROM rewards ORDER BY accountNum";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				HashMap<String, String> rewardMap = new HashMap<String, String>();
				
				rewardMap.put("rewardId", cursor.getString(0));
				rewardMap.put("rewardName", cursor.getString(1));
				rewardMap.put("accountNum", cursor.getString(2));
				rewardMap.put("category", cursor.getString(3));
				rewardMap.put("notes", cursor.getString(4));
				
				rewardsArrayList.add(rewardMap);
				
			} while(cursor.moveToNext());
			
		}
		
		return rewardsArrayList;
		
	}
	
	public HashMap<String, String> getRewardInfo(String id){
		
		HashMap<String, String> rewardMap = new HashMap<String, String>();
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM rewards WHERE rewardId='" + id + "'";
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				rewardMap.put("rewardId", cursor.getString(0));
				rewardMap.put("rewardName", cursor.getString(1));
				rewardMap.put("accountNum", cursor.getString(2));
				rewardMap.put("category", cursor.getString(3));
				rewardMap.put("notes", cursor.getString(4));

				
			} while(cursor.moveToNext());
			
		}
		
		return rewardMap;
		
	}
	
}








