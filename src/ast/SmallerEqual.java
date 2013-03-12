package ast;

/**
 * This class defines the logical smaller or equal.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class SmallerEqual extends BinaryTerm {
	
	public SmallerEqual(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.smallerequals(this);
	}
	
	public String toSMTString() {
		return "(<= " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(SmallerEqual smallerEqual) {
		return leftSubTerm.equals(smallerEqual.leftSubTerm)
				&& rightSubTerm.equals(smallerEqual.rightSubTerm);
	}
}