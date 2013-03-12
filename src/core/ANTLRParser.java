package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.*;


import ast.PrintAST;
import ast.Program;


/**
 * This class can be used to parse Code into an Abstract Syntax Tree
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class ANTLRParser {

	private Program root;

/**
 * The method parse a code object into an Abstract Syntax Tree. 
 * @return List<String> A List of Strings with errors will be returned, if no errors
 * occurs while parsing a empty list will be returned
 */
	public List<String> parse(Code code) throws RecognitionException {
		root = null;
		if(code == null || code.getLines() == null) {
			return null;
		}
		List<String> errors = new LinkedList<String>();
		String input = code.getLines();
		CharStream cs = new ANTLRStringStream(input);
		FaehrmannLexer lexer = new FaehrmannLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		FaehrmannParser parser = new FaehrmannParser(tokens);
		this.root = parser.program().prog;
		errors = parser.getErrors();
		if(errors.size() > 0) {
			this.root = null;
		}
		return errors;
	}

	public Program getRoot() {
		return this.root;
	}
	
	public static void main(String[] args) throws RecognitionException {
		  ANTLRParser parser = new ANTLRParser();
		  Code code = new Code();
		  code.setLines("int x = 10;int a[2] = {x+3,}; int c = a[0]; a[c] = 10;");
		  parser.parse(code);
		  PrintAST print = new PrintAST();
		  ArrayList<String> temp = print.print(parser.getRoot());
		  for (int i = 0; i < temp.size(); i++) {
			  System.out.println(temp.get(i));
		  }
	}
	  
}