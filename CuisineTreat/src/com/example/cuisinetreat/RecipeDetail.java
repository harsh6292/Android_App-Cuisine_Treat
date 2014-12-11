package com.example.cuisinetreat;



import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
/*
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
*/
public class RecipeDetail extends Activity implements OnClickListener{
//FaceBook implementation
	// Your Facebook APP ID
		/*private static String APP_ID = "300385043448285"; // Replace with your App ID

		// Instance of Facebook Class
		private Facebook facebook = new Facebook(APP_ID);
		private AsyncFacebookRunner mAsyncRunner;*/
		String FILENAME = "AndroidSSO_data";
		private SharedPreferences mPrefs;
		private Menu menu;
		/////////////////
	String loggedUser;
	TextView countdownTimerField;
	TextView ingredientsAni;
	TextView ingredientsLink;
	int enteredCountDownTimerValue;
	RadioButton seconds;
	RadioButton minutes;
	RadioGroup group;
	LinearLayout ll;
	Intent takeToIngredientsScreen;
	String videoURLValue;
	String displayValueInTextView;
	String ingredientsValue;
	String descriptionValue;
	String extraValue;
	String instructions;
	String Email;
	GridView gridView;
	static int position;
	//========ratings
	static DataHandler handler=null;
	static RatingBar ratingBar=null;
	
	/*int ratingStars[];
	
static	private RatingBar ratingBar=null;*/
	static final String[] buttons_array = new String[] { "Video", "Facebook",
			"Twitter", "Gallery" };
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_detail);
		countdownTimerField=(TextView)findViewById(R.id.countdowntimerfield);
		
		
		
		Typeface segoe =Typeface.createFromAsset(getAssets(), "segoeui.ttf");
		Typeface roboto_normal =Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
		Typeface roboto_thin =Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
		Typeface argh =Typeface.createFromAsset(getAssets(), "Aaargh.ttf");
