package ast;

/**
 * This class provides that only subclasses of this class can be children of a
 * program.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public abstract class LegalProgramChild extends Node {

	public abstract LegalProgramChild accept(ASTVisitor astVisitor);

}
