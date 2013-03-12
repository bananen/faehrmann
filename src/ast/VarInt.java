package ast;

/**
 * This class defines a variable of the type integer.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class VarInt extends ASTVariable {

	public VarInt(String name, boolean high) {
		super(name, high);
	}

	@Override
	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.varInt(this);
	}

	@Override
	public ASTVariable acceptVar(ASTVisitorVar astVisitor) {
		return astVisitor.varInt(this);
	}

	public String getSMTType() {
		return "Int";
	}

	public boolean equals(VarInt varInt) {
		return name.equals(varInt.name);
	}

}