package ast;

/**
 * This class defines the logical and.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class And extends BinaryTerm{
	
	public And(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.and(this);
	}
	
	public String toSMTString() {
		return "(and " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(And and) {
		return leftSubTerm.equals(and.leftSubTerm)
				&& rightSubTerm.equals(and.rightSubTerm);
	}
	
}