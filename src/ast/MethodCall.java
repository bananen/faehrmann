package ast;

/**
 * This class defines a method call, so it has a reference to a method definition.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 *
 */

public class MethodCall extends Statement {
	
	private final MethodDefinition methodDefinition;

    public MethodCall(int line, MethodDefinition methodDefintion) {
		super(line);
		this.methodDefinition = methodDefintion;
	}
    
    public MethodDefinition getMethodDefinition() {
    	return methodDefinition;
    }

	@Override
	public MethodCall accept(ASTVisitor astVisitor) {
		return astVisitor.callMethod(this);
	}
	
	public boolean equals(MethodCall methodCall) {
		return methodDefinition.equals(methodCall.methodDefinition);
	}

}