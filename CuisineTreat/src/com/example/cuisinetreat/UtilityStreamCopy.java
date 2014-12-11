package com.example.cuisinetreat;

import java.io.InputStream;
import java.io.OutputStream;

public class UtilityStreamCopy {

	public static void copyStream(InputStream input, OutputStream output)
	{
		final int buffer_size = 1024;
		try
		{
			byte[] getBytes = new byte[buffer_size];
			
			for(;;)
			{
				int count = input.read(getBytes, 0, buffer_size);
				if( count == -1 )
				{
					break;
				}
				output.write(getBytes, 0, count);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
