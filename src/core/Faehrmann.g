
grammar Faehrmann;

options {
  output   = AST;
  language = Java;
}

@header {
package core;
import ast.*;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.LinkedList;
}

@lexer::header {
package core;
import ast.*;
import ast.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
}

@members {
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

//List of all declarated functions
private ArrayList<MethodDefinition> functions = new ArrayList<MethodDefinition>();

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
	  errors.add("Error: Variable \"" + name + "\" in line \"" + line() + "\" not found");
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
private ArrayDefinition declarearray(String name, String size, boolean type, boolean high) {
ArrayList<ArrayAssignment> temp = new ArrayList();
ArrayDefinition arraydec = null;
BigInteger length = new BigInteger(size);
  if (type) {
    for (BigInteger i = new BigInteger("0"); i.compareTo(length) < 0; i = i.add(BigInteger.ONE)) {
      ArrayIntAccess arrayaccess = new ArrayIntAccess(name,length, high, new ConstInt(i));
      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, null);  
      temp.add(arrayassign);
      intArrays.add(arrayaccess);
    }
    arraydec = new ArrayDefinition(line(), temp, ArrayDefinition.INT);
  } else {
    for (BigInteger i = new BigInteger("0"); i.compareTo(length) < 0; i = i.add(BigInteger.ONE)) {
      ArrayBoolAccess arrayaccess = new ArrayBoolAccess(name, length, high, new ConstInt(i));
      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, null);  
      temp.add(arrayassign);
      boolArrays.add(arrayaccess);
    }
    arraydec = new ArrayDefinition(line(), temp, ArrayDefinition.BOOL);
  }
  if (length.compareTo(BigInteger.ONE) < 0) {
    errors.add("Error: Arraylength is smaller than 1!");
  }
  return arraydec;
}

private ArrayDefinition definearray(String name, String size, boolean type, ArrayList<RestrictedTerm> arrayvalues, boolean high) {
  ArrayList<ArrayAssignment> temp = new ArrayList();
  ArrayDefinition arraydef = null;
  BigInteger length = new BigInteger(size);
  if (type) {
    for (BigInteger i = new BigInteger("0"); i.compareTo(length) < 0; i = i.add(BigInteger.ONE)) {
      ArrayIntAccess arrayaccess = new ArrayIntAccess(name, length, high, new ConstInt(i));
      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, arrayvalues.get(i.intValue()));  
      temp.add(arrayassign);
      intArrays.add(arrayaccess);
    }
    arraydef = new ArrayDefinition(line(), temp, ArrayDefinition.INT);
  } else {
    for (BigInteger i = new BigInteger("0"); i.compareTo(length) < 0; i = i.add(BigInteger.ONE)) {
      ArrayBoolAccess arrayaccess = new ArrayBoolAccess(name, length, high, new ConstInt(i));
      ArrayAssignment arrayassign = new ArrayAssignment(line(),  arrayaccess, arrayvalues.get(i.intValue()));  
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
    errors.add("Error: Array \"" + name + "\" in line \"" + line() + "\" not found");
    return false;
}

private void checkNameArray(String name) {
    for (int i = 0; i < variables.size(); i++) {
      if (variables.get(i).getName().equals(name)) {
        errors.add("Error: Array \"" + name + "\" in line \"" + line() + "\" has the same name as a variable");
        break;
      }
    }
    for (int i = 0; i < intArrays.size(); i++) {
      if (intArrays.get(i).getName().equals(name)) {
        errors.add("Error: Array \"" + name + "\" in line \"" + line() + "\" has the same name as a array");
        break;
      }
    }
    for (int i = 0; i < boolArrays.size(); i++) {
      if (boolArrays.get(i).getName().equals(name)) {
        errors.add("Error: Array \"" + name + "\" in line \"" + line() + "\" has the same name as a array");
        break;
      }
    }
}

private void checkNameVariable(String name) {   
    for (int i = 0; i < intArrays.size(); i++) {
      if (intArrays.get(i).getName().equals(name)) {
        errors.add("Error: Variable \"" + name + "\" in line \"" + line() + "\" has the same name as a array");
        break;
      }
    }
    for (int i = 0; i < boolArrays.size(); i++) {
      if (boolArrays.get(i).getName().equals(name)) {
        errors.add("Error: Variable \"" + name + "\" in line \"" + line() + "\" has the same name as a array");
        break;
      }
    }
}

// returns the actual line in editor
int line() {
	return input.LT(1).getLine();
}

}

