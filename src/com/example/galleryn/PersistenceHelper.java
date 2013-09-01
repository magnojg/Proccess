package com.example.galleryn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "LawyerTasks";
	public static final int VERSION = 1;
	
	private static PersistenceHelper instance;
	
	public PersistenceHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}
	
	public static PersistenceHelper getInstance(Context context){
		if (instance == null){
			instance = new PersistenceHelper(context);
		}
		
		return instance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(UserDAO.SCRIPT_CREATE_TABLE_USERS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(UserDAO.SCRIPT_DELETE_TABLE);
		onCreate(db);
	}

}
