package ast;

import java.math.BigInteger;
import java.util.ArrayList;

import verifier.ArrayReplacement;

public abstract class ArrayAccess extends RestrictedTerm {

	private String name;
	private BigInteger length;
	private boolean high;
	private RestrictedTerm index;
	private ArrayList<ArrayReplacement> replacements;

	public ArrayAccess(String name, BigInteger length, boolean high,
			RestrictedTerm index) {
		this.name = name;
		this.length = length;
		this.high = high;
		this.index = index;
		this.replacements = new ArrayList<ArrayReplacement>();
	}

	public ArrayAccess(String name, BigInteger length, boolean high,
			RestrictedTerm index, ArrayList<ArrayReplacement> replacements) {
		this.name = name;
		this.length = length;
		this.high = high;
		this.index = index;
		this.replacements = replacements;
	}

	public String getName() {
		return name;
	}

	public BigInteger getLength() {
		return length;
	}

	public boolean isHigh() {
		return high;
	}

	public RestrictedTerm getIndex() {
		return index;
	}

	public ArrayList<ArrayReplacement> getReplacements() {
		return replacements;
	}

	@Override
	public String toSMTString() {
		return "(select " + replacementString() + " " + index.toSMTString()
				+ ")";
	}

	private String replacementString() {
		String result = "";
		for (int i = 0; i < replacements.size(); i++) {
			if (replacements.get(i).getValue() != null) {
				result = result + "(store ";
			}
		}
		result = result + name + " ";
		// for (ArrayReplacement current : replacements) {
		// result = result + current.getIndex().toSMTString()
		// + " " + current.getValue().toSMTString() + ") ";
		// }
		for (int i = replacements.size() - 1; i >= 0; i--) {
			if (replacements.get(i).getValue() != null) {
				result = result + replacements.get(i).getIndex().toSMTString()
						+ " " + replacements.get(i).getValue().toSMTString()
						+ ") ";
			}
		}
		return result;
	}

}
