package interpreter;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents the state of an interpreter while interpreting / debugging the source code.
 * It contains all variables, the current line and errors. 
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 * 
 */
public class InterpreterState {
	
	public final static int VAR_BOOL = 0;
	public final static int VAR_INT = 1;
	
	private final LinkedList<VariableScope> scopes = new LinkedList<VariableScope>();

	private int currentLine = -1;
	
	private boolean parserErrors;
	
	//A list of linenumbers to possibly highlight errors in the source code
	private final LinkedList<Integer> errorLines = new LinkedList<Integer>();

	private final LinkedList<String> errorMessages = new LinkedList<String>();
	
	private boolean errorFree = true;
	
	private boolean done = false;
	
	public InterpreterState() {
		openScope();
	}
	
	/**
	 * Adds an error to the interpreterstate
	 * @param line the line in which the error occured
	 * @param message the error message
	 */
	public void addError(int line, String message) {
		errorLines.addLast(line);
		errorMessages.addLast(message);
		errorFree = false;
	}

	public boolean isErrorFree() {
		return errorFree;
	}
	
	public void setDone() {
		done = true;
	}

	public boolean isDone() {
		return done;
	}

	public int getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}

	public LinkedList<Integer> getErrorLines() {
		return errorLines;
	}

	public LinkedList<String> getErrorMessages() {
		return errorMessages;	
	}

	public int getScopeCount() {
		return scopes.size();
	}
	
	public int getScopeSize(int i) {
		return scopes.get(i).getVariableCount();
	}
	
	public void openScope() {
		scopes.add(new VariableScope());
	}
	
	/**
	 * closes the last scope (to easily throw away all variables after e.g. a function has ended)
	 */
	public void closeScope() {
		if (scopes.size() != 0) {
			scopes.remove(scopes.size() - 1);
		}
	}
	
	/**
	 * adds a variable to the last scope
	 * @param variable the variable to be added
	 */
	private void addVariable(Variable variable) {
		scopes.get(scopes.size() - 1).addVariable(variable);
	}
	
	/**
	 * If the input variable already exists, it changes the value to the value of the input variable, if it doesn't
	 * exist yet, the function adds the variable to the last scope
	 * @param variable the variable to be added / changed
	 */
	public void setVariable(Variable variable) {
		Iterator<VariableScope> scopeIterator = scopes.iterator();
		boolean found = false;
		VariableScope scope;
		while (scopeIterator.hasNext() && !found) {
			scope = (VariableScope)scopeIterator.next();
			if (scope.contains(variable)) {
				found = true;
				scope.setValue(variable);
			}
		}
		if (!found) {
			addVariable(variable);
		}		
	}
	
	/**
	 * Sets a value in an array 
	 * @param scopeNo scope number of the array
	 * @param variableNo variable number of the array
	 * @param index index of the value to be set
	 * @param value desired value
	 */
	public void setArrayVariable(String name, BigInteger index, BigInteger value) {
		Iterator<VariableScope> scopeIterator = scopes.iterator();
		boolean found = false;
		VariableScope scope;
		Variable array = new Int(index, name);
		while (scopeIterator.hasNext() && !found) {
			scope = (VariableScope)scopeIterator.next();
			if (scope.contains(array)) {
				found = true;
				scope.setArray(name, index, value);
			}
		}
	}
	
	public Variable getVariable(int scopeNo, int variableNo) {
		return this.scopes.get(scopeNo).getVariable(variableNo);
	}

	/**
	 * Returns a BigInteger of the variable with the input name
	 * @param name the name of the variable
	 * @return a BigInteger containing the value of the variable. Returns 0 if Variable doesn't exist
	 */
	public BigInteger getValueOf(String name) {
		Variable variable = new Bool(BigInteger.ZERO, name);
		VariableScope scope = getScope(variable);
		BigInteger result = BigInteger.ZERO;
		if (scope != null) {
			result = scope.getValue(variable);	
		}
		return result;	
	}

	private VariableScope getScope(Variable variable) {
		Iterator<VariableScope> scopeIterator = scopes.iterator();
		boolean found = false;
		VariableScope result = null;
		VariableScope scope;
		while (scopeIterator.hasNext() && !found) {
			scope = (VariableScope)scopeIterator.next();
			if (scope.contains(variable)) {
				found = true;
				result = scope;
			}
		}
		return result;
	}

	public void addMessage(String message) {
		errorMessages.addLast(message);
	}

	public boolean isParserErrorFree() {
		return !parserErrors;
	}

	public void setParserErrors() {
		this.parserErrors = true;
	}
	
	public LinkedList<VariableScope> getScopes() {
		return scopes;
	}

	@Override
	public boolean equals(Object object) {
		boolean equal = true;
		InterpreterState state = null;
		try {
			state = (InterpreterState)object;
		} catch (ClassCastException e) {
			equal = false;
		} 
		if (equal) {
			if (this.isErrorFree() != state.isErrorFree()) {
				equal = false;
			} else if (!this.getErrorLines().equals(state.getErrorLines())) {
				equal = false;
			} else if (!this.getErrorMessages().equals(state.getErrorMessages())) {
				equal = false;
			} else if (this.getCurrentLine() != state.getCurrentLine()) {
				equal = false;
			} else if (this.isDone() != state.isDone()) {
				equal = false;
			} else if (this.getScopeCount() != state.getScopeCount()) {
				equal = false;
			} else if (!this.getScopes().equals(state.getScopes())) {
				equal = false;
			}
		}
		return equal;
	}

	public BigInteger getValueOf(String name, BigInteger index) {
		BigInteger result = BigInteger.ZERO;
		Variable variable = new Bool(result, name);
		VariableScope scope = getScope(variable);
		if (scope != null) {
			result = scope.getValue(variable, index);
		}
		return result;
	}
}