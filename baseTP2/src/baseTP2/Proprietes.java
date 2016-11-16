package baseTP2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Proprietes {

	Properties propriete;
	
	public Proprietes(){
		InputStream fichier = null;
		propriete = new Properties();

		try {
			fichier = new FileInputStream(new File("E:/Documents/config.properties"));
			propriete.load(fichier);
			fichier.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDriver(){
		return this.propriete.getProperty("driver");
	}
	
	public String getUrl(){
		return this.propriete.getProperty("url");
	}
	
	public String getLogin(){
		return this.propriete.getProperty("login");
	}
	
	public String getPassword(){
		return this.propriete.getProperty("password");
	}
}
