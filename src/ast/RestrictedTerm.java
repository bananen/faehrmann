package ast;

/**
 * This class defines a term without any quantifiers.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public abstract class RestrictedTerm extends Term {
	
	@Override
	public abstract RestrictedTerm accept(ASTVisitor astVisitor);

}