/*LinearLayout recipeDetail=(LinearLayout)findViewById(R.id.recipeDetailView);		
		AnimationSet set = new AnimationSet(true);

		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(100);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
		        -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
		        Animation.RELATIVE_TO_SELF, 0.0f,
		        Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(1500);
		set.addAnimation(animation);

		LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

		recipeDetail.setLayoutAnimation(controller);
		controller.start();*/
		
		
		doTheAniamtions();
		
		Bundle receivedBundle=this.getIntent().getExtras();
		
		 displayValueInTextView= receivedBundle.getString("RecipeClicked");
		 ingredientsValue = receivedBundle.getString("Ingredients");
		 descriptionValue = receivedBundle.getString("Description");
		 extraValue = receivedBundle.getString("Extra");
		 videoURLValue = receivedBundle.getString("Video");
		 instructions = receivedBundle.getString("Instructions");
		loggedUser=receivedBundle.getString("Username");
		Email=receivedBundle.getString("Email");
		position=receivedBundle.getInt("Position");
		/*ratingStars=receivedBundle.getIntArray("Rating");
		*/
		
		ActionBar ab = getActionBar();
		
		if(loggedUser!=null){
			ab.setTitle("Welcome "+loggedUser);
			
			
		}
		else
			{loggedUser="Guest";
			ab.setTitle("Welcome "+loggedUser);
			}
		
		int images = (int)receivedBundle.getLong("RecipeImage");
		TextView showRecipeName=(TextView)findViewById(R.id.recipeName);
		showRecipeName.setText(displayValueInTextView);
		showRecipeName.setTypeface(argh,5);
		
		
		ActionBar actionBar = getActionBar();
		
		actionBar.setTitle(displayValueInTextView);
		
		ImageView showImage = (ImageView)findViewById(R.id.recipeImage);
		showImage.setImageResource(images);
		
		TextView desc=(TextView)findViewById(R.id.descriptionField);
		desc.setText("Description: "+descriptionValue);
		desc.setTypeface(roboto_thin, 60);
		
		TextView duration=(TextView)findViewById(R.id.extraField);
		duration.setText(extraValue);
		duration.setTypeface(roboto_thin, 60);
		
		TextView ingredientsListTextView=(TextView)findViewById(R.id.ingredientsTextView);
		ingredientsListTextView.setTypeface(segoe,100);
		
		//ingredientsListTextView.getLocationOnScreen(FOCUSED_STATE_SET);
		//String s=Integer.toString();
		//Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
		
		
		//TextView showIngredients = (TextView)findViewById(R.id.recipeIngredients);
		LinearLayout layout = (LinearLayout) findViewById(R.id.recipeDetailLayout);

		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.card_background); 
		//layout.setBackgroundDrawable(drawable);
		
		String[] ingredList = ingredientsValue.split("/n/");
		
		for(int count = 0; count < ingredList.length; count++)
		{
			TextView text = new TextView(this);//) findViewById(R.id.recipeIngredients);
			text.setText(ingredList[count]);
			text.setTypeface(roboto_normal,80);
			text.setTextSize(16);
			text.setPadding(0, 15, 0, 0);
			if(count%2!=0)
			{
				text.setBackgroundColor(Color.GRAY);
			}
			else
			{
				text.setBackgroundColor(Color.WHITE);
			}
			layout.addView(text);
		}
		
		
		
		
		
		
		
		
		TextView checkAllIngredients=(TextView)findViewById(R.id.checkAllIngredients);
		checkAllIngredients.setOnClickListener(this);
		checkAllIngredients.setTypeface(segoe,60);
		
		
		gridView = (GridView) findViewById(R.id.gridView1);

		gridView.setAdapter(new ImageAdapter(this, buttons_array));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				/*Toast.makeText(
						getApplicationContext(),
						"Clicked", Toast.LENGTH_SHORT).show();*/
				//String s="ada";
			//System.out.println(s);
				if(position==0)
				{
					playVideo(videoURLValue);
				}
				else if(position ==1)
				{
					Intent shareIntent = new Intent();
					shareIntent.setAction(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_TEXT, videoURLValue);
					startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));
					
					
					
					
				}
				else if(position ==2)
				{
					Intent shareIntent = new Intent();
					shareIntent.setAction(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_TEXT, "I just cooked this awesome recipe "+displayValueInTextView+ "Check it's Video out at "+videoURLValue);
					startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));
				}
				
				else if(position ==3)
				{
					Intent takeToPhotoScreen = new Intent(RecipeDetail.this,
							PhotoViewer.class);
					
					takeToPhotoScreen.putExtra("Pos",RecipeDetail.position);
					String poss="In recepie detail clicked on "+RecipeDetail.position;
					
					Bundle bundleanimation = ActivityOptions.makeCustomAnimation(
							getApplicationContext(), R.anim.slide_in, R.anim.slide_out)
							.toBundle();

					startActivity(takeToPhotoScreen);
				}

			}
		});

	
		
	TextView directionsToCookView=new TextView(this);
	directionsToCookView.setText("Directions to Cook!");
	directionsToCookView.setPadding(15, 15, 15, 0);
	directionsToCookView.setBackgroundResource(R.drawable.layout_border);
	directionsToCookView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_circular_64, 0);
	directionsToCookView.setTypeface(segoe,60);
	directionsToCookView.setTextSize(23);
	directionsToCookView.setTextColor(Color.WHITE);
	layout.addView(directionsToCookView);
	
	
	TextView instructionsToCook=new TextView(this);
	instructionsToCook.setPadding(15, 15, 15, 0);
	instructionsToCook.setText(instructions);
	instructionsToCook.setTypeface(segoe,60);
	instructionsToCook.setTextSize(16);
	instructionsToCook.setTextColor(Color.BLACK);
	instructionsToCook.setBackgroundColor(Color.WHITE);
	layout.addView(instructionsToCook);
	
	
	
	/*ratingBar=(RatingBar)findViewById(R.id.rating);
	ratingBar.setRating(ratingStars[position]);
	addListener(ratingStars[position]);*/
	showRating();
	addListenerOnRatingBar();
	
	}

	
	/*public void addListener(int stars)
	{
		ratingBar=(RatingBar)findViewById(R.id.rating);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
					
				ratingStars[position]=(int)rating;
			}
		});
	}*/
	
	
	
	@SuppressLint("NewApi")
	public void onClick(View arg0)
	{
		//finish();
		
		if (haveNetworkConnection(getApplicationContext()))
        {
		takeToIngredientsScreen = new Intent(RecipeDetail.this,
				IngredientsScreen.class);

		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(
				getApplicationContext(), R.anim.slide_in, R.anim.slide_out)
				.toBundle();

		startActivity(takeToIngredientsScreen);
		}
		else
        {
            Toast.makeText(getApplicationContext(), "Internet Connection unavailable, Please enable it and return back !", Toast.LENGTH_LONG).show();
    	/*Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
    	startActivity(intent);*/
        }
	}
	
	
	
	
	
	@SuppressLint("NewApi")
	private void playVideo(String argVideoURLValue) {
		
		
		if (haveNetworkConnection(getApplicationContext()))
        {
			Intent gotToPlayVideoActivity=new Intent(RecipeDetail.this,PlayVideo.class);
			gotToPlayVideoActivity.putExtra("URL", argVideoURLValue);
			//startActivity(gotToPlayVideoActivity);
			Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
			startActivity(gotToPlayVideoActivity, bundleanimation);
        }
    else
        {
    	Toast.makeText(getApplicationContext(), "Internet Connection unavailable, Please enable it and return back !", Toast.LENGTH_LONG).show();
        }

		
		/*Intent gotToPlayVideoActivity=new Intent(RecipeDetail.this,PlayVideo.class);
		gotToPlayVideoActivity.putExtra("URL", argVideoURLValue);
		//startActivity(gotToPlayVideoActivity);
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		startActivity(gotToPlayVideoActivity, bundleanimation);*/
		
		
	}
	
	
	
	@SuppressLint("NewApi")
	@Override
	public void onBackPressed()
	{
		/*mAsyncRunner = new AsyncFacebookRunner(facebook);
		try {
			facebook.logout(this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Intent moveToAllRecipe= new Intent(RecipeDetail.this,AllRecipesScreen.class);
		moveToAllRecipe.putExtra("Username", loggedUser);
		moveToAllRecipe.putExtra("Email", Email);
	
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		ratingBar=null;
		finish();
		startActivity(moveToAllRecipe, bundleanimation);
	}
	
	
	private void doTheAniamtions() 
	{
		ImageView foodImage=(ImageView)findViewById(R.id.recipeImage);	
		//Animation viewDropDown=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop_down);
		//foodImage.startAnimation(viewDropDown);
		
		TextView recipeDesc=(TextView)findViewById(R.id.recipeName);
		//Animation animateTextview=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_up);
		//recipeDesc.startAnimation(animateTextview);
		
		 ingredientsAni = (TextView)findViewById(R.id.recipeIngredients);
		//Animation animateshowIngredients=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_up);
		//ingredientsAni.startAnimation(animateshowIngredients);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe_detail, menu);
		this.menu = menu;
		return true;
	}
	
	@Override

	public boolean onOptionsItemSelected(MenuItem item)

	{

	super.onOptionsItemSelected(item);

	switch(item.getItemId())

	{

	case R.id.countdown_timer:

		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
	     final EditText input = new EditText(this);
	    
	     Typeface argh =Typeface.createFromAsset(getAssets(), "Aaargh.ttf");
	     input.setHint("Enter time for Timer (in seconds)");
	     
		 ll=new LinearLayout(this);
       ll.setOrientation(LinearLayout.VERTICAL);
       ll.addView(input);
       alert.setTitle("Count Down Timer Dialog Box");
       alert.setView(ll);
       
		
	     
	   alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
	     {
	      public void onClick(DialogInterface dialog, int whichButton) 
	      {
	    	  enteredCountDownTimerValue=Integer.parseInt(input.getText().toString().trim()) ;
	    	 
	    		  
	    		    enteredCountDownTimerValue=enteredCountDownTimerValue*1000;
		    	  new CountDownTimer(enteredCountDownTimerValue, 1000) {

				     public void onTick(long millisUntilFinished) {
				    	 Typeface argh =Typeface.createFromAsset(getAssets(), "Aaargh.ttf");
				    	 countdownTimerField.setInputType(InputType.TYPE_CLASS_PHONE);
				    	 countdownTimerField.setTypeface(argh,2);
				    	 countdownTimerField.setText("Time remaining: " + millisUntilFinished / 1000+ " seconds!");
				     }

				     public void onFinish() {
				    	 countdownTimerField.setText("Time's Up!");
				     }
				  }.start();
	    	 
	    	 /*else if(minutes.isChecked())
	    	 {
	    		 enteredCountDownTimerValue=enteredCountDownTimerValue*60000;
	    		 new CountDownTimer(enteredCountDownTimerValue, 1000) {

				     public void onTick(long millisUntilFinished) {
				    	 ingredientsAni.setText("minutes remaining: " + millisUntilFinished / 60000);
				     }

				     public void onFinish() {
				    	 ingredientsAni.setText("done!");
				     }
				  }.start();
	    	 }*/
	    	 /* enteredCountDownTimerValue=Integer.parseInt(input.getText().toString().trim()) ;
	    
	    	  new CountDownTimer(enteredCountDownTimerValue, 1000) {

			     public void onTick(long millisUntilFinished) {
			    	 ingredientsAni.setText("seconds remaining: " + millisUntilFinished / 1000);
			     }

			     public void onFinish() {
			    	 ingredientsAni.setText("done!");
			     }
			  }.start();*/
	        
	        }
	        
	     });
	    alert.show();
		
	break;
	
	case R.id.favoriteBtn:
		Toast.makeText(this, "In fav. button clicked", Toast.LENGTH_SHORT).show();
		//menu.getItem(1).setIcon(getResources().getDrawable(R.drawable.ic_action_favorite_selected));
		addToFav();
		break;

	}
