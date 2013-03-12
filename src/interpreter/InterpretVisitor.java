package interpreter;

import java.math.BigInteger;
import java.util.LinkedList;

import core.GUIInterface;
import ast.*;

/**
 * This class represents the visitor of the AST.
 * It is the main class of the interpreter as it interprets the nodes of our AST.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 * 
 */
public class InterpretVisitor implements ASTVisitor{

	private InterpreterState interpreterState;
	private Variable currentVariable = new Bool(bigInt(2), "+");
	private GUIInterface gui = null;
	private boolean assignmentVariable = false;
	private boolean assignmentArray = false;
	private boolean isBool = false;
	private boolean paused = false;
	private LinkedList<ResumeToken> resumeTrace;
	private LinkedList<Integer> breakpoints;
	private boolean ignoreNextBreakpoint;
	
	public InterpretVisitor() {
		resetVariable();
	}
	
	/**
	 * Starts the debugging process.
	 * 
	 * Starts to interpret the program, but stops at breakpoints.
	 * Gives the possibility to start at the Breakpoint with resumeDebug().
	 * 
	 * @param program The root-node of the program to interpret
	 * @param gui The current GUIInterface, in order to make requests for variable values.
	 * @param breakLines The lines where the debugging process will stop.
	 * @return The result of the interpreting process including variable values,
	 *  the current breakline and possible errors of assumes and asserts.
	 */
	public InterpreterState startDebug(Program program, GUIInterface gui, int[] breakLines) {
		this.gui = gui;
		resetInterpreter();
		addBreakpoints(breakLines);
		program.accept(this);
		return interpreterState;
	}

	private void addBreakpoints(int[] breakLines) {
		for (int i = 0; i < breakLines.length; i++) {
			breakpoints.add((Integer)breakLines[i]);
		}
	}

	private void pauseDebug(Statement breakStatement) {
		paused = true;
		interpreterState.setCurrentLine(breakStatement.getLine());
	}

	/**
	 * Resumes the debugging process, which is started by startDebug() and paused by reaching a breakpoint
	 * @param breakpoints2 
	 * 
	 * @return The result of the interpreting process including variable values,
	 *  the current breakline and possible errors of assumes and asserts.
	 */
	public InterpreterState resumeDebug(int[] breakLines) {
		paused = false;
		breakpoints = new LinkedList<Integer>();
		addBreakpoints(breakLines);
		@SuppressWarnings("unchecked")
		LinkedList<ResumeToken> resumeJobList = (LinkedList<ResumeToken>)resumeTrace.clone();
		int jobCount = resumeJobList.size();
		ignoreNextBreakpoint = true;
		for (int i = 0; i < jobCount && !paused; i++){
			ResumeToken resumeToken = resumeJobList.getFirst();
			if (resumeToken.getType() == ResumeToken.PROGRAM) {
				visitProgram(resumeToken.getProgram(), resumeToken.getIndex());
			} else if (resumeToken.getType() == ResumeToken.STATEMENTBLOCK) {
				visitStatementBlock(resumeToken.getStatementBlock(), resumeToken.getIndex());
				
			} else if (resumeToken.getType() == ResumeToken.WHILEBLOCK) {
				visitWhileBlock(resumeToken.getWhileBlock());
			}
			if (!paused) {
				resumeJobList.removeFirst();
			}
		}
		if (resumeJobList.size() == 0) {
			interpreterState.setDone();
		}
		return interpreterState;
	}

	/**
	 * Starts to interpret the program without stopping at breakpoints
	 * 
	 * @param program
	 * @param gui
	 * @return
	 */
	public InterpreterState interprete(Program program, GUIInterface gui) {
		resetInterpreter();
		this.gui = gui;
		program.accept(this);
		return interpreterState;
	}
	
	@Override
	public Node visit(Program root) {
		resumeTrace.addFirst(new ResumeToken(root, 0));
		visitProgram(root, 0);
		return null;
	}

