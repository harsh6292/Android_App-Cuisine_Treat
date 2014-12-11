package com.example.cuisinetreat;


import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Application;

public class ParserClass extends Application {

	public NodeList nList;
	
	
	public void setNodeList(String xmlFile)
	{
		nList=parseTheXML(xmlFile);
	}
	
	public NodeList getNodeList()
	{
		return nList;
	}
	public NodeList parseTheXML(String xmlName)
	{
	  try {
		   InputStream is = this.getAssets().open(xmlName);

		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory
		     .newInstance();
		   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document doc = dBuilder.parse(is);
		   doc.getDocumentElement().normalize();
		   nList = doc.getElementsByTagName("Recipe");
		   /*for (int temp = 0; temp < nList.getLength(); temp++) {
		   nNode = nList.item(temp);
		    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		     Element eElement = (Element) nNode;
		     tv1.setText(tv1.getText() + "\n\nName : "
		       + getValue("name", eElement) + "\n");
		     tv1.setText(tv1.getText() + "Price : "
		       + getValue("price", eElement) + "\n");
		     tv1.setText(tv1.getText() + "-----------------------");

		    }
		   }*/
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	return nList;
	 }

	private static String getValue(String sTag, Element eElement) {
		  NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
		    .getChildNodes();
		  Node nValue = (Node) nlList.item(0);
		  return nValue.getNodeValue();
		 }
}
