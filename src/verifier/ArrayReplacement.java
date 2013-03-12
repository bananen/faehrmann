package verifier;

import ast.RestrictedTerm;

public class ArrayReplacement {
	
	private final RestrictedTerm index;
	private final RestrictedTerm value;
	
	public ArrayReplacement(RestrictedTerm index, RestrictedTerm value) {
		this.index = index;
		this.value = value;
	}
	
	public RestrictedTerm getIndex() {
		return index;
	}
	
	public RestrictedTerm getValue() {
		return value;
	}

}
