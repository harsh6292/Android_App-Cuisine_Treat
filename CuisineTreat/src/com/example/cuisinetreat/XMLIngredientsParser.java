package com.example.cuisinetreat;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;


public class XMLIngredientsParser {

	
	//Get XML File
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public String getXMLFromURL(String URL)
	{
		String XML = null;
		
		try
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(URL);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			HttpEntity httpEntity = httpResponse.getEntity();
			
			XML = EntityUtils.toString(httpEntity);			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return(XML);		
	}
	
	
	//Get XML DOM Element
	public Document getDomElement(String XML)
	{
		org.w3c.dom.Document document = null;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try
		{
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			
			InputSource input = new InputSource(new StringReader(XML));
			//input.setCharacterStream(new StringReader(XML));
			
			document = docBuilder.parse(input);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return(document);
	}
	
	//Get Node Value
	public final String getElementValue(Node element)
	{
		Node child;
		
		if(element!=null)
		{
			if(element.hasChildNodes())
			{
				for( child = element.getFirstChild(); child!=null; child = child.getNextSibling())
				{
					if(child.getNodeType() == Node.TEXT_NODE)
					{
						return(child.getNodeValue());
					}
				}
			}
		}
		
		return "";
	}
	
	//Get Node
	public String getValue(Element item, String string)
	{
		NodeList list = item.getElementsByTagName(string);
		return(this.getElementValue(list.item(0)));
	}
}
