package com.example.cuisinetreat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ProgressBar;

public class SplashScreen extends Activity {

	final long timeToWait = 3000;
	private ProgressBar progressBar, progress2;
	private int progressStatus = 0;
	// private int status2 = 50;
	private Handler handler = new Handler();

	// private Handler handlerForSecond = new Handler();

	/*
	 * //@SuppressLint("HandlerLeak")
	 * 
	 * @SuppressLint("HandlerLeak") Handler handlerForSplash=new Handler(){
	 * 
	 * public void handleMessage(Message msg) { Intent goToAllRecipe= new
	 * Intent(MainActivity.this,ChutiyCTIVITY.class);
	 * startActivity(goToAllRecipe); }
	 * 
	 * };
	 */

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		ActionBar actionBar = getActionBar();
		actionBar.hide();

		/*
		 * Runnable newRubbanleObj= new Runnable() {
		 *  @Override public void run()
		 * {
		 * 	synchronized (this) { try { wait(timeToWait); } catch
		 * (InterruptedException e) { e.printStackTrace(); } }
		 * 
		 * 
		 * handlerForSplash.sendEmptyMessage(0); }
		 * 
		 * }; Thread threadforSplash=new Thread(newRubbanleObj);
		 * threadforSplash.start();
		 */

		// Your code

		final SplashScreen main = this;
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		progressBar.getProgressDrawable()
				.setColorFilter(Color.WHITE, Mode.SRC_IN);

		progress2 = (ProgressBar) findViewById(R.id.progress_bar_second);
		progress2.getProgressDrawable().setColorFilter(Color.RED,
				Mode.SRC_IN);
		// progress2.setVisibility(View.INVISIBLE);
		// progress2.setBackgroundColor(Color.parseColor("#29888E"));
		// View v2 = (View)progress2.getV
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (progressStatus < 100) {
					progressStatus += 1;

					handler.post(new Runnable() {
						public void run() {
							if (progressStatus <= 50) {
								progressBar.setProgress(progressStatus);
								// progress2.setProgress(0);
							} else if (progressStatus >= 50) {
								// progress2.setVisibility(View.VISIBLE);
								progress2.setProgress(progressStatus - 50);

							}

						}
					});

					try {
						Thread.sleep(40);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				finish();

				Intent goToLoginSplash = new Intent(main,
						LoginActivitySplash.class);// AllRecipe.class);
				Bundle bundleanimation = ActivityOptions.makeCustomAnimation(
						getApplicationContext(), R.anim.slide_in,
						R.anim.slide_out).toBundle();
				startActivity(goToLoginSplash, bundleanimation);

			}
		}).start();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);

		return true;
	}

}
