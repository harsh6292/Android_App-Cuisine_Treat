package com.example.cuisinetreat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoImageAdapter extends BaseAdapter {
	private Context context;
	private final int post;
	static final int[] red_peppers = new int[] { R.drawable.red_peppers_1, 
		R.drawable.red_peppers_2,
		R.drawable.red_peppers_3,
	R.drawable.red_peppers_4,R.drawable.red_peppers_5,R.drawable.red_peppers_6};
	
	
	static final int[] choc_muffins = new int[] { R.drawable.choc_muffin_1, 
		R.drawable.choc_muffin_2,
		R.drawable.choc_muffin_3,
	R.drawable.choc_muffin_4,R.drawable.choc_muffin_5,R.drawable.choc_muffin_6};
	
	static final int[] caesar_wrap = new int[] { R.drawable.caesar_wrap_1, 
		R.drawable.caesar_wrap_2,
		R.drawable.caesar_wrap_3,
	R.drawable.caesar_wrap_4,R.drawable.caesar_wrap_5,R.drawable.caesar_wrap_6};
	
	static final int[] mango_rice_pudding = new int[] { R.drawable.mango_rice_pudding_1, 
		R.drawable.mango_rice_pudding_2,
		R.drawable.mango_rice_pudding_3,
	R.drawable.mango_rice_pudding_4,R.drawable.mango_rice_pudding_5,R.drawable.mango_rice_pudding_6};
	
	static final int[] russian_salad = new int[] { R.drawable.russian_salad_1, 
		R.drawable.russian_salad_2,
		R.drawable.russian_salad_3,
	R.drawable.russian_salad_4,R.drawable.russian_salad_5,R.drawable.russian_salad_6};
	
	static final int[] vegetable_biryani = new int[] { R.drawable.vegetable_biryani_1, 
		R.drawable.vegetable_biryani_2,
		R.drawable.vegetable_biryani_3,
	R.drawable.vegetable_biryani_4,R.drawable.vegetable_biryani_5,R.drawable.vegetable_biryani_6};
	
	public PhotoImageAdapter(Context context, int position) {
		this.context = context;
		this.post = position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
String posssss=""+post;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//Toast.makeText(context, posssss, Toast.LENGTH_SHORT).show();
		
		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.gallery, null);

			
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.gallery_item_image);

			
			if(post==0)
				imageView.setImageResource(red_peppers[position]);
			else if(post==1)
				imageView.setImageResource(choc_muffins[position]);
			else if(post==2)
				imageView.setImageResource(caesar_wrap[position]);
			else if(post==3)
				imageView.setImageResource(mango_rice_pudding[position]);
			else if(post==4)
				imageView.setImageResource(russian_salad[position]);
			else if(post==5)
				imageView.setImageResource(vegetable_biryani[position]);

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	@Override
	public int getCount() {
		return red_peppers.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
