package interpreter;


import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Array extends Variable {
	
	protected HashMap<BigInteger, BigInteger> array = new HashMap<BigInteger, BigInteger>();
	
	protected Array(BigInteger length, String name) {
		super(length, name);
	}
	
	public BigInteger getValue(BigInteger index) {
		BigInteger result;
		if (index.compareTo(getValue()) < 0 && index.compareTo(new BigInteger("-1")) > 0) {
			return array.get(index);
		} else {
			result = BigInteger.ZERO;
		}
		return result;
	}
	
	public void setVariable(BigInteger index, BigInteger value) {
		if (index.compareTo(getValue()) < 0) {
			array.put(index, value);
		}
	}
	
	public boolean equals(Object object) {
		boolean equal = true;
		Array array = null;
		try {
			array = (Array)object;
		} catch (ClassCastException e) {
			equal = false;
		}
		if (equal) {
			equal = array.getValue().equals(this.getValue());
		}
		if (equal) {
			equal = array.getType().equals(this.getType());
		}
		if (equal) {
			equal = array.getName().equals(this.getName());
		}
		if (equal) {
			Iterator<BigInteger> value1 = this.getValues().iterator();
			Iterator<BigInteger> value2 = array.getValues().iterator();
			while (equal && value1.hasNext() && value2.hasNext()) {
				equal = value1.next().equals(value2.next());
				if (value1.hasNext() != value2.hasNext()) {
					equal = false;
				}
			}
		}
		return equal;
	}
	
	public Collection<BigInteger> getValues() {
		return array.values();
	}
	
	protected static BigInteger bigInt(int i) {
		return BigInteger.valueOf((long)i);
	}
	
}
