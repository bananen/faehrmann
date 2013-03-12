package ast;

/**
 * This class defines a variable in the ast. In a term for example the attribute
 * value can be set to null.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public abstract class ASTVariable extends RestrictedTerm {

	protected final String name;
	protected final boolean high;

	public ASTVariable(String name, boolean high) {
		this.name = name;
		this.high = high;
	}

	public String getName() {
		return name;
	}

	public boolean isHigh() {
		return high;
	}

	public abstract String getSMTType();

	public abstract ASTVariable acceptVar(ASTVisitorVar astVisitor);

	public String toSMTString() {
			return name;
	}

}