package ast;

import java.math.BigInteger;

/**
 * This class defines a constant of the type integer.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class ConstInt extends Constant {

	public ConstInt(BigInteger value) {
		super(value);
	}
	
	public ConstInt(String value) {
		super(new BigInteger(value));
	}

	@Override
	public ConstInt accept(ASTVisitor astVisitor) {
		return astVisitor.constInt(this);
	}
	
	public String toSMTString() {
		return "" + value;
	}
	
	public boolean equals(ConstInt constInt) {
		return this.value.equals(constInt.value);
	}
	
	
}