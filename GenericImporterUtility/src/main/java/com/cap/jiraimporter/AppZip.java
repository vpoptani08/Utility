package com.cap.jiraimporter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.naming.AuthenticationException;

import org.apache.log4j.Logger;
import org.json.JSONException;


public class AppZip
{
	List<String> fileList;
	static String id;
	//CsvToJsonConverter csvtojson = new CsvToJsonConverter();
	private static Logger logger = Logger.getRootLogger();
	static String OUTPUT_ZIP_FILE;
	static String SOURCE_FOLDER;
	static String arch_folder;
	static String search_result;
	AppZip(String ticketId,final String source,final String output,final String archive,final String rearchResult){
		fileList = new ArrayList<String>();
		id=ticketId;
		SOURCE_FOLDER=source;
		OUTPUT_ZIP_FILE=output;
		arch_folder=archive;
		search_result=rearchResult;
		//csvtojson.initLogger();
	}
	

	public void Init() throws IOException
	{
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		AppZip appzip=new AppZip(id,SOURCE_FOLDER,OUTPUT_ZIP_FILE,arch_folder,search_result);
		appzip.generateFileList(new File(SOURCE_FOLDER),SOURCE_FOLDER);
		appzip.zipIt(OUTPUT_ZIP_FILE,SOURCE_FOLDER);
		String currentDir = new File(arch_folder).getAbsolutePath();
		//String folderNameWithTimeStamp=getDateTime();
		String finalPath=currentDir;
		//File f1=new File(finalPath);
		//f1.mkdir();
		//f1.setExecutable(true);
		//f1.setWritable(true);
		AppZip.copyDirectory(finalPath); 
		if(null != search_result)
		{
			if(OUTPUT_ZIP_FILE.startsWith("attachments"))
			{
				try {
					MultipartPost.appendAttachments(search_result,id);
					System.gc();
					Thread.sleep(10000);	
				} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			}
		else
			logger.info("No attachment:  File is not available ");
		}
		
		File delFile=new File(SOURCE_FOLDER + "\\");
		deleteFiles(delFile);

	}
	private static String getDateTime()  
	{  
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		return df.format(new Date());  
	}

	private static void copyDirectory(String path) throws IOException {
		// TODO Auto-generated method stub
		copyFileUsingFileStreams(SOURCE_FOLDER+"\\cism_"+id+"_"+getDateTime()+".zip",path);
		
	}
	
	
	private static void deleteFiles(File file) {
	
		if (file.isDirectory())
	        for (File f : file.listFiles())
	        {
	        	deleteFiles(f);
	        }
	            
	    else
	    {
	    	System.gc();
	    	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	file.delete();
	    }
		
		/*if (file.exists()) {
			//check if the file is a directory
			if (file.isDirectory()) {
				if ((file.list()).length > 0) {
					for(String s:file.list()){
						//call deletion of file individually
						File delfile=new File(SOURCE_FOLDER+"\\"+s);
						deleteFiles(delfile);
					}
				}
			}
			boolean result = file.delete();
			// test if delete of file is success or not
			if (result) {
				System.out.println("File deleted");
			} else {
				System.out.println("File was not deleted, unknown reason");
			}
		}*/
		
	  /* try {
		FileUtils.forceDelete(file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	    }
	private static void copyFileUsingFileStreams(String string, String string2)
			throws IOException {
		File in =new File(string);
		File out=new File(string2);
		FileInputStream input = null;
		FileOutputStream output = null;
		
		try {

	        input = new FileInputStream(in);

	        output = new FileOutputStream(out+File.separator+"cism_"+id+"_"+getDateTime()+".zip");
	   
	      byte[] buf = new byte[1024];

	        int bytesRead;

	        while ((bytesRead = input.read(buf)) > 0)
	        

	            output.write(buf, 0, bytesRead);

	        }

		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	}
	// TODO Auto-generated method stub
	/**
	 * Zip it
	 * @param zipFile output ZIP file location
	 */
	public void zipIt(String zipFile,String Source_folder){
		byte[] buffer = new byte[1024];

		try{
			File source_dir=new File(Source_folder);
			File[] files=source_dir.listFiles();
			
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			 for(int i=0; i < files.length ; i++){
				FileInputStream fin = new FileInputStream(files[i]);
				zos.putNextEntry(new ZipEntry(files[i].getName()));

				int len;
				while ((len = fin.read(buffer)) > 0) {
					zos.write(buffer, 0, len);

				}

				zos.closeEntry();
				fin.close();
			}

			zos.close();

		}catch(IOException ex){

			logger.info("File not found at specified location");    
		}
	}

	/**
	 * Traverse a directory and get all files,
	 * and add the file into fileList  
	 * @param node file or directory
	 */
	public void generateFileList(File node,String Source_folder){
		//add file only
		if(node.isFile()){
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString(),Source_folder));
		}

		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				generateFileList(new File(node, filename),Source_folder);
			}
		}

	}

	/**
	 * Format the file path for zip
	 * @param file file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file,String Source_folder){
		return file.substring(Source_folder.length()+1, file.length());

	}

}

