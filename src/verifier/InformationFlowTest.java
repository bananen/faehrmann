package verifier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import ast.*;

/**
 * This class can be used to transfer a given program into a program which can
 * be verified to check if there is bad information flow in the given program.
 * 
 * @author Manuel Kohnen
 * 
 */

public class InformationFlowTest implements ASTVisitorVar {

	private ArrayList<VarInt> lowInt;
	private ArrayList<VarBool> lowBool;
	private ArrayList<VarInt> highInt;
	private ArrayList<VarBool> highBool;
	private ArrayList<VarInt> randomInt;
	private ArrayList<VarBool> randomBool;
	private ArrayList<VarInt> res1Int;
	private ArrayList<VarBool> res1Bool;

	private HashMap<String, BigInteger> lowIntArrays;
	private HashMap<String, BigInteger> lowBoolArrays;

	private ArrayList<LegalProgramChild> doubleChildren;
	private Boolean secondIteration;

	private VarInt i_arraycount = new VarInt("i--arraycount", false);

	private ArrayList<ArrayAssignment> rand_IntArrayAccesses;
	private ArrayList<ArrayAssignment> rand_BoolArrayAccesses;
	private ArrayList<ArrayAssignment> sec_rand_IntArrayAccesses;
	private ArrayList<ArrayAssignment> sec_rand_BoolArrayAccesses;

