package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;


/**
 * This class represent the settings. The settings consists of fontsize, 
 * default path, max loop count, z3 path and a z3 timeout. The settings can
 * be saved in the .xml format.
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class Settings {

	private int fontSize;
	private String defaultPath;
	private int maxLoopCount;
	private String z3Path;
	private int z3Timeout;
	
	/**
	 * Creates a new settingsobject with the default values or with the loaded values
	 */
	public Settings(int fontSize, String defaultPath, int maxLoopCount, String z3Path, int z3Timeout) {
		this.fontSize = fontSize;
		this.defaultPath = defaultPath;
		this.maxLoopCount = maxLoopCount;
		this.z3Path = z3Path;
		this.z3Timeout = z3Timeout;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getDefaultPath() {
		return defaultPath;
	}

	public void setDefaultPath(String defaultPath) {
		this.defaultPath = defaultPath;
	}

	public Integer getMaxLoopCount() {
		return maxLoopCount;
	}

	public void setMaxLoopCount(int maxLoopCount) {
		this.maxLoopCount = maxLoopCount;
	}

	public String getz3Path() {
		return z3Path;
	}

	public void setz3Path(String z3Path) {
		this.z3Path = z3Path;
	}

	public int getZ3Timeout() {
		return z3Timeout;
	}

	public void setZ3Timeout(int z3Timeout) {
		this.z3Timeout = z3Timeout;
	}
	
	/**
	 * Saves the settings in given directories (Windows: "APPDATA", Unix: ".user.home")
	 * The settings saved in the .xml format and has the name "fmsettings"
	 */
	public void saveSettings() {
		  String settingsPath;
		  //Windows
		  settingsPath = System.getenv("APPDATA");
		  //Unix
		  if (settingsPath == null) {
		      settingsPath = System.getProperty("user.home");
		  }
		  File path = new File(settingsPath + File.separatorChar + ".fmsettings");
		  FileWriter writer;
		  
		  //settings get serialized to .xml
		  XStream xstream = new XStream();
		  String xml = xstream.toXML(this);
		  
		  try {
			  writer = new FileWriter(path);
			  writer.write(xml);
			  writer.flush();
			  writer.close();
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
	  }

}