package ast;

public class ArrayAssignment extends Statement {
	
	private ArrayAccess access;
	private RestrictedTerm value;
	
	public ArrayAssignment(int line, ArrayAccess access, RestrictedTerm value) {
		super(line);
		this.access = access;
		this.value = value;
	}
	
	public ArrayAccess getAccess() {
		return access;
	}
	
	public RestrictedTerm getValue() {
		return value;
	}

	@Override
	public Statement accept(ASTVisitor astVisitor) {
		return astVisitor.arrayAssignment(this);
	}

}
