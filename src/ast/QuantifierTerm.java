package ast;

/**
 * This class defines a quantified term which can only be used in assumptions
 * and assertions.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public abstract class QuantifierTerm extends Term {

	protected final ASTVariable variable;
	protected final Term subTerm;

	public QuantifierTerm(ASTVariable variable, Term subTerm) {
		this.variable = variable;
		this.subTerm = subTerm;
	}

	public ASTVariable getVariable() {
		return variable;
	}

	public Term getSubTerm() {
		return subTerm;
	}
	
	public abstract String toSMTString();

}