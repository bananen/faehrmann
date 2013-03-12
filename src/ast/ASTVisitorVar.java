package ast;

/*
 * This class defines the special visitor interface for visiting the AST and building an AST for checking the information flow.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public abstract interface ASTVisitorVar extends ASTVisitor {
	public abstract Node visit(Program root);

	public abstract RestrictedTerm add(Addition addition);

	public abstract RestrictedTerm and(And and);

	public abstract Assignment assign(Assignment assignment);

	public abstract Definition define(Definition definition);

	public abstract RestrictedTerm greater(GreaterThan greaterThan);

	public abstract RestrictedTerm greaterequals(GreaterEqual greaterEqual);

	public abstract MethodCall callMethod(MethodCall methodCall);

	public abstract RestrictedTerm equal(Equal equal);

	public abstract RestrictedTerm negate(Negation negation);

	public abstract RestrictedTerm multiply(Multiplication multiplication);

	public abstract RestrictedTerm smaller(SmallerThan smallerThan);

	public abstract RestrictedTerm not(Not not);

	public abstract RestrictedTerm or(Or or);

	public abstract RestrictedTerm subtract(Subtraction subtraction);

	public abstract RestrictedTerm smallerequals(SmallerEqual smallerEqual);

	public abstract RestrictedTerm unequal(Unequal unequal);

	public abstract Statement astIf(IfBlock ifBlock);

	public abstract Statement astWhile(WhileBlock whileBlock);

	public abstract Assertion astAssert(Assertion assertion);

	public abstract Assumption assume(Assumption assumption);

	public abstract RestrictedTerm imply(Implication implication);

	public abstract RestrictedTerm equivalize(Equivalence equivalence);

	public abstract UniversalQuantifier uniQuantify(
			UniversalQuantifier universalQuantifier);

	public abstract ExistentialQuantifier exQuantify(
			ExistentialQuantifier existentialQuantifier);

	public abstract ConstBool constBool(ConstBool constBool);

	public abstract ConstInt constInt(ConstInt constInt);

	public abstract ASTVariable varInt(VarInt varInt);

	public abstract ASTVariable varBool(VarBool varBool);

	public abstract MethodDefinition methodDefinition(
			MethodDefinition methodDefinition);

	public abstract StatementBlock statementBlock(StatementBlock statementBlock);
	
	public abstract ArrayIntAccess arrayIntAccess(ArrayIntAccess arrayIntAccess);
	
	public abstract ArrayBoolAccess arrayBoolAccess(ArrayBoolAccess arrayBoolAccess);
}
