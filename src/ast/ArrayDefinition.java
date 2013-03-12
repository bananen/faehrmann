package ast;

import java.util.ArrayList;

public class ArrayDefinition extends Statement {
	
	private enum Type {
		INT, BOOL
	}
	
	public final static Type INT = Type.INT;
	public final static Type BOOL = Type.BOOL;
	private ArrayList<ArrayAssignment> assignments;
	private Type type;
	
	
	public ArrayDefinition(int line, ArrayList<ArrayAssignment> assignments, Type type) {
		super(line);
		this.assignments = assignments;
		this.type = type;
	}
	
	public ArrayList<ArrayAssignment> getAssignments() {
		return assignments;
	}
	
	public Type getType() {
		return type;
	}

	@Override
	public Statement accept(ASTVisitor astVisitor) {
		return astVisitor.arrayDefinition(this);
	}

}
