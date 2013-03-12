package interpreter;

import ast.Program;
import ast.StatementBlock;
import ast.WhileBlock;

/**
 * This class always contains a node and possibly an index and keeps track of the order of the nodes.
 * This is done to know which nodes were already visited and which were not (to be able to resume the debugging process).
 * The instances of this class represent the first nodes to visit of each statementblock.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */
public class ResumeToken {
	private enum Type {
		PROGRAM,
		STATEMENTBLOCK,
		WHILEBLOCK;
	}
	
	public final static Type PROGRAM = Type.PROGRAM;
	public final static Type STATEMENTBLOCK = Type.STATEMENTBLOCK;
	public final static Type WHILEBLOCK = Type.WHILEBLOCK;
	private Program program;
	private StatementBlock statementBlock;
	private WhileBlock whileBlock;
	private final Type type;
	private int index;
	
	public ResumeToken(Program program, int index) {
		this.program = program;
		this.index = index;
		type = PROGRAM;
	}
	
	public ResumeToken(StatementBlock statementBlock, int index) {
		this.statementBlock = statementBlock;
		this.index = index;
		type = STATEMENTBLOCK;
	}
	
	public ResumeToken(WhileBlock whileBlock) {
		this.whileBlock = whileBlock;
		type = WHILEBLOCK;
	}
	
	public Program getProgram() {
		return program;
	}
	
	public StatementBlock getStatementBlock() {
		return statementBlock;
	}

	public WhileBlock getWhileBlock() {
		return whileBlock;
	}

	public Type getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}
}
