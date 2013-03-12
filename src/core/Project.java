package core;


/**
 * This class represents a project of the Faehrmann. A project consists of a name, 
 * path and an identifier.
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class Project {

	private String name;
	private String path;
	private int id;

	private Code code;
	
	public Project() {
		code = new Code();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}
	
	public Code getCode() {
		return this.code;
	}
}