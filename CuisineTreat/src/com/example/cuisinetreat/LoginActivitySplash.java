package com.example.cuisinetreat;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


@SuppressLint("NewApi")
public class LoginActivitySplash extends Activity implements OnClickListener {
	Button loginButton;
	Button signUpButton;
	Intent takeToLoginScreen;
	Intent takeToAllRecipeScreen;
	Bundle bundleanimation;
	TextView skipButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_splash);
		
		signUpButton = (Button) findViewById(R.id.sign_up_button);
		loginButton = (Button) findViewById(R.id.login_button);
		skipButton=(TextView)findViewById(R.id.textView1);
			
		signUpButton.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		skipButton.setOnClickListener(this);
		
		Typeface tf =Typeface.createFromAsset(getAssets(), "future.ttf");
		skipButton.setTypeface(tf,40);
			
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		/*Animation viewDropDown=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_down);
		signUpButton.startAnimation(viewDropDown);
		loginButton.startAnimation(viewDropDown);*/
		
		/*signUpButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Intent takeToLoginScreen = new Intent(LoginActivitySplash.this,
						SignUpForm.class);
				Bundle bundleanimation = ActivityOptions.makeCustomAnimation(
						getApplicationContext(), R.anim.slide_in,
						R.anim.slide_out).toBundle();
				startActivity(takeToLoginScreen, bundleanimation);

			}
		});

		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Intent takeToLoginScreen = new Intent(LoginActivitySplash.this,
						LoginPage.class);
				Bundle bundleanimation = ActivityOptions.makeCustomAnimation(
						getApplicationContext(), R.anim.slide_in,
						R.anim.slide_out).toBundle();
				startActivity(takeToLoginScreen, bundleanimation);

			}
		});*/

	}

	public void onClick(View arg0)
	{
		switch (arg0.getId()) {
		case R.id.sign_up_button:
			finish();
			takeToLoginScreen = new Intent(LoginActivitySplash.this,
					SignUpForm.class);
			bundleanimation = ActivityOptions.makeCustomAnimation(
					getApplicationContext(), R.anim.slide_in,
					R.anim.slide_out).toBundle();
			startActivity(takeToLoginScreen, bundleanimation);
			break;
		case R.id.login_button:
			finish();
			takeToLoginScreen = new Intent(LoginActivitySplash.this,
					LoginPage.class);
			bundleanimation = ActivityOptions.makeCustomAnimation(
					getApplicationContext(), R.anim.slide_in,
					R.anim.slide_out).toBundle();
			startActivity(takeToLoginScreen, bundleanimation);
			break;

		case R.id.textView1:
			finish();
			takeToAllRecipeScreen = new Intent(LoginActivitySplash.this,
					AllRecipesScreen.class);
			bundleanimation = ActivityOptions.makeCustomAnimation(
					getApplicationContext(), R.anim.slide_in,
					R.anim.slide_out).toBundle();
			startActivity(takeToAllRecipeScreen, bundleanimation);
			break;
			
		default:
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	public void onBackPressed()
	{
		finish();
		
		/*Intent moveToAllRecipe= new Intent(LoginActivitySplash.this,LoginActivitySplash.class);
		
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		startActivity(moveToAllRecipe, bundleanimation);*/
	}

}
