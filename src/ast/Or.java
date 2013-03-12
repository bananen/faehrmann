package ast;

/**
 * This class defines the logical or.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Or extends BinaryTerm{
	
	public Or(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.or(this);
	}
	
	public String toSMTString() {
		return "(or " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(Or or) {
		return leftSubTerm.equals(or.leftSubTerm)
				&& rightSubTerm.equals(or.rightSubTerm);
	}
}