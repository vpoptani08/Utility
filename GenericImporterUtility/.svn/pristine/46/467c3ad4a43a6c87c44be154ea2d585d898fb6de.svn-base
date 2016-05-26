package com.cap.jiraimporter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.AuthenticationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;


public class JIRAImporterRESTClient {
	public static String JSONFile="JSONFile";
	public static String logFile="LogFile";
	private static String BASE_URL ="BASE_URL";
	private static String USER ="USER";
	private static String PASSWORD ="PASSWORD";
	private static String csvFile = "csvFile";
	//static Logger logger = Logger.getLogger(JIRAImporterRESTClient.class.getName());	
	 static BufferedReader br=null;
		private static Logger logger = Logger.getRootLogger();
		private static String logFilename = "jiraimporter.log";
		
		private static Level logLevel = Level.WARN;
	  public static void main(String[] args) throws JSONException, IOException {
		  initLogger();
		  // Read User/Pass from configuration file
		String AuthUSerCredential=CsvToJsonConverter.getPropertiesValues(USER)+":"+CsvToJsonConverter.getPropertiesValues(PASSWORD);
		
		
		
		//Authenticate user
		String auth = new String(Base64.encode(AuthUSerCredential));
		
		
		
		// Read CSV file and Write JSON file
		  // CsvToJsonConverter converter=new CsvToJsonConverter();		
	     	//converter.readCsvWriteJSONFile(converter.getPropertiesValues(csvFile));
		
		
		//Read JSON file and Create Issue 
		try {			
			
			//================= create issue ====================
			
			/*String line = "";
			
			 //Check if JSON file is Emptly
					
			JSONObject jObjectIssue=CsvToJsonConverter.readCsvWriteJSONFile(getPropertiesValues(csvFile));
			String cismID=jObjectIssue.getString("customfield_10102");
			
			
	//String searchString="123456";
			
			String rearchResult=checkForDuplicate(auth,  cismID);
		
			
			
			if((rearchResult.equals("1"))){
				System.out.println("CISM-ID: "+cismID+" already imported in JIRA with Issue number: "+rearchResult+". This old Issue will be Updated.");
				logger.info("CISM-ID: "+cismID+" already imported in JIRA with Issue number: "+rearchResult+". This old Issue will be Updated.");
			}
			
			else{
				
				System.out.println("No Issue found for CISM ID-"+cismID +". A new Issue will be raised.");
				String issue = postJIRADataToCreateIssue(auth, CsvToJsonConverter.getPropertiesValues(BASE_URL)+"/rest/api/2/issue", line);
				JSONObject issueObj = new JSONObject(issue);
				String newKey = issueObj.getString("key");
				System.out.println("Issue is created having Key:"+newKey);
				logger.info("No Issue found for CISM ID-"+cismID +". A new Issue will be raised.");
			}
			*/
			
			
			
			
			 
			//Read JSON file
			/* br = new BufferedReader(new FileReader(CsvToJsonConverter.getPropertiesValues(JSONFile)));
			 while ((line = br.readLine()) != null) {	
				 */
					// read CISM ticket ID and make decide to "Create or Update"
					
				 
/*				String[] str=line.split(",");
				
				JSONObject issueJson=new JSONObject(line);
				
			System.out.println(issueJson.has("customfield_10102"));
				 
				 */
				 
			//String issue = postJIRADataToCreateIssue(auth, CsvToJsonConverter.getPropertiesValues(BASE_URL)+"/rest/api/2/issue", line);
			

			
			/*System.out.println(issue);
			JSONObject issueObj = new JSONObject(issue);
			String newKey = issueObj.getString("key");
			System.out.println("Issue is created having Key:"+newKey);
			 }
				 
			 br.close();*/
			
			
			
		
			
			//=================== field updation=======================
			
			// Working
			// updation field using PUT method
			//String IssueString="{\"fields\":{\"description\":\"test test\"}}";
			
			String IssueString="{\"fields\":{\"customfield_10112\":\"hello hello Navigationspfadollte aus ctime in start identisch übernommen werden\",\"summary\":\" hello hello [ALL] [P] [start] - PRD - <<Abweichende Lieferantenbenennung start im Vergleich zu Globus>>\",\"customfield_10103\":\"hello hello START_ashu\",\"customfield_10102\":\"0026607545\",\"customfield_10111\":\"hello hello 19.11.2014 09:14:49\",\"customfield_10114\":\"hello hello dehrman 19.11.2014 09:14:49  Bitte Hinweise beachten.    anhdentisch übernommen werden    \",\"customfield_10113\":\"hello hello Problem\",\"issuetype\":{\"name\":\"CISM-Bug\"},\"customfield_10110\":\"hello hello 19.11.2014 09:14:49\",\"customfield_10115\":\"hello hello Rel. v14.1b.07.18\",\"project\":{\"key\":\"VCIS\"},\"customfield_10108\":\"hello hello Assigned\",\"customfield_10109\":\"05.11.2014 09:03:09\",\"customfield_10104\":\"mbalg59\",\"customfield_10105\":\"PROBLEM_OTHERS\",\"customfield_10106\":\"START_GENERAL\",\"customfield_10107\":\"3\"}}";
			
		    updateIssueData(auth, CsvToJsonConverter.getPropertiesValues(BASE_URL)+"/rest/api/2/issue/VCIS-26800073", IssueString);			
				 
			//br.close();
			
			
			
			//========== check for existing issue =================
		
			
	
			
		/*	String searchString="123456";
			
			String rearchResult=checkForDuplicate(auth,  searchString);
		
			
			
			if((rearchResult.equals("1"))){
				System.out.println("CISM-ID: "+searchString+" already imported in JIRA with Issue number: "+rearchResult+". This old Issue will be Updated.");
				logger.info("CISM-ID: "+searchString+" already imported in JIRA with Issue number: "+rearchResult+". This old Issue will be Updated.");
			}
			
			else{
				
				System.out.println("No Issue found for CISM ID-"+searchString +". A new Issue will be raised.");
				logger.info("No Issue found for CISM ID-"+searchString +". A new Issue will be raised.");
			}*/
			
				 
			 //============================= Attachements========================
			 
	
			//attach files
			/*
			//"fields":{"attachment":{"filename":"filename"}}}
			
			String attachment="{\"fields\":{\"Attachments\":{\"name\":\"tmpdata.txt\"}}}";
			updateIssueData(auth, BASE_URL+"/rest/api/2/issue/VCIS-26221684/attachments", attachment);
			//	HttpPost httppost = new HttpPost(jira_attachment_baseURL+"/api/latest/issue/"+issueKey+"/attachments");
			
			*/
			   
			//=====********** Temp section (will be removed later***************===========
	/*		String Issuekey="VCIS-26800052";
			String filePath="D:\\workspace\\V4\\JiraImporter\\src\\main\\java\\com\\cap\\jiraimporter\\temp.csv";
			

			 HttpClient httpclient = new DefaultHttpClient();
			 HttpPost httppost = new HttpPost("http://de-dai-xtech-ls.corp.capgemini.com:8080/jira/rest/api/2/issue/"+Issuekey+"/attachments");
			 System.out.println(httppost);
			 httppost.setHeader("X-Atlassian-Token", "nocheck");
			 httppost.setHeader("Authorization", "Basic "+auth);
			 MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			 System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");

			File fileToUpload = new File(filePath);
		//	FileBody fileBody = new FileBody(fileToUpload, "application/octet-stream");
			FileBody fileBody = new FileBody(fileToUpload, "application/octet-stream");
			entity.addPart("file", fileBody);

			httppost.setEntity(entity);
			
			String mess = "executing request " + httppost.getRequestLine();
		    logger.info(mess);
		    System.out.println(mess);
		    
			System.out.println(entity.toString());
			HttpResponse response = null;
			
			try {
			    response = httpclient.execute(httppost);
			    System.out.println(response.toString());
			} catch (ClientProtocolException e) {
			    
			} catch (IOException e) {
			  
			}catch (ClientHandlerException e) {
				System.out.println("Error invoking REST method");
				e.printStackTrace();
			}*/
			
			//************************************************************
		
		
		} catch (AuthenticationException e) {
			System.out.println("Username or Password wrong!");
			e.printStackTrace();
		} 
		 

	}

	
	
