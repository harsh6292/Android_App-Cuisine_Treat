package com.example.cuisinetreat;

import java.io.File;
import java.net.URLEncoder;

import android.content.Context;

public class FileCache {

	private File cacheDir;
	
	
	//Find directory to save cached images
	public FileCache(Context context)
	{
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
			cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "IngredientsList");
		}
		else
		{
			cacheDir = context.getCacheDir();
		}
		
		if(!cacheDir.exists())
		{
			cacheDir.mkdirs();
		}
	}
	
	public File getFile(String Url)
	{
		String filename = String.valueOf(Url.hashCode());//URLEncoder.encode(URL);
		
		File file = new File(cacheDir, filename);
		
		return(file);
	}
	
	
	public void clear()
	{
		File[] files = cacheDir.listFiles();
		
		if(files == null)
		{
			return;
		}
		
		for(File f:files)
			f.delete();
	}
}
