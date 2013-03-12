package ast;

/**
 * This class defines the logical implication.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Implication extends BinaryTerm{
	
	public Implication(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.imply(this);
	}
	
	public String toSMTString() {
		return "(=> " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(Implication implication) {
		return leftSubTerm.equals(implication.leftSubTerm)
				&& rightSubTerm.equals(implication.rightSubTerm);
	}
	
}