package ast;

/**
 * This class defines a definition which is an assignment, too. A Declaration is
 * a definition with null for the term.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class Definition extends Assignment {

	public Definition(int line, ASTVariable variable, RestrictedTerm term) {
		super(line, variable, term);
	}

	@Override
	public Definition accept(ASTVisitor astVisitor) {
		return astVisitor.define(this);
	}

}