program returns [Program prog] :  (
        ( meth=methoddef { list.add($meth.r);}
        | stat=statement { list.add($stat.r);})*)
        {
          $prog = new Program(list);
        }
        EOF;

methoddef returns [MethodCall r] :
	      'func' name=VARIABLEID '{' block=statementblock '}' {
	       if (getFunc($name.text) == null){
	        MethodDefinition meth = new MethodDefinition($name.text, $block.r); 
	        functions.add(meth); $r = new MethodCall(line(), meth);
	        }
	        else { errors.add("Error: Function " + $name.text + " in line " + line() + " declarated twice");}
          
	       }
;

statement returns [Statement r] :
        (var1=assumption {
          $r = $var1.r;
        }
        | var2=assertion {
          $r = $var2.r;
        }
        | var3=whileBlock {
          $r = $var3.r;
        }
        | var4=ifBlock {
          $r = $var4.r;
        }
        | var5=assignment {
          $r = $var5.r;
        }
        | var6=methodcall 
        {
          $r = $var6.r;
        }) ';'
  ;

methodcall returns [Statement r] :
        name=VARIABLEID {
          if(getFunc($name.text) != null){
          $r = new MethodCall(line(), getFunc($name.text));
          }
          else{
              errors.add("Error: Function \"" + name + "\" in line \"" + line() + "\" not declared");
          }
        }
;

statementblock returns [StatementBlock r] : {
          ArrayList<Statement> bl = new ArrayList<Statement>();
        }
        (stat=statement {bl.add($stat.r);})* {
          $r = new StatementBlock(bl);
        }
;

assumption returns [Statement r] :
        'assume' '(' t=term ')' {
          $r = new Assumption(line(), $t.r);
        }
;

assertion returns [Statement r] :
        'assert' '(' t=term ')' {
          $r = new Assertion(line(), $t.r);
        }
;

whileBlock returns [Statement r] :
        'while' '(' t=expr ')' '{' stateBlock=statementblock '}' {
          $r = new WhileBlock(line(), $t.r, $stateBlock.r);
        }
;

ifBlock returns [Statement r] :
        'if' '(' t=expr ')' '{' stateIf=statementblock '}' ('else' '{' stateElse=statementblock '}')? {
          if (stateElse != null) {
          	$r = new IfBlock(line(), $t.r, $stateIf.r, $stateElse.r);
          } else {
          	$r = new IfBlock(line(), $t.r, $stateIf.r, noElse);
          }
        }
;

definition returns [Statement r] : {boolean high = false;}(
        ('high' {high = true;})? 'int' name=VARIABLEID {
          this.checkNameVariable($name.text);
          VarInt intvar = new VarInt($name.text, high);
          variables.add(intvar);                       
        }
        | ('high' {high = true;})? 'bool' name=VARIABLEID {
         this.checkNameVariable($name.text);
         VarBool boolvar = new VarBool($name.text, high);
         variables.add(boolvar);
        })
        '=' (t=expr) {
	       $r = new Definition(line(), getASTVariable($name.text), $t.r);
	      }
	      | array=arraydefinition {
	        $r = $array.r;
	      }
;

declaration returns [Statement r] : {boolean high = false;}(
       ('high' {high = true;})? 'int' name=VARIABLEID 
        {
           this.checkNameVariable($name.text);
	         VarInt intvar = new VarInt($name.text, high);
	         variables.add(intvar);
        }
        | ('high' {high = true;})? 'bool' name=VARIABLEID 
          {
           this.checkNameVariable($name.text);
	         VarBool boolvar = new VarBool($name.text, high);
	         variables.add(boolvar);
	        }) {
			   $r = new Definition(line(), getASTVariable($name.text), null);
			  }
			  | array=arraydeclaration {
			    $r = $array.r;
			  }
