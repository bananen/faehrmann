package ast;

/**
 * This class defines the mathematical addition.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class Addition extends BinaryTerm {

	public Addition(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.add(this);
	}

	public String toSMTString() {
		return "(+ " + leftSubTerm.toSMTString() + " "
				+ rightSubTerm.toSMTString() + ")";
	}

	public boolean equals(Addition addition) {
		return leftSubTerm.equals(addition.leftSubTerm)
				&& rightSubTerm.equals(addition.rightSubTerm);
	}

}