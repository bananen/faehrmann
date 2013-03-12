package ast;

import java.math.BigInteger;

/**
 * This class defines a constant in the ast.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public abstract class Constant extends RestrictedTerm {

	protected final BigInteger value;
	
	public Constant(BigInteger value) {
		this.value = value;
	}
	
	public BigInteger getValue() {
		return value;
	}
	
}