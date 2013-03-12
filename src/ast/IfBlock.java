package ast;

/**
 * This class defines an if structure, so it refers to a condition and to two blocks
 * of statements (one for the if- and one for the else-block).
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class IfBlock extends Statement {

	private final RestrictedTerm condition;
	private final StatementBlock thenBlock;
	private final StatementBlock elseBlock;

	public IfBlock(int line, RestrictedTerm condition, StatementBlock ifBlock,
			StatementBlock elseBlock) {
		super(line);
		this.condition = condition;
		this.thenBlock = ifBlock;
		this.elseBlock = elseBlock;
	}

	public RestrictedTerm getCondition() {
		return condition;
	}

	public StatementBlock getThenBlock() {
		return thenBlock;
	}

	public StatementBlock getElseBlock() {
		return elseBlock;
	}

	@Override
	public Statement accept(ASTVisitor astVisitor) {
		return astVisitor.astIf(this);
	}
	
	public boolean equals(IfBlock ifBlock) {
		return condition.equals(ifBlock.condition)
				&& thenBlock.equals(ifBlock.thenBlock)
				&& elseBlock.equals(ifBlock.elseBlock);
	}

}