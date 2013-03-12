// $ANTLR 3.5 C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g 2013-03-03 16:37:14

package core;
import ast.*;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.LinkedList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class FaehrmannParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARRAYLENGTH", "COMMENT", "DIGIT", 
		"NUMBER", "VARIABLEID", "WHITESPACE", "'!'", "'!='", "'&'", "'('", "')'", 
		"'*'", "'+'", "'++'", "','", "'-'", "'--'", "'->'", "':'", "';'", "'<'", 
		"'<->'", "'<='", "'='", "'=='", "'>'", "'>='", "'['", "'[A]'", "'[E]'", 
		"']'", "'assert'", "'assume'", "'bool'", "'else'", "'false'", "'func'", 
		"'high'", "'if'", "'int'", "'true'", "'while'", "'{'", "'|'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int ARRAYLENGTH=4;
	public static final int COMMENT=5;
	public static final int DIGIT=6;
	public static final int NUMBER=7;
	public static final int VARIABLEID=8;
	public static final int WHITESPACE=9;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public FaehrmannParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public FaehrmannParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return FaehrmannParser.tokenNames; }
	@Override public String getGrammarFileName() { return "C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g"; }


	// List of Program children
	private ArrayList<LegalProgramChild> list = new ArrayList<LegalProgramChild>();

	private ArrayList<Statement> list3 = new ArrayList<Statement>();

	// if theres no else structure in the if-block, noElse Statementblock (empty) is refferenced
	private StatementBlock noElse = new StatementBlock(list3);

	// List of all declarated variables
	private ArrayList<ASTVariable> variables = new ArrayList<ASTVariable>();

	// List of all declarated arrays
	private ArrayList<ArrayAccess> intArrays = new ArrayList<ArrayAccess>();
	private ArrayList<ArrayAccess> boolArrays = new ArrayList<ArrayAccess>();

	//Temp-List of arravalues
	private ArrayList<RestrictedTerm> arrayvalues = new ArrayList<RestrictedTerm>();

	//List of all declarated functions
	private ArrayList<MethodDefinition> functions = new ArrayList<MethodDefinition>();
	// Is a variable high or low? (to create object)
	private boolean high = false;

	private List<String> errors = new LinkedList<String>();
	public void displayRecognitionError(String[] tokenNames,
	                                    RecognitionException e) {
	    String hdr = getErrorHeader(e);
	    String msg = getErrorMessage(e, tokenNames);
	    errors.add(hdr + " " + msg);
	}

	public List<String> getErrors() {
	    return errors;
	}

	// returns the ASTVariable with name name, if existing
	private ASTVariable getASTVariable(String name) {
		ASTVariable result = null;
		for (int i = 0; i < variables.size(); i++) {
		    if(name.equals(variables.get(i).getName())) {
		        result = variables.get(i);
		    }
		}
		if (result == null) {
		  errors.add("Error: Variable\"" + name + "\" not found");
		}
		return result;
	}

	// returns the MethodDefinition with name name, if existing
	private MethodDefinition getFunc(String name) {
		MethodDefinition result = null;
		for (int i = 0; i < functions.size(); i++) {
			if (functions.get(i).getName().equals(name)) {
				result = functions.get(i);
			}
		}

		return result;
	}

	// creates objects for the whole array
	private ArrayDefinition declarearray(String name, String length, boolean type) {
	ArrayList<ArrayAssignment> temp = new ArrayList();
	ArrayDefinition arraydec = null;
	  if (type) {
	    for (int i = 0; i < Integer.parseInt(length); i++) {
	      ArrayIntAccess arrayaccess = new ArrayIntAccess(name, new BigInteger(length), high, new ConstInt(new BigInteger(new Integer(i).toString())));
	      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, null);  
	      temp.add(arrayassign);
	      intArrays.add(arrayaccess);
	    }
	    arraydec = new ArrayDefinition(line(), temp, ArrayDefinition.INT);
	  } else {
	    for (int i = 0; i < Integer.parseInt(length); i++) {
	      ArrayBoolAccess arrayaccess = new ArrayBoolAccess(name, new BigInteger(length), high, new ConstInt(new BigInteger(new Integer(i).toString())));
	      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, null);  
	      temp.add(arrayassign);
	      boolArrays.add(arrayaccess);
	    }
	    arraydec = new ArrayDefinition(line(), temp, ArrayDefinition.BOOL);
	  }
	  if (Integer.parseInt(length) < 1) {
	    errors.add("Error: Arraylength is smaller than 1!");
	    }
	  return arraydec;
	}

	private ArrayDefinition definearray(String name, String length, boolean type, ArrayList<RestrictedTerm> arrayvalues) {
	  ArrayList<ArrayAssignment> temp = new ArrayList();
	  ArrayDefinition arraydef = null;
	  if (type) {
	    for (int i = 0; i < Integer.parseInt(length); i++) {
	      ArrayIntAccess arrayaccess = new ArrayIntAccess(name, new BigInteger(length), high, new ConstInt(new BigInteger(new Integer(i).toString())));
	      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, arrayvalues.get(i));  
	      temp.add(arrayassign);
	      intArrays.add(arrayaccess);
	    }
	    arraydef = new ArrayDefinition(line(), temp, ArrayDefinition.INT);
	  } else {
	    for (int i = 0; i < Integer.parseInt(length); i++) {
	      ArrayBoolAccess arrayaccess = new ArrayBoolAccess(name, new BigInteger(length), high, new ConstInt(new BigInteger(new Integer(i).toString())));
	      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, arrayvalues.get(i));  
	      temp.add(arrayassign);
	      boolArrays.add(arrayaccess);
	    }
	    arraydef = new ArrayDefinition(line(), temp, ArrayDefinition.BOOL);
	  }

	  return arraydef;
	}

	//return 1 for type integer and 0 for type bool
	private int getArrayType(String name) {
	    for(int i = 0; i < intArrays.size(); i++) {
	          if(name.equals(intArrays.get(i).getName())) return 1;
	    }
	    for(int i = 0; i < boolArrays.size(); i++) {
	          if(name.equals(boolArrays.get(i).getName())) return 0;
	    }
	    return -1;
	}

	private boolean isArrayHigh(String name) {
	    for(int i = 0; i < intArrays.size(); i++) {
	          if(name.equals(intArrays.get(i).getName()) && intArrays.get(i).isHigh()) return true;
	    }
	    for(int i = 0; i < boolArrays.size(); i++) {
	          if(name.equals(boolArrays.get(i).getName()) && boolArrays.get(i).isHigh()) return true;
	    }
	    return false;
	}

	private BigInteger getArrayLength(String name) {
	    for(int i = 0; i < intArrays.size(); i++) {
	          if(name.equals(intArrays.get(i).getName())) {
	            return intArrays.get(i).getLength();
	          }
	    }
	    for(int i = 0; i < boolArrays.size(); i++) {
	          if(name.equals(boolArrays.get(i).getName())) {
	            return boolArrays.get(i).getLength();
	          }
	    }
	    return new BigInteger("-1");
	}

	private boolean existsArray(String name) {
	    for(int i = 0; i < intArrays.size(); i++) {
	          if(name.equals(intArrays.get(i).getName())) return true;
	    }
	    for(int i = 0; i < boolArrays.size(); i++) {
	          if(name.equals(boolArrays.get(i).getName())) return true;
	    }
	    errors.add("Error: Array \"" + name + "\" not found");
	    return false;
	}

	private void checkNameArray(String name) {
	    for (int i = 0; i < variables.size(); i++) {
	      if (variables.get(i).getName().equals(name)) {
	        errors.add("Error: Array \"" + name + "\" has the same name as a variable");
	        break;
	      }
	    }
	}

	private void checkNameVariable(String name) {   
	    for (int i = 0; i < intArrays.size(); i++) {
	      if (intArrays.get(i).getName().equals(name)) {
	        errors.add("Error: Variable \"" + name + "\" has the same name as a array");
	        break;
	      }
	    }
	    for (int i = 0; i < boolArrays.size(); i++) {
	      if (boolArrays.get(i).getName().equals(name)) {
	        errors.add("Error: Variable \"" + name + "\" has the same name as a array");
	        break;
	      }
	    }
	}

	// returns the actual line in editor
	int line() {
		return input.LT(1).getLine();
	}

	String array = "";
	String temp = "";
	int numberArrayElements = 0;



	public static class program_return extends ParserRuleReturnScope {
		public Program prog;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "program"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:219:1: program returns [Program prog] : ( (meth= methoddef |stat= statement )* ) EOF ;
	public final FaehrmannParser.program_return program() throws RecognitionException {
		FaehrmannParser.program_return retval = new FaehrmannParser.program_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EOF1=null;
		ParserRuleReturnScope meth =null;
		ParserRuleReturnScope stat =null;

		Object EOF1_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:219:32: ( ( (meth= methoddef |stat= statement )* ) EOF )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:219:35: ( (meth= methoddef |stat= statement )* ) EOF
			{
			root_0 = (Object)adaptor.nil();


			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:219:35: ( (meth= methoddef |stat= statement )* )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:220:9: (meth= methoddef |stat= statement )*
			{
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:220:9: (meth= methoddef |stat= statement )*
			loop1:
			while (true) {
				int alt1=3;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==40) ) {
					alt1=1;
				}
				else if ( (LA1_0==VARIABLEID||(LA1_0 >= 35 && LA1_0 <= 37)||(LA1_0 >= 41 && LA1_0 <= 43)||LA1_0==45) ) {
					alt1=2;
				}

				switch (alt1) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:220:11: meth= methoddef
					{
					pushFollow(FOLLOW_methoddef_in_program76);
					meth=methoddef();
					state._fsp--;

					adaptor.addChild(root_0, meth.getTree());

					 list.add((meth!=null?((FaehrmannParser.methoddef_return)meth).r:null));
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:221:11: stat= statement
					{
					pushFollow(FOLLOW_statement_in_program92);
					stat=statement();
					state._fsp--;

					adaptor.addChild(root_0, stat.getTree());

					 list.add((stat!=null?((FaehrmannParser.statement_return)stat).r:null));
					}
					break;

				default :
					break loop1;
				}
			}

			}


			          retval.prog = new Program(list);
			        
			EOF1=(Token)match(input,EOF,FOLLOW_EOF_in_program117); 
			EOF1_tree = (Object)adaptor.create(EOF1);
			adaptor.addChild(root_0, EOF1_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "program"


	public static class methoddef_return extends ParserRuleReturnScope {
		public MethodCall r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "methoddef"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:227:1: methoddef returns [MethodCall r] : 'func' name= VARIABLEID '{' block= statementblock '}' ;
	public final FaehrmannParser.methoddef_return methoddef() throws RecognitionException {
		FaehrmannParser.methoddef_return retval = new FaehrmannParser.methoddef_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token string_literal2=null;
		Token char_literal3=null;
		Token char_literal4=null;
		ParserRuleReturnScope block =null;

		Object name_tree=null;
		Object string_literal2_tree=null;
		Object char_literal3_tree=null;
		Object char_literal4_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:227:34: ( 'func' name= VARIABLEID '{' block= statementblock '}' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:228:8: 'func' name= VARIABLEID '{' block= statementblock '}'
			{
			root_0 = (Object)adaptor.nil();


			string_literal2=(Token)match(input,40,FOLLOW_40_in_methoddef136); 
			string_literal2_tree = (Object)adaptor.create(string_literal2);
			adaptor.addChild(root_0, string_literal2_tree);

			name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_methoddef140); 
			name_tree = (Object)adaptor.create(name);
			adaptor.addChild(root_0, name_tree);

			char_literal3=(Token)match(input,46,FOLLOW_46_in_methoddef142); 
			char_literal3_tree = (Object)adaptor.create(char_literal3);
			adaptor.addChild(root_0, char_literal3_tree);

			pushFollow(FOLLOW_statementblock_in_methoddef146);
			block=statementblock();
			state._fsp--;

			adaptor.addChild(root_0, block.getTree());

			char_literal4=(Token)match(input,48,FOLLOW_48_in_methoddef148); 
			char_literal4_tree = (Object)adaptor.create(char_literal4);
			adaptor.addChild(root_0, char_literal4_tree);


				       if (getFunc((name!=null?name.getText():null)) == null){
				        MethodDefinition meth = new MethodDefinition((name!=null?name.getText():null), (block!=null?((FaehrmannParser.statementblock_return)block).r:null)); 
				        functions.add(meth); retval.r = new MethodCall(line(), meth);
				        }
				        else { errors.add("Error: Function " + (name!=null?name.getText():null) + " declarated twice");}
			          
				       
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "methoddef"


	public static class statement_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:238:1: statement returns [Statement r] : (var1= assumption |var2= assertion |var3= whileBlock |var4= ifBlock |var5= assignment |var6= methodcall ) ';' ;
	public final FaehrmannParser.statement_return statement() throws RecognitionException {
		FaehrmannParser.statement_return retval = new FaehrmannParser.statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal5=null;
		ParserRuleReturnScope var1 =null;
		ParserRuleReturnScope var2 =null;
		ParserRuleReturnScope var3 =null;
		ParserRuleReturnScope var4 =null;
		ParserRuleReturnScope var5 =null;
		ParserRuleReturnScope var6 =null;

		Object char_literal5_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:238:33: ( (var1= assumption |var2= assertion |var3= whileBlock |var4= ifBlock |var5= assignment |var6= methodcall ) ';' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:239:9: (var1= assumption |var2= assertion |var3= whileBlock |var4= ifBlock |var5= assignment |var6= methodcall ) ';'
			{
			root_0 = (Object)adaptor.nil();


			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:239:9: (var1= assumption |var2= assertion |var3= whileBlock |var4= ifBlock |var5= assignment |var6= methodcall )
			int alt2=6;
			switch ( input.LA(1) ) {
			case 36:
				{
				alt2=1;
				}
				break;
			case 35:
				{
				alt2=2;
				}
				break;
			case 45:
				{
				alt2=3;
				}
				break;
			case 42:
				{
				alt2=4;
				}
				break;
			case VARIABLEID:
				{
				int LA2_5 = input.LA(2);
				if ( (LA2_5==17||LA2_5==20||LA2_5==27||LA2_5==31) ) {
					alt2=5;
				}
				else if ( (LA2_5==23) ) {
					alt2=6;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 37:
			case 41:
			case 43:
				{
				alt2=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:239:10: var1= assumption
					{
					pushFollow(FOLLOW_assumption_in_statement174);
					var1=assumption();
					state._fsp--;

					adaptor.addChild(root_0, var1.getTree());


					          retval.r = (var1!=null?((FaehrmannParser.assumption_return)var1).r:null);
					        
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:242:11: var2= assertion
					{
					pushFollow(FOLLOW_assertion_in_statement190);
					var2=assertion();
					state._fsp--;

					adaptor.addChild(root_0, var2.getTree());


					          retval.r = (var2!=null?((FaehrmannParser.assertion_return)var2).r:null);
					        
					}
					break;
				case 3 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:245:11: var3= whileBlock
					{
					pushFollow(FOLLOW_whileBlock_in_statement206);
					var3=whileBlock();
					state._fsp--;

					adaptor.addChild(root_0, var3.getTree());


					          retval.r = (var3!=null?((FaehrmannParser.whileBlock_return)var3).r:null);
					        
					}
					break;
				case 4 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:248:11: var4= ifBlock
					{
					pushFollow(FOLLOW_ifBlock_in_statement222);
					var4=ifBlock();
					state._fsp--;

					adaptor.addChild(root_0, var4.getTree());


					          retval.r = (var4!=null?((FaehrmannParser.ifBlock_return)var4).r:null);
					        
					}
					break;
				case 5 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:251:11: var5= assignment
					{
					pushFollow(FOLLOW_assignment_in_statement238);
					var5=assignment();
					state._fsp--;

					adaptor.addChild(root_0, var5.getTree());


					          retval.r = (var5!=null?((FaehrmannParser.assignment_return)var5).r:null);
					        
					}
					break;
				case 6 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:254:11: var6= methodcall
					{
					pushFollow(FOLLOW_methodcall_in_statement254);
					var6=methodcall();
					state._fsp--;

					adaptor.addChild(root_0, var6.getTree());


					          retval.r = (var6!=null?((FaehrmannParser.methodcall_return)var6).r:null);
					        
					}
					break;

			}

			char_literal5=(Token)match(input,23,FOLLOW_23_in_statement268); 
			char_literal5_tree = (Object)adaptor.create(char_literal5);
			adaptor.addChild(root_0, char_literal5_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class methodcall_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "methodcall"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:260:1: methodcall returns [Statement r] : name= VARIABLEID ;
	public final FaehrmannParser.methodcall_return methodcall() throws RecognitionException {
		FaehrmannParser.methodcall_return retval = new FaehrmannParser.methodcall_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;

		Object name_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:260:34: (name= VARIABLEID )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:261:9: name= VARIABLEID
			{
			root_0 = (Object)adaptor.nil();


			name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_methodcall293); 
			name_tree = (Object)adaptor.create(name);
			adaptor.addChild(root_0, name_tree);


			          if(getFunc((name!=null?name.getText():null)) != null){
			          retval.r = new MethodCall(line(), getFunc((name!=null?name.getText():null)));
			          }
			          else{
			              errors.add("Error: Function \"" + name + "\" not declared");
			          }
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "methodcall"


	public static class statementblock_return extends ParserRuleReturnScope {
		public StatementBlock r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statementblock"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:271:1: statementblock returns [StatementBlock r] : (stat= statement )* ;
	public final FaehrmannParser.statementblock_return statementblock() throws RecognitionException {
		FaehrmannParser.statementblock_return retval = new FaehrmannParser.statementblock_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope stat =null;


		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:271:43: ( (stat= statement )* )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:271:45: (stat= statement )*
			{
			root_0 = (Object)adaptor.nil();



			          ArrayList<Statement> bl = new ArrayList<Statement>();
			        
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:274:9: (stat= statement )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==VARIABLEID||(LA3_0 >= 35 && LA3_0 <= 37)||(LA3_0 >= 41 && LA3_0 <= 43)||LA3_0==45) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:274:10: stat= statement
					{
					pushFollow(FOLLOW_statement_in_statementblock321);
					stat=statement();
					state._fsp--;

					adaptor.addChild(root_0, stat.getTree());

					bl.add((stat!=null?((FaehrmannParser.statement_return)stat).r:null));
					}
					break;

				default :
					break loop3;
				}
			}


			          retval.r = new StatementBlock(bl);
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statementblock"


	public static class assumption_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assumption"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:279:1: assumption returns [Statement r] : 'assume' '(' t= term ')' ;
	public final FaehrmannParser.assumption_return assumption() throws RecognitionException {
		FaehrmannParser.assumption_return retval = new FaehrmannParser.assumption_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal6=null;
		Token char_literal7=null;
		Token char_literal8=null;
		ParserRuleReturnScope t =null;

		Object string_literal6_tree=null;
		Object char_literal7_tree=null;
		Object char_literal8_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:279:34: ( 'assume' '(' t= term ')' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:280:9: 'assume' '(' t= term ')'
			{
			root_0 = (Object)adaptor.nil();


			string_literal6=(Token)match(input,36,FOLLOW_36_in_assumption348); 
			string_literal6_tree = (Object)adaptor.create(string_literal6);
			adaptor.addChild(root_0, string_literal6_tree);

			char_literal7=(Token)match(input,13,FOLLOW_13_in_assumption350); 
			char_literal7_tree = (Object)adaptor.create(char_literal7);
			adaptor.addChild(root_0, char_literal7_tree);

			pushFollow(FOLLOW_term_in_assumption354);
			t=term();
			state._fsp--;

			adaptor.addChild(root_0, t.getTree());

			char_literal8=(Token)match(input,14,FOLLOW_14_in_assumption356); 
			char_literal8_tree = (Object)adaptor.create(char_literal8);
			adaptor.addChild(root_0, char_literal8_tree);


			          retval.r = new Assumption(line(), (t!=null?((FaehrmannParser.term_return)t).r:null));
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assumption"


	public static class assertion_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assertion"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:285:1: assertion returns [Statement r] : 'assert' '(' t= term ')' ;
	public final FaehrmannParser.assertion_return assertion() throws RecognitionException {
		FaehrmannParser.assertion_return retval = new FaehrmannParser.assertion_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal9=null;
		Token char_literal10=null;
		Token char_literal11=null;
		ParserRuleReturnScope t =null;

		Object string_literal9_tree=null;
		Object char_literal10_tree=null;
		Object char_literal11_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:285:33: ( 'assert' '(' t= term ')' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:286:9: 'assert' '(' t= term ')'
			{
			root_0 = (Object)adaptor.nil();


			string_literal9=(Token)match(input,35,FOLLOW_35_in_assertion379); 
			string_literal9_tree = (Object)adaptor.create(string_literal9);
			adaptor.addChild(root_0, string_literal9_tree);

			char_literal10=(Token)match(input,13,FOLLOW_13_in_assertion381); 
			char_literal10_tree = (Object)adaptor.create(char_literal10);
			adaptor.addChild(root_0, char_literal10_tree);

			pushFollow(FOLLOW_term_in_assertion385);
			t=term();
			state._fsp--;

			adaptor.addChild(root_0, t.getTree());

			char_literal11=(Token)match(input,14,FOLLOW_14_in_assertion387); 
			char_literal11_tree = (Object)adaptor.create(char_literal11);
			adaptor.addChild(root_0, char_literal11_tree);


			          retval.r = new Assertion(line(), (t!=null?((FaehrmannParser.term_return)t).r:null));
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assertion"


	public static class whileBlock_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "whileBlock"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:291:1: whileBlock returns [Statement r] : 'while' '(' t= expr ')' '{' stateBlock= statementblock '}' ;
	public final FaehrmannParser.whileBlock_return whileBlock() throws RecognitionException {
		FaehrmannParser.whileBlock_return retval = new FaehrmannParser.whileBlock_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal12=null;
		Token char_literal13=null;
		Token char_literal14=null;
		Token char_literal15=null;
		Token char_literal16=null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope stateBlock =null;

		Object string_literal12_tree=null;
		Object char_literal13_tree=null;
		Object char_literal14_tree=null;
		Object char_literal15_tree=null;
		Object char_literal16_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:291:34: ( 'while' '(' t= expr ')' '{' stateBlock= statementblock '}' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:292:9: 'while' '(' t= expr ')' '{' stateBlock= statementblock '}'
			{
			root_0 = (Object)adaptor.nil();


			string_literal12=(Token)match(input,45,FOLLOW_45_in_whileBlock410); 
			string_literal12_tree = (Object)adaptor.create(string_literal12);
			adaptor.addChild(root_0, string_literal12_tree);

			char_literal13=(Token)match(input,13,FOLLOW_13_in_whileBlock412); 
			char_literal13_tree = (Object)adaptor.create(char_literal13);
			adaptor.addChild(root_0, char_literal13_tree);

			pushFollow(FOLLOW_expr_in_whileBlock416);
			t=expr();
			state._fsp--;

			adaptor.addChild(root_0, t.getTree());

			char_literal14=(Token)match(input,14,FOLLOW_14_in_whileBlock418); 
			char_literal14_tree = (Object)adaptor.create(char_literal14);
			adaptor.addChild(root_0, char_literal14_tree);

			char_literal15=(Token)match(input,46,FOLLOW_46_in_whileBlock420); 
			char_literal15_tree = (Object)adaptor.create(char_literal15);
			adaptor.addChild(root_0, char_literal15_tree);

			pushFollow(FOLLOW_statementblock_in_whileBlock424);
			stateBlock=statementblock();
			state._fsp--;

			adaptor.addChild(root_0, stateBlock.getTree());

			char_literal16=(Token)match(input,48,FOLLOW_48_in_whileBlock426); 
			char_literal16_tree = (Object)adaptor.create(char_literal16);
			adaptor.addChild(root_0, char_literal16_tree);


			          retval.r = new WhileBlock(line(), (t!=null?((FaehrmannParser.expr_return)t).r:null), (stateBlock!=null?((FaehrmannParser.statementblock_return)stateBlock).r:null));
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whileBlock"


	public static class ifBlock_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ifBlock"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:297:1: ifBlock returns [Statement r] : 'if' '(' t= expr ')' '{' stateIf= statementblock '}' ( 'else' '{' stateElse= statementblock '}' )? ;
	public final FaehrmannParser.ifBlock_return ifBlock() throws RecognitionException {
		FaehrmannParser.ifBlock_return retval = new FaehrmannParser.ifBlock_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal17=null;
		Token char_literal18=null;
		Token char_literal19=null;
		Token char_literal20=null;
		Token char_literal21=null;
		Token string_literal22=null;
		Token char_literal23=null;
		Token char_literal24=null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope stateIf =null;
		ParserRuleReturnScope stateElse =null;

		Object string_literal17_tree=null;
		Object char_literal18_tree=null;
		Object char_literal19_tree=null;
		Object char_literal20_tree=null;
		Object char_literal21_tree=null;
		Object string_literal22_tree=null;
		Object char_literal23_tree=null;
		Object char_literal24_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:297:31: ( 'if' '(' t= expr ')' '{' stateIf= statementblock '}' ( 'else' '{' stateElse= statementblock '}' )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:298:9: 'if' '(' t= expr ')' '{' stateIf= statementblock '}' ( 'else' '{' stateElse= statementblock '}' )?
			{
			root_0 = (Object)adaptor.nil();


			string_literal17=(Token)match(input,42,FOLLOW_42_in_ifBlock449); 
			string_literal17_tree = (Object)adaptor.create(string_literal17);
			adaptor.addChild(root_0, string_literal17_tree);

			char_literal18=(Token)match(input,13,FOLLOW_13_in_ifBlock451); 
			char_literal18_tree = (Object)adaptor.create(char_literal18);
			adaptor.addChild(root_0, char_literal18_tree);

			pushFollow(FOLLOW_expr_in_ifBlock455);
			t=expr();
			state._fsp--;

			adaptor.addChild(root_0, t.getTree());

			char_literal19=(Token)match(input,14,FOLLOW_14_in_ifBlock457); 
			char_literal19_tree = (Object)adaptor.create(char_literal19);
			adaptor.addChild(root_0, char_literal19_tree);

			char_literal20=(Token)match(input,46,FOLLOW_46_in_ifBlock459); 
			char_literal20_tree = (Object)adaptor.create(char_literal20);
			adaptor.addChild(root_0, char_literal20_tree);

			pushFollow(FOLLOW_statementblock_in_ifBlock463);
			stateIf=statementblock();
			state._fsp--;

			adaptor.addChild(root_0, stateIf.getTree());

			char_literal21=(Token)match(input,48,FOLLOW_48_in_ifBlock465); 
			char_literal21_tree = (Object)adaptor.create(char_literal21);
			adaptor.addChild(root_0, char_literal21_tree);

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:298:60: ( 'else' '{' stateElse= statementblock '}' )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==38) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:298:61: 'else' '{' stateElse= statementblock '}'
					{
					string_literal22=(Token)match(input,38,FOLLOW_38_in_ifBlock468); 
					string_literal22_tree = (Object)adaptor.create(string_literal22);
					adaptor.addChild(root_0, string_literal22_tree);

					char_literal23=(Token)match(input,46,FOLLOW_46_in_ifBlock470); 
					char_literal23_tree = (Object)adaptor.create(char_literal23);
					adaptor.addChild(root_0, char_literal23_tree);

					pushFollow(FOLLOW_statementblock_in_ifBlock474);
					stateElse=statementblock();
					state._fsp--;

					adaptor.addChild(root_0, stateElse.getTree());

					char_literal24=(Token)match(input,48,FOLLOW_48_in_ifBlock476); 
					char_literal24_tree = (Object)adaptor.create(char_literal24);
					adaptor.addChild(root_0, char_literal24_tree);

					}
					break;

			}


			          if (stateElse != null) {
			          	retval.r = new IfBlock(line(), (t!=null?((FaehrmannParser.expr_return)t).r:null), (stateIf!=null?((FaehrmannParser.statementblock_return)stateIf).r:null), (stateElse!=null?((FaehrmannParser.statementblock_return)stateElse).r:null));
			          } else {
			          	retval.r = new IfBlock(line(), (t!=null?((FaehrmannParser.expr_return)t).r:null), (stateIf!=null?((FaehrmannParser.statementblock_return)stateIf).r:null), noElse);
			          }
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ifBlock"


	public static class definition_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "definition"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:307:1: definition returns [Statement r] : ( ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID ) '=' (t= expr ) |array= arraydefinition );
	public final FaehrmannParser.definition_return definition() throws RecognitionException {
		FaehrmannParser.definition_return retval = new FaehrmannParser.definition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token string_literal25=null;
		Token string_literal26=null;
		Token string_literal27=null;
		Token string_literal28=null;
		Token char_literal29=null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope array =null;

		Object name_tree=null;
		Object string_literal25_tree=null;
		Object string_literal26_tree=null;
		Object string_literal27_tree=null;
		Object string_literal28_tree=null;
		Object char_literal29_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:307:34: ( ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID ) '=' (t= expr ) |array= arraydefinition )
			int alt8=2;
			switch ( input.LA(1) ) {
			case 41:
				{
				int LA8_1 = input.LA(2);
				if ( (LA8_1==43) ) {
					int LA8_2 = input.LA(3);
					if ( (LA8_2==VARIABLEID) ) {
						int LA8_4 = input.LA(4);
						if ( (LA8_4==31) ) {
							alt8=2;
						}
						else if ( (LA8_4==27) ) {
							alt8=1;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 8, 4, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 8, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA8_1==37) ) {
					int LA8_3 = input.LA(3);
					if ( (LA8_3==VARIABLEID) ) {
						int LA8_5 = input.LA(4);
						if ( (LA8_5==31) ) {
							alt8=2;
						}
						else if ( (LA8_5==27) ) {
							alt8=1;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 8, 5, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 8, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 43:
				{
				int LA8_2 = input.LA(2);
				if ( (LA8_2==VARIABLEID) ) {
					int LA8_4 = input.LA(3);
					if ( (LA8_4==31) ) {
						alt8=2;
					}
					else if ( (LA8_4==27) ) {
						alt8=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 8, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 37:
				{
				int LA8_3 = input.LA(2);
				if ( (LA8_3==VARIABLEID) ) {
					int LA8_5 = input.LA(3);
					if ( (LA8_5==31) ) {
						alt8=2;
					}
					else if ( (LA8_5==27) ) {
						alt8=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 8, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:307:36: ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID ) '=' (t= expr )
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:307:36: ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID )
					int alt7=2;
					switch ( input.LA(1) ) {
					case 41:
						{
						int LA7_1 = input.LA(2);
						if ( (LA7_1==43) ) {
							alt7=1;
						}
						else if ( (LA7_1==37) ) {
							alt7=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 7, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 43:
						{
						alt7=1;
						}
						break;
					case 37:
						{
						alt7=2;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 7, 0, input);
						throw nvae;
					}
					switch (alt7) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:308:9: ( 'high' )? 'int' name= VARIABLEID
							{
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:308:9: ( 'high' )?
							int alt5=2;
							int LA5_0 = input.LA(1);
							if ( (LA5_0==41) ) {
								alt5=1;
							}
							switch (alt5) {
								case 1 :
									// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:308:10: 'high'
									{
									string_literal25=(Token)match(input,41,FOLLOW_41_in_definition504); 
									string_literal25_tree = (Object)adaptor.create(string_literal25);
									adaptor.addChild(root_0, string_literal25_tree);

									high = true;
									}
									break;

							}

							string_literal26=(Token)match(input,43,FOLLOW_43_in_definition510); 
							string_literal26_tree = (Object)adaptor.create(string_literal26);
							adaptor.addChild(root_0, string_literal26_tree);

							name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_definition514); 
							name_tree = (Object)adaptor.create(name);
							adaptor.addChild(root_0, name_tree);


							          this.checkNameVariable((name!=null?name.getText():null));
							          VarInt intvar = new VarInt((name!=null?name.getText():null), high);
							          variables.add(intvar);
							          high = false;                         
							        
							}
							break;
						case 2 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:314:11: ( 'high' )? 'bool' name= VARIABLEID
							{
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:314:11: ( 'high' )?
							int alt6=2;
							int LA6_0 = input.LA(1);
							if ( (LA6_0==41) ) {
								alt6=1;
							}
							switch (alt6) {
								case 1 :
									// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:314:12: 'high'
									{
									string_literal27=(Token)match(input,41,FOLLOW_41_in_definition529); 
									string_literal27_tree = (Object)adaptor.create(string_literal27);
									adaptor.addChild(root_0, string_literal27_tree);

									high = true;
									}
									break;

							}

							string_literal28=(Token)match(input,37,FOLLOW_37_in_definition535); 
							string_literal28_tree = (Object)adaptor.create(string_literal28);
							adaptor.addChild(root_0, string_literal28_tree);

							name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_definition539); 
							name_tree = (Object)adaptor.create(name);
							adaptor.addChild(root_0, name_tree);


							         this.checkNameVariable((name!=null?name.getText():null));
							         VarBool boolvar = new VarBool((name!=null?name.getText():null), high);
							         variables.add(boolvar);
							         high = false;
							        
							}
							break;

					}

					char_literal29=(Token)match(input,27,FOLLOW_27_in_definition552); 
					char_literal29_tree = (Object)adaptor.create(char_literal29);
					adaptor.addChild(root_0, char_literal29_tree);

					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:320:13: (t= expr )
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:320:14: t= expr
					{
					pushFollow(FOLLOW_expr_in_definition557);
					t=expr();
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());

					}


						       retval.r = new Definition(line(), getASTVariable((name!=null?name.getText():null)), (t!=null?((FaehrmannParser.expr_return)t).r:null));
						      
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:323:10: array= arraydefinition
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_arraydefinition_in_definition573);
					array=arraydefinition();
					state._fsp--;

					adaptor.addChild(root_0, array.getTree());


						        retval.r = (array!=null?((FaehrmannParser.arraydefinition_return)array).r:null);
						      
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "definition"


	public static class declaration_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "declaration"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:328:1: declaration returns [Statement r] : ( ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID ) |array= arraydeclaration );
	public final FaehrmannParser.declaration_return declaration() throws RecognitionException {
		FaehrmannParser.declaration_return retval = new FaehrmannParser.declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token string_literal30=null;
		Token string_literal31=null;
		Token string_literal32=null;
		Token string_literal33=null;
		ParserRuleReturnScope array =null;

		Object name_tree=null;
		Object string_literal30_tree=null;
		Object string_literal31_tree=null;
		Object string_literal32_tree=null;
		Object string_literal33_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:328:35: ( ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID ) |array= arraydeclaration )
			int alt12=2;
			switch ( input.LA(1) ) {
			case 41:
				{
				int LA12_1 = input.LA(2);
				if ( (LA12_1==43) ) {
					int LA12_2 = input.LA(3);
					if ( (LA12_2==VARIABLEID) ) {
						int LA12_4 = input.LA(4);
						if ( (LA12_4==31) ) {
							alt12=2;
						}
						else if ( (LA12_4==23) ) {
							alt12=1;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 12, 4, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 12, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA12_1==37) ) {
					int LA12_3 = input.LA(3);
					if ( (LA12_3==VARIABLEID) ) {
						int LA12_5 = input.LA(4);
						if ( (LA12_5==31) ) {
							alt12=2;
						}
						else if ( (LA12_5==23) ) {
							alt12=1;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 12, 5, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 12, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 43:
				{
				int LA12_2 = input.LA(2);
				if ( (LA12_2==VARIABLEID) ) {
					int LA12_4 = input.LA(3);
					if ( (LA12_4==31) ) {
						alt12=2;
					}
					else if ( (LA12_4==23) ) {
						alt12=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 12, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 37:
				{
				int LA12_3 = input.LA(2);
				if ( (LA12_3==VARIABLEID) ) {
					int LA12_5 = input.LA(3);
					if ( (LA12_5==31) ) {
						alt12=2;
					}
					else if ( (LA12_5==23) ) {
						alt12=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 12, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:328:37: ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID )
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:328:37: ( ( 'high' )? 'int' name= VARIABLEID | ( 'high' )? 'bool' name= VARIABLEID )
					int alt11=2;
					switch ( input.LA(1) ) {
					case 41:
						{
						int LA11_1 = input.LA(2);
						if ( (LA11_1==43) ) {
							alt11=1;
						}
						else if ( (LA11_1==37) ) {
							alt11=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 11, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 43:
						{
						alt11=1;
						}
						break;
					case 37:
						{
						alt11=2;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 11, 0, input);
						throw nvae;
					}
					switch (alt11) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:329:8: ( 'high' )? 'int' name= VARIABLEID
							{
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:329:8: ( 'high' )?
							int alt9=2;
							int LA9_0 = input.LA(1);
							if ( (LA9_0==41) ) {
								alt9=1;
							}
							switch (alt9) {
								case 1 :
									// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:329:9: 'high'
									{
									string_literal30=(Token)match(input,41,FOLLOW_41_in_declaration598); 
									string_literal30_tree = (Object)adaptor.create(string_literal30);
									adaptor.addChild(root_0, string_literal30_tree);

									high = true;
									}
									break;

							}

							string_literal31=(Token)match(input,43,FOLLOW_43_in_declaration604); 
							string_literal31_tree = (Object)adaptor.create(string_literal31);
							adaptor.addChild(root_0, string_literal31_tree);

							name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_declaration608); 
							name_tree = (Object)adaptor.create(name);
							adaptor.addChild(root_0, name_tree);


							           this.checkNameVariable((name!=null?name.getText():null));
								         VarInt intvar = new VarInt((name!=null?name.getText():null), high);
								         variables.add(intvar);
								         high = false;
							        
							}
							break;
						case 2 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:336:11: ( 'high' )? 'bool' name= VARIABLEID
							{
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:336:11: ( 'high' )?
							int alt10=2;
							int LA10_0 = input.LA(1);
							if ( (LA10_0==41) ) {
								alt10=1;
							}
							switch (alt10) {
								case 1 :
									// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:336:12: 'high'
									{
									string_literal32=(Token)match(input,41,FOLLOW_41_in_declaration632); 
									string_literal32_tree = (Object)adaptor.create(string_literal32);
									adaptor.addChild(root_0, string_literal32_tree);

									high = true;
									}
									break;

							}

							string_literal33=(Token)match(input,37,FOLLOW_37_in_declaration638); 
							string_literal33_tree = (Object)adaptor.create(string_literal33);
							adaptor.addChild(root_0, string_literal33_tree);

							name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_declaration642); 
							name_tree = (Object)adaptor.create(name);
							adaptor.addChild(root_0, name_tree);


							           this.checkNameVariable((name!=null?name.getText():null));
								         VarBool boolvar = new VarBool((name!=null?name.getText():null), high);
								         variables.add(boolvar);
								         high = false;
								        
							}
							break;

					}


								   retval.r = new Definition(line(), getASTVariable((name!=null?name.getText():null)), null);
								  
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:345:8: array= arraydeclaration
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_arraydeclaration_in_declaration669);
					array=arraydeclaration();
					state._fsp--;

					adaptor.addChild(root_0, array.getTree());


								    retval.r = (array!=null?((FaehrmannParser.arraydeclaration_return)array).r:null);
								  
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "declaration"


	public static class arraydefinition_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "arraydefinition"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:350:1: arraydefinition returns [Statement r] : ( ( 'high' )? 'int' name= VARIABLEID '[' length= NUMBER ']' '=' '{' value= expr ( ',' value= expr )* '}' | ( 'high' )? 'bool' name= VARIABLEID '[' length= NUMBER ']' '=' '{' value= expr ( ',' value= expr )* '}' );
	public final FaehrmannParser.arraydefinition_return arraydefinition() throws RecognitionException {
		FaehrmannParser.arraydefinition_return retval = new FaehrmannParser.arraydefinition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token length=null;
		Token string_literal34=null;
		Token string_literal35=null;
		Token char_literal36=null;
		Token char_literal37=null;
		Token char_literal38=null;
		Token char_literal39=null;
		Token char_literal40=null;
		Token char_literal41=null;
		Token string_literal42=null;
		Token string_literal43=null;
		Token char_literal44=null;
		Token char_literal45=null;
		Token char_literal46=null;
		Token char_literal47=null;
		Token char_literal48=null;
		Token char_literal49=null;
		ParserRuleReturnScope value =null;

		Object name_tree=null;
		Object length_tree=null;
		Object string_literal34_tree=null;
		Object string_literal35_tree=null;
		Object char_literal36_tree=null;
		Object char_literal37_tree=null;
		Object char_literal38_tree=null;
		Object char_literal39_tree=null;
		Object char_literal40_tree=null;
		Object char_literal41_tree=null;
		Object string_literal42_tree=null;
		Object string_literal43_tree=null;
		Object char_literal44_tree=null;
		Object char_literal45_tree=null;
		Object char_literal46_tree=null;
		Object char_literal47_tree=null;
		Object char_literal48_tree=null;
		Object char_literal49_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:350:38: ( ( 'high' )? 'int' name= VARIABLEID '[' length= NUMBER ']' '=' '{' value= expr ( ',' value= expr )* '}' | ( 'high' )? 'bool' name= VARIABLEID '[' length= NUMBER ']' '=' '{' value= expr ( ',' value= expr )* '}' )
			int alt17=2;
			switch ( input.LA(1) ) {
			case 41:
				{
				int LA17_1 = input.LA(2);
				if ( (LA17_1==43) ) {
					alt17=1;
				}
				else if ( (LA17_1==37) ) {
					alt17=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 17, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 43:
				{
				alt17=1;
				}
				break;
			case 37:
				{
				alt17=2;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:351:10: ( 'high' )? 'int' name= VARIABLEID '[' length= NUMBER ']' '=' '{' value= expr ( ',' value= expr )* '}'
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:351:10: ( 'high' )?
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( (LA13_0==41) ) {
						alt13=1;
					}
					switch (alt13) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:351:11: 'high'
							{
							string_literal34=(Token)match(input,41,FOLLOW_41_in_arraydefinition696); 
							string_literal34_tree = (Object)adaptor.create(string_literal34);
							adaptor.addChild(root_0, string_literal34_tree);

							high = true;
							}
							break;

					}

					string_literal35=(Token)match(input,43,FOLLOW_43_in_arraydefinition701); 
					string_literal35_tree = (Object)adaptor.create(string_literal35);
					adaptor.addChild(root_0, string_literal35_tree);

					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_arraydefinition705); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal36=(Token)match(input,31,FOLLOW_31_in_arraydefinition707); 
					char_literal36_tree = (Object)adaptor.create(char_literal36);
					adaptor.addChild(root_0, char_literal36_tree);

					length=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_arraydefinition711); 
					length_tree = (Object)adaptor.create(length);
					adaptor.addChild(root_0, length_tree);

					char_literal37=(Token)match(input,34,FOLLOW_34_in_arraydefinition713); 
					char_literal37_tree = (Object)adaptor.create(char_literal37);
					adaptor.addChild(root_0, char_literal37_tree);

					char_literal38=(Token)match(input,27,FOLLOW_27_in_arraydefinition715); 
					char_literal38_tree = (Object)adaptor.create(char_literal38);
					adaptor.addChild(root_0, char_literal38_tree);

					char_literal39=(Token)match(input,46,FOLLOW_46_in_arraydefinition717); 
					char_literal39_tree = (Object)adaptor.create(char_literal39);
					adaptor.addChild(root_0, char_literal39_tree);

					pushFollow(FOLLOW_expr_in_arraydefinition720);
					value=expr();
					state._fsp--;

					adaptor.addChild(root_0, value.getTree());

					arrayvalues.add((value!=null?((FaehrmannParser.expr_return)value).r:null));
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:351:126: ( ',' value= expr )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==18) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:351:127: ',' value= expr
							{
							char_literal40=(Token)match(input,18,FOLLOW_18_in_arraydefinition726); 
							char_literal40_tree = (Object)adaptor.create(char_literal40);
							adaptor.addChild(root_0, char_literal40_tree);

							pushFollow(FOLLOW_expr_in_arraydefinition730);
							value=expr();
							state._fsp--;

							adaptor.addChild(root_0, value.getTree());

							arrayvalues.add((value!=null?((FaehrmannParser.expr_return)value).r:null));
							}
							break;

						default :
							break loop14;
						}
					}

					char_literal41=(Token)match(input,48,FOLLOW_48_in_arraydefinition736); 
					char_literal41_tree = (Object)adaptor.create(char_literal41);
					adaptor.addChild(root_0, char_literal41_tree);

					        
					                  if(Integer.parseInt((length!=null?length.getText():null)) == arrayvalues.size()) {
					                      this.checkNameArray((name!=null?name.getText():null));
					                      VarInt lengthVar = new VarInt((name!=null?name.getText():null) + ".length", high);
					                      variables.add(lengthVar);
					                      list.add(new Assignment(line(), lengthVar, new ConstInt(new BigInteger((length!=null?length.getText():null)))));
					                      retval.r = definearray((name!=null?name.getText():null), (length!=null?length.getText():null), true, arrayvalues);                                                    
					                  }
					                  else{ errors.add("Error: Arraylength not valid, expected length: " + (length!=null?length.getText():null) + "  , actual length: " + arrayvalues.size());}
					                  temp="";
					                  high = false;
					                  arrayvalues.clear();
					           
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:365:11: ( 'high' )? 'bool' name= VARIABLEID '[' length= NUMBER ']' '=' '{' value= expr ( ',' value= expr )* '}'
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:365:11: ( 'high' )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==41) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:365:12: 'high'
							{
							string_literal42=(Token)match(input,41,FOLLOW_41_in_arraydefinition764); 
							string_literal42_tree = (Object)adaptor.create(string_literal42);
							adaptor.addChild(root_0, string_literal42_tree);

							high = true;
							}
							break;

					}

					string_literal43=(Token)match(input,37,FOLLOW_37_in_arraydefinition769); 
					string_literal43_tree = (Object)adaptor.create(string_literal43);
					adaptor.addChild(root_0, string_literal43_tree);

					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_arraydefinition773); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal44=(Token)match(input,31,FOLLOW_31_in_arraydefinition775); 
					char_literal44_tree = (Object)adaptor.create(char_literal44);
					adaptor.addChild(root_0, char_literal44_tree);

					length=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_arraydefinition779); 
					length_tree = (Object)adaptor.create(length);
					adaptor.addChild(root_0, length_tree);

					char_literal45=(Token)match(input,34,FOLLOW_34_in_arraydefinition781); 
					char_literal45_tree = (Object)adaptor.create(char_literal45);
					adaptor.addChild(root_0, char_literal45_tree);

					char_literal46=(Token)match(input,27,FOLLOW_27_in_arraydefinition783); 
					char_literal46_tree = (Object)adaptor.create(char_literal46);
					adaptor.addChild(root_0, char_literal46_tree);

					char_literal47=(Token)match(input,46,FOLLOW_46_in_arraydefinition785); 
					char_literal47_tree = (Object)adaptor.create(char_literal47);
					adaptor.addChild(root_0, char_literal47_tree);

					pushFollow(FOLLOW_expr_in_arraydefinition788);
					value=expr();
					state._fsp--;

					adaptor.addChild(root_0, value.getTree());

					arrayvalues.add((value!=null?((FaehrmannParser.expr_return)value).r:null));
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:365:128: ( ',' value= expr )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==18) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:365:129: ',' value= expr
							{
							char_literal48=(Token)match(input,18,FOLLOW_18_in_arraydefinition794); 
							char_literal48_tree = (Object)adaptor.create(char_literal48);
							adaptor.addChild(root_0, char_literal48_tree);

							pushFollow(FOLLOW_expr_in_arraydefinition798);
							value=expr();
							state._fsp--;

							adaptor.addChild(root_0, value.getTree());

							arrayvalues.add((value!=null?((FaehrmannParser.expr_return)value).r:null));
							}
							break;

						default :
							break loop16;
						}
					}

					char_literal49=(Token)match(input,48,FOLLOW_48_in_arraydefinition804); 
					char_literal49_tree = (Object)adaptor.create(char_literal49);
					adaptor.addChild(root_0, char_literal49_tree);


					                  if(Integer.parseInt((length!=null?length.getText():null)) == arrayvalues.size()) {
					                      this.checkNameArray((name!=null?name.getText():null));
					                      VarInt lengthVar = new VarInt((name!=null?name.getText():null) + ".length", high);
					                      variables.add(lengthVar);
					                      list.add(new Assignment(line(), lengthVar, new ConstInt(new BigInteger((length!=null?length.getText():null)))));
					                      retval.r = definearray((name!=null?name.getText():null), (length!=null?length.getText():null), false, arrayvalues);                                                    
					                  }
					                  else{ errors.add("Error: Arraylength not valid, expected length: " + (length!=null?length.getText():null) + "  , actual length: " + arrayvalues.size());}
					                  temp="";
					                  high = false;
					                  arrayvalues.clear();            
					           
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arraydefinition"


	public static class arraydeclaration_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "arraydeclaration"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:381:1: arraydeclaration returns [Statement r] : ( ( 'high' )? 'int' name= VARIABLEID '[' length= NUMBER ']' | ( 'high' )? 'bool' name= VARIABLEID '[' length= NUMBER ']' );
	public final FaehrmannParser.arraydeclaration_return arraydeclaration() throws RecognitionException {
		FaehrmannParser.arraydeclaration_return retval = new FaehrmannParser.arraydeclaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token length=null;
		Token string_literal50=null;
		Token string_literal51=null;
		Token char_literal52=null;
		Token char_literal53=null;
		Token string_literal54=null;
		Token string_literal55=null;
		Token char_literal56=null;
		Token char_literal57=null;

		Object name_tree=null;
		Object length_tree=null;
		Object string_literal50_tree=null;
		Object string_literal51_tree=null;
		Object char_literal52_tree=null;
		Object char_literal53_tree=null;
		Object string_literal54_tree=null;
		Object string_literal55_tree=null;
		Object char_literal56_tree=null;
		Object char_literal57_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:381:39: ( ( 'high' )? 'int' name= VARIABLEID '[' length= NUMBER ']' | ( 'high' )? 'bool' name= VARIABLEID '[' length= NUMBER ']' )
			int alt20=2;
			switch ( input.LA(1) ) {
			case 41:
				{
				int LA20_1 = input.LA(2);
				if ( (LA20_1==43) ) {
					alt20=1;
				}
				else if ( (LA20_1==37) ) {
					alt20=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 20, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 43:
				{
				alt20=1;
				}
				break;
			case 37:
				{
				alt20=2;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}
			switch (alt20) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:382:10: ( 'high' )? 'int' name= VARIABLEID '[' length= NUMBER ']'
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:382:10: ( 'high' )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==41) ) {
						alt18=1;
					}
					switch (alt18) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:382:11: 'high'
							{
							string_literal50=(Token)match(input,41,FOLLOW_41_in_arraydeclaration848); 
							string_literal50_tree = (Object)adaptor.create(string_literal50);
							adaptor.addChild(root_0, string_literal50_tree);

							high = true;
							}
							break;

					}

					string_literal51=(Token)match(input,43,FOLLOW_43_in_arraydeclaration854); 
					string_literal51_tree = (Object)adaptor.create(string_literal51);
					adaptor.addChild(root_0, string_literal51_tree);

					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_arraydeclaration858); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal52=(Token)match(input,31,FOLLOW_31_in_arraydeclaration860); 
					char_literal52_tree = (Object)adaptor.create(char_literal52);
					adaptor.addChild(root_0, char_literal52_tree);

					length=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_arraydeclaration864); 
					length_tree = (Object)adaptor.create(length);
					adaptor.addChild(root_0, length_tree);

					char_literal53=(Token)match(input,34,FOLLOW_34_in_arraydeclaration866); 
					char_literal53_tree = (Object)adaptor.create(char_literal53);
					adaptor.addChild(root_0, char_literal53_tree);

					 
					            this.checkNameArray((name!=null?name.getText():null));
					            VarInt lengthVar = new VarInt((name!=null?name.getText():null) + ".length", high);
					            variables.add(lengthVar);
					            list.add(new Assignment(line(), lengthVar, new ConstInt(new BigInteger((length!=null?length.getText():null)))));                                                                         
					            retval.r = declarearray((name!=null?name.getText():null), (length!=null?length.getText():null), true);
					            high = false;                  
					         
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:390:12: ( 'high' )? 'bool' name= VARIABLEID '[' length= NUMBER ']'
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:390:12: ( 'high' )?
					int alt19=2;
					int LA19_0 = input.LA(1);
					if ( (LA19_0==41) ) {
						alt19=1;
					}
					switch (alt19) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:390:13: 'high'
							{
							string_literal54=(Token)match(input,41,FOLLOW_41_in_arraydeclaration882); 
							string_literal54_tree = (Object)adaptor.create(string_literal54);
							adaptor.addChild(root_0, string_literal54_tree);

							high = true;
							}
							break;

					}

					string_literal55=(Token)match(input,37,FOLLOW_37_in_arraydeclaration888); 
					string_literal55_tree = (Object)adaptor.create(string_literal55);
					adaptor.addChild(root_0, string_literal55_tree);

					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_arraydeclaration892); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal56=(Token)match(input,31,FOLLOW_31_in_arraydeclaration894); 
					char_literal56_tree = (Object)adaptor.create(char_literal56);
					adaptor.addChild(root_0, char_literal56_tree);

					length=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_arraydeclaration898); 
					length_tree = (Object)adaptor.create(length);
					adaptor.addChild(root_0, length_tree);

					char_literal57=(Token)match(input,34,FOLLOW_34_in_arraydeclaration900); 
					char_literal57_tree = (Object)adaptor.create(char_literal57);
					adaptor.addChild(root_0, char_literal57_tree);


					            this.checkNameArray((name!=null?name.getText():null));
					            VarInt lengthVar = new VarInt((name!=null?name.getText():null) + ".length", high);
					            variables.add(lengthVar);
					            list.add(new Assignment(line(), lengthVar, new ConstInt(new BigInteger((length!=null?length.getText():null)))));
							        retval.r = declarearray((name!=null?name.getText():null), (length!=null?length.getText():null), false);
							        high = false;       
						       
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arraydeclaration"


	public static class assignment_return extends ParserRuleReturnScope {
		public Statement r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:401:1: assignment returns [Statement r] : (name= VARIABLEID '=' t= expr |name= VARIABLEID '[' ex= expr ']' '=' t= expr |def= definition |dec= declaration |name= VARIABLEID '++' |name= VARIABLEID '--' );
	public final FaehrmannParser.assignment_return assignment() throws RecognitionException {
		FaehrmannParser.assignment_return retval = new FaehrmannParser.assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token char_literal58=null;
		Token char_literal59=null;
		Token char_literal60=null;
		Token char_literal61=null;
		Token string_literal62=null;
		Token string_literal63=null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope ex =null;
		ParserRuleReturnScope def =null;
		ParserRuleReturnScope dec =null;

		Object name_tree=null;
		Object char_literal58_tree=null;
		Object char_literal59_tree=null;
		Object char_literal60_tree=null;
		Object char_literal61_tree=null;
		Object string_literal62_tree=null;
		Object string_literal63_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:401:34: (name= VARIABLEID '=' t= expr |name= VARIABLEID '[' ex= expr ']' '=' t= expr |def= definition |dec= declaration |name= VARIABLEID '++' |name= VARIABLEID '--' )
			int alt21=6;
			switch ( input.LA(1) ) {
			case VARIABLEID:
				{
				switch ( input.LA(2) ) {
				case 27:
					{
					alt21=1;
					}
					break;
				case 31:
					{
					alt21=2;
					}
					break;
				case 17:
					{
					alt21=5;
					}
					break;
				case 20:
					{
					alt21=6;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 21, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 41:
				{
				int LA21_2 = input.LA(2);
				if ( (LA21_2==43) ) {
					int LA21_3 = input.LA(3);
					if ( (LA21_3==VARIABLEID) ) {
						switch ( input.LA(4) ) {
						case 31:
							{
							int LA21_11 = input.LA(5);
							if ( (LA21_11==NUMBER) ) {
								int LA21_15 = input.LA(6);
								if ( (LA21_15==34) ) {
									int LA21_17 = input.LA(7);
									if ( (LA21_17==27) ) {
										alt21=3;
									}
									else if ( (LA21_17==23) ) {
										alt21=4;
									}

									else {
										int nvaeMark = input.mark();
										try {
											for (int nvaeConsume = 0; nvaeConsume < 7 - 1; nvaeConsume++) {
												input.consume();
											}
											NoViableAltException nvae =
												new NoViableAltException("", 21, 17, input);
											throw nvae;
										} finally {
											input.rewind(nvaeMark);
										}
									}

								}

								else {
									int nvaeMark = input.mark();
									try {
										for (int nvaeConsume = 0; nvaeConsume < 6 - 1; nvaeConsume++) {
											input.consume();
										}
										NoViableAltException nvae =
											new NoViableAltException("", 21, 15, input);
										throw nvae;
									} finally {
										input.rewind(nvaeMark);
									}
								}

							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 21, 11, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case 27:
							{
							alt21=3;
							}
							break;
						case 23:
							{
							alt21=4;
							}
							break;
						default:
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 21, 9, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 21, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA21_2==37) ) {
					int LA21_4 = input.LA(3);
					if ( (LA21_4==VARIABLEID) ) {
						switch ( input.LA(4) ) {
						case 31:
							{
							int LA21_14 = input.LA(5);
							if ( (LA21_14==NUMBER) ) {
								int LA21_16 = input.LA(6);
								if ( (LA21_16==34) ) {
									int LA21_18 = input.LA(7);
									if ( (LA21_18==27) ) {
										alt21=3;
									}
									else if ( (LA21_18==23) ) {
										alt21=4;
									}

									else {
										int nvaeMark = input.mark();
										try {
											for (int nvaeConsume = 0; nvaeConsume < 7 - 1; nvaeConsume++) {
												input.consume();
											}
											NoViableAltException nvae =
												new NoViableAltException("", 21, 18, input);
											throw nvae;
										} finally {
											input.rewind(nvaeMark);
										}
									}

								}

								else {
									int nvaeMark = input.mark();
									try {
										for (int nvaeConsume = 0; nvaeConsume < 6 - 1; nvaeConsume++) {
											input.consume();
										}
										NoViableAltException nvae =
											new NoViableAltException("", 21, 16, input);
										throw nvae;
									} finally {
										input.rewind(nvaeMark);
									}
								}

							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 21, 14, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case 27:
							{
							alt21=3;
							}
							break;
						case 23:
							{
							alt21=4;
							}
							break;
						default:
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 21, 10, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 21, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 21, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 43:
				{
				int LA21_3 = input.LA(2);
				if ( (LA21_3==VARIABLEID) ) {
					switch ( input.LA(3) ) {
					case 31:
						{
						int LA21_11 = input.LA(4);
						if ( (LA21_11==NUMBER) ) {
							int LA21_15 = input.LA(5);
							if ( (LA21_15==34) ) {
								int LA21_17 = input.LA(6);
								if ( (LA21_17==27) ) {
									alt21=3;
								}
								else if ( (LA21_17==23) ) {
									alt21=4;
								}

								else {
									int nvaeMark = input.mark();
									try {
										for (int nvaeConsume = 0; nvaeConsume < 6 - 1; nvaeConsume++) {
											input.consume();
										}
										NoViableAltException nvae =
											new NoViableAltException("", 21, 17, input);
										throw nvae;
									} finally {
										input.rewind(nvaeMark);
									}
								}

							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 21, 15, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 21, 11, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 27:
						{
						alt21=3;
						}
						break;
					case 23:
						{
						alt21=4;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 21, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 21, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 37:
				{
				int LA21_4 = input.LA(2);
				if ( (LA21_4==VARIABLEID) ) {
					switch ( input.LA(3) ) {
					case 31:
						{
						int LA21_14 = input.LA(4);
						if ( (LA21_14==NUMBER) ) {
							int LA21_16 = input.LA(5);
							if ( (LA21_16==34) ) {
								int LA21_18 = input.LA(6);
								if ( (LA21_18==27) ) {
									alt21=3;
								}
								else if ( (LA21_18==23) ) {
									alt21=4;
								}

								else {
									int nvaeMark = input.mark();
									try {
										for (int nvaeConsume = 0; nvaeConsume < 6 - 1; nvaeConsume++) {
											input.consume();
										}
										NoViableAltException nvae =
											new NoViableAltException("", 21, 18, input);
										throw nvae;
									} finally {
										input.rewind(nvaeMark);
									}
								}

							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 21, 16, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 21, 14, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 27:
						{
						alt21=3;
						}
						break;
					case 23:
						{
						alt21=4;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 21, 10, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 21, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}
			switch (alt21) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:402:9: name= VARIABLEID '=' t= expr
					{
					root_0 = (Object)adaptor.nil();


					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_assignment968); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal58=(Token)match(input,27,FOLLOW_27_in_assignment970); 
					char_literal58_tree = (Object)adaptor.create(char_literal58);
					adaptor.addChild(root_0, char_literal58_tree);

					pushFollow(FOLLOW_expr_in_assignment974);
					t=expr();
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());


					           retval.r = new Assignment(line(), getASTVariable((name!=null?name.getText():null)), (t!=null?((FaehrmannParser.expr_return)t).r:null));
					        
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:405:11: name= VARIABLEID '[' ex= expr ']' '=' t= expr
					{
					root_0 = (Object)adaptor.nil();


					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_assignment990); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal59=(Token)match(input,31,FOLLOW_31_in_assignment992); 
					char_literal59_tree = (Object)adaptor.create(char_literal59);
					adaptor.addChild(root_0, char_literal59_tree);

					pushFollow(FOLLOW_expr_in_assignment996);
					ex=expr();
					state._fsp--;

					adaptor.addChild(root_0, ex.getTree());

					char_literal60=(Token)match(input,34,FOLLOW_34_in_assignment998); 
					char_literal60_tree = (Object)adaptor.create(char_literal60);
					adaptor.addChild(root_0, char_literal60_tree);

					char_literal61=(Token)match(input,27,FOLLOW_27_in_assignment1000); 
					char_literal61_tree = (Object)adaptor.create(char_literal61);
					adaptor.addChild(root_0, char_literal61_tree);

					pushFollow(FOLLOW_expr_in_assignment1004);
					t=expr();
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());


					           if(existsArray((name!=null?name.getText():null))) {
					              if(getArrayType((name!=null?name.getText():null)) == 1) {
					                  ArrayIntAccess temp = new ArrayIntAccess((name!=null?name.getText():null), getArrayLength((name!=null?name.getText():null)), isArrayHigh((name!=null?name.getText():null)), (ex!=null?((FaehrmannParser.expr_return)ex).r:null));
					                  retval.r = new ArrayAssignment(line(), temp, (t!=null?((FaehrmannParser.expr_return)t).r:null));
					              } else {
					                  ArrayBoolAccess temp = new ArrayBoolAccess((name!=null?name.getText():null), getArrayLength((name!=null?name.getText():null)), isArrayHigh((name!=null?name.getText():null)), (ex!=null?((FaehrmannParser.expr_return)ex).r:null));
					                  retval.r = new ArrayAssignment(line(), temp, (t!=null?((FaehrmannParser.expr_return)t).r:null));
					              }
					           }
					        
					}
					break;
				case 3 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:416:11: def= definition
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_definition_in_assignment1020);
					def=definition();
					state._fsp--;

					adaptor.addChild(root_0, def.getTree());


					           retval.r = (def!=null?((FaehrmannParser.definition_return)def).r:null);
					        
					}
					break;
				case 4 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:419:11: dec= declaration
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_declaration_in_assignment1036);
					dec=declaration();
					state._fsp--;

					adaptor.addChild(root_0, dec.getTree());


					           retval.r = (dec!=null?((FaehrmannParser.declaration_return)dec).r:null);
					        
					}
					break;
				case 5 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:422:11: name= VARIABLEID '++'
					{
					root_0 = (Object)adaptor.nil();


					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_assignment1052); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					string_literal62=(Token)match(input,17,FOLLOW_17_in_assignment1054); 
					string_literal62_tree = (Object)adaptor.create(string_literal62);
					adaptor.addChild(root_0, string_literal62_tree);


					           retval.r = new Assignment(line(), getASTVariable((name!=null?name.getText():null)), new Addition(
					           getASTVariable((name!=null?name.getText():null)), new ConstInt(new BigInteger("1"))));
					        
					}
					break;
				case 6 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:426:11: name= VARIABLEID '--'
					{
					root_0 = (Object)adaptor.nil();


					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_assignment1070); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					string_literal63=(Token)match(input,20,FOLLOW_20_in_assignment1072); 
					string_literal63_tree = (Object)adaptor.create(string_literal63);
					adaptor.addChild(root_0, string_literal63_tree);


					           retval.r = new Assignment(line(), getASTVariable((name!=null?name.getText():null)), new Subtraction(
					           getASTVariable((name!=null?name.getText():null)), new ConstInt(new BigInteger("1"))));
					        
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assignment"


	public static class term_return extends ParserRuleReturnScope {
		public Term r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "term"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:443:1: term returns [Term r] : (t1= quantTerm |t2= expr );
	public final FaehrmannParser.term_return term() throws RecognitionException {
		FaehrmannParser.term_return retval = new FaehrmannParser.term_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope t2 =null;


		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:443:23: (t1= quantTerm |t2= expr )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( ((LA22_0 >= 32 && LA22_0 <= 33)) ) {
				alt22=1;
			}
			else if ( (LA22_0==ARRAYLENGTH||(LA22_0 >= NUMBER && LA22_0 <= VARIABLEID)||LA22_0==10||LA22_0==13||LA22_0==19||LA22_0==39||LA22_0==44) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:444:9: t1= quantTerm
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_quantTerm_in_term1109);
					t1=quantTerm();
					state._fsp--;

					adaptor.addChild(root_0, t1.getTree());


					           retval.r = (t1!=null?((FaehrmannParser.quantTerm_return)t1).r:null);
					        
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:447:11: t2= expr
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_expr_in_term1125);
					t2=expr();
					state._fsp--;

					adaptor.addChild(root_0, t2.getTree());


					           retval.r = (t2!=null?((FaehrmannParser.expr_return)t2).r:null);
					        
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "term"


	public static class quantTerm_return extends ParserRuleReturnScope {
		public QuantifierTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "quantTerm"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:452:1: quantTerm returns [QuantifierTerm r] : ( '[A]' name= VARIABLEID ':' t= term | '[E]' name= VARIABLEID ':' t= term ) ;
	public final FaehrmannParser.quantTerm_return quantTerm() throws RecognitionException {
		FaehrmannParser.quantTerm_return retval = new FaehrmannParser.quantTerm_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token string_literal64=null;
		Token char_literal65=null;
		Token string_literal66=null;
		Token char_literal67=null;
		ParserRuleReturnScope t =null;

		Object name_tree=null;
		Object string_literal64_tree=null;
		Object char_literal65_tree=null;
		Object string_literal66_tree=null;
		Object char_literal67_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:452:38: ( ( '[A]' name= VARIABLEID ':' t= term | '[E]' name= VARIABLEID ':' t= term ) )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:452:40: ( '[A]' name= VARIABLEID ':' t= term | '[E]' name= VARIABLEID ':' t= term )
			{
			root_0 = (Object)adaptor.nil();


			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:452:40: ( '[A]' name= VARIABLEID ':' t= term | '[E]' name= VARIABLEID ':' t= term )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==32) ) {
				alt23=1;
			}
			else if ( (LA23_0==33) ) {
				alt23=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:453:9: '[A]' name= VARIABLEID ':' t= term
					{
					string_literal64=(Token)match(input,32,FOLLOW_32_in_quantTerm1150); 
					string_literal64_tree = (Object)adaptor.create(string_literal64);
					adaptor.addChild(root_0, string_literal64_tree);

					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_quantTerm1154); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal65=(Token)match(input,22,FOLLOW_22_in_quantTerm1156); 
					char_literal65_tree = (Object)adaptor.create(char_literal65);
					adaptor.addChild(root_0, char_literal65_tree);

					pushFollow(FOLLOW_term_in_quantTerm1160);
					t=term();
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());


					           retval.r = new UniversalQuantifier(getASTVariable((name!=null?name.getText():null)), (t!=null?((FaehrmannParser.term_return)t).r:null));
					        
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:468:11: '[E]' name= VARIABLEID ':' t= term
					{
					string_literal66=(Token)match(input,33,FOLLOW_33_in_quantTerm1186); 
					string_literal66_tree = (Object)adaptor.create(string_literal66);
					adaptor.addChild(root_0, string_literal66_tree);

					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_quantTerm1190); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal67=(Token)match(input,22,FOLLOW_22_in_quantTerm1192); 
					char_literal67_tree = (Object)adaptor.create(char_literal67);
					adaptor.addChild(root_0, char_literal67_tree);

					pushFollow(FOLLOW_term_in_quantTerm1196);
					t=term();
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());


					           retval.r = new ExistentialQuantifier(getASTVariable((name!=null?name.getText():null)), (t!=null?((FaehrmannParser.term_return)t).r:null));
					        
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quantTerm"


	public static class restrictedTerm_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "restrictedTerm"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:473:1: restrictedTerm returns [RestrictedTerm r] : ( NUMBER |name= ( VARIABLEID | ARRAYLENGTH ) |name= VARIABLEID '[' index= expr ']' | '(' t= expr ')' | 'false' | 'true' );
	public final FaehrmannParser.restrictedTerm_return restrictedTerm() throws RecognitionException {
		FaehrmannParser.restrictedTerm_return retval = new FaehrmannParser.restrictedTerm_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token name=null;
		Token NUMBER68=null;
		Token char_literal69=null;
		Token char_literal70=null;
		Token char_literal71=null;
		Token char_literal72=null;
		Token string_literal73=null;
		Token string_literal74=null;
		ParserRuleReturnScope index =null;
		ParserRuleReturnScope t =null;

		Object name_tree=null;
		Object NUMBER68_tree=null;
		Object char_literal69_tree=null;
		Object char_literal70_tree=null;
		Object char_literal71_tree=null;
		Object char_literal72_tree=null;
		Object string_literal73_tree=null;
		Object string_literal74_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:473:43: ( NUMBER |name= ( VARIABLEID | ARRAYLENGTH ) |name= VARIABLEID '[' index= expr ']' | '(' t= expr ')' | 'false' | 'true' )
			int alt24=6;
			switch ( input.LA(1) ) {
			case NUMBER:
				{
				alt24=1;
				}
				break;
			case VARIABLEID:
				{
				int LA24_2 = input.LA(2);
				if ( (LA24_2==31) ) {
					alt24=3;
				}
				else if ( ((LA24_2 >= 11 && LA24_2 <= 12)||(LA24_2 >= 14 && LA24_2 <= 16)||(LA24_2 >= 18 && LA24_2 <= 19)||LA24_2==21||(LA24_2 >= 23 && LA24_2 <= 26)||(LA24_2 >= 28 && LA24_2 <= 30)||LA24_2==34||(LA24_2 >= 47 && LA24_2 <= 48)) ) {
					alt24=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ARRAYLENGTH:
				{
				alt24=2;
				}
				break;
			case 13:
				{
				alt24=4;
				}
				break;
			case 39:
				{
				alt24=5;
				}
				break;
			case 44:
				{
				alt24=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}
			switch (alt24) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:474:9: NUMBER
					{
					root_0 = (Object)adaptor.nil();


					NUMBER68=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_restrictedTerm1220); 
					NUMBER68_tree = (Object)adaptor.create(NUMBER68);
					adaptor.addChild(root_0, NUMBER68_tree);


					          retval.r = new ConstInt(new BigInteger((NUMBER68!=null?NUMBER68.getText():null)));
					        
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:477:11: name= ( VARIABLEID | ARRAYLENGTH )
					{
					root_0 = (Object)adaptor.nil();


					name=input.LT(1);
					if ( input.LA(1)==ARRAYLENGTH||input.LA(1)==VARIABLEID ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(name));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}

					          retval.r = getASTVariable((name!=null?name.getText():null));
					        
					}
					break;
				case 3 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:480:11: name= VARIABLEID '[' index= expr ']'
					{
					root_0 = (Object)adaptor.nil();


					name=(Token)match(input,VARIABLEID,FOLLOW_VARIABLEID_in_restrictedTerm1258); 
					name_tree = (Object)adaptor.create(name);
					adaptor.addChild(root_0, name_tree);

					char_literal69=(Token)match(input,31,FOLLOW_31_in_restrictedTerm1260); 
					char_literal69_tree = (Object)adaptor.create(char_literal69);
					adaptor.addChild(root_0, char_literal69_tree);

					pushFollow(FOLLOW_expr_in_restrictedTerm1264);
					index=expr();
					state._fsp--;

					adaptor.addChild(root_0, index.getTree());

					char_literal70=(Token)match(input,34,FOLLOW_34_in_restrictedTerm1266); 
					char_literal70_tree = (Object)adaptor.create(char_literal70);
					adaptor.addChild(root_0, char_literal70_tree);


					          if(existsArray((name!=null?name.getText():null))) {
						          if(getArrayType((name!=null?name.getText():null)) == 1) {
						            retval.r = new ArrayIntAccess((name!=null?name.getText():null), getArrayLength((name!=null?name.getText():null)), isArrayHigh((name!=null?name.getText():null)), (index!=null?((FaehrmannParser.expr_return)index).r:null));
						          }
						          else {
					              retval.r = new ArrayBoolAccess((name!=null?name.getText():null), getArrayLength((name!=null?name.getText():null)), isArrayHigh((name!=null?name.getText():null)), (index!=null?((FaehrmannParser.expr_return)index).r:null));
					            }
						        }
					        
					}
					break;
				case 4 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:490:11: '(' t= expr ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal71=(Token)match(input,13,FOLLOW_13_in_restrictedTerm1280); 
					char_literal71_tree = (Object)adaptor.create(char_literal71);
					adaptor.addChild(root_0, char_literal71_tree);

					pushFollow(FOLLOW_expr_in_restrictedTerm1284);
					t=expr();
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());

					char_literal72=(Token)match(input,14,FOLLOW_14_in_restrictedTerm1286); 
					char_literal72_tree = (Object)adaptor.create(char_literal72);
					adaptor.addChild(root_0, char_literal72_tree);


					          retval.r = (t!=null?((FaehrmannParser.expr_return)t).r:null);
					        
					}
					break;
				case 5 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:493:11: 'false'
					{
					root_0 = (Object)adaptor.nil();


					string_literal73=(Token)match(input,39,FOLLOW_39_in_restrictedTerm1300); 
					string_literal73_tree = (Object)adaptor.create(string_literal73);
					adaptor.addChild(root_0, string_literal73_tree);


					          retval.r = new ConstBool(new BigInteger("0"));
					        
					}
					break;
				case 6 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:496:11: 'true'
					{
					root_0 = (Object)adaptor.nil();


					string_literal74=(Token)match(input,44,FOLLOW_44_in_restrictedTerm1314); 
					string_literal74_tree = (Object)adaptor.create(string_literal74);
					adaptor.addChild(root_0, string_literal74_tree);


					          retval.r = new ConstBool(new BigInteger("1"));
					        
					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "restrictedTerm"


	public static class expr_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expr"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:501:1: expr returns [RestrictedTerm r] : (im1= implication ) ( '<->' (im2= expr ) )? ;
	public final FaehrmannParser.expr_return expr() throws RecognitionException {
		FaehrmannParser.expr_return retval = new FaehrmannParser.expr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal75=null;
		ParserRuleReturnScope im1 =null;
		ParserRuleReturnScope im2 =null;

		Object string_literal75_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:501:33: ( (im1= implication ) ( '<->' (im2= expr ) )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:502:9: (im1= implication ) ( '<->' (im2= expr ) )?
			{
			root_0 = (Object)adaptor.nil();


			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:502:9: (im1= implication )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:502:10: im1= implication
			{
			pushFollow(FOLLOW_implication_in_expr1340);
			im1=implication();
			state._fsp--;

			adaptor.addChild(root_0, im1.getTree());

			}

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:502:27: ( '<->' (im2= expr ) )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==25) ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:502:28: '<->' (im2= expr )
					{
					string_literal75=(Token)match(input,25,FOLLOW_25_in_expr1344); 
					string_literal75_tree = (Object)adaptor.create(string_literal75);
					adaptor.addChild(root_0, string_literal75_tree);

					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:502:34: (im2= expr )
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:502:35: im2= expr
					{
					pushFollow(FOLLOW_expr_in_expr1349);
					im2=expr();
					state._fsp--;

					adaptor.addChild(root_0, im2.getTree());

					}

					}
					break;

			}


			          if ((im2!=null?((FaehrmannParser.expr_return)im2).r:null) != null) {
			          	retval.r = new Equivalence((im1!=null?((FaehrmannParser.implication_return)im1).r:null), (im2!=null?((FaehrmannParser.expr_return)im2).r:null));
			          } else {
			          	retval.r = (im1!=null?((FaehrmannParser.implication_return)im1).r:null);
			          }
			         
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class implication_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "implication"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:511:1: implication returns [RestrictedTerm r] : co1= compare ( '->' co2= implication )? ;
	public final FaehrmannParser.implication_return implication() throws RecognitionException {
		FaehrmannParser.implication_return retval = new FaehrmannParser.implication_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal76=null;
		ParserRuleReturnScope co1 =null;
		ParserRuleReturnScope co2 =null;

		Object string_literal76_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:511:40: (co1= compare ( '->' co2= implication )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:512:9: co1= compare ( '->' co2= implication )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_compare_in_implication1377);
			co1=compare();
			state._fsp--;

			adaptor.addChild(root_0, co1.getTree());

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:512:21: ( '->' co2= implication )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==21) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:512:22: '->' co2= implication
					{
					string_literal76=(Token)match(input,21,FOLLOW_21_in_implication1380); 
					string_literal76_tree = (Object)adaptor.create(string_literal76);
					adaptor.addChild(root_0, string_literal76_tree);

					pushFollow(FOLLOW_implication_in_implication1384);
					co2=implication();
					state._fsp--;

					adaptor.addChild(root_0, co2.getTree());

					}
					break;

			}


			          if ((co2!=null?((FaehrmannParser.implication_return)co2).r:null) != null) {
			          	retval.r = new Implication((co1!=null?((FaehrmannParser.compare_return)co1).r:null), (co2!=null?((FaehrmannParser.implication_return)co2).r:null));
			          } else {
			          	retval.r = (co1!=null?((FaehrmannParser.compare_return)co1).r:null);
			          }
			         
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "implication"


	public static class compare_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "compare"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:521:1: compare returns [RestrictedTerm r] : or1= or ( '==' or2= compare | '<' or2= compare | '>' or2= compare | '<=' or2= compare | '>=' or2= compare | '!=' or2= compare )? ;
	public final FaehrmannParser.compare_return compare() throws RecognitionException {
		FaehrmannParser.compare_return retval = new FaehrmannParser.compare_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal77=null;
		Token char_literal78=null;
		Token char_literal79=null;
		Token string_literal80=null;
		Token string_literal81=null;
		Token string_literal82=null;
		ParserRuleReturnScope or1 =null;
		ParserRuleReturnScope or2 =null;

		Object string_literal77_tree=null;
		Object char_literal78_tree=null;
		Object char_literal79_tree=null;
		Object string_literal80_tree=null;
		Object string_literal81_tree=null;
		Object string_literal82_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:521:36: (or1= or ( '==' or2= compare | '<' or2= compare | '>' or2= compare | '<=' or2= compare | '>=' or2= compare | '!=' or2= compare )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:522:9: or1= or ( '==' or2= compare | '<' or2= compare | '>' or2= compare | '<=' or2= compare | '>=' or2= compare | '!=' or2= compare )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_or_in_compare1411);
			or1=or();
			state._fsp--;

			adaptor.addChild(root_0, or1.getTree());

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:522:15: ( '==' or2= compare | '<' or2= compare | '>' or2= compare | '<=' or2= compare | '>=' or2= compare | '!=' or2= compare )?
			int alt27=7;
			switch ( input.LA(1) ) {
				case 28:
					{
					alt27=1;
					}
					break;
				case 24:
					{
					alt27=2;
					}
					break;
				case 29:
					{
					alt27=3;
					}
					break;
				case 26:
					{
					alt27=4;
					}
					break;
				case 30:
					{
					alt27=5;
					}
					break;
				case 11:
					{
					alt27=6;
					}
					break;
			}
			switch (alt27) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:522:16: '==' or2= compare
					{
					string_literal77=(Token)match(input,28,FOLLOW_28_in_compare1413); 
					string_literal77_tree = (Object)adaptor.create(string_literal77);
					adaptor.addChild(root_0, string_literal77_tree);

					pushFollow(FOLLOW_compare_in_compare1417);
					or2=compare();
					state._fsp--;

					adaptor.addChild(root_0, or2.getTree());


					          retval.r = new Equal((or1!=null?((FaehrmannParser.or_return)or1).r:null), (or2!=null?((FaehrmannParser.compare_return)or2).r:null));
					        
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:525:11: '<' or2= compare
					{
					char_literal78=(Token)match(input,24,FOLLOW_24_in_compare1431); 
					char_literal78_tree = (Object)adaptor.create(char_literal78);
					adaptor.addChild(root_0, char_literal78_tree);

					pushFollow(FOLLOW_compare_in_compare1435);
					or2=compare();
					state._fsp--;

					adaptor.addChild(root_0, or2.getTree());


					          retval.r = new SmallerThan((or1!=null?((FaehrmannParser.or_return)or1).r:null), (or2!=null?((FaehrmannParser.compare_return)or2).r:null));
					        
					}
					break;
				case 3 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:528:11: '>' or2= compare
					{
					char_literal79=(Token)match(input,29,FOLLOW_29_in_compare1449); 
					char_literal79_tree = (Object)adaptor.create(char_literal79);
					adaptor.addChild(root_0, char_literal79_tree);

					pushFollow(FOLLOW_compare_in_compare1453);
					or2=compare();
					state._fsp--;

					adaptor.addChild(root_0, or2.getTree());


					          retval.r = new GreaterThan((or1!=null?((FaehrmannParser.or_return)or1).r:null), (or2!=null?((FaehrmannParser.compare_return)or2).r:null));
					        
					}
					break;
				case 4 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:531:11: '<=' or2= compare
					{
					string_literal80=(Token)match(input,26,FOLLOW_26_in_compare1467); 
					string_literal80_tree = (Object)adaptor.create(string_literal80);
					adaptor.addChild(root_0, string_literal80_tree);

					pushFollow(FOLLOW_compare_in_compare1471);
					or2=compare();
					state._fsp--;

					adaptor.addChild(root_0, or2.getTree());


					          retval.r = new SmallerEqual((or1!=null?((FaehrmannParser.or_return)or1).r:null), (or2!=null?((FaehrmannParser.compare_return)or2).r:null));
					        
					}
					break;
				case 5 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:534:11: '>=' or2= compare
					{
					string_literal81=(Token)match(input,30,FOLLOW_30_in_compare1485); 
					string_literal81_tree = (Object)adaptor.create(string_literal81);
					adaptor.addChild(root_0, string_literal81_tree);

					pushFollow(FOLLOW_compare_in_compare1489);
					or2=compare();
					state._fsp--;

					adaptor.addChild(root_0, or2.getTree());


					          retval.r = new GreaterEqual((or1!=null?((FaehrmannParser.or_return)or1).r:null), (or2!=null?((FaehrmannParser.compare_return)or2).r:null));
					        
					}
					break;
				case 6 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:537:11: '!=' or2= compare
					{
					string_literal82=(Token)match(input,11,FOLLOW_11_in_compare1503); 
					string_literal82_tree = (Object)adaptor.create(string_literal82);
					adaptor.addChild(root_0, string_literal82_tree);

					pushFollow(FOLLOW_compare_in_compare1507);
					or2=compare();
					state._fsp--;

					adaptor.addChild(root_0, or2.getTree());


					          retval.r = new Unequal((or1!=null?((FaehrmannParser.or_return)or1).r:null), (or2!=null?((FaehrmannParser.compare_return)or2).r:null));
					        
					}
					break;

			}


			          if ((or2!=null?((FaehrmannParser.compare_return)or2).r:null) == null) {
			            retval.r = (or1!=null?((FaehrmannParser.or_return)or1).r:null);
			            }
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "compare"


	public static class or_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "or"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:547:1: or returns [RestrictedTerm r] : a1= and ( '|' a2= or )? ;
	public final FaehrmannParser.or_return or() throws RecognitionException {
		FaehrmannParser.or_return retval = new FaehrmannParser.or_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal83=null;
		ParserRuleReturnScope a1 =null;
		ParserRuleReturnScope a2 =null;

		Object char_literal83_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:547:30: (a1= and ( '|' a2= or )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:548:9: a1= and ( '|' a2= or )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_and_in_or1544);
			a1=and();
			state._fsp--;

			adaptor.addChild(root_0, a1.getTree());

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:548:16: ( '|' a2= or )?
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==47) ) {
				alt28=1;
			}
			switch (alt28) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:548:17: '|' a2= or
					{
					char_literal83=(Token)match(input,47,FOLLOW_47_in_or1547); 
					char_literal83_tree = (Object)adaptor.create(char_literal83);
					adaptor.addChild(root_0, char_literal83_tree);

					pushFollow(FOLLOW_or_in_or1551);
					a2=or();
					state._fsp--;

					adaptor.addChild(root_0, a2.getTree());

					}
					break;

			}


			          if ((a2!=null?((FaehrmannParser.or_return)a2).r:null) != null) {
			         	 retval.r = new Or((a1!=null?((FaehrmannParser.and_return)a1).r:null), (a2!=null?((FaehrmannParser.or_return)a2).r:null));
			         	}else {
			         	 retval.r = (a1!=null?((FaehrmannParser.and_return)a1).r:null);
			          }
			        
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "or"


	public static class and_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "and"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:557:1: and returns [RestrictedTerm r] : as1= addsub ( '&' as2= and )? ;
	public final FaehrmannParser.and_return and() throws RecognitionException {
		FaehrmannParser.and_return retval = new FaehrmannParser.and_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal84=null;
		ParserRuleReturnScope as1 =null;
		ParserRuleReturnScope as2 =null;

		Object char_literal84_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:557:32: (as1= addsub ( '&' as2= and )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:558:9: as1= addsub ( '&' as2= and )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_addsub_in_and1578);
			as1=addsub();
			state._fsp--;

			adaptor.addChild(root_0, as1.getTree());

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:558:20: ( '&' as2= and )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==12) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:558:21: '&' as2= and
					{
					char_literal84=(Token)match(input,12,FOLLOW_12_in_and1581); 
					char_literal84_tree = (Object)adaptor.create(char_literal84);
					adaptor.addChild(root_0, char_literal84_tree);

					pushFollow(FOLLOW_and_in_and1585);
					as2=and();
					state._fsp--;

					adaptor.addChild(root_0, as2.getTree());

					}
					break;

			}


			        if ((as2!=null?((FaehrmannParser.and_return)as2).r:null) != null) {
			        	retval.r = new And((as1!=null?((FaehrmannParser.addsub_return)as1).r:null), (as2!=null?((FaehrmannParser.and_return)as2).r:null));
			        } else {
			        	retval.r = (as1!=null?((FaehrmannParser.addsub_return)as1).r:null);
			        }
			       
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "and"


	public static class addsub_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "addsub"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:567:1: addsub returns [RestrictedTerm r] : mult1= mult ( '+' mult2= addsub | '-' mult2= addsub )? ;
	public final FaehrmannParser.addsub_return addsub() throws RecognitionException {
		FaehrmannParser.addsub_return retval = new FaehrmannParser.addsub_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal85=null;
		Token char_literal86=null;
		ParserRuleReturnScope mult1 =null;
		ParserRuleReturnScope mult2 =null;

		Object char_literal85_tree=null;
		Object char_literal86_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:567:35: (mult1= mult ( '+' mult2= addsub | '-' mult2= addsub )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:568:7: mult1= mult ( '+' mult2= addsub | '-' mult2= addsub )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_mult_in_addsub1610);
			mult1=mult();
			state._fsp--;

			adaptor.addChild(root_0, mult1.getTree());

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:568:17: ( '+' mult2= addsub | '-' mult2= addsub )?
			int alt30=3;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==16) ) {
				alt30=1;
			}
			else if ( (LA30_0==19) ) {
				alt30=2;
			}
			switch (alt30) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:568:18: '+' mult2= addsub
					{
					char_literal85=(Token)match(input,16,FOLLOW_16_in_addsub1612); 
					char_literal85_tree = (Object)adaptor.create(char_literal85);
					adaptor.addChild(root_0, char_literal85_tree);

					pushFollow(FOLLOW_addsub_in_addsub1616);
					mult2=addsub();
					state._fsp--;

					adaptor.addChild(root_0, mult2.getTree());


					        retval.r = new Addition((mult1!=null?((FaehrmannParser.mult_return)mult1).r:null), (mult2!=null?((FaehrmannParser.addsub_return)mult2).r:null));
					      
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:571:9: '-' mult2= addsub
					{
					char_literal86=(Token)match(input,19,FOLLOW_19_in_addsub1628); 
					char_literal86_tree = (Object)adaptor.create(char_literal86);
					adaptor.addChild(root_0, char_literal86_tree);

					pushFollow(FOLLOW_addsub_in_addsub1632);
					mult2=addsub();
					state._fsp--;

					adaptor.addChild(root_0, mult2.getTree());


					        retval.r = new Subtraction((mult1!=null?((FaehrmannParser.mult_return)mult1).r:null), (mult2!=null?((FaehrmannParser.addsub_return)mult2).r:null));
					      
					}
					break;

			}


				      if ((mult2!=null?((FaehrmannParser.addsub_return)mult2).r:null) == null) {
				        retval.r = (mult1!=null?((FaehrmannParser.mult_return)mult1).r:null);
				      }
				    
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "addsub"


	public static class mult_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "mult"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:581:1: mult returns [RestrictedTerm r] : n1= negation ( '*' n2= mult )? ;
	public final FaehrmannParser.mult_return mult() throws RecognitionException {
		FaehrmannParser.mult_return retval = new FaehrmannParser.mult_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal87=null;
		ParserRuleReturnScope n1 =null;
		ParserRuleReturnScope n2 =null;

		Object char_literal87_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:581:33: (n1= negation ( '*' n2= mult )? )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:582:7: n1= negation ( '*' n2= mult )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_negation_in_mult1665);
			n1=negation();
			state._fsp--;

			adaptor.addChild(root_0, n1.getTree());

			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:582:19: ( '*' n2= mult )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==15) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:582:20: '*' n2= mult
					{
					char_literal87=(Token)match(input,15,FOLLOW_15_in_mult1668); 
					char_literal87_tree = (Object)adaptor.create(char_literal87);
					adaptor.addChild(root_0, char_literal87_tree);

					pushFollow(FOLLOW_mult_in_mult1672);
					n2=mult();
					state._fsp--;

					adaptor.addChild(root_0, n2.getTree());

					}
					break;

			}


			        if ((n2!=null?((FaehrmannParser.mult_return)n2).r:null) != null) {
			          retval.r = new Multiplication((n1!=null?((FaehrmannParser.negation_return)n1).r:null), (n2!=null?((FaehrmannParser.mult_return)n2).r:null));
			        } else {
			          retval.r = (n1!=null?((FaehrmannParser.negation_return)n1).r:null);
			        }
			      
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "mult"


	public static class negation_return extends ParserRuleReturnScope {
		public RestrictedTerm r;
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "negation"
	// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:591:1: negation returns [RestrictedTerm r] : ( ( '!' neg= negation | '-' neg= negation ) | (t= restrictedTerm ) );
	public final FaehrmannParser.negation_return negation() throws RecognitionException {
		FaehrmannParser.negation_return retval = new FaehrmannParser.negation_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal88=null;
		Token char_literal89=null;
		ParserRuleReturnScope neg =null;
		ParserRuleReturnScope t =null;

		Object char_literal88_tree=null;
		Object char_literal89_tree=null;

		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:591:37: ( ( '!' neg= negation | '-' neg= negation ) | (t= restrictedTerm ) )
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==10||LA33_0==19) ) {
				alt33=1;
			}
			else if ( (LA33_0==ARRAYLENGTH||(LA33_0 >= NUMBER && LA33_0 <= VARIABLEID)||LA33_0==13||LA33_0==39||LA33_0==44) ) {
				alt33=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}

			switch (alt33) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:592:7: ( '!' neg= negation | '-' neg= negation )
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:592:7: ( '!' neg= negation | '-' neg= negation )
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==10) ) {
						alt32=1;
					}
					else if ( (LA32_0==19) ) {
						alt32=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 32, 0, input);
						throw nvae;
					}

					switch (alt32) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:592:8: '!' neg= negation
							{
							char_literal88=(Token)match(input,10,FOLLOW_10_in_negation1696); 
							char_literal88_tree = (Object)adaptor.create(char_literal88);
							adaptor.addChild(root_0, char_literal88_tree);

							pushFollow(FOLLOW_negation_in_negation1700);
							neg=negation();
							state._fsp--;

							adaptor.addChild(root_0, neg.getTree());


							        retval.r = new Not((neg!=null?((FaehrmannParser.negation_return)neg).r:null));
							      
							}
							break;
						case 2 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:595:9: '-' neg= negation
							{
							char_literal89=(Token)match(input,19,FOLLOW_19_in_negation1712); 
							char_literal89_tree = (Object)adaptor.create(char_literal89);
							adaptor.addChild(root_0, char_literal89_tree);

							pushFollow(FOLLOW_negation_in_negation1716);
							neg=negation();
							state._fsp--;

							adaptor.addChild(root_0, neg.getTree());


							        retval.r = new Negation((neg!=null?((FaehrmannParser.negation_return)neg).r:null));
							      
							}
							break;

					}

					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:598:9: (t= restrictedTerm )
					{
					root_0 = (Object)adaptor.nil();


					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:598:9: (t= restrictedTerm )
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:598:10: t= restrictedTerm
					{
					pushFollow(FOLLOW_restrictedTerm_in_negation1732);
					t=restrictedTerm();
					state._fsp--;

					adaptor.addChild(root_0, t.getTree());


					        retval.r = (t!=null?((FaehrmannParser.restrictedTerm_return)t).r:null);
					      
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "negation"

	// Delegated rules



	public static final BitSet FOLLOW_methoddef_in_program76 = new BitSet(new long[]{0x00002F3800000100L});
	public static final BitSet FOLLOW_statement_in_program92 = new BitSet(new long[]{0x00002F3800000100L});
	public static final BitSet FOLLOW_EOF_in_program117 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_40_in_methoddef136 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_methoddef140 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_methoddef142 = new BitSet(new long[]{0x00012E3800000100L});
	public static final BitSet FOLLOW_statementblock_in_methoddef146 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_methoddef148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assumption_in_statement174 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_assertion_in_statement190 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_whileBlock_in_statement206 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_ifBlock_in_statement222 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_assignment_in_statement238 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_methodcall_in_statement254 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_statement268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLEID_in_methodcall293 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_statementblock321 = new BitSet(new long[]{0x00002E3800000102L});
	public static final BitSet FOLLOW_36_in_assumption348 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_assumption350 = new BitSet(new long[]{0x0000108300082590L});
	public static final BitSet FOLLOW_term_in_assumption354 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assumption356 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_assertion379 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_assertion381 = new BitSet(new long[]{0x0000108300082590L});
	public static final BitSet FOLLOW_term_in_assertion385 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_assertion387 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_45_in_whileBlock410 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_whileBlock412 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_whileBlock416 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_whileBlock418 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_whileBlock420 = new BitSet(new long[]{0x00012E3800000100L});
	public static final BitSet FOLLOW_statementblock_in_whileBlock424 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_whileBlock426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_ifBlock449 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_ifBlock451 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_ifBlock455 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_ifBlock457 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_ifBlock459 = new BitSet(new long[]{0x00012E3800000100L});
	public static final BitSet FOLLOW_statementblock_in_ifBlock463 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_ifBlock465 = new BitSet(new long[]{0x0000004000000002L});
	public static final BitSet FOLLOW_38_in_ifBlock468 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_ifBlock470 = new BitSet(new long[]{0x00012E3800000100L});
	public static final BitSet FOLLOW_statementblock_in_ifBlock474 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_ifBlock476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_definition504 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_definition510 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_definition514 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_41_in_definition529 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_37_in_definition535 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_definition539 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_definition552 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_definition557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arraydefinition_in_definition573 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_declaration598 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_declaration604 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_declaration608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_declaration632 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_37_in_declaration638 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_declaration642 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arraydeclaration_in_declaration669 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_arraydefinition696 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_arraydefinition701 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_arraydefinition705 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_arraydefinition707 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_NUMBER_in_arraydefinition711 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_arraydefinition713 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_arraydefinition715 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_arraydefinition717 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_arraydefinition720 = new BitSet(new long[]{0x0001000000040000L});
	public static final BitSet FOLLOW_18_in_arraydefinition726 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_arraydefinition730 = new BitSet(new long[]{0x0001000000040000L});
	public static final BitSet FOLLOW_48_in_arraydefinition736 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_arraydefinition764 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_37_in_arraydefinition769 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_arraydefinition773 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_arraydefinition775 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_NUMBER_in_arraydefinition779 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_arraydefinition781 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_arraydefinition783 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_arraydefinition785 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_arraydefinition788 = new BitSet(new long[]{0x0001000000040000L});
	public static final BitSet FOLLOW_18_in_arraydefinition794 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_arraydefinition798 = new BitSet(new long[]{0x0001000000040000L});
	public static final BitSet FOLLOW_48_in_arraydefinition804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_arraydeclaration848 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_arraydeclaration854 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_arraydeclaration858 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_arraydeclaration860 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_NUMBER_in_arraydeclaration864 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_arraydeclaration866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_arraydeclaration882 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_37_in_arraydeclaration888 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_arraydeclaration892 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_arraydeclaration894 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_NUMBER_in_arraydeclaration898 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_arraydeclaration900 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLEID_in_assignment968 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_assignment970 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_assignment974 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLEID_in_assignment990 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_assignment992 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_assignment996 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_assignment998 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_assignment1000 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_assignment1004 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_definition_in_assignment1020 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declaration_in_assignment1036 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLEID_in_assignment1052 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_assignment1054 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLEID_in_assignment1070 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_assignment1072 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quantTerm_in_term1109 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_term1125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_quantTerm1150 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_quantTerm1154 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_quantTerm1156 = new BitSet(new long[]{0x0000108300082590L});
	public static final BitSet FOLLOW_term_in_quantTerm1160 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_quantTerm1186 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_VARIABLEID_in_quantTerm1190 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_quantTerm1192 = new BitSet(new long[]{0x0000108300082590L});
	public static final BitSet FOLLOW_term_in_quantTerm1196 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_restrictedTerm1220 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_restrictedTerm1236 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLEID_in_restrictedTerm1258 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_restrictedTerm1260 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_restrictedTerm1264 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_restrictedTerm1266 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_restrictedTerm1280 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_restrictedTerm1284 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_restrictedTerm1286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_39_in_restrictedTerm1300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_44_in_restrictedTerm1314 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_implication_in_expr1340 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_25_in_expr1344 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_expr_in_expr1349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_compare_in_implication1377 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_21_in_implication1380 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_implication_in_implication1384 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_in_compare1411 = new BitSet(new long[]{0x0000000075000802L});
	public static final BitSet FOLLOW_28_in_compare1413 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_compare_in_compare1417 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_compare1431 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_compare_in_compare1435 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_compare1449 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_compare_in_compare1453 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_compare1467 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_compare_in_compare1471 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_compare1485 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_compare_in_compare1489 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_compare1503 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_compare_in_compare1507 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_in_or1544 = new BitSet(new long[]{0x0000800000000002L});
	public static final BitSet FOLLOW_47_in_or1547 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_or_in_or1551 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_addsub_in_and1578 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_12_in_and1581 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_and_in_and1585 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_mult_in_addsub1610 = new BitSet(new long[]{0x0000000000090002L});
	public static final BitSet FOLLOW_16_in_addsub1612 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_addsub_in_addsub1616 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_addsub1628 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_addsub_in_addsub1632 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_negation_in_mult1665 = new BitSet(new long[]{0x0000000000008002L});
	public static final BitSet FOLLOW_15_in_mult1668 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_mult_in_mult1672 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_negation1696 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_negation_in_negation1700 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_negation1712 = new BitSet(new long[]{0x0000108000082590L});
	public static final BitSet FOLLOW_negation_in_negation1716 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_restrictedTerm_in_negation1732 = new BitSet(new long[]{0x0000000000000002L});
}
