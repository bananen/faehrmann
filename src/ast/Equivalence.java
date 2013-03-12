package ast;

/**
 * This class defines the logical equivalence.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Equivalence extends BinaryTerm{
	
	public Equivalence(Term leftSubTerm, Term rightSubTerm) {
		super(leftSubTerm, rightSubTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.equivalize(this);
	}
	
	public String toSMTString() {
		return "(= " + leftSubTerm.toSMTString() + " " + rightSubTerm.toSMTString() + ")";
	}
	
	public boolean equals(Equivalence equivalence) {
		return leftSubTerm.equals(equivalence.leftSubTerm)
				&& rightSubTerm.equals(equivalence.rightSubTerm);
	}
}