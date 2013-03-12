package interpreter;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a scope containing variables.
 * Its basic functions are the ones of a list, we added the automatic sort function if a variable is added.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 * 
 */
public class VariableScope {

	final LinkedList<Variable> variables = new LinkedList<Variable>();

	public VariableScope() {
		
	}
	
	public void addVariable(Variable variable) {
		variables.add(variable);
		Collections.sort(variables);
	}

	public Variable getVariable(int variableNo) {
		return variables.get(variableNo);
	}
	
	public boolean contains(Variable variable) {
		return indexOf(variable) != -1;
	}
	
	public int getVariableCount() {
		return variables.size();
	}

	public void setValue(Variable variable) {
		variables.get(indexOf(variable)).setValue(variable.getValue());	
	}

	private int indexOf(Variable variable) {
		int index = -1;
		int counter = 0;
		Iterator<Variable> variableIterator = variables.iterator();
		while (variableIterator.hasNext() && index == -1) {
			if (variable.hasSameName(variableIterator.next())) {
				index = counter;
			}
			counter++;
		}
		return index;
	}
	
	public BigInteger getValue(Variable variable) throws IndexOutOfBoundsException{
		Variable result = getVariable(variable);
		return result.getValue();
	}
	
	private Variable getVariable(Variable variable) {
		return variables.get(indexOf(variable));
	}

	public LinkedList<Variable> getVariableList() {
		return variables;
	}
  
	public boolean equals(Object object) {
		VariableScope scope = null;
		boolean equal = true;
		try {
			scope = (VariableScope)object;
		} catch (ClassCastException e) {
			equal = false;
		}
		if (equal) {
			equal = this.getVariableList().equals(scope.getVariableList());
		}
		return equal;
	}

	public void setArray(String name, BigInteger index, BigInteger value) {
		Variable array = new Int(index, name);
		int variableIndex = indexOf(array);
		if (variableIndex != -1) {
			try {
				((Array)variables.get(variableIndex)).setVariable(index, value);
			} catch (ClassCastException e) {}
		}
	}

	public BigInteger getValue(Variable variable, BigInteger index) {
		int variableIndex = indexOf(variable);
		BigInteger result = new BigInteger("0");
		if (variableIndex != -1) {
			try {
				result = ((Array)variables.get(variableIndex)).getValue(index);
			} catch (ClassCastException e) {}
		}
		return result;
	}
}