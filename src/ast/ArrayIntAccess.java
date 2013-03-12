package ast;

import java.math.BigInteger;
import java.util.ArrayList;

import verifier.ArrayReplacement;

public class ArrayIntAccess extends ArrayAccess {

	public ArrayIntAccess(String name, BigInteger length, boolean high,
			RestrictedTerm index) {
		super(name, length, high, index);
	}
	
	public ArrayIntAccess(String name, BigInteger length, boolean high,
			RestrictedTerm index, ArrayList<ArrayReplacement> replacements) {
		super(name, length, high, index, replacements);
	}

	@Override
	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.arrayIntAccess(this);
	}

	
	public ArrayIntAccess acceptAccess(ASTVisitorVar astVisitor) {
		return astVisitor.arrayIntAccess(this);
	}
}
