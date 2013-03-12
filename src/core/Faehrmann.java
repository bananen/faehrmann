package core;

import java.io.File;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;

/**
 * This class is the main class of the Faehrmann and initialize the projectmanager and the interface
 * between GUI and Core. It also manage the settings.
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class Faehrmann {

  private Settings  settings;
  
  /**
   * Main method and creates the Faehrmann instance 
   */
  public static void main(String[] args) {
	  new Faehrmann();
  }

  /**
   * Initialize project manager and GUIInterface
   * Load setting or set default settings
  */
  private Faehrmann() {
	  new Projectmanager();
	  settings = loadSettings();
	  this.setSettings(settings);
	  new GUIInterface(this);
  }
  // Constructor
  public Faehrmann(String test) {
	  new Projectmanager();
	  settings = loadSettings();
	  this.setSettings(settings);
  }

  public Settings getSettings() {
	  return settings;
  }

  public void setSettings(Settings settings) {
	  this.settings = settings;
  }
  
/**
 * Loads the settings from the given directories (Windows: "APPDATA", Unix: ".user.home")
 * The settings loaded from the .xml format and has the name "fmsettings". If there
 * is no "fmsettings" file a new one with the following default values will be created
 * fontsize: 12, max loop count: 3, Z3 timeout: 20000 and a empty Z3 Path and Default Path
 */
  private Settings loadSettings() {
	  Settings tempSettings = null;	  
	  XStream xstream = new XStream();
	  String settingsPath;
	  
	  //Windows
	  settingsPath = System.getenv("APPDATA");
	  //Unix
	  if (settingsPath == null) {
	      settingsPath = System.getProperty("user.home");
	  }
	  
	  File file = new File(settingsPath + File.separatorChar + ".fmsettings");

	  if (file.isFile()) {
		  //load from settings from "fmsettings" file
		  try {
			  tempSettings = (Settings)xstream.fromXML(file);
		  } catch (CannotResolveClassException e) {
			  tempSettings = defaultSettings();
		  } catch (StreamException e) {
			  tempSettings = defaultSettings();
		  }
	  }								
	  else {
		  tempSettings = defaultSettings();
	  }
	  return tempSettings;
  }
  
  public void saveSettings() {
	  settings.saveSettings();
  }
  
  private Settings defaultSettings() {
	  String path;
	  //Windows
	  path = System.getenv("HOMEPATH");
	  //Unix
	  if (path == null) {
	      path = System.getProperty("user.home");
	  }
	  Settings defaultSettings = new Settings(12, path, 3, "", 20000);
	  defaultSettings.saveSettings();
	  return defaultSettings;
  }
  
  

}