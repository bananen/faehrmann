package ast;

/**
 * This class defines the logical smaller than.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class SmallerThan extends BinaryTerm{
	
	public SmallerThan(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.smaller(this);
	}
	
	public String toSMTString() {
		return "(< " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(SmallerThan smallerThan) {
		return leftSubTerm.equals(smallerThan.leftSubTerm)
				&& rightSubTerm.equals(smallerThan.rightSubTerm);
	}
	
}