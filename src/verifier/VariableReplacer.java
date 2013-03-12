package verifier;

import java.util.ArrayList;

import ast.*;

/**
 * This class can be used to transform a given term into a new term in which the
 * replacement will be applied.
 * 
 * @author Fedor Scholz
 * 
 */

public class VariableReplacer implements ASTVisitor {

	private final Replacement replacement;
	private String dontChange;

	public VariableReplacer(Replacement replacement) {
		this.replacement = replacement;
	}

	/**
	 * Returns a term in which the replacement is applied.
	 */
	public Term replace(Term term) {
		Term result;
		if (term != null) {
			result = term.accept(this);
		} else {
			result = null;
		}
		return result;
	}

	@Override
	public ConstBool constBool(ConstBool constBool) {
		return constBool;
	}

	@Override
	public ConstInt constInt(ConstInt constInt) {
		return constInt;
	}

	/**
	 * Checks if the given variable is equal to the variable in the replacement
	 * and replaces it with the term given in the replacement if they are equal.
	 */
	@Override
	public RestrictedTerm varInt(VarInt varInt) {
		if (!(varInt.getName().equals(dontChange))
				&& varInt.getName().equals(replacement.getVar().getName())) {
			return replacement.getTerm();
		}

		return varInt;

	}

	/**
	 * Checks if the given variable is equal to the variable in the replacement
	 * and replaces it with the term given in the replacement if they are equal.
	 */
	@Override
	public RestrictedTerm varBool(VarBool varBool) {
		if (!(varBool.getName().equals(dontChange))
				&& varBool.getName().equals(replacement.getVar().getName())) {
			return replacement.getTerm();
		}

		return varBool;
	}

	@Override
	public MethodDefinition methodDefinition(MethodDefinition methodDefintion) {
		return null; // There should be no MethoDefintions in a term.
	}

	@Override
	public StatementBlock statementBlock(StatementBlock statementBlock) {
		return null; // There should be no StatementBlocks in a term.
	}

	@Override
	public Program visit(Program root) {
		return null; // This class is for terms only.
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
		return null; // There should be no assignments in a term.
	}

	@Override
	public Definition define(Definition definition) {
		return null; // There should be no definion in a term.
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
		return null; // There should be no MethodCalls in a term.
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
		return null; // There should be no assumption in a term.
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
		if (universalQuantifier == null
				|| universalQuantifier.getVariable() == null
				|| universalQuantifier.getVariable().getName() == null
				|| universalQuantifier.getSubTerm() == null) {
			return null;
		}
		dontChange = universalQuantifier.getVariable().getName();
		UniversalQuantifier result = new UniversalQuantifier(
				universalQuantifier.getVariable(), universalQuantifier
						.getSubTerm().accept(this));
		dontChange = null;
		return result;
	}

	@Override
	public ExistentialQuantifier exQuantify(
			ExistentialQuantifier existentialQuantifier) {
		if (existentialQuantifier == null
				|| existentialQuantifier.getVariable() == null
				|| existentialQuantifier.getVariable().getName() == null
				|| existentialQuantifier.getSubTerm() == null) {
			return null;
		}
		dontChange = existentialQuantifier.getVariable().getName();
		ExistentialQuantifier result = new ExistentialQuantifier(
				existentialQuantifier.getVariable(), existentialQuantifier
						.getSubTerm().accept(this));
		dontChange = null;
		return result;
	}

	@Override
	public ArrayIntAccess arrayIntAccess(ArrayIntAccess arrayIntAccess) {
		ArrayList<ArrayReplacement> oldReplacements = arrayIntAccess
				.getReplacements();
		ArrayList<ArrayReplacement> newReplacements = new ArrayList<ArrayReplacement>();
		for (ArrayReplacement current : oldReplacements) {
			if (current.getValue() != null) {
				newReplacements.add(new ArrayReplacement(current.getIndex()
						.accept(this), current.getValue().accept(this)));
			} else {
				newReplacements.add(new ArrayReplacement(current.getIndex()
						.accept(this), null));
			}
		}

		return new ArrayIntAccess(arrayIntAccess.getName(),
				arrayIntAccess.getLength(), arrayIntAccess.isHigh(),
				arrayIntAccess.getIndex().accept(this), newReplacements);
	}

	@Override
	public ArrayBoolAccess arrayBoolAccess(ArrayBoolAccess arrayBoolAccess) {
		ArrayList<ArrayReplacement> oldReplacements = arrayBoolAccess
				.getReplacements();
		ArrayList<ArrayReplacement> newReplacements = new ArrayList<ArrayReplacement>();
		for (ArrayReplacement current : oldReplacements) {
			if (current.getValue() != null) {
				newReplacements.add(new ArrayReplacement(current.getIndex()
						.accept(this), current.getValue().accept(this)));
			} else {
				newReplacements.add(new ArrayReplacement(current.getIndex()
						.accept(this), null));
			}
		}

		return new ArrayBoolAccess(arrayBoolAccess.getName(),
				arrayBoolAccess.getLength(), arrayBoolAccess.isHigh(),
				arrayBoolAccess.getIndex().accept(this), newReplacements);
	}

	@Override
	public ArrayDefinition arrayDefinition(ArrayDefinition arrayDefinition) {
		return null; // There should be no array definitions in a term.
	}

	@Override
	public ArrayAssignment arrayAssignment(ArrayAssignment arrayAssignment) {
		return null; // There should be no array assignments in a term.
	}

}
