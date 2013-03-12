package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

/**
 * This class manage the existing projects. It can be used to create new projects, 
 * close projects, save and load projects. 
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class Projectmanager {

	private final ArrayList<Project> projects = new ArrayList<Project>();
	private int actualID = 0;

	/**
	 * Creates a new project 
	 */
	public void newProject() {
		Project tempProject = new Project();
		tempProject.setId(actualID);
		this.projects.add(tempProject);
		actualID++;
	}

	/**
	 * Close the project with the given identifier (id) 
	 */
	public void closeProject(int id) {
		for(int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getId() == id) {
				projects.remove(i);
			}
		}
	}

	/**
	 * Saves the code from a project in as a textfile in the given path.
	 * The ending of the files are .fm
	 */
	public void saveCode(int id, String path, Code code) {
		 FileWriter writer;
		 if (path != null && code != null && !path.isEmpty()) {
			 File directory = new File(path);
		     try {
		    	 writer = new FileWriter(directory);
		    	 XStream xstream = new XStream();
		    	 writer.write(xstream.toXML(code));
		    	 writer.flush();
		    	 writer.close();
		     } catch (IOException e) {
				 e.printStackTrace();		    
			 }
		 }
	}

	/**
	 * Loads the the code from the given textfile in the path. A new project with
	 * the loaded code will be created. 
	 * @return Code A Code object with the loaded text will be returned
	 */
	public Code loadProject(String path) {
		
		if (path == null || path.isEmpty()) return null;
		
		File file = new File(path);
		//checking file is an file
        if (!file.isFile())
            return null;
        		  
        XStream xstream = new XStream();
        //new project will be created with the code 
        this.newProject();
        try {
        	this.getNewestProject().setCode((Code)xstream.fromXML(file));
        }
        catch(Exception e) {
        	return new Code();
        }
        return this.getNewestProject().getCode();
	}
	
	public Project getNewestProject() {
		return projects.get(projects.size() - 1);
	}

}