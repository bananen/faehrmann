package interpreter;

import java.math.BigInteger;

/**
 * This class represents a variable in the interpretation process, it combines the shared functions of boolean and integer.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */
public abstract class Variable implements Comparable<Variable>{

	protected BigInteger value;
	protected final String name;

	protected Variable(BigInteger value, String name) {
		this.value = value;
		this.name = name;
	}

	public int compareTo(Variable variable) {
		return this.getName().compareTo(variable.getName());
	}
	/**
	 * @return the value
	 */
	public BigInteger getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(BigInteger value) {
		this.value = value;
	}
	
	abstract public String getType();
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equal = true;
		Variable variable = null;
		try {
			variable = (Variable)object;
		} catch (ClassCastException e){
			equal = false;
		}
		if (equal) {
			equal = this.getName().equals(variable.getName());
		}
		if (equal) {
			equal = this.getValue().equals(variable.getValue());
		}
		if (equal) {
			equal = this.getType().equals(variable.getType());
		}
		return equal;
	}
	
	public boolean hasSameName(Variable variable) {
		return this.getName().equals(variable.getName());
	}

	public void setValue(int i) {
		setValue(new BigInteger(String.valueOf(i)));
	}
}