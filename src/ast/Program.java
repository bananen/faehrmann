package ast;

import java.util.ArrayList;

public class Program extends Node {

	private final ArrayList<LegalProgramChild> children;

	public Program(ArrayList<LegalProgramChild> children) {
		this.children = new ArrayList<LegalProgramChild>();
		if (children != null) {
			for (LegalProgramChild current : children) {
				this.children.add(current);
			}
		}
	}

	public LegalProgramChild getChild(int index) {
		return children.get(index);
	}

	public int getNumberOfChildren() {
		return children.size();
	}

	@Override
	public Node accept(ASTVisitor astVisitor) {
		return astVisitor.visit(this);
	}

	public boolean equals(Program program) {
		return children.equals(program.children);

	}

}