	@Override
	public RestrictedTerm add(Addition addition) {
		BigInteger value;
		addition.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		addition.getRightSubTerm().accept(this);
		currentVariable.setValue(value.add(currentVariable.getValue()));
		return null;
	}

	@Override
	public RestrictedTerm and(And and) {
		BigInteger value;
		and.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		and.getRightSubTerm().accept(this);
		if (currentVariable.getValue().equals(bigInt(0)) || value.equals(bigInt(0))) {
			currentVariable.setValue(0);
		} else {
			currentVariable.setValue(1);
		}
		return null;
	}

	@Override
	public Assignment assign(Assignment assignment) {
		assignmentVariable = true;
		assignment.getVariable().accept(this); 
		assignmentVariable = false;
		assignment.getTerm().accept(this);
		if (isBool && !currentVariable.getValue().equals(bigInt(0))) {
			currentVariable.setValue(1);
		}
		if (!currentVariable.getName().endsWith(".length")) {
			interpreterState.setVariable(currentVariable);
			resetVariable();
		}
		return null;
	}

	@Override
	public Definition define(Definition definition) {
		assignmentVariable = true;
		definition.getVariable().accept(this);
		assignmentVariable = false;
		try {
			definition.getTerm().accept(this);
		} catch (NullPointerException e) {
			BigInteger answer = gui.requestVariable(currentVariable.getName(), definition.getLine());
			if (answer == null) {
				paused = true;
				interpreterState.setDone();
			} else {
				currentVariable.setValue(answer);
			}
		}
		if (!interpreterState.isDone()) {
			if (isBool && !currentVariable.getValue().equals(bigInt(0))) {
				currentVariable.setValue(1);
			}
			if (!currentVariable.getName().endsWith(".length")) {
				interpreterState.setVariable(currentVariable);
				resetVariable();
			}
		}
		return null;
	}

	@Override
	public RestrictedTerm greater(GreaterThan greaterThan) {
		BigInteger value;
		greaterThan.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		greaterThan.getRightSubTerm().accept(this);
		if (value.compareTo(currentVariable.getValue()) == 1) {
			currentVariable.setValue(1);
		} else {
			currentVariable.setValue(0);
		}
		return null;
	}

	@Override
	public RestrictedTerm greaterequals(GreaterEqual greaterEqual) {
		BigInteger value;
		greaterEqual.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		greaterEqual.getRightSubTerm().accept(this);
		if (value.compareTo(currentVariable.getValue()) != -1) {
			currentVariable.setValue(1);
		} else {
			currentVariable.setValue(0);
		}
		return null;
	}

	@Override
	public MethodCall callMethod(MethodCall methodCall) {
		methodCall.getMethodDefinition().getBody().accept(this);
		return null;
	}

	@Override
	public RestrictedTerm equal(Equal equal) {
		BigInteger value;
		equal.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		equal.getRightSubTerm().accept(this);
		if (value.equals(currentVariable.getValue())) {
			currentVariable.setValue(1);
		} else {
			currentVariable.setValue(0);
		}
		return null;
	}

	@Override
	public RestrictedTerm negate(Negation negation) {
		negation.getSubTerm().accept(this);
		currentVariable.setValue(bigInt(0).subtract(currentVariable.getValue()));
		return null;
	}

	@Override
	public RestrictedTerm multiply(Multiplication multiplication) {
		BigInteger value;
		multiplication.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		multiplication.getRightSubTerm().accept(this);
		currentVariable.setValue(value.multiply(currentVariable.getValue()));
		return null;
	}

	@Override
	public RestrictedTerm smaller(SmallerThan smallerThan) {
		BigInteger value;
		smallerThan.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		smallerThan.getRightSubTerm().accept(this);
		if (value.compareTo(currentVariable.getValue()) == -1) {
			currentVariable.setValue(1);
		} else {
			currentVariable.setValue(0);
		}
		return null;
	}

	@Override
	public RestrictedTerm not(Not not) {
		not.getSubTerm().accept(this);
		if (currentVariable.getValue().equals(bigInt(0))) {
			currentVariable.setValue(1);
		} else {
			currentVariable.setValue(0);
		}
		return null;
	}

