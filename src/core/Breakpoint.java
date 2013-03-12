package core;

/**
 * This class represents a breakpoint which belongs to a code. A breakpoint has an
 * attribute in which line it is and an attribute whether it is activated.
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class Breakpoint {

	private final int line;
	private boolean activated;
	
	public Breakpoint(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}