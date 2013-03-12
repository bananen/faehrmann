package verifier;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * This class transforms a given program into a program without while loops.
 * Those while loops will be transformed into multiple if statements,
 * depending of the given depth.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

import ast.*;

/**
 * This class can be used to transform a given program with while loops in to a
 * new program with if statements instead of while loops.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class LoopToIf implements ASTVisitor {

	private final int maxLoopCount;
	private int loopLeft;

	public LoopToIf(int maxLoopCount) {
		this.maxLoopCount = maxLoopCount;

	}

	@Override
	public Program visit(Program root) {

		if (root == null) {
			return null;
		}

		ArrayList<LegalProgramChild> newChildren = new ArrayList<LegalProgramChild>();

		for (int i = 0; i < root.getNumberOfChildren(); i++) {
			if (root.getChild(i) != null) {
				newChildren.add(root.getChild(i).accept(this));
			} else {
				System.out.println("NULLKIND!");
			}
		}

		return new Program(newChildren);
	}

	@Override
	public RestrictedTerm add(Addition addition) {
		return addition;
	}

	@Override
	public RestrictedTerm and(And and) {
		return and;
	}

	@Override
	public Assignment assign(Assignment assignment) {
		return assignment;
	}

	@Override
	public Definition define(Definition definition) {
		return definition;
	}

	@Override
	public RestrictedTerm greater(GreaterThan greaterThan) {
		return greaterThan;
	}

	@Override
	public RestrictedTerm greaterequals(GreaterEqual greaterEqual) {
		return greaterEqual;
	}

	@Override
	public MethodCall callMethod(MethodCall methodCall) {
		return methodCall;
	}

	@Override
	public RestrictedTerm equal(Equal equal) {
		return equal;
	}

	@Override
	public RestrictedTerm negate(Negation negation) {
		return negation;
	}

	@Override
	public RestrictedTerm multiply(Multiplication multiplication) {
		return multiplication;
	}

	@Override
	public RestrictedTerm smaller(SmallerThan smallerThan) {
		return smallerThan;
	}

	@Override
	public RestrictedTerm not(Not not) {
		return not;
	}

	@Override
	public RestrictedTerm or(Or or) {
		return or;
	}

	@Override
	public RestrictedTerm subtract(Subtraction subtraction) {
		return subtraction;
	}

	@Override
	public RestrictedTerm smallerequals(SmallerEqual smallerEqual) {
		return smallerEqual;
	}

	@Override
	public RestrictedTerm unequal(Unequal unequal) {
		return unequal;
	}

	@Override
	public Statement astIf(IfBlock ifBlock) {
		return new IfBlock(ifBlock.getLine(), ifBlock.getCondition().accept(
				this), ifBlock.getThenBlock().accept(this), ifBlock
				.getElseBlock().accept(this));
	}

	@Override
	public Statement astWhile(WhileBlock whileBlock) {
		loopLeft = maxLoopCount;
		return createIfBlock(whileBlock.getCondition(), whileBlock.getBody());
	}

	private IfBlock createIfBlock(RestrictedTerm term,
			StatementBlock statementBlock) {
		if (loopLeft > 1) {
			loopLeft--;
			ArrayList<Statement> list = new ArrayList<Statement>();
			for (int i = 0; i < statementBlock.getNumberOfChildren(); i++) {
				// if (statementBlock.getChild(i) != null) {
				list.add(statementBlock.getChild(i).accept(this));
				// }
			}
			list.add(createIfBlock(term, statementBlock));
			StatementBlock thenBlock = new StatementBlock(list);
			StatementBlock elseBlock = new StatementBlock(
					new ArrayList<Statement>());
			return new IfBlock(0, term, thenBlock, elseBlock);
		} else {
			Assumption assumption = new Assumption(0, new ConstBool(
					new BigInteger("0")));
			ArrayList<Statement> list = new ArrayList<Statement>();
			list.add(assumption);
			for (int i = 0; i < statementBlock.getNumberOfChildren(); i++) {
				list.add(statementBlock.getChild(i).accept(this));
			}
			StatementBlock thenBlock = new StatementBlock(list);
			StatementBlock elseBlock = new StatementBlock(
					new ArrayList<Statement>());
			return new IfBlock(0, term, thenBlock, elseBlock);
		}
	}

	@Override
	public Assertion astAssert(Assertion assertion) {
		return assertion;
	}

	@Override
	public Assumption assume(Assumption assumption) {
		return assumption;
	}

	@Override
	public RestrictedTerm imply(Implication implication) {
		return implication;
	}

	@Override
	public RestrictedTerm equivalize(Equivalence equivalence) {
		return equivalence;
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
		return varInt;
	}

	@Override
	public VarBool varBool(VarBool varBool) {
		return varBool;
	}

	@Override
	public MethodDefinition methodDefinition(MethodDefinition methodDefinition) {
		if (methodDefinition == null) {
			return null;
		} else {
			if (methodDefinition.getBody() == null) {
				return methodDefinition;
			} else {
				return new MethodDefinition(methodDefinition.getName(),
						methodDefinition.getBody().accept(this));
			}
		}

	}

	@Override
	public StatementBlock statementBlock(StatementBlock statementBlock) {
		if (statementBlock == null) {
			return null;
		}
		ArrayList<Statement> children = new ArrayList<Statement>();
		for (int i = 0; i < statementBlock.getNumberOfChildren(); i++) {
			children.add(statementBlock.getChild(i).accept(this));
		}
		return new StatementBlock(children);
	}

	@Override
	public ArrayIntAccess arrayIntAccess(ArrayIntAccess arrayIntAccess) {
		return arrayIntAccess;
	}

	@Override
	public ArrayBoolAccess arrayBoolAccess(ArrayBoolAccess arrayBoolAccess) {
		return arrayBoolAccess;
	}

	@Override
	public ArrayDefinition arrayDefinition(ArrayDefinition arrayDefinition) {
		return arrayDefinition;
	}

	@Override
	public ArrayAssignment arrayAssignment(ArrayAssignment arrayAssignment) {
		return arrayAssignment;
	}

}