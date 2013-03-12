package ast;

/**
 * This class defines the logical equal.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Equal extends BinaryTerm {
	
	public Equal(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.equal(this);
	}
	
	public String toSMTString() {
		return "(= " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(Equal equal) {
		return leftSubTerm.equals(equal.leftSubTerm)
				&& rightSubTerm.equals(equal.rightSubTerm);
	}
}