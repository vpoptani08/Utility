package com.cap.jiraimporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.naming.AuthenticationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import com.cap.jiraimporter.CsvHeader;

/**
 * @author AshutVer
 * 
 */
public class CsvToJsonConverter {

	// CSV file header
	private static final String[] FILE_HEADER_MAPPING = { "Ticket_ID",
			"AssignedToGroup", "Submitter", "Classification", "Component",
			"Priority", "Status", "CreationDate", "ModificationDate",
			"History_StartDate", "Comment", "Details", "ShortDescription",
			"LOGDiary", "Ticket_Typ", "Att_Attachment1", "Att_Attachment2",
			"Att_Attachment3", "Att_Attachment4", "Att_Attachment5", "External" };

	// CSV attributes

	public static String JSONFile = "JSONFile";
	public static String logFile = "logFile";
	private static String BASE_URL = "BASE_URL";
	private static String USER = "USER";
	private static String PASSWORD = "PASSWORD";
	private static String Ticket_ID = "Ticket_ID";
	private static String AssignedToGroup = "AssignedToGroup";
	private static String Submitter = "Submitter";
	private static String Classification = "Classification";
	private static String Component = "Component";
	private static String Priority = "Priority";
	private static String Status = "Status";
	private static String CreationDate = "CreationDate";
	private static String ModificationDate = "ModificationDate";
	private static String History_StartDate = "History_StartDate";
	
	private static String Comment = "Comment";
	private static String Details = "Details";
	private static String ShortDescription = "ShortDescription";
	private static final String LOGDiary = "LOGDiary";
	private static String Ticket_Typ = "Ticket_Typ";
	private static final String Att_Attachment1 = "Att_Attachment1";
	private static final String Att_Attachment2 = "Att_Attachment2";
	private static final String Att_Attachment3 = "Att_Attachment3";
	private static final String Att_Attachment4 = "Att_Attachment4";
	private static final String Att_Attachment5 = "Att_Attachment5";
	private static String External = "External";
	private static final String issueType = "issueType";
	private static final String projectId = "projectId";
	private static final String importerConfig = "importer.config";
	private static final String XmlFile = "XmlFile";
	private static Logger logger = Logger.getRootLogger();
	/*private static String logFilename = "logFilename";*/
	private static final String nullComment = "nullComment";
	private static final String summaryComment = "summaryComment";	
	private static Level logLevel = Level.WARN;
	private static final String nullpointer = null;
	private static String attachmentFileName="attachmentFileName";
	private static String attachmentDir="attachmentDir";
	private static String archName="archName";
	private static String searchResult="searchResult";
	private Map<String, String> hm = new HashMap<String, String>();
	static String dt = "";
	static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	static String user = "";
	static String dairyContents = "";
	static String logDairyString = "";
	static String DetailsStr;
	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException {
		initLogger();
		
		String str=getPropertiesValues(XmlFile);
		System.out.println("XML file for import: "+str);

		File f = new File(str);
		if(f.exists() )
		
		{
			readCsvWriteJSONFile(getPropertiesValues(XmlFile));
	
		} else{			
			
		
			System.out.println("Input Xml file is not available at " +str);
			logger.info("Input Xml file is not available at " +str);
		}
	
	}

