package com.example.cuisinetreat;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] button_names;

	public ImageAdapter(Context context, String[] argsButtonsArray) {
		this.context = context;
		this.button_names = argsButtonsArray;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.button, null);

			// set value into textview
			/**/

			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			String mobile = button_names[position];

			if (mobile.equals("Video")) {
				imageView.setImageResource(R.drawable.youtube);
			} else if (mobile.equals("Facebook")) {
				imageView.setImageResource(R.drawable.facebook);
			} else if (mobile.equals("Twitter")) {
				imageView.setImageResource(R.drawable.twitter);
			} else {
				imageView.setImageResource(R.drawable.gallery_1);
			}

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	@Override
	public int getCount() {
		return button_names.length;
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
