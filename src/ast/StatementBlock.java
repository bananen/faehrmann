package ast;

/**
 * This class defines a block which consists of multiple statements.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

import java.util.ArrayList;

public class StatementBlock extends Node {

	private final ArrayList<Statement> children;

	public StatementBlock(ArrayList<Statement> children) {
		this.children = new ArrayList<Statement>();
		if (children != null) {
			for (Statement current : children) {
				this.children.add(current);
			}
		}
	}

	public Statement getChild(int index) {
		return children.get(index);
	}

	public int getNumberOfChildren() {
		return children.size();
	}

	@Override
	public StatementBlock accept(ASTVisitor astVisitor) {
		return astVisitor.statementBlock(this);
	}

	public boolean equals(StatementBlock statementBlock) {
		return children.equals(statementBlock.children);
	}

}