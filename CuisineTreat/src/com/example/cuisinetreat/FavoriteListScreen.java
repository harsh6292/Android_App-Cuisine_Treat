package com.example.cuisinetreat;

import java.io.InputStream;

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
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;



@SuppressLint("NewApi")
public class FavoriteListScreen extends Activity implements OnClickListener {


	

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
	
    ScrollView AllRecipeScrollView_fav;
    RelativeLayout rLayout;
    Intent takeToIngredientsScreen;
    String positions;
    
    String eachPos[];
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_favorite_list_screen);
	//}


		nList=parseTheXML("recipe.xml");
		
		Intent getMyIntent=getIntent();
		loggedInUser= getMyIntent.getStringExtra("Username");
		Email=getMyIntent.getStringExtra("Email");
		
		
		
		Bundle receivedBundle = this.getIntent().getExtras();
		//bundleToFavoriteScreen.putString("RecipeClicked", recipes[position]);
		positions=receivedBundle.getString("Position");
		
		eachPos = positions.split("<sp>");
		int[] eachPosValue = new int[eachPos.length];
		for(int i=0; i<eachPos.length; i++)
		{
			//String val = eachPos[i];
			eachPosValue[i] = Integer.valueOf(eachPos[i]);// parseInt(val);
		}
		
		
		/*ratingStars=receivedBundle.getIntArray("Rating");

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
		{
			loggedInUser="Guest";
			ab.setTitle("Welcome "+loggedInUser);
		}
		
		final int width = this.getResources().getDisplayMetrics().widthPixels;
		
		
	
		AllRecipeScrollView_fav = new ScrollView(this);
		
		rLayout = new RelativeLayout(this);//(RelativeLayout) findViewById(R.id.AllRecipeLayout);
		rLayout.setId(2500);
		
		RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
		
		rLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
		rParams.addRule(RelativeLayout.CENTER_VERTICAL);
		
		
		
		
		for(int i=0;i<nList.getLength(); i++)
		{
			nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) 
		    {
		    	Element eElement = (Element) nNode;
		    	recipes[i]= getValue("Name", eElement) ;
		    	//ingredients[i]= getValue("Ingredients", eElement) ;
		    	//description[i]=getValue("Description", eElement);
		    	//extra[i]=getValue("Extra", eElement);
		    	//videoURL[i]=getValue("Video", eElement);
		    	//instructions[i]=getValue("Instructions", eElement);
		    }
		}
						
		
		
		
		//for(int i=0;i<nList.getLength(); i++)
		{
			for(int j=0;j<eachPosValue.length;j++)
			{
				//if(eachPosValue[j]==i)
				int i = eachPosValue[j];
				{
				/*	nNode = nList.item(i);
				    if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				    {
				    	Element eElement = (Element) nNode;
				    	recipes[i]= getValue("Name", eElement) ;
				    	ingredients[i]= getValue("Ingredients", eElement) ;
				    	description[i]=getValue("Description", eElement);
				    	extra[i]=getValue("Extra", eElement);
				    	videoURL[i]=getValue("Video", eElement);
				    	instructions[i]=getValue("Instructions", eElement);
				    }*/
				//}
			//}
			
		
		
					//RelativeLayout rLayout = new RelativeLayout(this);
					//RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
					//rLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
					//rParams.addRule(RelativeLayout.CENTER_VERTICAL);
					
					ImageView imgView = new ImageView(this);
					imgView.setId(1000+j);
					imgView.setImageResource(imageID[i]);
					
					Typeface tf =Typeface.createFromAsset(getAssets(), "future.ttf");
					
					TextView txtView = new TextView(this);
					txtView.setId(3000+j);
					txtView.setText(recipes[i]);
					//txtView.setTextSize(34);
					txtView.setTypeface(tf,34);
					txtView.setLines(2);
					
					RelativeLayout rSecondLayout = new RelativeLayout(this);
		            rSecondLayout.setId(2000+j);
		            rSecondLayout.setBackground(getResources().getDrawable(R.drawable.card_background));
		            
		            RelativeLayout.LayoutParams rSecondParams = new RelativeLayout.LayoutParams(width-25, RelativeLayout.LayoutParams.WRAP_CONTENT);//((2*width)/3));//(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		            //rSecondParams.setMargins(20, 20, 20, 0);
		            
		            RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
					imageParams.setMargins(15, 15, 15, 15);
					
					RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
					textParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
					textParams.addRule(RelativeLayout.BELOW, imgView.getId());
					textParams.setMargins(15, 0, 15, 15);
					
					
					if(j==0)
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
					
					//rSecondLayout.setOnClickListener(this);
					//rSecondParams.addRule(RelativeLayout.BELOW);
					
			
				}
			}
		}
		
		AllRecipeScrollView_fav.addView(rLayout, rParams);
		
		setContentView(AllRecipeScrollView_fav);		
		
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
		
		Intent moveToRecipeDetail= new Intent(FavoriteListScreen.this,RecipeDetail.class);
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorite_list_screen, menu);
		return true;
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
		Intent moveToAllRecipe= new Intent(FavoriteListScreen.this,AllRecipesScreen.class);
		moveToAllRecipe.putExtra("Username", loggedInUser);
		moveToAllRecipe.putExtra("Email", Email);
	
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		//ratingBar=null;
		finish();
		startActivity(moveToAllRecipe, bundleanimation);
	}
/*public class FavoriteListScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_list_screen);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorite_list_screen, menu);
		return true;
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_favorite_list_screen, container, false);
			return rootView;
		}
	}

}
