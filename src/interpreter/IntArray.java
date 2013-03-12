package interpreter;

import java.math.BigInteger;

public class IntArray extends Array {

	protected IntArray(BigInteger length, String name) {
		super(length, name);
	}
	
	@Override
	public String getType() {
		return "int[]";
	}
	
}