return true;
}
	
	public static boolean haveNetworkConnection(final Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        final ConnectivityManager cm = (ConnectivityManager) context
                     .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
               final NetworkInfo[] netInfo = cm.getAllNetworkInfo();
               for (final NetworkInfo netInfoCheck : netInfo) {
                     if (netInfoCheck.getTypeName().equalsIgnoreCase("WIFI")) {
                            if (netInfoCheck.isConnected()) {
                                   haveConnectedWifi = true;
                            }
                     }
                     if (netInfoCheck.getTypeName().equalsIgnoreCase("MOBILE")) {
                            if (netInfoCheck.isConnected()) {
                                   haveConnectedMobile = true;
                            }
                     }
               }
        }

        return haveConnectedWifi || haveConnectedMobile;
 }
	
	/*@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}
*/
	
	/*@SuppressWarnings("deprecation")
	public void loginToFacebook() {

		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);
			Log.d("FB Sessions", "" + facebook.isSessionValid());
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
		
		//postToWall();
	}*/
	
	/*@SuppressWarnings("deprecation")
	public void postToWall() {
		
		Bundle parameters = new Bundle();
	    parameters.putString("name",displayValueInTextView );
	    parameters.putString("link", videoURLValue);
	    
		// post on user's wall.
		facebook.dialog(this, "feed", parameters,new DialogListener() {

			@Override
			public void onFacebookError(FacebookError e) {
			}

			@Override
			public void onError(DialogError e) {
			}

			@Override
			public void onComplete(Bundle values) {
			}

			@Override
			public void onCancel() {
			}
		});

	}*/
	
	
	//======================ratings
	
