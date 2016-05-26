package com.cap.jiraimporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.xml.internal.messaging.saaj.soap.XmlDataContentHandler;


public class XmlToJira {
	
	static String [][] xmlData = new String [40][40];
	
 	private static final String importerConfig = "importer.config";
	private static final String XmlFile = "XmlFile";
	private static final String mappingFile = "mappingFile";
	
	private static final String username="username";
	private static final String password="password";
	static String dt = "";
//	static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	static String user = "";
	static String dairyContents = "";
	static String logDairyString = "";
	static Scanner scRead = new Scanner(System.in);

	
	
	public static JSONObject readXmlWriteJSONFile(String xmlFileLocation)
			throws IOException, ParserConfigurationException, SAXException {
		File xmlfile = new File(getPropertiesValues(XmlFile));
		File mapping = new File(getPropertiesValues(mappingFile));
		RandomAccessFile raf = new RandomAccessFile(mapping, "rw");
		Scanner scanner = new Scanner(mapping);
		JSONObject json =new JSONObject();
		
		
			try
			{
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(xmlfile);
				doc.getDocumentElement().normalize();
	
				NodeList nodeList = doc.getDocumentElement().getElementsByTagName("field");
				NodeList nodeData = doc.getDocumentElement().getElementsByTagName("field_value");
				System.out.println("nodelist="+nodeList.getLength());
				System.out.println("nodedata="+nodeData.getLength());
				
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element e = (Element) nodeList.item(i);
					xmlData[i][0]=e.getElementsByTagName("name").item(0).getTextContent();
				}
				
				for(int i = 0; i < nodeData.getLength(); i++)
				{		
						Element e2 = (Element) nodeData.item(i);
						xmlData[i][1]=e2.getElementsByTagName("value").item(0).getTextContent();
						NodeList logDairy = e2.getElementsByTagName("diary");
						for (int j = logDairy.getLength() - 1; j >= 0; j--) {
							Element dairyDate = (Element) logDairy.item(j);
							Date d = new Date(1000 * Long.parseLong(dairyDate
									.getElementsByTagName("date").item(0).getChildNodes()
									.item(0).getNodeValue()));
							//dt = df.format(d);
							dt=d.toString();
							user = dairyDate.getElementsByTagName("user").item(0)
									.getFirstChild().getNodeValue();
							dairyContents = dairyDate
									.getElementsByTagName("diary_contents").item(0)
									.getFirstChild().getNodeValue();
							dairyContents = dairyContents.replaceAll("\\\\n", "\n");
							dairyContents = user + "\t" + dt + "\n" + dairyContents;
							logDairyString = logDairyString + "\n\n" + dairyContents;
						}
						
				}
				for (int i = 0; i < xmlData.length; i++)
				{ 
						
						//System.out.println(xmlData[i][0].toString());
						if(xmlData[i][1].toString().equals(""))
							break;
						else
							System.out.println(xmlData[i][1].toString());
				}
				for(int i = 0; i < xmlData.length ; i++)
				{
					  
						String[] line = scanner.nextLine().split("=");
						System.out.println("line..."+line[0]);
						if(line[0].equals(xmlData[i][0].toString()))
						{
							if(line[0].equalsIgnoreCase("Log Diary"))
							{
								json.put("description", "Log Diary: \n"+logDairyString);
								continue;
							}
							json.put(xmlData[i][1], line[1]);
							
						}
						else
						{
							System.out.println("bye");
							break;
						}
						
				}
				System.out.println(json);

				
			}
			
				
			catch(UniformInterfaceException uie)
			{
		
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	public static void setUserPass()
	{
		System.out.println("Enter Username");
		
		
	}
	
	
	public static String getPropertiesValues(String property)
			throws IOException {
		Properties prop = new Properties();
		
		String propFileName ="importerConfig";
		
		InputStream inputStream = new FileInputStream(new File(importerConfig));
		
		if (null != inputStream) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("Property file'" + propFileName
					+ "' not found in the classpath");
		}
		 
		return prop.getProperty(property);
	}

	
	/**
	 * @param args
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		JSONObject temp=readXmlWriteJSONFile(getPropertiesValues(XmlFile));

	}

}
