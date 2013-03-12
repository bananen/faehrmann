package ast;

import java.util.ArrayList;

import verifier.ArrayReplacement;

public class PrintAST implements ASTVisitor {

	String tab = "";
	ArrayList<String> astString = new ArrayList<String>();
	boolean methodDef = true;

	public ArrayList<String> print(Program program) {
		if (program == null) {
			return null;
		}
		astString = new ArrayList<String>();
		methodDef = true;
		visit(program);
		return astString;
	}

	public ArrayList<String> print(Node node) {
		if (node == null) {
			return null;
		}
		astString = new ArrayList<String>();
		methodDef = true;
		node.accept(this);
		return astString;
	}

	@Override
	public Node visit(Program root) {
		tab = "";
		astString.add(tab + "Program");
		tab = tab + "  ";
		for (int i = 0; i < root.getNumberOfChildren(); i++) {
			root.getChild(i).accept(this);
		}
		return null;
	}

	@Override
	public RestrictedTerm add(Addition addition) {
		astString.add(tab + "+");
		tab = tab + "  ";
		addition.getLeftSubTerm().accept(this);
		addition.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm and(And and) {
		astString.add(tab + "and");
		tab = tab + "  ";
		and.getLeftSubTerm().accept(this);
		and.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public Assignment assign(Assignment assignment) {
		astString.add(tab + "=");
		tab = tab + "  ";
		assignment.getVariable().accept(this);
		if (assignment.getTerm() != null) {
			assignment.getTerm().accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public Definition define(Definition definition) {
		astString.add(tab + "=");
		tab = tab + "  ";
		definition.getVariable().accept(this);
		if (definition.getTerm() != null) {
			definition.getTerm().accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm greater(GreaterThan greaterThan) {
		astString.add(tab + ">");
		tab = tab + "  ";
		greaterThan.getLeftSubTerm().accept(this);
		greaterThan.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm greaterequals(GreaterEqual greaterEqual) {
		astString.add(tab + ">=");
		tab = tab + "  ";
		greaterEqual.getLeftSubTerm().accept(this);
		greaterEqual.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public MethodCall callMethod(MethodCall methodCall) {
		astString.add(tab + methodCall.getMethodDefinition().getName());
		if (methodDef) {
			astString.add("==== DefStart ====");
			methodCall.getMethodDefinition().accept(this);
			astString.add("===== DefEnd =====");
			methodDef = false;
		}
		return null;
	}

	@Override
	public RestrictedTerm equal(Equal equal) {
		astString.add(tab + "==");
		tab = tab + "  ";
		equal.getLeftSubTerm().accept(this);
		equal.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm negate(Negation negation) {
		astString.add(tab + "-");
		tab = tab + "  ";
		negation.getSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm multiply(Multiplication multiplication) {
		astString.add(tab + "*");
		tab = tab + "  ";
		multiplication.getLeftSubTerm().accept(this);
		multiplication.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm smaller(SmallerThan smallerThan) {
		astString.add(tab + "<");
		tab = tab + "  ";
		smallerThan.getLeftSubTerm().accept(this);
		smallerThan.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm not(Not not) {
		astString.add(tab + "!");
		tab = tab + "  ";
		not.getSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm or(Or or) {
		astString.add(tab + "or");
		tab = tab + "  ";
		or.getLeftSubTerm().accept(this);
		or.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm subtract(Subtraction subtraction) {
		astString.add(tab + "-");
		tab = tab + "  ";
		subtraction.getLeftSubTerm().accept(this);
		subtraction.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm smallerequals(SmallerEqual smallerEqual) {
		astString.add(tab + "<=");
		tab = tab + "  ";
		smallerEqual.getLeftSubTerm().accept(this);
		smallerEqual.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm unequal(Unequal unequal) {
		astString.add(tab + "!=");
		tab = tab + "  ";
		unequal.getLeftSubTerm().accept(this);
		unequal.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public Statement astIf(IfBlock ifBlock) {
		astString.add(tab + "if");
		tab = tab + "  ";
		ifBlock.getCondition().accept(this);
		ifBlock.getThenBlock().accept(this);
		if (ifBlock.getElseBlock() != null) {
			ifBlock.getElseBlock().accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public Statement astWhile(WhileBlock whileBlock) {
		astString.add(tab + "while");
		tab = tab + "  ";
		whileBlock.getCondition().accept(this);
		whileBlock.getBody().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public Assertion astAssert(Assertion assertion) {
		astString.add(tab + "assert");
		tab = tab + "  ";
		assertion.getTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public Assumption assume(Assumption assumption) {
		astString.add(tab + "assume");
		tab = tab + "  ";
		assumption.getTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm imply(Implication implication) {
		astString.add(tab + "=>");
		tab = tab + "  ";
		implication.getLeftSubTerm().accept(this);
		implication.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public RestrictedTerm equivalize(Equivalence equivalence) {
		astString.add(tab + "<->");
		tab = tab + "  ";
		equivalence.getLeftSubTerm().accept(this);
		equivalence.getRightSubTerm().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public UniversalQuantifier uniQuantify(
			UniversalQuantifier universalQuantifier) {
		astString.add(tab + "forall");
		tab = tab + "  ";
		if (universalQuantifier.getVariable() != null) {
			universalQuantifier.getVariable().accept(this);
		}
		if (universalQuantifier.getSubTerm() != null) {
			universalQuantifier.getSubTerm().accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public ExistentialQuantifier exQuantify(
			ExistentialQuantifier existentialQuantifier) {
		astString.add(tab + "exists");
		tab = tab + "  ";
		if (existentialQuantifier.getVariable() != null) {
			existentialQuantifier.getVariable().accept(this);
		}
		if (existentialQuantifier.getSubTerm() != null) {
			existentialQuantifier.getSubTerm().accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public ConstBool constBool(ConstBool constBool) {
		astString.add(tab + constBool.getValue() + " (bool)");
		return null;
	}

	@Override
	public ConstInt constInt(ConstInt constInt) {
		astString.add(tab + constInt.getValue() + " (int)");
		return null;
	}

	@Override
	public RestrictedTerm varInt(VarInt varInt) {
		astString.add(tab + varInt.getSMTType() + " " + varInt.getName()
				+ ", high: " + varInt.isHigh());
		return null;
	}

	@Override
	public RestrictedTerm varBool(VarBool varBool) {
		astString.add(tab + varBool.getSMTType() + " " + varBool.getName()
				+ ", high: " + varBool.isHigh());
		return null;
	}

	@Override
	public MethodDefinition methodDefinition(MethodDefinition methodDefinition) {
		astString.add(tab + "func" + methodDefinition.getName());
		tab = tab + "  ";
		methodDefinition.getBody().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public StatementBlock statementBlock(StatementBlock statementBlock) {
		astString.add(tab + "block");
		tab = tab + "  ";
		for (int i = 0; i < statementBlock.getNumberOfChildren(); i++) {
			statementBlock.getChild(i).accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public ArrayIntAccess arrayIntAccess(ArrayIntAccess arrayIntAccess) {
		astString.add(tab + arrayIntAccess.getName() + "[");
		tab = tab + "  ";
		arrayIntAccess.getIndex().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		astString.add(tab + "]: " + arrayIntAccess.isHigh());
		if (arrayIntAccess.getReplacements().size() > 0) {
			astString.add(tab + "=== With Replacements ===");
			for (ArrayReplacement current : arrayIntAccess.getReplacements()) {
				astString.add(tab + "=");
				current.getIndex().accept(this);
				current.getValue().accept(this);
			}
			astString.add(tab + "=== End Replacements ===");
		}
		return null;
	}

	@Override
	public ArrayBoolAccess arrayBoolAccess(ArrayBoolAccess arrayBoolAccess) {
		astString.add(tab + arrayBoolAccess.getName() + "[");
		tab = tab + "  ";
		arrayBoolAccess.getIndex().accept(this);
		tab = tab.substring(0, tab.length() - 2);
		astString.add(tab + "]: " + arrayBoolAccess.isHigh());
		if (arrayBoolAccess.getReplacements().size() > 0) {
			astString.add(tab + "=== With Replacements ===");
			for (ArrayReplacement current : arrayBoolAccess.getReplacements()) {
				astString.add(tab + "=");
				current.getIndex().accept(this);
				current.getValue().accept(this);
			}
			astString.add(tab + "=== End Replacements ===");
		}
		return null;
	}

	@Override
	public ArrayDefinition arrayDefinition(ArrayDefinition arrayDefinition) {
		astString.add(tab + "arrayDef:");
		tab = tab + "  ";
		for (ArrayAssignment current : arrayDefinition.getAssignments()) {
			current.accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

	@Override
	public ArrayAssignment arrayAssignment(ArrayAssignment arrayAssignment) {
		astString.add(tab + "=");
		tab = tab + "  ";
		arrayAssignment.getAccess().accept(this);
		if (arrayAssignment.getValue() != null) {
			arrayAssignment.getValue().accept(this);
		}
		tab = tab.substring(0, tab.length() - 2);
		return null;
	}

}