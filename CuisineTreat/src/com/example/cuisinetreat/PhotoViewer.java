package com.example.cuisinetreat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class PhotoViewer extends Activity {
	
	static GridView gridOfPhotos;
	static Intent intent;
	int pos=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_photo_viewer);
String posss="In Photovieweractivity "+pos;
		//Toast.makeText(getApplicationContext(), posss, Toast.LENGTH_SHORT).show();
		intent=getIntent();
	
			pos=intent.getIntExtra("Pos", 0);
		gridOfPhotos=(GridView)findViewById(R.id.gridofPhotos);
		gridOfPhotos.setAdapter(new PhotoImageAdapter(this, pos));
		/*if(pos==0)
		{
			gridOfPhotos.setAdapter(new PhotoImageAdapter(this, pos));
		}
		else if(pos==1)
		{
			gridOfPhotos.setAdapter(new PhotoImageAdapter(this, pos));
		}
		else if(pos==2)
		{
			gridOfPhotos.setAdapter(new PhotoImageAdapter(this, pos));
		}
		else if(pos==3)
		{
			gridOfPhotos.setAdapter(new PhotoImageAdapter(this, pos));
		}
		else if(pos==4)
		{
			gridOfPhotos.setAdapter(new PhotoImageAdapter(this, pos));
		}
		else if(pos==5)
		{
			gridOfPhotos.setAdapter(new PhotoImageAdapter(this, pos));
		}*/
		/*if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	//	gridOfPhotos=new GridView(getApplicationContext());
		

		/*gridOfPhotos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
						getApplicationContext(),
						"Clicked", Toast.LENGTH_SHORT).show();
				//String s="ada";
			//System.out.println(s);
				if(position==0)
				{
					String s="a";
					String g="b";
				}
				

			}
		});*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_viewer, menu);
		return true;
	}

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
	@SuppressLint("NewApi")
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_photo_viewer,
					container, false);
			return rootView;
		}
	}

}
