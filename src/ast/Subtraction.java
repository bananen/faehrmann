package ast;

/**
 * This class defines the mathematical subtraction.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Subtraction extends BinaryTerm{
	
	public Subtraction(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	@Override
	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.subtract(this);
	}
	
	public String toSMTString() {
		return "(- " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(Subtraction subtraction) {
		return leftSubTerm.equals(subtraction.leftSubTerm)
				&& rightSubTerm.equals(subtraction.rightSubTerm);
	}
	
}