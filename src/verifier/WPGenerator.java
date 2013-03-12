package verifier;

import java.math.BigInteger;
import java.util.ArrayList;

import ast.*;

/**
 * This class can be used to get the weakest precondition of a given program.
 * 
 * @author Fedor Scholz
 * 
 */

public class WPGenerator implements ASTVisitor {

	private Term wp;
	private boolean visitMethod;

	public WPGenerator(Term wp) {
		if (wp == null) {
			this.wp = new ConstBool(new BigInteger("1"));
		} else {
			this.wp = wp;
		}
	}

	/**
	 * Returns the weakest precondition of a given block of statements and a
	 * given postcondition.
	 */
	public Term wp(StatementBlock block, Term wp) {
		if (block == null) {
			return null;
		}
		WPGenerator wpGenerator = new WPGenerator(wp);
		ArrayList<LegalProgramChild> list = new ArrayList<LegalProgramChild>();
		for (int i = 0; i < block.getNumberOfChildren(); i++) {
			list.add(block.getChild(i));
		}
		Program program = new Program(list);
		return wpGenerator.visit(program);
	}

	/**
	 * Entry point. Return the negated weakest precondition of a given program.
	 */
	public Term getWP(Program root) {
		if (root == null) {
			return null;
		}
		return new Not(visit(root));
	}

	/**
	 * Returns the weakest precondition of a given program.
	 */
	@Override
	public Term visit(Program root) {
		visitMethod = false;
		int lines = root.getNumberOfChildren();
		for (int i = 0; i < lines; i++) {
			root.getChild(lines - i - 1).accept(this);
		}
		return wp;
	}

	@Override
	public RestrictedTerm add(Addition addition) {
		return addition;
	}

	@Override
	public RestrictedTerm and(And and) {
		return and;
	}

	/**
	 * Uses a VariableReplacer to replace the appropriate variable in the term
	 * which is calculated until now.
	 */
	@Override
	public Assignment assign(Assignment assignment) {
		if (assignment == null || assignment.getTerm() == null) {
			return null;
		}

		Replacement replacement = new Replacement(assignment.getVariable(),
				assignment.getTerm());
		VariableReplacer variableReplacer = new VariableReplacer(replacement);
		wp = variableReplacer.replace(wp);

		return null;
	}

	/**
	 * Uses a VariableReplacer to replace the appropriate variable in the term
	 * which is calculated until now.
	 */
	@Override
	public Definition define(Definition definition) {
		if (definition == null || definition.getTerm() == null) {
			return null;
		}

		Replacement replacement = new Replacement(definition.getVariable(),
				definition.getTerm());
		VariableReplacer variableReplacer = new VariableReplacer(replacement);
		wp = variableReplacer.replace(wp);

		return null;
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
		if (methodCall == null || methodCall.getMethodDefinition() == null) {
			return null;
		}
		visitMethod = true;
		visitMethod = true;
		methodCall.getMethodDefinition().accept(this);
		visitMethod = false;
		return null;
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

	/**
	 * Transforms the already calculated term into a new term which "includes"
	 * the given if statement.
	 */
	@Override
	public Statement astIf(IfBlock ifBlock) {
		wp = new Or(new And(ifBlock.getCondition(), wp(ifBlock.getThenBlock(),
				wp)), new And(new Not(ifBlock.getCondition()), wp(
				ifBlock.getElseBlock(), wp)));
		return null;
	}

	@Override
	public Statement astWhile(WhileBlock whileBlock) {
		return null; // There should be no WhileBlocks because of the LoopToIf
						// Visitor
	}

	/**
	 * Transforms the already calculated term into a new conjunction of the
	 * already calculated term and the given assertion.
	 */
	@Override
	public Assertion astAssert(Assertion assertion) {
		wp = new And(assertion.getTerm(), wp);
		return null;
	}

	/**
	 * Transforms the already calculated term into a new implication of the
	 * already calculated term and the given assertion.
	 */
	@Override
	public Assumption assume(Assumption assumption) {
		wp = new Implication(assumption.getTerm(), wp);
		return null;
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
	public RestrictedTerm varInt(VarInt varInt) {
		return varInt;
	}

	@Override
	public RestrictedTerm varBool(VarBool varBool) {
		return varBool;
	}

	/**
	 * Checks whether the visitation of methods is allowed by a method call a
	 * visits its body if so.
	 */
	@Override
	public MethodDefinition methodDefinition(MethodDefinition methodDefinition) {
		if (methodDefinition == null || methodDefinition.getBody() == null) {
			return null;
		}
		if (visitMethod) {
			wp = wp(methodDefinition.getBody(), wp);
		}
		return null;
	}

	@Override
	public StatementBlock statementBlock(StatementBlock statementBlock) {
		return null; // There wont be statementblocks to visit, I hope..

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
		if (arrayDefinition.getAssignments() != null) {
			for (ArrayAssignment current : arrayDefinition.getAssignments()) {
				current.accept(this);
			}
		}
		return null;
	}

	@Override
	public ArrayAssignment arrayAssignment(ArrayAssignment arrayAssignment) {
		ArrayReplacement newReplacement = new ArrayReplacement(arrayAssignment
				.getAccess().getIndex(), arrayAssignment.getValue());
		ArrayReplacementAppender arrayReplacementAppender = new ArrayReplacementAppender(
				arrayAssignment.getAccess().getName(), newReplacement);
		wp = arrayReplacementAppender.replace(wp);
		return null;
	}

}
