package com.vaahano.staffmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileResourceUtil {
	
	private static FileResourceUtil obj = new FileResourceUtil();
	
	private FileResourceUtil(){
		
	}
	
	
	public static String getContent(String fileName) {
		return obj.readFromResources(fileName);
	}
	
	private String readFromResources(String fileName) {
		
//		File file = ResourceUtils.getFile("classpath:config/sample.txt")
		
	     ClassLoader classLoader = getClass().getClassLoader();
	     File file = new File(classLoader.getResource(fileName).getFile());
         
	     //File is found
	     System.out.println("File Found : " + file.exists());
	         
	    //Read File Content
	    String content = null;
		try {
			content = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return content;
	}
}