	private static String postJIRADataToCreateIssue(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
				.accept("application/json").post(ClientResponse.class, data);
		int statusCode = response.getStatus();
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		return response.getEntity(String.class);
	}
	
	private static String attachfile(String auth, String url, String file) throws AuthenticationException, ClientHandlerException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
				.accept("application/json").post(ClientResponse.class, file);
		int statusCode = response.getStatus();
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		return response.getEntity(String.class);
	}
	
	private static String updateIssueData(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
				.accept("application/json").put(ClientResponse.class, data);
		int statusCode = response.getStatus();
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		return response.getEntity(String.class);
	}
	
private static String checkForDuplicate(String auth,String searchString) throws AuthenticationException, ClientHandlerException, JSONException {
    String searchURL="http://de-dai-xtech-ls.corp.capgemini.com:8080/jira/rest/api/2/search?jql=CISMID="+searchString;
	Client client = Client.create();
	WebResource webResource = client.resource(searchURL);
	ClientResponse response  = webResource.header("Authorization", "Basic " + auth).type("application/json").accept("application/json").get(ClientResponse.class);
	
	String respo = response.getEntity(String.class);
//	System.out.println(respo);
	JSONObject jsonresponse = new JSONObject(respo);
	String issueKey=null;
	
	//get the string value total that will represent the existence of ticket for a perticular CISM-ID
	String total=jsonresponse.getString("total");
	
	if((Integer.parseInt(total))==1){
		//System.out.println(jsonresponse.getString("issues"));
		 
		JSONArray issueJson=new JSONArray(jsonresponse.getString("issues"));
		
		for (int i = 0; i < issueJson.length(); i++) {
		    JSONObject proj = issueJson.getJSONObject(i);
		   // System.out.println("Key:"+proj.getString("key"));
		   issueKey=proj.getString("key");
		}
	}
	
	else{
		
		issueKey="No key found";
		
	}
	
	//System.out.println(issueKey);
	
	return issueKey;
	
}
/**
 * Initializes the logger.
 */
private static void initLogger() {
	Layout layout = new PatternLayout("%d{ISO8601} %-5p [%t] %c: %m%n");
	try {
		logger.addAppender(new FileAppender(layout, logFilename, true));
		}
	catch (IOException ex) {
		System.err.println("Error while initializing logger: " + ex.getMessage());
	}
//	logger.setLevel(logLevel);
}

public static String getPropertiesValues(String property) throws IOException{
	Properties prop=new Properties();
	String propFileName="importer.config";		
	InputStream inputStream=CsvToJsonConverter.class.getClassLoader().getResourceAsStream(propFileName);
	prop.load(inputStream);
	if(inputStream==null){
		throw new FileNotFoundException("Property file'"+ propFileName+"' not found in the classpath");
	}
	String propertyValue=prop.getProperty(property);
	
	return propertyValue;
}

}
