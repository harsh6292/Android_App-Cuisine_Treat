package com.example.cuisinetreat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageFromURL {

	MemoryCache memoryCache = new MemoryCache();
	FileCache fileCache;
	
	private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
	ExecutorService executorService;
	
	public ImageFromURL(Context context)
	{
		fileCache = new FileCache(context);
		executorService = Executors.newFixedThreadPool(5);
	}
	
	final int stub_image = R.drawable.system_loader1;
	
	
	public void DisplayImage(String Url, ImageView getImgView)
	{
		imageViews.put(getImgView, Url);
		
		Bitmap bitmap = memoryCache.get(Url);
		
		if(bitmap != null)
		{
			getImgView.setImageBitmap(bitmap);
		}
		else
		{
			queuePhoto(Url, getImgView);
			getImgView.setImageResource(stub_image);
		}
	}
	
	
	private void queuePhoto(String Url, ImageView getImgView)
	{
		PhotoToLoad photo = new PhotoToLoad(Url, getImgView);
		executorService.submit(new PhotosLoader(photo));
	}
	
	
	private Bitmap getBitmap(String Url)
	{
		File file = fileCache.getFile(Url);
		
		//load from cache
		Bitmap bmap = decodeFile(file);
		if(bmap!=null)
			return(bmap);
		
		//from web
		try
		{
			Bitmap bitmap = null;
			URL imageUrl = new URL(Url);
			
			HttpURLConnection httpConnection = (HttpURLConnection) imageUrl.openConnection();
			httpConnection.setConnectTimeout(30000);
			httpConnection.setReadTimeout(30000);
			httpConnection.setInstanceFollowRedirects(true);
			
			InputStream input = httpConnection.getInputStream();
			OutputStream output = new FileOutputStream(file);
			
			UtilityStreamCopy.copyStream(input, output);
			
			output.close();
			bitmap = decodeFile(file);
			
			return(bitmap);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return(null);
		}
	}
	
	
	//decode image and scale it to reduce memory consumption
	
	private Bitmap decodeFile(File file)
	{
		try
		{
			
			//decode image size
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(file), null, options);
			
			//find correct scale value. it should be power of 2
			
			final int REQUIRED_SIZE = 70;
			int width_temp = options.outWidth, height_temp = options.outHeight;
			int scale = 1;
			
			while(true)
			{
				if( ((width_temp/2)<REQUIRED_SIZE) || ((height_temp/2)<REQUIRED_SIZE) )
				{
					break;
				}
				
				width_temp/=2;
				height_temp/=2;
				
				scale*=2;
			}
			
			
			//decode with inSampleSize
			BitmapFactory.Options optn2 = new BitmapFactory.Options();
			optn2.inSampleSize = scale;
			
			return(BitmapFactory.decodeStream(new FileInputStream(file), null, optn2));
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return(null);
	}
	
	
	//Task for queue
	
	private class PhotoToLoad
	{
		public String Url;
		public ImageView imageView;
		public PhotoToLoad(String getURL, ImageView iv)
		{
			Url = getURL;
			imageView = iv;
		}
	}
	
	class PhotosLoader implements Runnable
	{
		PhotoToLoad photoLoadObject;
		
		PhotosLoader(PhotoToLoad ptl)
		{
			this.photoLoadObject = ptl;
		}
		
		@Override
		public void run()
		{
			if(imageViewReused(photoLoadObject))
				return;
			
			Bitmap bitmap = getBitmap(photoLoadObject.Url);
			memoryCache.put(photoLoadObject.Url, bitmap);
			
			if(imageViewReused(photoLoadObject))
				return;
			
			BitmapDisplayer displayer = new BitmapDisplayer(bitmap, photoLoadObject);
			Activity activity = (Activity)photoLoadObject.imageView.getContext();
			activity.runOnUiThread(displayer);
		}
	}
	
	
	boolean imageViewReused(PhotoToLoad ptl)
	{
		String tag = imageViews.get(ptl.imageView);
		if( (tag==null) || !tag.equals(ptl.Url))
		{
			return(true);
		}
		return(false);
	}
	
	
	//Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable
    {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p)
        {
        	bitmap = b;
        	photoToLoad = p;
        }
        
        public void run()
        {
            if(imageViewReused(photoToLoad))
                return;
            if(bitmap!=null)
                photoToLoad.imageView.setImageBitmap(bitmap);
            else
                photoToLoad.imageView.setImageResource(stub_image);
        }
    }
 
    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }
}

