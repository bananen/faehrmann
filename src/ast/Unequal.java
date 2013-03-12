package ast;

/**
 * This class defines the logical unequal.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Unequal extends BinaryTerm{
	
	public Unequal(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.unequal(this);
	}
	
	public String toSMTString() {
		return "(not (= " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + "))";
	}
	
	public boolean equals(Unequal unequal) {
		return leftSubTerm.equals(unequal.leftSubTerm)
				&& rightSubTerm.equals(unequal.rightSubTerm);
	}
}