	@Override
	public RestrictedTerm or(Or or) {
		BigInteger value;
		or.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		or.getRightSubTerm().accept(this);
		if(value.equals(bigInt(0)) && currentVariable.getValue().equals(bigInt(0))) {
			currentVariable.setValue(0);
		} else {
			currentVariable.setValue(1);
		}
		return null;
	}

	@Override
	public RestrictedTerm subtract(Subtraction subtraction) {
		BigInteger value;
		subtraction.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		subtraction.getRightSubTerm().accept(this);
		currentVariable.setValue(value.subtract(currentVariable.getValue()));
		return null;
	}

	@Override
	public RestrictedTerm smallerequals(SmallerEqual smallerEqual) {
		BigInteger value;
		smallerEqual.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		smallerEqual.getRightSubTerm().accept(this);
		if (value.compareTo(currentVariable.getValue()) != 1) {
			currentVariable.setValue(1);
		} else {
			currentVariable.setValue(0);
		}
		return null;
	}

	@Override
	public RestrictedTerm unequal(Unequal unequal) {
		BigInteger value;
		unequal.getLeftSubTerm().accept(this);
		value = currentVariable.getValue();
		unequal.getRightSubTerm().accept(this);
		if (value.equals(currentVariable.getValue())) {
			currentVariable.setValue(0);
		} else {
			currentVariable.setValue(1);
		}
		return null;
	}

	@Override
	public Statement astIf(IfBlock ifBlock) {
		ifBlock.getCondition().accept(this);
		if (getBool()) {
			ifBlock.getThenBlock().accept(this);
		} else {
			if (ifBlock.getElseBlock() != null) {
				ifBlock.getElseBlock().accept(this);
			}
		}
		return null;
	}

	@Override
	public Statement astWhile(WhileBlock whileBlock) {
		resumeTrace.addFirst(new ResumeToken(whileBlock));
		visitWhileBlock(whileBlock);
		return null;
	}
	
	private void visitWhileBlock(WhileBlock whileBlock) {
		whileBlock.getCondition().accept(this);
		int loopCount = 0;
		while (getBool() && !paused && !interpreterState.isDone()) {
			loopCount++;
			whileBlock.getBody().accept(this);
			whileBlock.getCondition().accept(this);
			if (loopCount == 1000000) {
				boolean resume = gui.askResume();
				if (resume) {
					loopCount = 0;
				} else {
					interpreterState.setDone();
				}
			}
		}
		if (!paused) {
			resumeTrace.removeFirst();
		}
	}

	@Override
	public Assertion astAssert(Assertion assertion) {
		assertion.getTerm().accept(this);
		if (currentVariable.getValue() != null) {
				if (currentVariable.getValue().equals(bigInt(0))) {
				interpreterState.addError(assertion.getLine(), "Assertion not accurate in line " + assertion.getLine() + ".");
			} else {
				interpreterState.addMessage("The assertion in line " + assertion.getLine() + " is accurate.");
			} 
		} else {
			interpreterState.addError(assertion.getLine(), "The assertion in line " + assertion.getLine() + " can't be interpreted.");
		}
		return null;
	}

	@Override
	public Assumption assume(Assumption assumption) {
		assumption.getTerm().accept(this);
		if (currentVariable.getValue() != null) {
			if (currentVariable.getValue().equals(bigInt(0))) {
				interpreterState.addError(assumption.getLine(), "Assumption not accurate in line " + assumption.getLine() + ".");
			} else {
				interpreterState.addMessage("The assumption in line " + assumption.getLine() + " is accurate.");
			}
		} else {
			interpreterState.addError(assumption.getLine(), "The assumption in line " + assumption.getLine() + " can't be interpreted.");
		}
		return null;
	}

