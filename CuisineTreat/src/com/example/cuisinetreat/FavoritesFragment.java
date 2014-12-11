package com.example.cuisinetreat;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class FavoritesFragment extends Fragment 
{
	static DataHandler handler=null;
	static String loggedUser;
	static String Email;
	static Context contextVar;
    
   @SuppressLint("ValidFragment")
public FavoritesFragment(String loggedInUser, String Email,DataHandler handler,Context contextVar)
   {
	   this.loggedUser=loggedInUser;
	   this.Email=Email;
	   this.handler=handler;
	   this.contextVar=contextVar;
   }
    
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.activity_signup_form, container, false);
         
       if(loggedUser=="" || loggedUser.equals("Guest"))
		{
			Toast.makeText(contextVar, "Login to see", Toast.LENGTH_SHORT).show();
			//return;
		}
       else 
    	   {
    	   
    	   handler.open();
    	   
		Cursor c = handler.getFavorites(Email);
		if (c.moveToFirst()) {
			String list="";
	        do {
	            list=list+c.getString(1)+" "+c.getString(2);
	            
	        } while (c.moveToNext());
	        Toast.makeText(contextVar,list , Toast.LENGTH_LONG).show();
		}
		else
			Toast.makeText(contextVar,"No favorites Added" , Toast.LENGTH_SHORT).show();
		handler.close();
   }
       return rootView;
   }
}