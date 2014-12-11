package com.example.cuisinetreat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressLint("NewApi")
public class SignUpForm extends Activity {
EditText emailField; String userEmail;
EditText passwordField; String userPassword;
EditText firstNameField; String userFirstName;
EditText lastNameField; String userLastName;
RadioButton genderMaleField; 
RadioButton genderFemaleField;
RadioButton vegPreferenceField;
RadioButton nonVegPreferenceField;
RadioButton bothPreferenceField; 
String userGender;
String  userFoodPreference;
Button doneButton;
DataHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup_form);
		emailField=(EditText)findViewById(R.id.emailField);
		passwordField=(EditText)findViewById(R.id.password_field);
		firstNameField=(EditText)findViewById(R.id.firstNameSign_up);
		lastNameField=(EditText)findViewById(R.id.lastNameSign_up);
		genderMaleField=(RadioButton)findViewById(R.id.male);
		genderFemaleField=(RadioButton)findViewById(R.id.female);
		vegPreferenceField=(RadioButton)findViewById(R.id.veg);
		nonVegPreferenceField=(RadioButton)findViewById(R.id.nonveg);
		bothPreferenceField=(RadioButton)findViewById(R.id.both);
		//doneButton= (Button)findViewById(R.id.d);

		
		saveDetailsToDatabase();
	}

	
	public String getGender()
	{
		if(genderMaleField.isSelected())
			return "M";
		else if(genderFemaleField.isSelected())
			return "F";
		else
			return "";
	}
	
	public String getFoodPreference()
	{
		if(vegPreferenceField.isSelected())
			return "veg";

		else if(nonVegPreferenceField.isSelected())
			return "nonveg";

		else //if(bothPreferenceField.isSelected())
			return "both";
	}

	@SuppressLint("NewApi")
	public void saveDetailsToDatabase() {
		
/*doneButton.setOnClickListener(new OnClickListener() {//===========inserting creating databse
			
			@Override
			public void onClick(View v) {
				String userEmail=emailField.getText().toString();
				String userPassword=passwordField.getText().toString();
				String userFirstName=firstNameField.getText().toString();
				String userLastName=lastNameField.getText().toString();
				String userGender= getGender();
				String userFoodPreference= getFoodPreference();
				boolean noException=true;	
				
				//===============check for null values
				if(userPassword.isEmpty() || userFirstName.isEmpty() )
					noException=false;
				
				if(noException) {
				handler =new DataHandler(getBaseContext());
				handler.open();
				
				
				//=============check if username already exists====//
				boolean uniqueEmail=true;
				Cursor c= handler.isUniqueEmail(userEmail);
				if(c.moveToFirst())
				{
					do {
						if(userEmail.equalsIgnoreCase(c.getString(0))  )
							uniqueEmail=false;
					}while(c.moveToNext());
					
				}
			
				if(uniqueEmail) {
				 try{
						long id=handler.insertData(userEmail,userPassword,userFirstName,userLastName,userGender,userFoodPreference);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					Toast.makeText(getBaseContext(), "Account Created", Toast.LENGTH_LONG).show();
					handler.close();
				}
				}
				else
				Toast.makeText(getBaseContext(), "UserName already exists", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(getBaseContext(), "Enter * marked Fields", Toast.LENGTH_LONG).show();
							
			}
		});*/
		
		
		
	}

	@SuppressLint("NewApi")
	@Override
	public void onBackPressed()
	{
		finish();
		Intent moveToAllRecipe= new Intent(SignUpForm.this,LoginActivitySplash.class);
		
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		startActivity(moveToAllRecipe, bundleanimation);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.activity_login_page_actions, menu);
		return super.onCreateOptionsMenu(menu);*/
		getMenuInflater().inflate(R.menu.sign_up_page_actions, menu);

		return true;

		
	}
	
	@Override

	public boolean onOptionsItemSelected(MenuItem item)

	{

	super.onOptionsItemSelected(item);

	switch(item.getItemId())

	{

	case R.id.doneButton:

		String userEmail=emailField.getText().toString();
		String userPassword=passwordField.getText().toString();
		String userFirstName=firstNameField.getText().toString();
		String userLastName=lastNameField.getText().toString();
		String userGender= getGender();
		String userFoodPreference= getFoodPreference();
		boolean noException=true;	
		
		//===============check for null values
		if(userPassword.isEmpty() || userFirstName.isEmpty() )
			noException=false;
		
		if(noException) {
		handler =new DataHandler(getBaseContext());
		handler.open();
		
		
		//=============check if username already exists====//
		boolean uniqueEmail=true;
		Cursor c= handler.isUniqueEmail(userEmail);
		if(c.moveToFirst())
		{
			do {
				if(userEmail.equalsIgnoreCase(c.getString(0))  )
					uniqueEmail=false;
			}while(c.moveToNext());
			
		}
	
		if(uniqueEmail) {
		 try{
				long id=handler.insertData(userEmail,userPassword,userFirstName,userLastName,userGender,userFoodPreference);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Toast.makeText(getBaseContext(), "Account Created", Toast.LENGTH_LONG).show();
			handler.close();
			finish();
			Intent moveToAllRecipe= new Intent(SignUpForm.this,AllRecipesScreen.class);
			moveToAllRecipe.putExtra("Username",userFirstName );
			moveToAllRecipe.putExtra("Email",userEmail);
			Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
			startActivity(moveToAllRecipe, bundleanimation);
		}
		}
		else
		Toast.makeText(getBaseContext(), "UserName already exists", Toast.LENGTH_LONG).show();
		}
		else
			Toast.makeText(getBaseContext(), "Please enter the registration details !", Toast.LENGTH_LONG).show();

	break;

	}
return true;
}
}