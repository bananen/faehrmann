package ast;

import java.math.BigInteger;
import java.util.ArrayList;

import verifier.ArrayReplacement;

public class ArrayBoolAccess extends ArrayAccess {

	public ArrayBoolAccess(String name, BigInteger length, boolean high,
			RestrictedTerm index) {
		super(name, length, high, index);
	}

	public ArrayBoolAccess(String name, BigInteger length, boolean high,
			RestrictedTerm index, ArrayList<ArrayReplacement> replacements) {
		super(name, length, high, index, replacements);
	}

	@Override
	public RestrictedTerm accept(ASTVisitor astVisitor) {
		return astVisitor.arrayBoolAccess(this);
	}

	public ArrayBoolAccess acceptAccess(ASTVisitorVar astVisitor) {
		return astVisitor.arrayBoolAccess(this);
	}

}