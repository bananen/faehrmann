package ast;

/**
 * This class defines the logical not.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Not extends UnaryTerm{

	public Not(Term subTerm) {
		super(subTerm);
	}

	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.not(this);
	}
	
	public String toSMTString() {
		return "(not " + subTerm.toSMTString() + ")";
	}
	
	public boolean equals(Not not) {
		return subTerm.equals(not.subTerm);
	}
	
}