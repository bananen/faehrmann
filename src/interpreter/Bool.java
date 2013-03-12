package interpreter;

import java.math.BigInteger;

/**
 * This class represents a boolean in the interpretation process
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */
public class Bool extends Variable {

	public Bool(BigInteger value, String name) {
		super(value, name);
	}
	
	@Override
	public String getType() {
		return "bool";
	}
}
