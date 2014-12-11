package com.example.cuisinetreat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHandler {

	public static final String EMAIL= "emailField";
	public static final String PASSWORD="password_field";
	public static final String FIRSTNAME="firstNameSign_up";
	public static final String LASTNAME="lastNameSign_up";
	public static final String GENDER="radioGroup2";
	public static final String FOODPREF="radioGroup1";
	public static final String POSITION="position";
	
	
	
	public static final String FAV_LIST_TABLE="Fav_List_Table";
	public static final String TABLE_NAME="Login_Table";
	public static final String DATABASE_NAME= "Cusisne_Treat";
	public static final int DATABASE_VERSION=1;
	
	public static final String CREATE_FAV_LIST_TABLE =
			"create table Fav_List_Table (emailField text,RecipieId text,position text, PRIMARY KEY (emailField, RecipieId) );";
	
	
	public static final String CREATE_TABLE= 
	"create table Login_Table( emailField PRIMARY KEY,password_field NOT NULL,firstNameSign_up text NOT NULL ,lastNameSign_up text,radioGroup2 text,radioGroup1 text);";
	
	public static final String RATING_TABLE="Rating_Table";
	public static final String RECIPIE_ID="RecipieId";
	public static final String RATING="rating";
	public static final String CREATE_RATING_TABLE = 
	"create table Rating_Table (emailField text, RecipieId text,rating text, PRIMARY KEY (emailField, RecipieId) );";
	//for future reference ,FOREIGN KEY (emailField) REFERENCES Login_Table(emailField)
	//public static final String SELECT_USER="select"

		DataBaseHelper dbHelper;
		Context ctx;
		SQLiteDatabase db;
		public DataHandler(Context c) {
			ctx=c;
			dbHelper= new DataBaseHelper(ctx);
		}
	private static class DataBaseHelper extends SQLiteOpenHelper {

		public DataBaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
			db.execSQL(CREATE_TABLE);
			db.execSQL(CREATE_RATING_TABLE);
			db.execSQL(CREATE_FAV_LIST_TABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//db.execSQL("DROP TABLE IF EXISTS Login_Table");
			onCreate(db);
			
		}
		
	}
	public DataHandler open()
	{
		db=dbHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		dbHelper.close();
	}
	public long insertData(String userEmail, String userPassword, String userFirstName,
			String userLastName,String userGender, String userFoodPreference) {
		ContentValues content= new ContentValues();
		content.put(EMAIL, userEmail);
		content.put(PASSWORD, userPassword);
		content.put(FIRSTNAME, userFirstName);
		content.put(LASTNAME, userLastName);
		content.put(GENDER, userGender);
		content.put(FOODPREF, userFoodPreference);
		
		return db.insertOrThrow(TABLE_NAME, null, content);
		
	}
	public Cursor isUniqueEmail(String email){
		return db.query(TABLE_NAME,new String[] {EMAIL},EMAIL +"=?",new String[] {email},null,null,null,null);
	}
	
	public Cursor isExists(String email) {
		return db.query(TABLE_NAME,new String[] {EMAIL,PASSWORD},EMAIL +"=?",new String[] {email},null,null,null,null);
	}
	public Cursor returnData() {
		
		return db.query(TABLE_NAME, new String[] {EMAIL,PASSWORD}, null, null, null,null,null);
	}
	
public Cursor returnUserName(String email) {
		
	return db.query(TABLE_NAME,new String[] {FIRSTNAME},EMAIL +"=?",new String[] {email},null,null,null,null);
	}

public void resetPassword(String password,String email) {
	
	//db.query(TABLE_NAME,new String[] {FIRSTNAME},EMAIL +"=?",new String[] {email},null,null,null,null);
	//db.update(TABLE_NAME, password, EMAIL +"=?",new String[] {email}, whereArgs)
	//db.execSQL("update "+TABLE_NAME+" set password_field="+password+" where emailField="+email);
	db.execSQL("update " + TABLE_NAME+ " set password_field='"+password+"' where emailField='"+email+"'");
	//return 1;
	}
//===============Methods for Rating table=========	
public void insertInToRatingTable(String email,String recipieID,String Rating) {
	
	ContentValues content= new ContentValues();
	content.put(EMAIL, email);
	content.put(RECIPIE_ID, recipieID);
	content.put(RATING, Rating);
	db.insertOrThrow(RATING_TABLE, null, content);
	
}
//=========does the entry exists in rating table.. if so then update it
public Cursor isExistsRating(String email) {
	
	return db.query(RATING_TABLE,new String[] {EMAIL,RECIPIE_ID,RATING},EMAIL +"=?" ,new String[] {email},null,null,null,null);
	
	
}

public void updateRating(String email,String recipieID,String Rating) {
	db.execSQL("update " + RATING_TABLE+ " set RATING='"+Rating+"' where emailField='"+email+"' and RecipieId= '"+recipieID+"'" );
	}

//=================FAVORITES LIS
public void addtoFavorites(String email,String recipieID,String position) {
	ContentValues content= new ContentValues();
	content.put(EMAIL, email);
	content.put(RECIPIE_ID, recipieID);
	content.put(POSITION, position);
	db.insertOrThrow(FAV_LIST_TABLE, null, content);
	
}
public void deleteFavorite(String email,String recipieID) {
	
	db.execSQL("delete from  " + FAV_LIST_TABLE+ " where emailField='"+email+"' and RecipieId= '"+recipieID+"'");
	//db.delete(FAV_LIST_TABLE,emailField='5' and column2 like '3'", null);
}

public Cursor getFavorites(String email) {
	return db.query(FAV_LIST_TABLE,new String[] {EMAIL,RECIPIE_ID,POSITION},EMAIL +"=?" ,new String[] {email},null,null,null,null);
	
}
	
}