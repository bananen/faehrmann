package ast;

/**
 * This class defines an assignment.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class Assignment extends Statement {

	private final ASTVariable variable;
	private final RestrictedTerm term;
	
	public Assignment(int line, ASTVariable variable) {
		super(line);
		this.variable = variable;
		this.term = null;
	}

	public Assignment(int line, ASTVariable variable, RestrictedTerm term) {
		super(line);
		this.variable = variable;
		this.term = term;
	}

	public ASTVariable getVariable() {
		return variable;
	}

	public RestrictedTerm getTerm() {
		return term;
	}

	@Override
	public Assignment accept(ASTVisitor astVisitor) {
		return astVisitor.assign(this);
	}

	public boolean equals(Assignment assignment) {
		return this.variable.equals(assignment.variable)
				&& this.term.equals(assignment.term);
	}

}