package verifier;

import java.util.ArrayList;

import ast.*;

/**
 * This class can be used to find all variables in a given term.
 * 
 * @author Fedor Scholz
 * 
 */

public class VariableFinder implements ASTVisitor {

	private ArrayList<VarInt> intVariables;
	private ArrayList<VarBool> boolVariables;
	private ArrayList<ArrayIntAccess> intArrays;
	private ArrayList<ArrayBoolAccess> boolArrays;

	public VariableFinder() {
		this.intVariables = new ArrayList<VarInt>();
		this.boolVariables = new ArrayList<VarBool>();
		this.intArrays = new ArrayList<ArrayIntAccess>();
		this.boolArrays = new ArrayList<ArrayBoolAccess>();
	}

	public ArrayList<VarInt> getIntVariables() {
		return intVariables;
	}

	public ArrayList<VarBool> getBoolVariables() {
		return boolVariables;
	}

	public ArrayList<ArrayIntAccess> getIntArrays() {
		return intArrays;
	}

	public ArrayList<ArrayBoolAccess> getBoolArrays() {
		return boolArrays;
	}

	public void findVariables(Node node) {
		intVariables = new ArrayList<VarInt>();
		boolVariables = new ArrayList<VarBool>();
		intArrays = new ArrayList<ArrayIntAccess>();
		boolArrays = new ArrayList<ArrayBoolAccess>();
		if (node != null) {
			node.accept(this);
		}
	}

	@Override
	public Node visit(Program root) {
		return null;
	}

	@Override
	public RestrictedTerm add(Addition addition) {
		addition.getLeftSubTerm().accept(this);
		addition.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm and(And and) {
		and.getLeftSubTerm().accept(this);
		and.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public Assignment assign(Assignment assignment) {
		return null; // There should be no assignments in a term.
	}

	@Override
	public Definition define(Definition definition) {
		return null; // There should be no definitions in a term.
	}

	@Override
	public RestrictedTerm greater(GreaterThan greaterThan) {
		greaterThan.getLeftSubTerm().accept(this);
		greaterThan.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm greaterequals(GreaterEqual greaterEqual) {
		greaterEqual.getLeftSubTerm().accept(this);
		greaterEqual.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public MethodCall callMethod(MethodCall methodCall) {
		return null; // There should be no methodcalls in a term.
	}

	@Override
	public RestrictedTerm equal(Equal equal) {
		equal.getLeftSubTerm().accept(this);
		equal.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm negate(Negation negation) {
		negation.getSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm multiply(Multiplication multiplication) {
		multiplication.getLeftSubTerm().accept(this);
		multiplication.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm smaller(SmallerThan smallerThan) {
		smallerThan.getLeftSubTerm().accept(this);
		smallerThan.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm not(Not not) {
		not.getSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm or(Or or) {
		or.getLeftSubTerm().accept(this);
		or.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm subtract(Subtraction subtraction) {
		subtraction.getLeftSubTerm().accept(this);
		subtraction.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm smallerequals(SmallerEqual smallerEqual) {
		smallerEqual.getLeftSubTerm().accept(this);
		smallerEqual.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm unequal(Unequal unequal) {
		unequal.getLeftSubTerm().accept(this);
		unequal.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public Statement astIf(IfBlock ifBlock) {
		return null; // There should be no if statements in a term.
	}

	@Override
	public Statement astWhile(WhileBlock whileBlock) {
		return null; // There should be no while loops in a term.
	}

	@Override
	public Assertion astAssert(Assertion assertion) {
		return null; // There should be no assertions in a term.
	}

	@Override
	public Assumption assume(Assumption assumption) {
		return null; // There should be no assumptions in a term.
	}

	@Override
	public RestrictedTerm imply(Implication implication) {
		implication.getLeftSubTerm().accept(this);
		implication.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm equivalize(Equivalence equivalence) {
		equivalence.getLeftSubTerm().accept(this);
		equivalence.getRightSubTerm().accept(this);
		return null;
	}

	@Override
	public UniversalQuantifier uniQuantify(
			UniversalQuantifier universalQuantifier) {
		if (universalQuantifier == null
				|| universalQuantifier.getVariable() == null
				|| universalQuantifier.getSubTerm() == null) {
			return null;
		}
		universalQuantifier.getVariable().accept(this);
		universalQuantifier.getSubTerm().accept(this);
		return null;
	}

	@Override
	public ExistentialQuantifier exQuantify(
			ExistentialQuantifier existentialQuantifier) {
		if (existentialQuantifier == null
				|| existentialQuantifier.getVariable() == null
				|| existentialQuantifier.getSubTerm() == null) {
			return null;
		}
		existentialQuantifier.getVariable().accept(this);
		existentialQuantifier.getSubTerm().accept(this);
		return null;
	}

	@Override
	public ConstBool constBool(ConstBool constBool) {
		return null; // Nothing to do with a constant.
	}

	@Override
	public ConstInt constInt(ConstInt constInt) {
		return null; // Nothing to do with a constant.
	}

	/**
	 * Checks if the given variable is in the list already. If not it will be
	 * added.
	 */
	@Override
	public RestrictedTerm varInt(VarInt varInt) {
		for (VarInt current : intVariables) {
			if (current.getName().equals(varInt.getName())) {
				return null;
			}
		}
		intVariables.add(varInt);
		return null;
	}

	/**
	 * Checks if the given variable is in the list already. If not it will be
	 * added.
	 */
	@Override
	public RestrictedTerm varBool(VarBool varBool) {
		for (VarBool current : boolVariables) {
			if (current.getName().equals(varBool.getName())) {
				return null;
			}
		}
		boolVariables.add(varBool);
		return null;
	}

	@Override
	public MethodDefinition methodDefinition(MethodDefinition methodDefinition) {
		return null; // There should be no method definitions in a term.
	}

	@Override
	public StatementBlock statementBlock(StatementBlock statementBlock) {
		return null; // There should be no statement blocks in a term.
	}

	@Override
	public ArrayIntAccess arrayIntAccess(ArrayIntAccess arrayIntAccess) {
		for (ArrayIntAccess current : intArrays) {
			if (current.getName().equals(arrayIntAccess.getName())) {
				return null;
			}
		}
		intArrays.add(arrayIntAccess);

		arrayIntAccess.getIndex().accept(this);
		for (ArrayReplacement current : arrayIntAccess.getReplacements()) {
			current.getIndex().accept(this);
			if (current.getValue() != null) {
				current.getValue().accept(this);
			}
		}
		return null;
	}

	@Override
	public ArrayBoolAccess arrayBoolAccess(ArrayBoolAccess arrayBoolAccess) {
		for (ArrayBoolAccess current : boolArrays) {
			if (current.getName().equals(arrayBoolAccess.getName())) {
				return null;
			}
		}
		boolArrays.add(arrayBoolAccess);

		arrayBoolAccess.getIndex().accept(this);
		for (ArrayReplacement current : arrayBoolAccess.getReplacements()) {
			current.getIndex().accept(this);
			if (current.getValue() != null) {
				current.getValue().accept(this);
			}
		}
		return null;
	}

	@Override
	public ArrayDefinition arrayDefinition(ArrayDefinition arrayDefinition) {
		return null; // There should be no array definitions in a term.
	}

	@Override
	public ArrayAssignment arrayAssignment(ArrayAssignment arrayAssignment) {
		return null; // There should be no array assignment in a term.
	}

}
