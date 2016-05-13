package kalah;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static Properties config;
	
	public static int getProperty(Property prop){
		if (config == null){
			config = configSetup();
		}
		
		return Integer.parseInt(config.getProperty(prop.value));
	}
	
	private static Properties configSetup(){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			 File resourcesDirectory = new File("src/kalah/");
			input = new FileInputStream(resourcesDirectory.getAbsolutePath() + "/config.properties");

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	public enum Property {
	    PLAYERS("playerNumber"),
	    BOARDSIZE("boardSize"),
	    STARTINGSEEDS("startingSeeds");
	    
	    private String value;

	    Property(String value) {
	        this.value = value;
	    }
	    
	    public String value(){
	    	return value;
	    }
	}
}


