package com.example.cuisinetreat;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IngredientsLayoutAdapter extends BaseAdapter{

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	public ImageFromURL imageFromUrl;
	
	public IngredientsLayoutAdapter(Activity act, ArrayList<HashMap<String, String>> arr)
	{
		activity = act;
		data = arr;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageFromUrl = new ImageFromURL(activity.getApplicationContext());		
	}
	
	public int getCount()
	{
		return(data.size());
	}
	
	public Object getItem(int position)
	{
		return(position);
	}
	
	public long getItemId(int position)
	{
		return(position);
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;
		
		if(convertView == null)
		{
			view = inflater.inflate(R.layout.ingredients_layout, null);
		}
		
		ImageView thumb_image = (ImageView) view.findViewById(R.id.ingredPicture);
		TextView ingred_name = (TextView) view.findViewById(R.id.ingredText);
		
		HashMap<String, String> eachIngred = new HashMap<String, String>();
		eachIngred = data.get(position);
		
		//Toast.makeText(activity.getApplicationContext(), "position "+position, Toast.LENGTH_SHORT).show();
		
		if( (position%2) == 0)
		{
			RelativeLayout.LayoutParams imageHolderLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			imageHolderLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			
			RelativeLayout.LayoutParams textHolderLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			textHolderLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.ingredPicture);
			textHolderLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			
			thumb_image.setLayoutParams(imageHolderLayoutParams);
			ingred_name.setBackgroundResource(R.drawable.msg_in_selected);
			ingred_name.setLayoutParams(textHolderLayoutParams);
			//ingred_name.setGravity(Gravity.CENTER);
		}
		else
		{
			RelativeLayout.LayoutParams imageHolderLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			imageHolderLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			
			RelativeLayout.LayoutParams textHolderLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			textHolderLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.ingredPicture);
			textHolderLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			
			thumb_image.setLayoutParams(imageHolderLayoutParams);
			ingred_name.setBackgroundResource(R.drawable.msg_out);
			ingred_name.setLayoutParams(textHolderLayoutParams);
			//ingred_name.setGravity(Gravity.CENTER);
		}
		
		ingred_name.setText(eachIngred.get(IngredientsScreen.KEY_TITLE));
		ingred_name.setMinWidth(50);
		ingred_name.setMinHeight(20);
		ingred_name.setPadding(33, 10, 33, 10);
		imageFromUrl.DisplayImage(eachIngred.get(IngredientsScreen.KEY_THUMB_URL), thumb_image);
		
		return(view);
	}
}