public void addListenerOnRatingBar() {
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
	 
				String ratingNum=String.valueOf(ratingBar.getRating());
				addRating(ratingNum);
	 
			}
		});
	  }
	public void addRating(String ratingNum)
	{
		handler= new DataHandler(getBaseContext());
		handler.open();
		
		if(loggedUser=="" || loggedUser.equals("Guest"))
			Toast.makeText(getApplicationContext(), "Login to rate", Toast.LENGTH_SHORT).show();
		else {
			Toast.makeText(getApplicationContext(),Email , Toast.LENGTH_SHORT).show();			
			Cursor c = handler.isExistsRating(Email);
			boolean isExists =false;
			if (c.moveToFirst()) {
		        do {
		            if(c.getString(1).equals(displayValueInTextView)) {
		            	Toast.makeText(getApplicationContext(),"update:"+c.getString(2) , Toast.LENGTH_SHORT).show();
		            	handler.updateRating(Email, displayValueInTextView, ratingNum);
		            	isExists= true;
		            	
		            	break;
		            }
		        } while (c.moveToNext());
		        
		        if(!isExists) {
		        	Toast.makeText(getApplicationContext(),"insert", Toast.LENGTH_SHORT).show() ;
					handler.insertInToRatingTable(Email,displayValueInTextView, ratingNum);
		        }
		    }
			else {
				Toast.makeText(getApplicationContext(),"Insert empty", Toast.LENGTH_SHORT).show() ;
				handler.insertInToRatingTable(Email,displayValueInTextView, ratingNum);
			}
		}
			
			

		handler.close();
	}
	
	void showRating() {
		if(ratingBar==null) {
			ratingBar = (RatingBar) findViewById(R.id.rating);
			}
		if(loggedUser=="" || loggedUser.equals("Guest"))
			return ;
		else {		
		handler= new DataHandler(getBaseContext());
		handler.open();
		Cursor c = handler.isExistsRating(Email);
		if (c.moveToFirst()) {
	        do {
	            if(c.getString(1).equals(displayValueInTextView)) {
	            	Toast.makeText(getApplicationContext(),"show rating" , Toast.LENGTH_SHORT).show();
	            	String rate=c.getString(2);
	    			ratingBar.setRating(Float.parseFloat(rate));
	            	break;
	            }
	        } while (c.moveToNext());
		}
		handler.close();
		
	}
	}
	
	