;
  
arraydefinition returns [Statement r]: {boolean high = false; boolean type = false; ArrayList<RestrictedTerm> arrayvalues = new ArrayList<RestrictedTerm>();}
         ('high'{high = true;})? ('int' {type = true;} | 'bool') name=VARIABLEID '[' length=NUMBER ']' '=' '{'value=expr {arrayvalues.add($value.r);}  (',' value=expr {arrayvalues.add($value.r);})* '}' 
            {        
                  if(Integer.parseInt($length.text) == arrayvalues.size()) {
                      this.checkNameArray($name.text);
                      VarInt lengthVar = new VarInt($name.text + ".length", high);
                      variables.add(lengthVar);
                      list.add(new Assignment(line(), lengthVar, new ConstInt(new BigInteger($length.text))));
                      $r = definearray($name.text, $length.text, type, arrayvalues, high);                                                    
                  }
                  else{ errors.add("Error: Arraylength not valid, expected length: " + $length.text + "  , actual length: " + arrayvalues.size());}
                  arrayvalues.clear();
           }
        ;
arraydeclaration returns [Statement r]: {boolean high = false; boolean type = false;}
         ('high' {high = true;})? ('int' {type = true;}|'bool') name=VARIABLEID '[' length=NUMBER ']' { 
            this.checkNameArray($name.text);
            VarInt lengthVar = new VarInt($name.text + ".length", high);
            variables.add(lengthVar);
            list.add(new Assignment(line(), lengthVar, new ConstInt(new BigInteger($length.text))));                                                                         
            $r = declarearray($name.text, $length.text, type, high);                
         }                                      
 ;


assignment returns [Statement r] :
        name=VARIABLEID '=' t=expr {
           $r = new Assignment(line(), getASTVariable($name.text), $t.r);
        }
        | name=VARIABLEID '[' ex=expr ']' '=' t=expr {
           if(existsArray($name.text)) {
              if(getArrayType($name.text) == 1) {
                  ArrayIntAccess temp = new ArrayIntAccess($name.text, getArrayLength($name.text), isArrayHigh($name.text), $ex.r);
                  $r = new ArrayAssignment(line(), temp, $t.r);
              } else {
                  ArrayBoolAccess temp = new ArrayBoolAccess($name.text, getArrayLength($name.text), isArrayHigh($name.text), $ex.r);
                  $r = new ArrayAssignment(line(), temp, $t.r);
              }
           }
        }
        | def=definition {
           $r = $def.r;
        }
        | dec=declaration {
           $r = $dec.r;
        }
        | name=VARIABLEID '++' {
           $r = new Assignment(line(), getASTVariable($name.text), new Addition(
           getASTVariable($name.text), new ConstInt(new BigInteger("1"))));
        }
        | name=VARIABLEID '--' {
           $r = new Assignment(line(), getASTVariable($name.text), new Subtraction(
           getASTVariable($name.text), new ConstInt(new BigInteger("1"))));
        } 
;

term returns [Term r] :
        t1=quantTerm {
           $r = $t1.r;
        }
        | t2=expr {
           $r = $t2.r;
        }
;

quantTerm returns [QuantifierTerm r] : (
        '[A]' name=VARIABLEID ':' t=term {
           $r = new UniversalQuantifier(getASTVariable($name.text), $t.r);
        }
        | '[E]' name=VARIABLEID ':' t=term {
           $r = new ExistentialQuantifier(getASTVariable($name.text), $t.r);
        })
;

