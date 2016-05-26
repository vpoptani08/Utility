package com.cap.jiraimporter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.AuthenticationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
public class MultipartPost {
	public static String JSONFile="JSONFile";
	public static String logFile="LogFile";
	private static String BASE_URL ="BASE_URL";
	private static String USER ="USER";
	private static String PASSWORD ="PASSWORD";
	private static String attachmentFileName="attachmentFileName";
	private static String attachmentDir="attachmentDir";
	
	
	static Logger logger = Logger.getLogger(MultipartPost.class.getName());	
	static BufferedReader br=null;	
	private static String getDateTime()  
	{  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		//  df.setTimeZone(TimeZone.getTimeZone("PST"));  
		return df.format(new Date());  
	}
	
	
	public static void appendAttachments(String issueKey, String cismID) throws JSONException,  AuthenticationException {
		// Read User/Pass from configuration file
			
		//Authenticate user
		String attachmentFile="";
		
		try {	
			String AuthUSerCredential=CsvToJsonConverter.getPropertiesValues(USER)+":"+CsvToJsonConverter.getPropertiesValues(PASSWORD);	
			String auth = new String(Base64.encode(AuthUSerCredential));
			String Issuekey=issueKey;
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(CsvToJsonConverter.getPropertiesValues(BASE_URL)+"/rest/api/2/issue/"+Issuekey+"/attachments");
			//logger.info(httppost);
			httppost.setHeader("X-Atlassian-Token", "nocheck");
			httppost.setHeader("Authorization", "Basic "+auth);
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");

			attachmentFile=CsvToJsonConverter.getPropertiesValues(attachmentDir)+"\\"+cismID+"\\"+"cism_"+cismID+"_"+getDateTime()+".zip";
			
			logger.info("attachmentFile >>"+attachmentFile);
			File fileToUpload = new File(attachmentFile);
			FileBody fileBody = new FileBody(fileToUpload, "application/octet-stream");
			entity.addPart("file", fileBody);
			httppost.setEntity(entity);
			String mess = "executing request " + httppost.getRequestLine();
			//logger.info(mess);
			//logger.info("entity.toString() "+entity.toString());
			HttpResponse response = null;

			try {
				response = httpclient.execute(httppost);
				//logger.info(response.toString());
			} catch (ClientProtocolException e) {

			} 

		}catch (FileNotFoundException e){			
			logger.info("attachment file not found");
			
		} 
		
		catch (ClientHandlerException e) {
			logger.info("Error invoking REST method");
			e.printStackTrace();
		}catch (IOException e) {
			logger.info("File "+attachmentFile+ "is not available");
			e.printStackTrace();
		}
	}
}