void addToFav() {
		
	if(handler==null)
	{
		handler=new DataHandler(getBaseContext());
	}
		
		if(loggedUser=="" || loggedUser.equals("Guest"))
		{
			Toast.makeText(getApplicationContext(), "Login to add", Toast.LENGTH_SHORT).show();
			return;
		}
		handler.open();
		boolean inFavList= false;
		System.out.println(Email);
		Cursor c = handler.getFavorites(Email);
		if (c.moveToFirst()) {
	        do {
	            if(c.getString(1).equals(displayValueInTextView)) {
	            	handler.deleteFavorite(Email, displayValueInTextView);
	            	Toast.makeText(getApplicationContext(),displayValueInTextView , Toast.LENGTH_SHORT).show();
	            	inFavList=true;
	            	menu.getItem(1).setIcon(getResources().getDrawable(R.drawable.ic_action_favorite)); 
	            }
	        } while (c.moveToNext());
		}
		
		if(!inFavList) {
			String padded_val=""+position;
			handler.addtoFavorites(Email, displayValueInTextView,padded_val);
			menu.getItem(1).setIcon(getResources().getDrawable(R.drawable.ic_action_favorite_selected));
        	Toast.makeText(getApplicationContext(),"Added to favorites" , Toast.LENGTH_SHORT).show();
		}
		handler.close();
	}
}
