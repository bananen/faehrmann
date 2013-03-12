package ast;

/**
 * This class defines the logical greater or equal.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class GreaterEqual extends BinaryTerm{
		
	public GreaterEqual(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.greaterequals(this);
	}
	
	public String toSMTString() {
		return "(>= " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(GreaterEqual greaterEqual) {
		return leftSubTerm.equals(greaterEqual.leftSubTerm)
				&& rightSubTerm.equals(greaterEqual);
	}
	
}