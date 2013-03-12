package ast;

/**
 * This class defines an assumption.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Assumption extends Statement {
	
	private final Term term;

	public Assumption(int line, Term term) {
		super(line);
		this.term = term;
	}
	
	public Term getTerm() {
		return term;
	}
	
	@Override
	public Assumption accept(ASTVisitor astVisitor) {
		return astVisitor.assume(this);
	}

	public boolean equals(Assumption assumption) {
		return term.equals(assumption.term);
	}

}