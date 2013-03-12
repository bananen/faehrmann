package ast;

/**
 * This is the super class of all Nodes in the abstract syntax tree which
 * defines the basic operations.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public abstract class Node {

	/*
	 * This method provides the possibility of visiting the AST. It calls the
	 * appropriate method on the AST Visitor.
	 */
	public abstract Node accept(ASTVisitor astVisitor);
	
}