package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadingPropertiesFile {

	
	protected Properties Prop=null;
	protected FileInputStream LoadFile=null;
	
	public ReadingPropertiesFile (String FilePath) throws IOException{
		Prop=new Properties();
		LoadFile= new FileInputStream(FilePath);
		Prop.load(LoadFile);
	}
	
	
//	===== Driver Methods =======
	
	public String GetBrowser (){
		return Prop.getProperty("browser");
	}
	
	public String GetURL(){
		return Prop.getProperty("URL");
	}


}
