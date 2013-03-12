package interpreter;

import java.math.BigInteger;

public class BoolArray extends Array {

	protected BoolArray(BigInteger length, String name) {
		super(length, name);
	}
	
	public void setVariable(BigInteger index, BigInteger value) {
		if (index.compareTo(getValue()) < 0) {
			if (value.equals(BigInteger.ZERO)) {
				array.put(index, BigInteger.ZERO);
			} else {
				array.put(index, BigInteger.ONE);
			}
			
		}
	}
	
	@Override
	public String getType() {
		return "bool[]";
	}
	
}
