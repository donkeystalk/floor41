package octane.floor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

public class ClassPathResourceUtil {

	private static final Logger logger = Logger.getLogger(ClassPathResourceUtil.class);
	
	public static String getFileAsString(String path){
		String input = "";
		String file = "";
		try
		{
			ClassPathResource cpr = new ClassPathResource(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(cpr.getInputStream()));
			while((input = reader.readLine()) != null)
			{
				file.concat(input).concat("\n");
			}
		}
		catch(IOException e)
		{
			logger.error(e);
		}
		return file;
	}

	
	
}
