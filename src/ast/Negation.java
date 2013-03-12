package ast;

/**
 * This class defines the mathematical negation.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Negation extends UnaryTerm {
 
	public Negation(Term subTerm) {
		super(subTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor){
		return astVisitor.negate(this);
	}
	
	public String toSMTString() {
		return "(- " + subTerm.toSMTString() + ")";
	}
	
	public boolean equals(Negation negation) {
		return subTerm.equals(negation.subTerm);
	}
	
}