package com.example.cuisinetreat;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

public class PlayVideo extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);

		
			
			Bundle receivedBundle=this.getIntent().getExtras();
			String urlForVideo= receivedBundle.getString("URL");
			//String urlForVideo="http://www.youtube.com/embed/OCL2oENa8GU";
			/*Button btn=(Button)findViewById(R.id.playVideoBtn);
			btn.setOnClickListener(this);
			*/
			
	
			
			WebView webview = (WebView) findViewById(R.id.webview);
			// webSettings.setBuiltInZoomControls(true);
			webview.setWebChromeClient(new WebChromeClient()); 

			//webView.setWebViewClient(new WebViewClient()); 

			webview.getSettings().setJavaScriptEnabled(true); 


			String data = String.format("<iframe width='315' height='315' src=\"%s\" frameborder='0' allowfullscreen></iframe>",urlForVideo); 
			webview.loadData(data, "text/html", "UTF-8"); 
			webview.getSettings().setCacheMode(MODE_WORLD_WRITEABLE);
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_video, menu);
		return true;
	}
	
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

	
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		
		/*case R.id.playVideoBtn:
			
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
		    Log.i("Video", "Video Playing....");
			break;*/
		
		default:
		break;
		
		}
		}

}
