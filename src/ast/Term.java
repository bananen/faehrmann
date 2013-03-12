package ast;

/**
 * This class defines a term.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public abstract class Term extends Node {
	
	public abstract Term accept(ASTVisitor astVisitor);
	public abstract String toSMTString();

}