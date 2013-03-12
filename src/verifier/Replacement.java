package verifier;

import ast.ASTVariable;
import ast.RestrictedTerm;

/**
 * This class defines a replacement which means that the variable var shall be
 * replaced by the term term.
 * 
 * @author Fedor Scholz
 * 
 */

public class Replacement {

	private final ASTVariable var;
	private final RestrictedTerm term;

	public Replacement(ASTVariable var, RestrictedTerm term) {
		this.var = var;
		this.term = term;
	}

	public ASTVariable getVar() {
		return var;
	}

	public RestrictedTerm getTerm() {
		return term;
	}

}
