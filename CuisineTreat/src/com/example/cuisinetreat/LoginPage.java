package com.example.cuisinetreat;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LoginPage extends Activity implements OnClickListener{

	String userNameForVerification;
	String emailToSendPasswordTo;
	String password;
	Button loginBtn;
	EditText emailAddressField;
	EditText passwordField;
	TextView loginInstruction;
	String userEmailAddress;
	String userPassword;
	DataHandler handler;
	String userName;
	Button forgotPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		loginInstruction=(TextView)findViewById(R.id.loginPageInstruction);
		loginInstruction.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		loginBtn=(Button)findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(this);
		emailAddressField=(EditText)findViewById(R.id.email_Field_loginPage);
		passwordField=(EditText)findViewById(R.id.password_Field_loginPage);
		forgotPassword=(Button)findViewById(R.id.forgotPassword);
		forgotPassword.setOnClickListener(this);
		userEmailAddress=emailAddressField.getText().toString();
		userPassword=passwordField.getText().toString();
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
	}
		
	@SuppressLint("NewApi")
	@Override
	public void onBackPressed()
	{
		finish();
		Intent moveToAllRecipe= new Intent(LoginPage.this,LoginActivitySplash.class);
		
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		startActivity(moveToAllRecipe, bundleanimation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_page, menu);
		return true;
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.login_btn:
			userEmailAddress=emailAddressField.getText().toString();
			userPassword=passwordField.getText().toString();
			
			handler =new DataHandler(getBaseContext());
			handler.open();
			Cursor c= handler.isExists(userEmailAddress);
			Cursor getUserName=handler.returnUserName(userEmailAddress);
			if (getUserName.moveToFirst()) {
			    userName =getUserName.getString(0);
			}
			boolean isExists=false;
			if(c.moveToFirst())
			{
				do {
					if(userEmailAddress.equalsIgnoreCase(c.getString(0)) 
							&& userPassword.equalsIgnoreCase(c.getString(1)))
						isExists=true;
				}while(c.moveToNext());
				
			}
			handler.close();
			if(isExists)
			{
				finish();
				
				Intent takeToAllRecipesScreen = new Intent(LoginPage.this,
						AllRecipesScreen.class);
				takeToAllRecipesScreen.putExtra("Origin", "LoginPage");
				takeToAllRecipesScreen.putExtra("Username",userName );
				takeToAllRecipesScreen.putExtra("Email",userEmailAddress );
				Bundle bundleanimation = ActivityOptions.makeCustomAnimation(
						getApplicationContext(), R.anim.slide_in,
						R.anim.slide_out).toBundle();
				startActivity(takeToAllRecipesScreen, bundleanimation);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Enter correct Details ", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.forgotPassword:
		{
			final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		     final EditText input = new EditText(this);
		     final EditText input2 = new EditText(this);
		     final EditText input3 = new EditText(this);
		     input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		     input.setHint("Enter New Password");
		     input2.setHint("Enter your username for verification");
		     input3.setHint("Enter your email address for sending your password");
		     
			LinearLayout ll=new LinearLayout(this);
	        ll.setOrientation(LinearLayout.VERTICAL);
	        ll.addView(input);
	        ll.addView(input2);
	        ll.addView(input3);
	        alert.setTitle("Password Retreival DialogBox");
	        alert.setView(ll);
	        
			
		     alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
		     {
		      public void onClick(DialogInterface dialog, int whichButton) 
		      {
		    	  emailToSendPasswordTo=input3.getText().toString().trim();
		    	  userNameForVerification= input2.getText().toString().trim();
		        password=input.getText().toString().trim();
		    	 boolean status=((emailToSendPasswordTo != null && !emailToSendPasswordTo.isEmpty())&&(userNameForVerification != null && !userNameForVerification.isEmpty())&&(password != null && !password.isEmpty()));
		        if(status)
		        {
		        	
		        			 handler =new DataHandler(getBaseContext());
		     				handler.open();
		     		        handler.resetPassword(password, userNameForVerification);
		     		        Toast.makeText(getApplicationContext(), "Your Password has been reset and also emailed to you!", Toast.LENGTH_SHORT).show();
		     		        Intent intent = new Intent(Intent.ACTION_SEND); // it's not ACTION_SEND
		     		        intent.setType("application/octet-stream");
		     		        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
		     		        intent.putExtra(Intent.EXTRA_TEXT, "Your new password is :"+ password);
		     		        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {emailToSendPasswordTo}); // or just "mailto:" for blank
		     		        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
		     		        
		     		        startActivity(Intent.createChooser(intent, "Send Mail"));
		     		        
		     		        }
		     
		        else
 		        {
 		        	Toast.makeText(getApplicationContext(), "Please enter details to reset password", Toast.LENGTH_SHORT).show();
 		        }
		        }
		        
		     });
		    alert.show();
		}
		break;
		default:
			break;
		}
		
	}

}
