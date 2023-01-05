package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import constants.FileNameConstants;

public class Datautils{
	
	public static String readAccounts(String key) throws IOException {
	
	FileReader fr = new FileReader(FileNameConstants.PROD_ACCOUNTS);

	Properties p = new Properties();
	p.load(fr);
	String value = p.getProperty(key);
	
	return value;
	
}
	
	public static String readErrorMsgs(String key) throws IOException {
		
		FileReader fr = new FileReader(FileNameConstants.ERROR_MESSAGES);

		Properties p = new Properties();
		p.load(fr);
		String value = p.getProperty(key);
		
		return value;
		
	}
	public static String appLaunchUrl(String key) throws IOException {
		
		FileReader fr = new FileReader(FileNameConstants.AAP_LAUNCH_URL);

		Properties p = new Properties();
		p.load(fr);
		String value = p.getProperty(key);
		
		return value;
		
	}
}