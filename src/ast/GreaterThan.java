package ast;

/**
 * This class defines the logical greater than.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class GreaterThan extends BinaryTerm{
	
	public GreaterThan(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.greater(this);
	}
	
	public String toSMTString() {
		return "(> " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(GreaterThan greaterThan) {
		return leftSubTerm.equals(greaterThan.leftSubTerm)
				&& rightSubTerm.equals(greaterThan.rightSubTerm);
	}
}