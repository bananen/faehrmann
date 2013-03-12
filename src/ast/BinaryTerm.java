package ast;

/**
 * This class defines a binary term which always has two terms as children.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public abstract class BinaryTerm extends RestrictedTerm {

	protected final Term leftSubTerm;
	protected final Term rightSubTerm;

	public BinaryTerm(Term leftSubTerm, Term rightSubTerm) {
		this.leftSubTerm = leftSubTerm;
		this.rightSubTerm = rightSubTerm;
	}

	public Term getLeftSubTerm() {
		return leftSubTerm;
	}

	public Term getRightSubTerm() {
		return rightSubTerm;
	}
}
