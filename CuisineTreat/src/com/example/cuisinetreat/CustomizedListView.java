package com.example.cuisinetreat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomizedListView extends ArrayAdapter<String>
{
	private final Activity context;
	private final String[] recipesNames;
	private final int[] imageID;
	
	static class ListViewHolder
	{
		View rowView; 
		TextView txtTitle;
		
		ImageView thumbnail;
		
	}
	public CustomizedListView(Activity context, String[] recipes, int[] id)
	{
		super(context, R.layout.list_row, recipes);
		this.context = context;
		this.recipesNames = recipes;
		this.imageID = id;
	}
	
	@Override
	/*public View getView(int position, View view, ViewGroup parent)
	{
		ListViewHolder listViewHolderObj=new ListViewHolder();
		if(view==null)
		{
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.list_row, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
		//TextView artist = (TextView) rowView.findViewById(R.id.artist);
		
		ImageView thumbnail = (ImageView) rowView.findViewById(R.id.thumbnail);
		//ImageView arrow = (ImageView) rowView.findViewById(R.id.)
		
		txtTitle.setText(singersNames[position]);
		thumbnail.setImageResource(imageID[position]);
		}
		return rowView;
		
	}
*/
	public View getView(int position, View view, ViewGroup parent)
	{
		ListViewHolder viewHolder;
		LayoutInflater inflater;
	    if(view == null){
	    	inflater=context.getLayoutInflater();
	        view = inflater.inflate(R.layout.list_row,parent,false);
	 
	        viewHolder = new ListViewHolder();
	 
	        viewHolder.txtTitle = (TextView) view.findViewById(R.id.title);
			//TextView artist = (TextView) rowView.findViewById(R.id.artist);
			
	        viewHolder.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
			
	        view.setTag(viewHolder);
	    }else{
	        viewHolder = (ListViewHolder)view.getTag();
	    }
	 
	  //  ListViewHolder item = (ListViewHolder)getItem(position);
	 
	    viewHolder.txtTitle.setText(recipesNames[position]);
	    viewHolder.thumbnail.setImageResource(imageID[position]);
	    return view;
	}

}