	@Override
	public Program visit(Program root) {
		if (root == null) {
			return null;
		}
		lowInt = new ArrayList<VarInt>();
		lowBool = new ArrayList<VarBool>();
		randomInt = new ArrayList<VarInt>();
		randomBool = new ArrayList<VarBool>();
		res1Int = new ArrayList<VarInt>();
		res1Bool = new ArrayList<VarBool>();
		highInt = new ArrayList<VarInt>();
		highBool = new ArrayList<VarBool>();

		lowIntArrays = new HashMap<String, BigInteger>();
		lowBoolArrays = new HashMap<String, BigInteger>();

		secondIteration = false;

		rand_BoolArrayAccesses = new ArrayList<ArrayAssignment>();
		rand_IntArrayAccesses = new ArrayList<ArrayAssignment>();
		sec_rand_BoolArrayAccesses = new ArrayList<ArrayAssignment>();
		sec_rand_IntArrayAccesses = new ArrayList<ArrayAssignment>();

		// create new list of legalProgramChild to create the new AST later on
		// and remove definitions of low variables
		doubleChildren = new ArrayList<LegalProgramChild>();
		for (int i = 0; i < root.getNumberOfChildren(); i++) {
			doubleChildren.add(root.getChild(i).accept(this));
		}

		// Initialize list of random int values and result int values
		for (int i = 0; i < lowInt.size(); i++) {
			randomInt.add(new VarInt("randInt--" + i, false));
			res1Int.add(new VarInt("resInt--" + i, false));
		}

		// Initialize list of random bool values and result bool values
		for (int i = 0; i < lowBool.size(); i++) {
			randomBool.add(new VarBool("randBool--" + i, false));
			res1Bool.add(new VarBool("resBool--" + i, false));
		}

		// Initialize list of random array values and list of result array
		// values for low int arrays
		for (String name : lowIntArrays.keySet()) {
			for (Long i = 0L; i < lowIntArrays.get(name).longValue(); i++) {
				rand_IntArrayAccesses.add(new ArrayAssignment(0,
						new ArrayIntAccess(name, lowIntArrays.get(name), false,
								new ConstInt(String.valueOf(i))), new VarInt(
								"randomIntValue--" + name + "--" + i, false)));

				sec_rand_IntArrayAccesses.add(new ArrayAssignment(0,
						new ArrayIntAccess(name + "--sec", lowIntArrays
								.get(name), false, new ConstInt(String
								.valueOf(i))), new VarInt("randomIntValue--"
								+ name + "--" + i, false)));

			}
		}

		// Initialize list of random array values and list of result array
		// low bool arrays
		for (String name : lowBoolArrays.keySet()) {
			for (Long i = 0L; i < lowBoolArrays.get(name).longValue(); i++) {
				rand_BoolArrayAccesses.add(new ArrayAssignment(0,
						new ArrayBoolAccess(name, lowBoolArrays.get(name),
								false, new ConstInt(String.valueOf(i))),
						new VarBool("randomBoolValue--" + name + "--" + i,
								false)));
				sec_rand_BoolArrayAccesses.add(new ArrayAssignment(0,
						new ArrayBoolAccess(name + "--sec", lowBoolArrays
								.get(name), false, new ConstInt(String
								.valueOf(i))), new VarBool("randomBoolValue--"
								+ name + "--" + i, false)));
			}
		}

		// INTARRAY:
		// Add the Assignments like array[0] = random1 to the beginning
		// of the program and assignments like array--res[0] to the end of
		// the program
		if (rand_IntArrayAccesses.size() > 0) {
			for (int i = 0; i < rand_IntArrayAccesses.size(); i++) {
				doubleChildren.add(0, rand_IntArrayAccesses.get(i));
			}
		}

		// BOOLARRAY:
		// Add the Assignments like array[0] = random1 to the beginning
		// of the program and assignments like array--res[0] to the end of
		// the program
		if (rand_BoolArrayAccesses.size() > 0) {
			for (int i = 0; i < rand_BoolArrayAccesses.size(); i++) {
				doubleChildren.add(0, rand_BoolArrayAccesses.get(i));
			}
		}

		// INT:
		// add assignements like low1=rand1 to the beginning and res1=low1 to
		// the end of the program
		if (randomInt.size() > 0) {
			for (int i = 0; i < randomInt.size(); i++) {
				doubleChildren.add(0, new Assignment(0, lowInt.get(i),
						randomInt.get(i)));
				doubleChildren.add(new Assignment(0, res1Int.get(i), lowInt
						.get(i)));
			}
		}
		// BOOL:
		// add assignments like low1=rand1 to the beginning and res1=low1 to
		// the end of the program
		if (randomBool.size() > 0) {
			for (int i = 0; i < randomBool.size(); i++) {
				doubleChildren.add(0, new Assignment(0, lowBool.get(i),
						randomBool.get(i)));
				doubleChildren.add(new Assignment(0, res1Bool.get(i), lowBool
						.get(i)));
			}
		}

		// INTARRAY:
		// set low int arrays to the same random values as before
		for (int i = 0; i < rand_IntArrayAccesses.size(); i++) {
			doubleChildren.add(sec_rand_IntArrayAccesses.get(i));
		}

		// BOOLARRAY:
		// set low bool arrays to the same random values as before
		for (int i = 0; i < rand_BoolArrayAccesses.size(); i++) {
			doubleChildren.add(sec_rand_BoolArrayAccesses.get(i));
		}

		// INT:
		// set the low int to the same random values as before
		if (randomInt.size() > 0) {
			for (int i = 0; i < randomInt.size(); i++) {
				doubleChildren.add(new Assignment(0, lowInt.get(i), randomInt
						.get(i)));
			}
		}

		// BOOL:
		// set the low bool to the same random values as before
		if (randomBool.size() > 0) {
			for (int i = 0; i < randomBool.size(); i++) {
				doubleChildren.add(new Assignment(0, lowBool.get(i), randomBool
						.get(i)));
			}
		}

		// set flag for second iteration
		secondIteration = true;
		// add the second programblock and remove definitions of low variables
		for (int i = 0; i < root.getNumberOfChildren(); i++) {
			doubleChildren.add(root.getChild(i).accept(this));
		}

		// INTARRAY:
		// add the assertions of type
		// assert( for all i : array[i] == array--res[i]) to end
		// of the program
		for (String name : lowIntArrays.keySet()) {
			doubleChildren.add(new Assertion(0, new UniversalQuantifier(
					i_arraycount, new Implication(
							new And(new GreaterEqual(i_arraycount,
									new ConstInt("0")), new SmallerThan(
									i_arraycount, new ConstInt(lowIntArrays
											.get(name)))),

							new Equal(new ArrayIntAccess(name, lowIntArrays
									.get(name), false, i_arraycount),
									new ArrayIntAccess(name + "--sec",
											lowIntArrays.get(name), false,
											i_arraycount))))));
		}

		// BOOLARRAY:
		// add the assertions of type
		// assert( for all i : array[i] == array--res[i]) to end
		// of the program
		for (String name : lowBoolArrays.keySet()) {
			doubleChildren.add(new Assertion(0, new UniversalQuantifier(
					i_arraycount, new Implication(new And(new GreaterEqual(
							i_arraycount, new ConstInt("0")),
							new SmallerThan(i_arraycount, new ConstInt(
									lowBoolArrays.get(name)))),

					new Equal(new ArrayBoolAccess(name, lowIntArrays.get(name),
							false, i_arraycount), new ArrayBoolAccess(name
							+ "--sec", lowBoolArrays.get(name), false,
							i_arraycount))))));
		}

		// INT:
		// add the assertions of type assert(res1 == low1) to the end of the
		// program
		if (randomInt.size() > 0) {
			for (int i = 0; i < randomInt.size(); i++) {
				Term currentTerm = new Equal(res1Int.get(i), lowInt.get(i));
				doubleChildren.add(new Assertion(0, currentTerm));
			}
		}

		// BOOL:
		// add the assertions of type assert(res1 == low1) to the end of the
		// program
		if (randomBool.size() > 0) {
			for (int i = 0; i < randomBool.size(); i++) {
				Term currentTerm = new Equal(res1Bool.get(i), lowBool.get(i));
				doubleChildren.add(new Assertion(0, currentTerm));
			}
		}
		// Remove children that are null.
		for (int i = 0; i < doubleChildren.size(); i++) {
			if (doubleChildren.get(i) == null) {
				doubleChildren.remove(i);
				i--;
			}
		}

		return new Program(doubleChildren);
	}