restrictedTerm returns [RestrictedTerm r] :
        NUMBER {
          $r = new ConstInt(new BigInteger($NUMBER.text));
        }
        | name=(VARIABLEID | ARRAYLENGTH) {
          $r = getASTVariable($name.text);
        }
        | name=VARIABLEID '[' index=expr ']' {
          if(existsArray($name.text)) {
	          if(getArrayType($name.text) == 1) {
	            $r = new ArrayIntAccess($name.text, getArrayLength($name.text), isArrayHigh($name.text), $index.r);
	          }
	          else {
              $r = new ArrayBoolAccess($name.text, getArrayLength($name.text), isArrayHigh($name.text), $index.r);
            }
	        }
        }
        | '(' t=expr ')' {
          $r = $t.r;
        }
        | 'false' {
          $r = new ConstBool(new BigInteger("0"));
        }
        | 'true' {
          $r = new ConstBool(new BigInteger("1"));
        }
;

expr returns [RestrictedTerm r] :
        (im1=implication) ('<->' (im2=expr))? {
          if ($im2.r != null) {
          	$r = new Equivalence($im1.r, $im2.r);
          } else {
          	$r = $im1.r;
          }
         }
;

implication returns [RestrictedTerm r] :
        co1=compare ('->' co2=implication)? {
          if ($co2.r != null) {
          	$r = new Implication($co1.r, $co2.r);
          } else {
          	$r = $co1.r;
          }
         }
;

compare returns [RestrictedTerm r] :
        or1=or('==' or2=compare {
          $r = new Equal($or1.r, $or2.r);
        }
        | '<' or2=compare {
          $r = new SmallerThan($or1.r, $or2.r);
        }
        | '>' or2=compare {
          $r = new GreaterThan($or1.r, $or2.r);
        }
        | '<=' or2=compare {
          $r = new SmallerEqual($or1.r, $or2.r);
        }
        | '>=' or2=compare {
          $r = new GreaterEqual($or1.r, $or2.r);
        }
        | '!=' or2=compare {
          $r = new Unequal($or1.r, $or2.r);
        })? 
        {
          if ($or2.r == null) {
            $r = $or1.r;
            }
        }
;

or returns [RestrictedTerm r]:
        a1=and ('|' a2=or)? {
          if ($a2.r != null) {
         	 $r = new Or($a1.r, $a2.r);
         	}else {
         	 $r = $a1.r;
          }
        }
;

and returns [RestrictedTerm r] :
        as1=addsub ('&' as2=and)? {
        if ($as2.r != null) {
        	$r = new And($as1.r, $as2.r);
        } else {
        	$r = $as1.r;
        }
       }
;

addsub returns [RestrictedTerm r] :
      mult1=mult('+' mult2=addsub {
        $r = new Addition($mult1.r, $mult2.r);
      }
      | '-' mult2=addsub {
        $r = new Subtraction($mult1.r, $mult2.r);
      })?
      {
	      if ($mult2.r == null) {
	        $r = $mult1.r;
	      }
	    }
;

mult returns [RestrictedTerm r] :
      n1=negation ('*' n2=mult)? {
        if ($n2.r != null) {
          $r = new Multiplication($n1.r, $n2.r);
        } else {
          $r = $n1.r;
        }
      }
;

negation returns [RestrictedTerm r] :
      ('!' neg=negation {
        $r = new Not($neg.r);
      }
      | '-' neg=negation {
        $r = new Negation($neg.r);
      })
      | (t=restrictedTerm {
        $r = $t.r;
      })
;
  
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

VARIABLEID : ('a'..'z' | 'A'..'Z')('_' | 'a'..'z' | 'A'..'Z' | '0'..'9')*;

ARRAYLENGTH : VARIABLEID '.length'; 

NUMBER : (DIGIT)+;

fragment DIGIT : '0'..'9';

WHITESPACE : ('\t' | ' ' | '\r' | '\n' | '\u000C')+ {$channel = HIDDEN;};

COMMENT : '//' ~('\n' | '\r')* '\r'? '\n'? {$channel = HIDDEN;}
          | '/*' (options {greedy=false;}: .)* '*/' {$channel = HIDDEN;};

