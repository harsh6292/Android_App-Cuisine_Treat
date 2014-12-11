package com.example.cuisinetreat;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AllRecipesScreen extends Activity implements OnClickListener{
	//final AlertDialog alertDialog;
	String loggedInUser;
	ListView list;
	NodeList nList;
	Node nNode ;
	String[] recipes=new String[100];
	String[] description=new String[100];
	String[] extra=new String[100];
	String[] videoURL=new String[100];
	String[] instructions=new String[100];
	String Email;
	static DataHandler handler=null;
	
	//int rating[]={0,0,0,0,0,0};
	
	int[] imageID = {
			R.drawable.red_pepper_landscape,
			R.drawable.chocolate_muffin_landscape,
			R.drawable.caesar_wrap_large,//fig_newton_cookies,
			R.drawable.mang_rice_pudding_landscape,
			R.drawable.russalad1,
			R.drawable.biryani_landscape
	};
	
	
	int[] sendImage = {
			R.drawable.stuffed_green_peppers_2,
			R.drawable.chocolate_muffin_portrait,
			R.drawable.caesar_wrap_small,
			R.drawable.mango_rice_pudding_portrait,
			R.drawable.russ_salad_potrait,
			R.drawable.biryani_portrait
	};
	
	
	String[] ingredients = new String[100];
	
	
	
	
	//------------------------------------------------------------------
	
		private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;
	 
	    // nav drawer title
	    private CharSequence mDrawerTitle;
	 
	    // used to store app title
	    private CharSequence mTitle;
	 
	    // slide menu items
	    private String[] navMenuTitles;
	    private TypedArray navMenuIcons;
	 
	    private ArrayList<NavigationDrawerItem> navDrawerItems;
	    private NavigationDrawerListAdapter adapter;
	    ScrollView AllRecipeScrollView;
	    //----------------------------------------------------------------------
	    RelativeLayout rLayout;
	
	    Intent takeToFavoriteScreen;
	    String positions;
	    
	    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_all_recipes_screen);
		nList=parseTheXML("recipe.xml");
		
		Intent getMyIntent=getIntent();
		loggedInUser= getMyIntent.getStringExtra("Username");
		Email=getMyIntent.getStringExtra("Email");
		//int posValue=getMyIntent.getIntExtra("Position", -1);
		
		/*if(posValue!=-1)
		{
			rating=getMyIntent.getIntArrayExtra("Rating");
		}*/
		ActionBar ab = getActionBar();
		
		if(loggedInUser!=null){
			ab.setTitle("Welcome "+loggedInUser);
			
			
		}
		else
			{loggedInUser="Guest";
			ab.setTitle("Welcome "+loggedInUser);
			}
		final int width = this.getResources().getDisplayMetrics().widthPixels;
		
		float sizer = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
		
		mDrawerLayout = new DrawerLayout(getApplicationContext());//(DrawerLayout) findViewById(R.id.drawer_layout);
	    DrawerLayout.LayoutParams mDrawerlayoutParams = new DrawerLayout.LayoutParams((int)sizer, (int)sizer);//DrawerLayout.LayoutParams.FILL_PARENT);
	    mDrawerLayout.setLayoutParams(mDrawerlayoutParams);
	    mDrawerLayout.setId(3500);
	        
		
		AllRecipeScrollView = new ScrollView(this);
		
		rLayout = new RelativeLayout(this);//(RelativeLayout) findViewById(R.id.AllRecipeLayout);
		rLayout.setId(2500);
		
		RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
		
		rLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
		rParams.addRule(RelativeLayout.CENTER_VERTICAL);
		
		
		for (int i=0;i<nList.getLength(); i++)
		{
			nNode = nList.item(i);
		    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		     Element eElement = (Element) nNode;
		    recipes[i]= getValue("Name", eElement) ;
		    ingredients[i]= getValue("Ingredients", eElement) ;
		    description[i]=getValue("Description", eElement);
		    extra[i]=getValue("Extra", eElement);
		    videoURL[i]=getValue("Video", eElement);
		    instructions[i]=getValue("Instructions", eElement);
		     /*tv1.setText(tv1.getText() + "Price : "
		       + getValue("price", eElement) + "\n");
		     tv1.setText(tv1.getText() + "-----------------------");*/
		    }
			//RelativeLayout rLayout = new RelativeLayout(this);
			//RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
			//rLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
			//rParams.addRule(RelativeLayout.CENTER_VERTICAL);
			
			ImageView imgView = new ImageView(this);
			imgView.setId(1000+i);
			imgView.setImageResource(imageID[i]);
			
			Typeface tf =Typeface.createFromAsset(getAssets(), "segoeui.ttf");
			
			TextView txtView = new TextView(this);
			txtView.setId(3000+i);
			txtView.setText(recipes[i]);
			//txtView.setTextSize(34);
			txtView.setTypeface(tf,34);
			txtView.setLines(2);
			
			RelativeLayout rSecondLayout = new RelativeLayout(this);
            rSecondLayout.setId(2000+i);
            rSecondLayout.setBackground(getResources().getDrawable(R.drawable.card_background));
            
            RelativeLayout.LayoutParams rSecondParams = new RelativeLayout.LayoutParams(width-25, RelativeLayout.LayoutParams.WRAP_CONTENT);//((2*width)/3));//(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //rSecondParams.setMargins(20, 20, 20, 0);
            
            RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			imageParams.setMargins(15, 15, 15, 15);
			
			RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			textParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			textParams.addRule(RelativeLayout.BELOW, imgView.getId());
			textParams.setMargins(15, 0, 15, 15);
			
			
			if(i==0)
			{
				rSecondParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	            rSecondParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            rSecondParams.setMargins(25, 30, 25, 0);
	           // imgView.setLayoutParams(rSecondParams);
	            rSecondLayout.addView(imgView, imageParams);
	            
	            rSecondLayout.addView(txtView, textParams);
	            rLayout.addView(rSecondLayout, rSecondParams);
			}
			
			else
			{
				rSecondParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            rSecondParams.addRule(RelativeLayout.BELOW, (rSecondLayout.getId()-1));
	            if(i==(recipes.length-1))
	            {
	            	rSecondParams.setMargins(25, 25, 25, 25);
	            }
	            else
	            {
	            	rSecondParams.setMargins(25, 25, 25, 0);
	            }
	           // imgView.setLayoutParams(rSecondParams);
	            
	            rSecondLayout.addView(imgView, imageParams);
	            rSecondLayout.addView(txtView, textParams);
	            /*RelativeLayout rSecondLayout = new RelativeLayout(this);
	            rSecondLayout.setId(2000+i);
	            
	            RelativeLayout.LayoutParams rSecondParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	            rSecondLayout.setBackground(getResources().getDrawable(R.drawable.card_background));
	            imgView.setLayoutParams(rSecondParams);
	            rSecondLayout.addView(imgView);*/
	            
	            rLayout.addView(rSecondLayout, rSecondParams);
			}
			
			rSecondLayout.setOnClickListener(this);
			//rSecondParams.addRule(RelativeLayout.BELOW);
			
		}
		
		AllRecipeScrollView.addView(rLayout, rParams);
		
		mDrawerLayout.addView(AllRecipeScrollView);
		setContentView(mDrawerLayout);		

		
		mTitle = mDrawerTitle = getTitle();
		 
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
 
        
       
        
        
        mDrawerList = new ListView(getApplicationContext());// findViewById(R.id.list_slidermenu);
        mDrawerList.setDivider(new ColorDrawable(this.getResources().getColor(R.color.list_divider)));
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        mDrawerList.setDividerHeight((int)pixels);
        mDrawerList.setSelector(R.drawable.list_selector);
        mDrawerList.setBackground(new ColorDrawable(this.getResources().getColor(R.color.list_background_pressed)));
        mDrawerList.setId(4500);
        
       
        float drawerListPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics());
        LayoutParams mDrawerListParams = new LayoutParams((int)drawerListPixels, LayoutParams.FILL_PARENT);
        mDrawerListParams.gravity = Gravity.START;
        
        
        
        
        mDrawerLayout.addView(mDrawerList, mDrawerListParams);
        
        
        
        navDrawerItems = new ArrayList<NavigationDrawerItem>();
        
        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
       // navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), true, "22"));
        // Pages
       // navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
       // navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
        //navDrawerItems.trimToSize();
 
        // Recycle the typed array
 
        
        navMenuIcons.recycle();
 
      //  String some = navDrawerItems.get(0).getTitle()+"  "+navDrawerItems.get(1).getTitle()+"  "+navDrawerItems.get(2).getTitle();
        
        
    //    Toast.makeText(this, some, Toast.LENGTH_LONG).show();
        
        
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        // setting the nav drawer list adapter
        adapter = new NavigationDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) 
        {
            // on first time display view for first nav item
            displayView(0,getApplicationContext());
        }
        
        
        
        
        //=======================================================================================
	
		
		
		
		/*CustomizedListView adapter = new CustomizedListView(AllRecipesScreen.this, recipes, imageID);
		
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(AllRecipesScreen.this, "You Clicked at "+singers[+position], Toast.LENGTH_SHORT).show();
				
				
				// Creating bundle to send to another activity 
				Bundle bundleToRecipeDetail=new Bundle();
				bundleToRecipeDetail.putString("RecipeClicked", recipes[position]);
				bundleToRecipeDetail.putLong("RecipeImage", imageID[position]);
				bundleToRecipeDetail.putString("Ingredients", ingredients[position]);
				
				Intent moveToRecipeDetail= new Intent(AllRecipesScreen.this,RecipeDetail.class);
				moveToRecipeDetail.putExtras(bundleToRecipeDetail);
				ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
					      0, view.getWidth(), view.getHeight());
					  startActivity(moveToRecipeDetail, options.toBundle());
				finish();
				startActivity(moveToRecipeDetail);	
				overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
				Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
				startActivity(moveToRecipeDetail, bundleanimation);
			}
		});*/
		
		
		
	}

	private NodeList parseTheXML(String xmlName)
	{
	  try {
		   InputStream is = this.getAssets().open(xmlName);

		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory
		     .newInstance();
		   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document doc = dBuilder.parse(is);
		   doc.getDocumentElement().normalize();
		   nList = doc.getElementsByTagName("Recipe");
		   /*for (int temp = 0; temp < nList.getLength(); temp++) {
		   nNode = nList.item(temp);
		    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		     Element eElement = (Element) nNode;
		     tv1.setText(tv1.getText() + "\n\nName : "
		       + getValue("name", eElement) + "\n");
		     tv1.setText(tv1.getText() + "Price : "
		       + getValue("price", eElement) + "\n");
		     tv1.setText(tv1.getText() + "-----------------------");

		    }
		   }*/
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	return nList;
	 }

	private static String getValue(String sTag, Element eElement) {
		  NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
		    .getChildNodes();
		  Node nValue = (Node) nlList.item(0);
		  return nValue.getNodeValue();
		 }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_recipes_screen, menu);
		return true;
	}



	
	//===================================================================
	
	

	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
	{
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) 
        {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) 
        {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 
	
	
	/***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) 
    {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
 
    @Override
    public void setTitle(CharSequence title) 
    {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
 
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) 
    {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) 
    {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    


	
	
	
	//=====================================================================
	
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int layoutID = v.getId();
		int position = (layoutID%2000);
		//String abs = "Rel_Layout id "+v.getId()+" "+position;
		//Toast.makeText(getApplicationContext(), abs, Toast.LENGTH_SHORT).show();
		
		
		
		
		// Creating bundle to send to another activity 
		Bundle bundleToRecipeDetail=new Bundle();
		bundleToRecipeDetail.putString("RecipeClicked", recipes[position]);
		bundleToRecipeDetail.putLong("RecipeImage", sendImage[position]);
		bundleToRecipeDetail.putString("Ingredients", ingredients[position]);
		bundleToRecipeDetail.putString("Description", description[position]);
		bundleToRecipeDetail.putString("Extra", extra[position]);
		bundleToRecipeDetail.putString("Video", videoURL[position]);
		bundleToRecipeDetail.putString("Instructions", instructions[position]);
		bundleToRecipeDetail.putString("Username", loggedInUser);
		bundleToRecipeDetail.putString("Email", Email);
		//bundleToRecipeDetail.putIntArray("Rating", rating);
		bundleToRecipeDetail.putInt("Position",position);
		
		Intent moveToRecipeDetail= new Intent(AllRecipesScreen.this,RecipeDetail.class);
		moveToRecipeDetail.putExtras(bundleToRecipeDetail);
		/*ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0,
			      0, v.getWidth(), v.getHeight());
			  startActivity(moveToRecipeDetail, options.toBundle());
		finish();
		
		startActivity(moveToRecipeDetail);	*/
		finish();
		overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		startActivity(moveToRecipeDetail, bundleanimation);
		
		
	}
	/*@SuppressWarnings("deprecation")
	public void onBackPressed()
	{
		finish();
		if(loggedInUser.equals("Guest"))
		{
			Intent moveToAllRecipe= new Intent(AllRecipesScreen.this,LoginActivitySplash.class);
			
			Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
			startActivity(moveToAllRecipe, bundleanimation);
		}
		else
		{
			Intent moveToAllRecipe= new Intent(AllRecipesScreen.this,LoginActivitySplash.class);
			
			Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
			startActivity(moveToAllRecipe, bundleanimation);
			
			alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("LogOut Prompt");
            alertDialog.setMessage("Do You want to LogOut");
            alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   Toast.makeText(getApplicationContext(), "Thanks for Visitng !", 1).show();
               }
            });
        alertDialog.setButton2("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "yoy have pressed cancel", 1).show();
            }
	        // Set the Icon for the Dialog
	        alertDialog.setIcon(R.drawable.ic_launcher);
	        alertDialog.show();
		}
	}
	*/
	
	
	
	//////////////
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	if(loggedInUser.equals("Guest"))
			{
	    		finish();
				Intent moveToAllRecipe= new Intent(AllRecipesScreen.this,LoginActivitySplash.class);
				
				Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
				startActivity(moveToAllRecipe, bundleanimation);
			}
	    	else
	    	{
	        exitByBackKey();

	        //moveTaskToBack(false);

	        return true;}
	    }
	    return super.onKeyDown(keyCode, event);
	}

	protected void exitByBackKey() {

	    AlertDialog alertbox = new AlertDialog.Builder(this)
	    .setMessage("Do you want to logout?")
	    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

	        // do something when the button is clicked
	        public void onClick(DialogInterface arg0, int arg1) {

	            finish();
Intent moveToAllRecipe= new Intent(AllRecipesScreen.this,LoginActivitySplash.class);
				
				Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
				startActivity(moveToAllRecipe, bundleanimation);


	        }
	    })
	    .setNegativeButton("No", new DialogInterface.OnClickListener() {

	        // do something when the button is clicked
	        public void onClick(DialogInterface arg0, int arg1) {
	                       }
	    })
	      .show();

	}
	
	
	
	//====================================================================
	
	
	
	/**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
            displayView(position,getApplicationContext());
        }
    }
 

 

    
    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
	private void displayView(int position,Context activityContext) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) 
        {
        	case 0:
        			fragment = new HomeFragment();
        			if (fragment != null) 
        	        {
        	            FragmentManager fragmentManager = getFragmentManager();
        	            fragmentManager.beginTransaction().replace(rLayout.getId(), fragment).commit();
        	 
        	            // update selected item and title, then close the drawer
        	            mDrawerList.setItemChecked(position, true);
        	            mDrawerList.setSelection(position);
        	            setTitle(navMenuTitles[position]);
        	            mDrawerLayout.closeDrawer(mDrawerList);
        	        } 
        	        else 
        	        {
        	            // error in creating fragment
        	            Log.e("MainActivity", "Error in creating fragment");
        	        }
        			break;
        	
        	case 1:
        			/*if(handler==null)
        				handler=new DataHandler(getBaseContext());
        			fragment = new FavoritesFragment(loggedInUser,Email,handler,activityContext);*/
        		
        		
	        		mDrawerList.setItemChecked(position, true);
		            mDrawerList.setSelection(position);
		            setTitle(navMenuTitles[position]);
		            mDrawerLayout.closeDrawer(mDrawerList);
		            
		            
		            handler= new DataHandler(getBaseContext());
		            handler.open();
		     	   
		            if(Email==null)
		            {
		            	Toast.makeText(activityContext, "Login to See your Favorites !!", Toast.LENGTH_SHORT).show();	
		            }
		            else
		            {
		            	
		            	Cursor c = handler.getFavorites(Email);
			     	   	if (c.moveToFirst()) 
			     	   	{
			     	   		//String list="";
			     	   		positions="";
			     	   		do 
			     	   		{
			     	   			positions=positions+c.getString(2)+"<sp>";
			 	            
			     	   		}while (c.moveToNext());
			 	        
			     	   		Toast.makeText(activityContext, positions, Toast.LENGTH_LONG).show();
			     	   	}
		            
			     	   	else
			     	   	{
			     	   		Toast.makeText(activityContext, "No favorites Added", Toast.LENGTH_SHORT).show();
			     	   		
			     	   	}
		            
		     	   
		     	    handler.close();
		            Bundle bundleToFavoriteScreen=new Bundle();
		            bundleToFavoriteScreen.putString("RecipeClicked", recipes[position]);
		            bundleToFavoriteScreen.putLong("RecipeImage", sendImage[position]);
		            bundleToFavoriteScreen.putString("Ingredients", ingredients[position]);
		            bundleToFavoriteScreen.putString("Description", description[position]);
		            bundleToFavoriteScreen.putString("Extra", extra[position]);
		            bundleToFavoriteScreen.putString("Video", videoURL[position]);
		            bundleToFavoriteScreen.putString("Instructions", instructions[position]);
		            bundleToFavoriteScreen.putString("Username", loggedInUser);
		            bundleToFavoriteScreen.putString("Email", Email);
					
		            bundleToFavoriteScreen.putString("Position",positions);
		            finish();
		    		takeToFavoriteScreen = new Intent(AllRecipesScreen.this, FavoriteListScreen.class);
		    		takeToFavoriteScreen.putExtras(bundleToFavoriteScreen);
		        
		            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);		        	
					Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in, R.anim.slide_out).toBundle();			
					startActivity(takeToFavoriteScreen, bundleanimation);
		            }
        			break;
        			
        	case 2:
        		if(Email==null)
        		Toast.makeText(getApplicationContext(), "You have to Login to Logout!", Toast.LENGTH_SHORT).show();
        		else
        		{
        			exitByBackKey();
        		}
        		
        		mDrawerList.setItemChecked(position, true);
	            mDrawerList.setSelection(position);
	            setTitle(navMenuTitles[position]);
	            mDrawerLayout.closeDrawer(mDrawerList);
        		break;
            /*     case 2:
            fragment = new CommunityFragment();
            break;
 */
        	default:
        	
        			break;
        }
 
        
    }
	
	
	//===================================================================
}