	@Override
	public RestrictedTerm add(Addition addition) {
		return new Addition(addition.getLeftSubTerm().accept(this), addition
				.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm and(And and) {
		return new And(and.getLeftSubTerm().accept(this), and.getRightSubTerm()
				.accept(this));
	}

	@Override
	public Assignment assign(Assignment assignment) {
		if (assignment.getVariable().isHigh()) {
			return new Assignment(0, new VarInt("_dummy_", true));
		} else {
			return new Assignment(assignment.getLine(), assignment
					.getVariable().acceptVar(this), assignment.getTerm()
					.accept(this));
		}
	}

	// Returns the definition, except if the term is null. If this is the case
	// it returns the definition only if the variable is high. otherwise null is
	// returned
	@Override
	public Definition define(Definition definition) {
		if (definition.getTerm() != null && !definition.getVariable().isHigh()) {
			return new Definition(definition.getLine(), definition
					.getVariable().acceptVar(this), definition.getTerm()
					.accept(this));
		} else {
			return null;
		}
	}

	@Override
	public RestrictedTerm greater(GreaterThan greaterThan) {
		return new GreaterThan(greaterThan.getLeftSubTerm().accept(this),
				greaterThan.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm greaterequals(GreaterEqual greateEqual) {
		return new GreaterEqual(greateEqual.getLeftSubTerm().accept(this),
				greateEqual.getRightSubTerm().accept(this));
	}

	@Override
	public MethodCall callMethod(MethodCall methodCall) {
		return new MethodCall(methodCall.getLine(), methodCall
				.getMethodDefinition().accept(this));
	}

	@Override
	public RestrictedTerm equal(Equal equal) {
		return new Equal(equal.getLeftSubTerm().accept(this), equal
				.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm negate(Negation negation) {
		return new Negation(negation.getSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm multiply(Multiplication multiplication) {
		return new Multiplication(multiplication.getLeftSubTerm().accept(this),
				multiplication.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm smaller(SmallerThan smallerThan) {
		return new SmallerThan(smallerThan.getLeftSubTerm().accept(this),
				smallerThan.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm not(Not not) {
		return new Not(not.getSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm or(Or or) {
		return new Or(or.getLeftSubTerm().accept(this), or.getRightSubTerm()
				.accept(this));
	}

	@Override
	public RestrictedTerm subtract(Subtraction subtraction) {
		return new Subtraction(subtraction.getLeftSubTerm().accept(this),
				subtraction.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm smallerequals(SmallerEqual smallerEqual) {
		return new SmallerEqual(smallerEqual.getLeftSubTerm().accept(this),
				smallerEqual.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm unequal(Unequal unequal) {
		return new Unequal(unequal.getLeftSubTerm().accept(this), unequal
				.getRightSubTerm().accept(this));
	}

	@Override
	public Statement astIf(IfBlock ifBlock) {
		if (ifBlock.getElseBlock() != null) {
			return new IfBlock(ifBlock.getLine(), ifBlock.getCondition()
					.accept(this), ifBlock.getThenBlock().accept(this), ifBlock
					.getElseBlock().accept(this));
		} else {
			return new IfBlock(ifBlock.getLine(), ifBlock.getCondition()
					.accept(this), ifBlock.getThenBlock().accept(this), null);
		}
	}

	@Override
	public Statement astWhile(WhileBlock whileBlock) {
		return new WhileBlock(whileBlock.getLine(), whileBlock.getCondition()
				.accept(this), whileBlock.getBody().accept(this));
	}

	@Override
	public Assertion astAssert(Assertion assertion) {
		return new Assertion(assertion.getLine(), assertion.getTerm().accept(
				this));
	}

	@Override
	public Assumption assume(Assumption assumption) {
		return new Assumption(assumption.getLine(), assumption.getTerm()
				.accept(this));
	}

	@Override
	public RestrictedTerm imply(Implication implication) {
		return new Implication(implication.getLeftSubTerm().accept(this),
				implication.getRightSubTerm().accept(this));
	}

	@Override
	public RestrictedTerm equivalize(Equivalence equivalence) {
		return new Equivalence(equivalence.getLeftSubTerm().accept(this),
				equivalence.getRightSubTerm().accept(this));
	}

	@Override
	public UniversalQuantifier uniQuantify(
			UniversalQuantifier universalQuantifier) {
		return universalQuantifier;
	}

	@Override
	public ExistentialQuantifier exQuantify(
			ExistentialQuantifier existentialQuantifier) {
		return existentialQuantifier;
	}

	@Override
	public ConstBool constBool(ConstBool constBool) {
		return constBool;
	}

	@Override
	public ConstInt constInt(ConstInt constInt) {
		return constInt;
	}

	@Override
	public VarInt varInt(VarInt varInt) {
		if (!varInt.isHigh() && !lowInt.contains(varInt)) {
			this.lowInt.add(varInt);
		} else if (varInt.isHigh() && !highInt.contains(varInt)) {
			this.highInt.add(varInt);
		}

		if (secondIteration && varInt.isHigh()) {
			return new VarInt(varInt.getName() + "--sec", true);
		} else {
			return varInt;
		}
	}

	@Override
	public VarBool varBool(VarBool varBool) {
		if (!varBool.isHigh() && !lowBool.contains(varBool)) {
			this.lowBool.add(varBool);
		} else if (!highBool.contains(varBool)) {
			this.highBool.add(varBool);
		}

		if (secondIteration && varBool.isHigh()) {
			return new VarBool(varBool.getName() + "--sec", true);
		} else {
			return varBool;
		}
	}

	@Override
	public MethodDefinition methodDefinition(MethodDefinition methodDefinition) {
		return new MethodDefinition(methodDefinition.getName(),
				methodDefinition.getBody().accept(this));
	}

	@Override
	public StatementBlock statementBlock(StatementBlock statementBlock) {
		if (statementBlock.getNumberOfChildren() == 0) {
			return statementBlock;
		} else {
			ArrayList<Statement> newChildren = new ArrayList<Statement>();
			for (int i = 0; i < statementBlock.getNumberOfChildren(); i++) {
				newChildren.add(statementBlock.getChild(i).accept(this));
			}
			return new StatementBlock(newChildren);
		}
	}

	@Override
	public ArrayIntAccess arrayIntAccess(ArrayIntAccess arrayIntAccess) {
		if (!arrayIntAccess.isHigh()
				&& !lowIntArrays.containsKey(arrayIntAccess.getName())) {
			lowIntArrays.put(arrayIntAccess.getName(),
					arrayIntAccess.getLength());
		}
		if (secondIteration) {
			return new ArrayIntAccess(arrayIntAccess.getName() + "--sec",
					arrayIntAccess.getLength(), arrayIntAccess.isHigh(),
					arrayIntAccess.getIndex());
		} else {
			return arrayIntAccess;
		}
	}

	@Override
	public ArrayBoolAccess arrayBoolAccess(ArrayBoolAccess arrayBoolAccess) {
		if (!arrayBoolAccess.isHigh()
				&& !lowBoolArrays.containsKey(arrayBoolAccess.getName())) {
			lowBoolArrays.put(arrayBoolAccess.getName(),
					arrayBoolAccess.getLength());
		}
		if (secondIteration) {
			return new ArrayBoolAccess(arrayBoolAccess.getName() + "--sec",
					arrayBoolAccess.getLength(), arrayBoolAccess.isHigh(),
					arrayBoolAccess.getIndex());
		} else {
			return arrayBoolAccess;
		}
	}

	@Override
	public ArrayDefinition arrayDefinition(ArrayDefinition arrayDefinition) {
		for (ArrayAssignment current : arrayDefinition.getAssignments()) {
			current.accept(this);
		}
		return null;
	}

	@Override
	public ArrayAssignment arrayAssignment(ArrayAssignment arrayAssignment) {
		if (arrayAssignment.getAccess().isHigh()) {
			return new ArrayAssignment(arrayAssignment.getLine(),
					new ArrayIntAccess("_dummyArray_"
							+ arrayAssignment.getAccess().getName(),
							arrayAssignment.getAccess().getLength(), true,
							arrayAssignment.getAccess().getIndex()), null);
		} else {
			arrayAssignment.getAccess().accept(this);
			if (arrayAssignment.getValue() != null) {
				return new ArrayAssignment(arrayAssignment.getLine(),
						(ArrayAccess) arrayAssignment.getAccess().accept(this),
						arrayAssignment.getValue().accept(this));
			} else {
				return arrayAssignment;
			}
		}

	}

}
