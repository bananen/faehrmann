package ast;

public class MethodDefinition extends LegalProgramChild {

	private final String name;
	private final StatementBlock body;
	
	public MethodDefinition(String name, StatementBlock body) {
		this.name = name;
		this.body = body;
	}

	public String getName() {
		return name;
	}
	
	public StatementBlock getBody() {
		return body;
	}

	@Override
	public MethodDefinition accept(ASTVisitor astVisitor) {
		return astVisitor.methodDefinition(this);
	}
	
	public boolean equals(MethodDefinition methodDefinition) {
		return name.equals(methodDefinition.name)
				&& body.equals(methodDefinition.body);
	}

}