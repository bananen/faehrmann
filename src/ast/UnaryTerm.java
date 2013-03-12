package ast;

/**
 * This class defines a binary term which always has one term as a child.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public abstract class UnaryTerm extends RestrictedTerm {
	
	protected final Term subTerm;
	
	public UnaryTerm (Term subTerm) {
		this.subTerm = subTerm;
	}
	
	public Term getSubTerm() {
		return subTerm;
	}
	
}