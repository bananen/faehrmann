package ast;

/**
 * This class defines an assertion.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class Assertion extends Statement {
	
	private final Term term;

	public Assertion(int line, Term term) {
		super(line);
		this.term = term;
	}
	
	public Term getTerm() {
		return term;
	}
	
	@Override
	public Assertion accept(ASTVisitor astVisitor) {
		return astVisitor.astAssert(this);
	}
	
	public boolean equals(Assertion assertion) {
		return term.equals(assertion.term);
	}

}