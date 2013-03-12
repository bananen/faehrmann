package ast;

/**
 * This class defines an universal quantifier.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class UniversalQuantifier extends QuantifierTerm {

	public UniversalQuantifier(ASTVariable variable, Term subTerm) {
		super(variable, subTerm);
	}

	@Override
	public UniversalQuantifier accept(ASTVisitor astVisitor) {
		return astVisitor.uniQuantify(this);
	}
	
	public String toSMTString() {
		return "(forall ((" + variable.getName() + " " + variable.getSMTType() + ")) " + subTerm.toSMTString() + ")";
	}
	
	public boolean equals(UniversalQuantifier universalQuantifier) {
		return variable.equals(universalQuantifier.variable)
				&& subTerm.equals(universalQuantifier.subTerm);
	}

}