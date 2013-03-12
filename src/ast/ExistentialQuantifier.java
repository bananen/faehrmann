package ast;

/**
 * This class defines an existential quantifier.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class ExistentialQuantifier extends QuantifierTerm {

	public ExistentialQuantifier(ASTVariable variable, Term subTerm) {
		super(variable, subTerm);
	}

	@Override
	public ExistentialQuantifier accept(ASTVisitor astVisitor) {
		return astVisitor.exQuantify(this);
	}
	
	public String toSMTString() {
		return subTerm.toSMTString();
	}
	
	public boolean equals(ExistentialQuantifier existentialQuantifier) {
		return variable.equals(existentialQuantifier.variable)
				&& subTerm.equals(existentialQuantifier.subTerm);
	}
	
}