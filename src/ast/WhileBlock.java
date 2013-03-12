package ast;

/**
 * This class defines a while loop, so it refers to a condition and a body.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class WhileBlock extends Statement {
	
	private final RestrictedTerm condition;
	private final StatementBlock body;

	public WhileBlock(int line, RestrictedTerm condition, StatementBlock body) {
		super(line);
		this.condition = condition;
		this.body = body;
	}
	
	public RestrictedTerm getCondition() {
		return condition;
	}
	
	public StatementBlock getBody() {
		return body;
	}

	@Override
	public Statement accept(ASTVisitor astVisitor) {
		return astVisitor.astWhile(this);
	}

	public boolean equals(WhileBlock whileBlock) {
		return condition.equals(whileBlock.condition)
				&& body.equals(whileBlock.body);
	}
}