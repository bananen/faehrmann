package ast;

/**
 * This is the super class for all types of single statements.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public abstract class Statement extends LegalProgramChild {

	private final int line;
	
	public Statement(int line) {
		this.line = line;
	}
	
	public int getLine() {
		return line;
	}
	
	@Override
	public abstract Statement accept(ASTVisitor astVisitor);

}