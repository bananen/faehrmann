package ast;

/**
 * This class defines the mathematical multiplication.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Multiplication extends BinaryTerm{
	
	public Multiplication(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.multiply(this);
	}
	
	public String toSMTString() {
		return "(* " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(Multiplication multiplication) {
		return leftSubTerm.equals(multiplication.leftSubTerm)
				&& rightSubTerm.equals(multiplication.rightSubTerm);
	}
}