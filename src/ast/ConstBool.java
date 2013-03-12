package ast;

import java.math.BigInteger;

/**
 * This class defines a constant of the type boolean.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class ConstBool extends Constant {

	public ConstBool(BigInteger value) {
		super(value);
	}
	
	public ConstBool(String value) {
		super(new BigInteger(value));
	}

	@Override
	public ConstBool accept(ASTVisitor astVisitor) {
		return astVisitor.constBool(this);
	}
	
	public String toSMTString() {
		if (value.intValue() == 0) {
			return "false";
		} else {
			return "true";
		}
	}

	public boolean equals(ConstBool constBool) {
		return this.value.equals(constBool.value);
	}
	
}