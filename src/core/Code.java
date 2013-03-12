package core;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents code which consists of text and the marked breakpoints in 
 * the code.
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class Code {
	
	private String lines;
	private final ArrayList<Breakpoint> breakpoints;

	public Code() {
		breakpoints = new ArrayList<Breakpoint>();
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	/**
	 * Creates a breakpoint for each line number from the given integer array
	 */
	public void setLinesWithBreakpoints(int[] linesWithBreakpoints) {
		for (int i = 0; i < linesWithBreakpoints.length; i++) {
			this.setBreakpoint(linesWithBreakpoints[i]);
		}
	}
	
	/**
	 * Returns a integer array with the line numbers where breakpoints were staid
	 */
	public int[] getBreakpoints() {
		int[] linesWithBreakpoints = new int[breakpoints.size()];
		for (int i = 0; i < breakpoints.size(); i++) {
			linesWithBreakpoints[i] = breakpoints.get(i).getLine();
		}
		return linesWithBreakpoints;
	}
	
	/**
	 * Creates a new breakpoint object with the given line and set it activated
	 */
	private void setBreakpoint(int line) {
		Breakpoint newBreakpoint = new Breakpoint(line);
		newBreakpoint.setActivated(true);
		breakpoints.add(newBreakpoint);
	}
	
	public boolean equals(Code c) {
		if (c == null) return false;
		else if((c.getLines() == null && this.getLines() != null) || this.getLines() == null && c.getLines() != null) return false;
		else if(c.getLines() == null && this.getLines() == null) return true;
		else if (lines.equals(c.getLines()) && Arrays.equals(this.getBreakpoints(), c.getBreakpoints())) {
			return true;
		}
		return false;
	}
}