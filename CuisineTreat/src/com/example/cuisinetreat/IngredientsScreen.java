package com.example.cuisinetreat;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class IngredientsScreen extends Activity {

	
	static final String ingredientsURL = "http://www4.ncsu.edu/~haggarw/ingredients/ingredients.xml";
	
	static final String KEY_INGREDIENTS = "ingredients";
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_THUMB_URL = "thumb_url";
	
	public ImageFromURL ImageFromURLObject;
	
	ListView listView;
	IngredientsLayoutAdapter layoutAdapter;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingredients_screen);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		ArrayList<HashMap<String, String>> listOfAllIngredients = new ArrayList<HashMap<String, String>>();
		
		XMLIngredientsParser ingredientsParser = new XMLIngredientsParser();
		//System.out.println("XML loc : "+ingredientsURL);
		String getXML = ingredientsParser.getXMLFromURL(ingredientsURL);
		//System.out.println("got xml");
		Document document = ingredientsParser.getDomElement(getXML);
		//System.out.println("got dom element");
		NodeList eachingredientsList = document.getElementsByTagName(KEY_INGREDIENTS);
		
		LinearLayout mainScreenLayout = new LinearLayout(this);
		
		for(int i=0; i< eachingredientsList.getLength(); i++)
		{
			//Toast.makeText(this.getApplicationContext(), "position "+i, Toast.LENGTH_SHORT).show();
			HashMap<String, String> map = new HashMap<String, String>();
			
			Element element = (Element) eachingredientsList.item(i);
			
			map.put(KEY_TITLE, ingredientsParser.getValue(element, KEY_TITLE));
			map.put(KEY_THUMB_URL, ingredientsParser.getValue(element, KEY_THUMB_URL));
			
			listOfAllIngredients.add(map);

		}
		
		listView = (ListView) findViewById(R.id.ingredListView);
		layoutAdapter = new IngredientsLayoutAdapter(this, listOfAllIngredients);
		
		listView.setAdapter(layoutAdapter);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingredients_screen, menu);
		return true;
	}
	
	
	@SuppressLint("NewApi")
	@Override
	public void onBackPressed()
	{
		finish();
		overridePendingTransition(R.anim.slide_in, R.anim.slide_out); 
		/*Intent moveToRecipeDetail = new Intent(IngredientsScreen.this, RecipeDetail.class);
		//moveToAllRecipe.putExtra("Username", loggedUser);
		Bundle bundleanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in,R.anim.slide_out).toBundle();
		startActivity(moveToRecipeDetail, bundleanimation);*/
	}

}