	@Override
	public RestrictedTerm imply(Implication implication) {
		BigInteger value;
		implication.getLeftSubTerm().accept(this);
		value = castBool(currentVariable.getValue());
		implication.getRightSubTerm().accept(this);
		if (value.equals(bigInt(1)) && castBool(currentVariable.getValue()).equals(bigInt(0))) {
			currentVariable.setValue(0);
		} else {
			currentVariable.setValue(1);
		}
		return null;
	}

	@Override
	public RestrictedTerm equivalize(Equivalence equivalence) {
		BigInteger value;
		equivalence.getLeftSubTerm().accept(this);
		value = castBool(currentVariable.getValue());
		equivalence.getRightSubTerm().accept(this);
		if (value.equals(castBool(currentVariable.getValue()))) {
			currentVariable.setValue(1);
		} else {
			currentVariable.setValue(0);
		}
		return null;
	}

	@Override
	public UniversalQuantifier uniQuantify(
			UniversalQuantifier universalQuantifier) {
		currentVariable.setValue(null);
		return null;
	}

	@Override
	public ExistentialQuantifier exQuantify(
			ExistentialQuantifier existentialQuantifier) {
		currentVariable.setValue(null);
		return null;
	}

	@Override
	public ConstBool constBool(ConstBool constBool) {
		currentVariable.setValue(constBool.getValue());
		return null;
	}

	@Override
	public ConstInt constInt(ConstInt constInt) {
		currentVariable.setValue(constInt.getValue());
		return null;
	}

	@Override
	public RestrictedTerm varInt(VarInt varInt) {
		String name = varInt.getName();
		if (assignmentVariable) {
			if (name.endsWith(".length")) {
				currentVariable = new IntArray(bigInt(0), name);
			} else {
				currentVariable = new Int(bigInt(0), name);
			}
			isBool = false;
		} else {
			currentVariable.setValue(interpreterState.getValueOf(name));
		}
		return null;
	}

	@Override
	public RestrictedTerm varBool(VarBool varBool) {
		String name = varBool.getName();
		if (assignmentVariable) {
			currentVariable = new Bool(bigInt(0), name);
			isBool = true;
		} else {
			currentVariable.setValue(interpreterState.getValueOf(name));
		}
		return null;
	}

	@Override
	public MethodDefinition methodDefinition(MethodDefinition methodDefinition) {
		return null;
	}

	@Override
	public StatementBlock statementBlock(StatementBlock statementBlock) {
		resumeTrace.addFirst(new ResumeToken(statementBlock, 0));
		interpreterState.openScope();
		visitStatementBlock(statementBlock, 0);
		return null;
	}
	
	private void visitStatementBlock(StatementBlock statementBlock, int firstIndex) {
		for(int i = firstIndex; i < statementBlock.getNumberOfChildren() && !paused && !interpreterState.isDone(); i++) {
			if (breakpoints.contains((Integer)((Statement)statementBlock.getChild(i)).getLine())) {
				if (ignoreNextBreakpoint) {
					try {
						if (!(((Assignment)statementBlock.getChild(i)).getVariable().getName().endsWith(".length"))) {
							ignoreNextBreakpoint = false;
						}
					} catch (ClassCastException e) {
						ignoreNextBreakpoint = false;
					}
				} else {
					pauseDebug((Statement)statementBlock.getChild(i));
				}
			}
			if (!paused) {
				resumeTrace.removeFirst();
				resumeTrace.addFirst(new ResumeToken(statementBlock, i + 1));
				statementBlock.getChild(i).accept(this);
			}
		}
		if (!paused) {
			interpreterState.closeScope();
			resumeTrace.removeFirst();
		}
	}
	