	/**
	 * @param csvFileLocation
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	
	public static JSONObject readCsvWriteJSONFile(String csvFileLocation)
			throws IOException, ParserConfigurationException, SAXException {
		String AuthUSerCredential = CsvToJsonConverter
				.getPropertiesValues(USER)
				+ ":"
				+ CsvToJsonConverter.getPropertiesValues(PASSWORD);
		String auth = "";
		if (null != AuthUSerCredential && !AuthUSerCredential.equals("")) {
			// Authenticate user
			auth = new String(Base64.encode(AuthUSerCredential));
		}

		JSONObject jsonforFields = null;

		try {
			File xmlfile = new File(getPropertiesValues(XmlFile));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlfile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getDocumentElement().getElementsByTagName("entry");
			
			
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element e = (Element) nodeList.item(i);
				NodeList childnodes = e.getElementsByTagName("value");
				Ticket_ID = childnodes.item(0).getChildNodes().item(0).getNodeValue();
				AssignedToGroup = childnodes.item(1).getChildNodes().item(0).getNodeValue();
				Submitter = childnodes.item(2).getChildNodes().item(0).getNodeValue();
				Classification = childnodes.item(3).getChildNodes().item(0).getNodeValue();
				Component = childnodes.item(4).getChildNodes().item(0).getNodeValue();
				Priority = childnodes.item(5).getChildNodes().item(0).getNodeValue();
				Status = childnodes.item(6).getChildNodes().item(0).getNodeValue();
				CreationDate = dateformatter(childnodes.item(7).getChildNodes().item(0).getNodeValue());
				ModificationDate = dateformatter(childnodes.item(8).getChildNodes().item(0).getNodeValue());
				History_StartDate = dateformatter(childnodes.item(9).getChildNodes().item(0).getNodeValue());
				NodeList node= childnodes.item(10).getChildNodes();
				if(node.getLength()<=0)
					Comment = getPropertiesValues(nullComment);
				else
					Comment = childnodes.item(10).getChildNodes().item(0).getNodeValue();
				Details = childnodes.item(11).getChildNodes().item(0).getNodeValue();
				
						DetailsStr  = Details.replaceAll("\\\\n", "\n");	
					
				
				
				ShortDescription = childnodes.item(12).getChildNodes().item(0).getNodeValue();
				
				NodeList logDairy = e.getElementsByTagName("diary");
				for (int j = logDairy.getLength() - 1; j >= 0; j--) {
					Element dairyDate = (Element) logDairy.item(j);
					Date d = new Date(1000 * Long.parseLong(dairyDate
							.getElementsByTagName("date").item(0).getChildNodes()
							.item(0).getNodeValue()));
					dt = df.format(d);
					user = dairyDate.getElementsByTagName("user").item(0)
							.getFirstChild().getNodeValue();
					dairyContents = dairyDate
							.getElementsByTagName("diary_contents").item(0)
							.getFirstChild().getNodeValue();
					dairyContents = dairyContents.replaceAll("\\\\n", "\n");
					dairyContents = user + "\t" + dt + "\n" + dairyContents;
					logDairyString = logDairyString + "\n\n" + dairyContents;
				}
				
				Ticket_Typ = childnodes.item(14).getChildNodes().item(0).getNodeValue();
				
				if(node.getLength()!=0)
					External = childnodes.item(20).getChildNodes().item(0).getNodeValue();
			
				JSONObject json = new JSONObject();
				jsonforFields = new JSONObject();
		
				
				JSONObject jsonProjectKey = new JSONObject();
			
				
				jsonProjectKey.put("key", getPropertiesValues(projectId));
				json.put("project", jsonProjectKey);
				
				
				json.put("customfield_11532", Ticket_ID);
				
			
				json.put("customfield_11536",AssignedToGroup);
				
				json.put("customfield_11327",Submitter);
				// set CISM Classification
				json.put("customfield_11535", Classification);
				// set CISM components
				json.put("customfield_10616", Component);
				// set CISM Priority
				json.put("customfield_10617", Priority);
				
				JSONObject jsonPriority = new JSONObject();
				
				jsonPriority.put("id", getPropertiesValues(Priority));
				json.put("customfield_11314", jsonPriority);	
				
				// set CISM Status
				json.put("customfield_11533", Status);
				// set CISM Creation date
				if(CreationDate.contains("."))
				{
					String[] date=(CreationDate.split(" "));
					String[] creationdate=(date[0]).split("\\.");
					String strr=creationdate[2]+"-"+creationdate[1]+"-"+creationdate[0];
					String[] time=(date[1]).split(":");
					String strr2=time[0]+":"+time[1]+":"+time[2]+".000+0100";
					String datetime=strr+"T"+strr2;
					json.put("customfield_11702",datetime);	
				}
				//json.put("customfield_11702", header.getStrCreationDate());
				// set CISM Modification date
				if(ModificationDate.contains("."))
				{
					String[] date=(ModificationDate.split(" "));
					String[] creationdate=(date[0]).split("\\.");
					String strr=creationdate[2]+"-"+creationdate[1]+"-"+creationdate[0];
					String[] time=(date[1]).split(":");
					String strr2=time[0]+":"+time[1]+":"+time[2]+".000+0100";
					String datetime=strr+"T"+strr2;
					json.put("customfield_12404",datetime);	
				}
				//json.put("customfield_11546",header.getStrModificationDate());
				
				// set CISM History_StartDate
				if(History_StartDate.contains(".")){
					String[] date=(History_StartDate.split(" "));
					String[] temp=(date[0]).split("\\.");
					String strr=temp[2]+"-"+temp[1]+"-"+temp[0];
					json.put("customfield_10300",strr);	// ONLY DATE		2011-10-19	
					//System.out.println(strr);
				}
				//json.put("customfield_10300",header.getStrHistory_StartDate());
				
				if((Comment).isEmpty()){						
					json.put("customfield_11335",getPropertiesValues(nullComment));  
					
				} else{
					json.put("customfield_11335",Comment);
				}
				
				
				json.put("description", " Details : \n "+DetailsStr+"\n\n Log Diary: \n"+logDairyString);
				
				logDairyString="";
		
				// 2. Set summary field
				json.put("customfield_11331", ShortDescription);   //German field
				if(ShortDescription.contains("[P]"))
				{
					JSONObject jsonClassi = new JSONObject();
					jsonClassi.put("id", "11379");
					json.put("customfield_11540",jsonClassi);
				}
			
				if(ShortDescription.contains("[I]"))
				{
					JSONObject jsonClassi = new JSONObject();
					jsonClassi.put("id", "11381");
					json.put("customfield_11540",jsonClassi);
				}
				if (AssignedToGroup.contains("INQUIRY"))
				{
					JSONObject jsonClassi = new JSONObject();
					jsonClassi.put("id", "14213");
					json.put("customfield_11540",jsonClassi);
					
				}
			
				
				json.put("customfield_11538", Ticket_Typ);
				
				json.put("customfield_11336", External);
				
				JSONObject jsonIssueType = new JSONObject();

				
				jsonIssueType.put("name", getPropertiesValues(issueType));
				json.put("issuetype", jsonIssueType);
				
				String cismID = json.getString("customfield_11532");
				logger.info("cismID : " + cismID);
				
				
				
				String rearchResult = JQLCheck(auth, cismID);
				searchResult=rearchResult;
				logger.info("rearchResult " + rearchResult);
				
				//update ticket
				if (null != rearchResult) {
					
					json.put("summary",ShortDescription);   // fill dummy re writable text for manual updation of summay field
					jsonforFields.put("fields", json);   //update ticket without updating summary
					
					//check if attachment is available
					String attachmentFile=getPropertiesValues(attachmentDir)+cismID+"\\";
					File f1 = new File(attachmentFile);
					String[] listFiles=f1.list();
					if(f1.exists())
					{
						if(listFiles.length>0)
						{
							attachments(cismID);
						}
					else
						logger.info("No files available inside directory");
					}
											
					logger.info("CISM-ID: "
							+ cismID
							+ " already imported in JIRA with Issue number: "
							+ rearchResult
							+ ". This old Issue will be Updated.");
					
					System.out.println("CISM-ID: "
							+ cismID
							+ " already imported in JIRA with Issue number: "
							+ rearchResult
							+ ". This old Issue will be Updated.");
					
					
					
				 updateIssueData(
							auth,
							CsvToJsonConverter
									.getPropertiesValues(BASE_URL)
									+ "/rest/api/2/issue/" + rearchResult,
							jsonforFields.toString());
					logger.info("Update String" +jsonforFields.toString());

				} 
				
				
				else {
					
					
					json.put("summary",ShortDescription);   // fill dummy re writable text for manual updation of summay field
					
					jsonforFields.put("fields", json);
				
					logger.info("No Issue found for CISM ID-" + cismID
							+ ". A new Issue will be raised.");
					
					
					String issue = postJIRADataToCreateIssue(
							auth,
							CsvToJsonConverter
									.getPropertiesValues(BASE_URL)
									+ "/rest/api/2/issue",
							jsonforFields.toString());
					JSONObject issueObj = new JSONObject(issue);
					logger.info("issueObj:" + issueObj);
					
					//JSONObject projectJSON = json.getJSONObject("project");						
					String newKey = issueObj.getString("key");
					System.out.println(newKey);
					searchResult=newKey;
					
					logger.info("Issue is created having Key:" + newKey);
					logger.info("No Issue found for CISM ID-" + cismID
							+ ". A new Issue will be raised.");
					logger.info("Issue is created having Key:" + newKey);

					System.out.println("CISM-ID: "
							+ cismID
							+ " has no imported issue in JIRA"
							+ ". A new Issue is created with issue Key: " + newKey);		
					
					String attachmentFile=getPropertiesValues(attachmentDir)+cismID+"\\";
					
					File f1 = new File(attachmentFile);
					String[] listFiles=f1.list();
					
					if(f1.exists())
					{
						if(listFiles.length>0)
							attachments(cismID);
						else
							logger.info("No files available inside directory");
					}
				}
			
			}
			
					
			 
			
		} catch (UniformInterfaceException uie) {

			//logger.info(uie.getStackTrace());

		} /*catch (Exception e) {
			logger.info("Error in CsvFileReader !!!");
			e.printStackTrace();
		}*/ 
		catch (FileNotFoundException e){
			
			logger.info(">>FileNotFoundException<<");
		} 
		catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*finally {
			try {
				
			} catch (IOException e) {
				logger.info("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}*/
		return null;

	}

	/**
	 * Get value of properties from the config file
	 * @param property
	 * @return Values of properties
	 * @throws IOException
	 * 
	 */
	public static String getPropertiesValues(String property)
			throws IOException {
		Properties prop = new Properties();
		//String propFileName = "importer.config";
		String propFileName ="importerConfig";
		/*InputStream inputStream = CsvToJsonConverter.class.getClassLoader()
				.getResourceAsStream(propFileName);*/
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
	 * Check for "if CISM ID" already has its own Issue in JIRA
	 * @param auth
	 * @param searchString
	 * @return if Issue exist "issue Key".  If dose not exist "null"
	 * @throws AuthenticationException
	 * @throws JSONException
	 * @throws IOException 
	 */
	private static String JQLCheck(String auth, String searchString)
			throws AuthenticationException, JSONException, IOException {
		String searchURL1 = getPropertiesValues(BASE_URL)+"/rest/api/2/search?jql=CISM-ID~%22"
				+ searchString +"%22";
		System.out.println(searchString);
		searchString="00"+searchString;
		System.out.println(searchString);
		String searchURL2 = getPropertiesValues(BASE_URL)+"/rest/api/2/search?jql=CISM-ID~%22"
				+"00"+ searchString +"%22";
		System.out.println(searchURL1);
		System.out.println(searchURL2);
		logger.info("JQL Search URL " + searchURL1);
		logger.info("JQL Search URL " + searchURL2);
		String searchkey1 = checkForDuplicate(auth,searchURL1);
		String searchkey2 = checkForDuplicate(auth,searchURL2);
		if (searchkey1!=null)
			return searchkey1;
		else
			return searchkey2;
	}
	
	private static String checkForDuplicate(String auth,String searchURL) throws JSONException
	{
		Client client = Client.create();
		WebResource webResource = client.resource(searchURL);
		ClientResponse response = webResource
				.header("Authorization", "Basic " + auth)
				.type("application/json").accept("application/json")
				.get(ClientResponse.class);
		String respo = response.getEntity(String.class);
		
		
		int statusCode = response.getStatus();
	//System.out.println(statusCode);
	
		JSONObject jsonresponse = new JSONObject(respo);
	
		String issueKey = null;
		// get the string value total that will represent the existence of
		// ticket for a particular CISM-ID
		
			JSONArray issueJson = (JSONArray) jsonresponse.get("issues");
			int intJasonIssueLength = issueJson.length();
			//System.out.println(intJasonIssueLength);
			for (int i = 0; i < intJasonIssueLength; i++) {
				JSONObject proj = issueJson.getJSONObject(i);
				issueKey = proj.getString("key");
				if (null != issueKey) {
					break;
				}
			
		
		}
		logger.info("method returns " + issueKey);
		return issueKey;
		
	}
	private static String getDateTime()  
	{  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		//  df.setTimeZone(TimeZone.getTimeZone("PST"));  
		return df.format(new Date());  
	}
	
	private static void attachments(String cismID) throws IOException
	{
		String attachmentFile=getPropertiesValues(attachmentDir)+cismID;
		String archive=getPropertiesValues(archName);
		String source=attachmentFile+"\\";
		String output=attachmentFile+"\\cism_"+cismID+"_"+getDateTime()+".zip";
		File f1 = new File(attachmentFile);
		if(f1.isDirectory()){
			AppZip appzip=new AppZip(cismID,source,output,archive,searchResult);
			appzip.Init();
			
		} else{
			
			logger.info("No attachment:  File is not available at " + attachmentFile);
			
		}
	}

	/**
	 * Posts JSON string to create an issue.
	 * @param auth
	 * @param url
	 * @param data
	 * @return created issue response
	 * @throws AuthenticationException
	 */
	private static String postJIRADataToCreateIssue(String auth, String url,
			String data) throws AuthenticationException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource
				.header("Authorization", "Basic " + auth)
				.type("application/json").accept("application/json")
				.post(ClientResponse.class, data);
		int statusCode = response.getStatus();
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		return response.getEntity(String.class);
	}

	/**
	 * Update JIRA fields
	 * @param auth
	 * @param url
	 * @param data
	 * @return HTTP 204
	 * @throws AuthenticationException
	 * @throws ClientHandlerException
	 */
	private static void updateIssueData(String auth, String url, String data)
			throws AuthenticationException, ClientHandlerException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource
				.header("Authorization", "Basic " + auth)
				.type("application/json").accept("application/json")
				.put(ClientResponse.class, data);
		int statusCode = response.getStatus();
		logger.info("status code>>>>>>>>>>>>>>>>>>>>> " + statusCode);
		logger.info("URL " +url);
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		
	}

	/**
	 * Initiate logger
	 */
	static void initLogger() {
		Layout layout = new PatternLayout("%d{ISO8601} %-5p [%t] %c: %m%n");
		try {
			logger.addAppender(new FileAppender(layout, getPropertiesValues(logFile), true));
		} catch (IOException ex) {
			System.err.println("Error while initializing logger: "
					+ ex.getMessage());
		}
		// logger.setLevel(logLevel);
	}
	
	static String dateformatter(String xmldate){
	Date d = new Date(1000 * Long.parseLong(xmldate));
	dt = df.format(d);
	return dt;
	}
	
}
