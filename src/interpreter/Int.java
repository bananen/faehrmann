package interpreter;

import java.math.BigInteger;

/**
 * This class represents an integer in the interpretation process
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */
public class Int extends Variable {
	
	public Int(BigInteger value, String name) {
		super(value, name);
	}
	
	@Override
	public String getType() {
		return "int";
	}
	
}