	private void visitProgram(Program program, int firstIndex) {
		if (0 == program.getNumberOfChildren()) {
			interpreterState.setDone();
		} else {
			for (int i = firstIndex ; i < program.getNumberOfChildren() && !paused && !interpreterState.isDone(); i++) {
				try {
					if (breakpoints.contains((Integer)((Statement)program.getChild(i)).getLine())) {
						if (ignoreNextBreakpoint) {
							try {
								if (!(((Assignment)program.getChild(i)).getVariable().getName().endsWith(".length"))) {
									ignoreNextBreakpoint = false;
								}
							} catch (ClassCastException e) {
								ignoreNextBreakpoint = false;
							}
						} else {
							pauseDebug((Statement)program.getChild(i));
						}
					}
				} catch (ClassCastException e) {}
				if (!paused) {
					resumeTrace.removeFirst();
					resumeTrace.addFirst(new ResumeToken(program, i + 1));
					program.getChild(i).accept(this);
					if (i == program.getNumberOfChildren() - 1 && !paused) {
						interpreterState.setDone();
					}
				}
			}
		}
	}
	
	private void resetInterpreter() {
		interpreterState = new InterpreterState();
		currentVariable = new Bool(bigInt(2), "+");
		assignmentVariable = false;
		isBool = false;
		paused = false;
		breakpoints = new LinkedList<Integer>();
		resumeTrace = new LinkedList<ResumeToken>();
		ignoreNextBreakpoint = false;
	}
	
	private BigInteger castBool(BigInteger value) {
		BigInteger result = bigInt(0);
		if (!value.equals(BigInteger.ZERO)) {
			result = bigInt(1);
		}
		return result;
	}
	
	private boolean getBool() {
		return currentVariable.getValue().equals(bigInt(1));
	}
	
	private static BigInteger bigInt(int value) {
		return new BigInteger(String.valueOf(value));
	}

	@Override
	public ArrayIntAccess arrayIntAccess(ArrayIntAccess arrayIntAccess) {
		String name = arrayIntAccess.getName() + ".length";
		arrayIntAccess.getIndex().accept(this);
		BigInteger index = currentVariable.getValue();
		if (assignmentArray) {
			currentVariable = new IntArray(index, name);
			isBool = false;
		} else {
			currentVariable.setValue(interpreterState.getValueOf(name, index));
		}
		return null;
	}

	@Override
	public ArrayBoolAccess arrayBoolAccess(ArrayBoolAccess arrayBoolAccess) {
		String name = arrayBoolAccess.getName() + ".length";
		arrayBoolAccess.getIndex().accept(this);
		BigInteger index = currentVariable.getValue();
		if (assignmentArray) {
			currentVariable = new BoolArray(index, name);
			isBool = true;
		} else {
			currentVariable.setValue(interpreterState.getValueOf(name, index));
		}
		return null;
	}

	@Override
	public ArrayDefinition arrayDefinition(ArrayDefinition arrayDefinition) {
		if (arrayDefinition.getType() == ArrayDefinition.BOOL) {
			currentVariable = new BoolArray(currentVariable.getValue(), currentVariable.getName());
		} else {
			currentVariable = new IntArray(currentVariable.getValue(), currentVariable.getName());
		}
		interpreterState.setVariable(currentVariable);
		resetVariable();
		for (ArrayAssignment assignment : arrayDefinition.getAssignments()) {
			assignment.accept(this);
		}
		return null;
	}

	@Override
	public ArrayAssignment arrayAssignment(ArrayAssignment arrayAssignment) {
		assignmentArray = true;
		arrayAssignment.getAccess().accept(this);
		String name = currentVariable.getName();
		BigInteger index = currentVariable.getValue();
		assignmentArray = false;
		try {
			arrayAssignment.getValue().accept(this);
			BigInteger value = currentVariable.getValue();
			if (isBool && !value.equals(BigInteger.ZERO)) {
				value = BigInteger.ONE;
			}
			interpreterState.setArrayVariable(name, index, value);
		} catch (NullPointerException e) {
			BigInteger answer = gui.requestVariable(currentVariable.getName() + "[" 
		+ currentVariable.getValue().toString() + "]", arrayAssignment.getLine());
			if (answer == null) {
				paused = true;
				interpreterState.setDone();
			} else {
				interpreterState.setArrayVariable(name, index, answer);
			}
		}
		return null;
	}
	
	private void resetVariable() {
		currentVariable = new Bool(bigInt(2), "+");
	}

}