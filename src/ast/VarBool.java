package ast;

/**
 * This class defines a variable of the type boolean.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class VarBool extends ASTVariable {
	
	public VarBool(String name, boolean high) {
		super(name, high);
	}

	@Override
	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.varBool(this);
	}
	
	public ASTVariable acceptVar(ASTVisitorVar astVisitor) {
		return astVisitor.varBool(this);
	}
	
	public String getSMTType() {
		return "Bool";
	}
	
	public boolean equals(VarBool varBool) {
		return name.equals(varBool.name);
	}

}