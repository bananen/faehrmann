// $ANTLR 3.5 C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g 2013-03-03 16:37:14

package core;
import ast.*;
import ast.Statement;
import java.util.ArrayList;
import java.util.LinkedList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class FaehrmannLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public FaehrmannLexer() {} 
	public FaehrmannLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public FaehrmannLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g"; }

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:15:7: ( '!' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:15:9: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:16:7: ( '!=' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:16:9: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:17:7: ( '&' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:17:9: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:18:7: ( '(' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:18:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:19:7: ( ')' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:19:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:20:7: ( '*' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:20:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:21:7: ( '+' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:21:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:22:7: ( '++' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:22:9: '++'
			{
			match("++"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:23:7: ( ',' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:23:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:24:7: ( '-' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:24:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:25:7: ( '--' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:25:9: '--'
			{
			match("--"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:26:7: ( '->' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:26:9: '->'
			{
			match("->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:27:7: ( ':' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:27:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:28:7: ( ';' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:28:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:29:7: ( '<' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:29:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:30:7: ( '<->' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:30:9: '<->'
			{
			match("<->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:31:7: ( '<=' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:31:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:32:7: ( '=' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:32:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:33:7: ( '==' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:33:9: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:34:7: ( '>' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:34:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:35:7: ( '>=' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:35:9: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:36:7: ( '[' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:36:9: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:37:7: ( '[A]' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:37:9: '[A]'
			{
			match("[A]"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:38:7: ( '[E]' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:38:9: '[E]'
			{
			match("[E]"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:39:7: ( ']' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:39:9: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:40:7: ( 'assert' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:40:9: 'assert'
			{
			match("assert"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:41:7: ( 'assume' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:41:9: 'assume'
			{
			match("assume"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:42:7: ( 'bool' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:42:9: 'bool'
			{
			match("bool"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:43:7: ( 'else' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:43:9: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:44:7: ( 'false' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:44:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:45:7: ( 'func' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:45:9: 'func'
			{
			match("func"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:46:7: ( 'high' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:46:9: 'high'
			{
			match("high"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:47:7: ( 'if' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:47:9: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:48:7: ( 'int' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:48:9: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:49:7: ( 'true' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:49:9: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:50:7: ( 'while' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:50:9: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:51:7: ( '{' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:51:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:52:7: ( '|' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:52:9: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:53:7: ( '}' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:53:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "VARIABLEID"
	public final void mVARIABLEID() throws RecognitionException {
		try {
			int _type = VARIABLEID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:607:12: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:607:14: ( 'a' .. 'z' | 'A' .. 'Z' ) ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:607:35: ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VARIABLEID"

	// $ANTLR start "ARRAYLENGTH"
	public final void mARRAYLENGTH() throws RecognitionException {
		try {
			int _type = ARRAYLENGTH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:609:13: ( VARIABLEID '.length' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:609:15: VARIABLEID '.length'
			{
			mVARIABLEID(); 

			match(".length"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARRAYLENGTH"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:611:8: ( ( DIGIT )+ )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:611:10: ( DIGIT )+
			{
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:611:10: ( DIGIT )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBER"

	// $ANTLR start "DIGIT"
	public final void mDIGIT() throws RecognitionException {
		try {
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:613:16: ( '0' .. '9' )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT"

	// $ANTLR start "WHITESPACE"
	public final void mWHITESPACE() throws RecognitionException {
		try {
			int _type = WHITESPACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:615:12: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:615:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
			{
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:615:14: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\t' && LA3_0 <= '\n')||(LA3_0 >= '\f' && LA3_0 <= '\r')||LA3_0==' ') ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHITESPACE"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:617:9: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0=='/') ) {
				int LA7_1 = input.LA(2);
				if ( (LA7_1=='/') ) {
					alt7=1;
				}
				else if ( (LA7_1=='*') ) {
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

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:617:11: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
					{
					match("//"); 

					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:617:16: (~ ( '\\n' | '\\r' ) )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= '\u0000' && LA4_0 <= '\t')||(LA4_0 >= '\u000B' && LA4_0 <= '\f')||(LA4_0 >= '\u000E' && LA4_0 <= '\uFFFF')) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop4;
						}
					}

					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:617:32: ( '\\r' )?
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0=='\r') ) {
						alt5=1;
					}
					switch (alt5) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:617:32: '\\r'
							{
							match('\r'); 
							}
							break;

					}

					match('\n'); 
					_channel = HIDDEN;
					}
					break;
				case 2 :
					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:618:13: '/*' ( options {greedy=false; } : . )* '*/'
					{
					match("/*"); 

					// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:618:18: ( options {greedy=false; } : . )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( (LA6_0=='*') ) {
							int LA6_1 = input.LA(2);
							if ( (LA6_1=='/') ) {
								alt6=2;
							}
							else if ( ((LA6_1 >= '\u0000' && LA6_1 <= '.')||(LA6_1 >= '0' && LA6_1 <= '\uFFFF')) ) {
								alt6=1;
							}

						}
						else if ( ((LA6_0 >= '\u0000' && LA6_0 <= ')')||(LA6_0 >= '+' && LA6_0 <= '\uFFFF')) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:618:44: .
							{
							matchAny(); 
							}
							break;

						default :
							break loop6;
						}
					}

					match("*/"); 

					_channel = HIDDEN;
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:8: ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | VARIABLEID | ARRAYLENGTH | NUMBER | WHITESPACE | COMMENT )
		int alt8=44;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:10: T__10
				{
				mT__10(); 

				}
				break;
			case 2 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:16: T__11
				{
				mT__11(); 

				}
				break;
			case 3 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:22: T__12
				{
				mT__12(); 

				}
				break;
			case 4 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:28: T__13
				{
				mT__13(); 

				}
				break;
			case 5 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:34: T__14
				{
				mT__14(); 

				}
				break;
			case 6 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:40: T__15
				{
				mT__15(); 

				}
				break;
			case 7 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:46: T__16
				{
				mT__16(); 

				}
				break;
			case 8 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:52: T__17
				{
				mT__17(); 

				}
				break;
			case 9 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:58: T__18
				{
				mT__18(); 

				}
				break;
			case 10 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:64: T__19
				{
				mT__19(); 

				}
				break;
			case 11 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:70: T__20
				{
				mT__20(); 

				}
				break;
			case 12 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:76: T__21
				{
				mT__21(); 

				}
				break;
			case 13 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:82: T__22
				{
				mT__22(); 

				}
				break;
			case 14 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:88: T__23
				{
				mT__23(); 

				}
				break;
			case 15 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:94: T__24
				{
				mT__24(); 

				}
				break;
			case 16 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:100: T__25
				{
				mT__25(); 

				}
				break;
			case 17 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:106: T__26
				{
				mT__26(); 

				}
				break;
			case 18 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:112: T__27
				{
				mT__27(); 

				}
				break;
			case 19 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:118: T__28
				{
				mT__28(); 

				}
				break;
			case 20 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:124: T__29
				{
				mT__29(); 

				}
				break;
			case 21 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:130: T__30
				{
				mT__30(); 

				}
				break;
			case 22 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:136: T__31
				{
				mT__31(); 

				}
				break;
			case 23 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:142: T__32
				{
				mT__32(); 

				}
				break;
			case 24 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:148: T__33
				{
				mT__33(); 

				}
				break;
			case 25 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:154: T__34
				{
				mT__34(); 

				}
				break;
			case 26 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:160: T__35
				{
				mT__35(); 

				}
				break;
			case 27 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:166: T__36
				{
				mT__36(); 

				}
				break;
			case 28 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:172: T__37
				{
				mT__37(); 

				}
				break;
			case 29 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:178: T__38
				{
				mT__38(); 

				}
				break;
			case 30 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:184: T__39
				{
				mT__39(); 

				}
				break;
			case 31 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:190: T__40
				{
				mT__40(); 

				}
				break;
			case 32 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:196: T__41
				{
				mT__41(); 

				}
				break;
			case 33 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:202: T__42
				{
				mT__42(); 

				}
				break;
			case 34 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:208: T__43
				{
				mT__43(); 

				}
				break;
			case 35 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:214: T__44
				{
				mT__44(); 

				}
				break;
			case 36 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:220: T__45
				{
				mT__45(); 

				}
				break;
			case 37 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:226: T__46
				{
				mT__46(); 

				}
				break;
			case 38 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:232: T__47
				{
				mT__47(); 

				}
				break;
			case 39 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:238: T__48
				{
				mT__48(); 

				}
				break;
			case 40 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:244: VARIABLEID
				{
				mVARIABLEID(); 

				}
				break;
			case 41 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:255: ARRAYLENGTH
				{
				mARRAYLENGTH(); 

				}
				break;
			case 42 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:267: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 43 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:274: WHITESPACE
				{
				mWHITESPACE(); 

				}
				break;
			case 44 :
				// C:\\Users\\Marc\\Bluetooth Software\\Desktop\\UNI\\3.Semester\\PSE\\Implementierung\\Faehrmann\\src\\core\\Faehrmann.g:1:285: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\1\uffff\1\40\4\uffff\1\42\1\uffff\1\45\2\uffff\1\50\1\52\1\54\1\57\1"+
		"\uffff\10\62\3\uffff\1\62\24\uffff\2\62\2\uffff\5\62\1\103\11\62\1\uffff"+
		"\1\116\4\62\1\123\1\124\1\62\1\126\1\127\1\uffff\1\130\3\62\2\uffff\1"+
		"\134\3\uffff\1\135\1\136\1\137\4\uffff";
	static final String DFA8_eofS =
		"\140\uffff";
	static final String DFA8_minS =
		"\1\11\1\75\4\uffff\1\53\1\uffff\1\55\2\uffff\1\55\2\75\1\101\1\uffff\10"+
		"\56\3\uffff\1\56\24\uffff\2\56\2\uffff\17\56\1\uffff\12\56\1\uffff\4\56"+
		"\2\uffff\1\56\3\uffff\3\56\4\uffff";
	static final String DFA8_maxS =
		"\1\175\1\75\4\uffff\1\53\1\uffff\1\76\2\uffff\3\75\1\105\1\uffff\10\172"+
		"\3\uffff\1\172\24\uffff\2\172\2\uffff\17\172\1\uffff\12\172\1\uffff\4"+
		"\172\2\uffff\1\172\3\uffff\3\172\4\uffff";
	static final String DFA8_acceptS =
		"\2\uffff\1\3\1\4\1\5\1\6\1\uffff\1\11\1\uffff\1\15\1\16\4\uffff\1\31\10"+
		"\uffff\1\45\1\46\1\47\1\uffff\1\52\1\53\1\54\1\2\1\1\1\10\1\7\1\13\1\14"+
		"\1\12\1\20\1\21\1\17\1\23\1\22\1\25\1\24\1\27\1\30\1\26\2\uffff\1\50\1"+
		"\51\17\uffff\1\41\12\uffff\1\42\4\uffff\1\34\1\35\1\uffff\1\37\1\40\1"+
		"\43\3\uffff\1\36\1\44\1\32\1\33";
	static final String DFA8_specialS =
		"\140\uffff}>";
	static final String[] DFA8_transitionS = {
			"\2\35\1\uffff\2\35\22\uffff\1\35\1\1\4\uffff\1\2\1\uffff\1\3\1\4\1\5"+
			"\1\6\1\7\1\10\1\uffff\1\36\12\34\1\11\1\12\1\13\1\14\1\15\2\uffff\32"+
			"\33\1\16\1\uffff\1\17\3\uffff\1\20\1\21\2\33\1\22\1\23\1\33\1\24\1\25"+
			"\12\33\1\26\2\33\1\27\3\33\1\30\1\31\1\32",
			"\1\37",
			"",
			"",
			"",
			"",
			"\1\41",
			"",
			"\1\43\20\uffff\1\44",
			"",
			"",
			"\1\46\17\uffff\1\47",
			"\1\51",
			"\1\53",
			"\1\55\3\uffff\1\56",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\22\61\1\60\7"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\16\61\1\64\13"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\13\61\1\65\16"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\1\66\23\61\1"+
			"\67\5\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\10\61\1\70\21"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\5\61\1\71\7\61"+
			"\1\72\14\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\21\61\1\73\10"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\7\61\1\74\22"+
			"\61",
			"",
			"",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\22\61\1\75\7"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\16\61\1\76\13"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\22\61\1\77\7"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\13\61\1\100\16"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\15\61\1\101\14"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\6\61\1\102\23"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\23\61\1\104\6"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\24\61\1\105\5"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\10\61\1\106\21"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\4\61\1\107\17"+
			"\61\1\110\5\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\13\61\1\111\16"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\4\61\1\112\25"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\22\61\1\113\7"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\2\61\1\114\27"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\7\61\1\115\22"+
			"\61",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\4\61\1\117\25"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\13\61\1\120\16"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\21\61\1\121\10"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\14\61\1\122\15"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\4\61\1\125\25"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\4\61\1\131\25"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\23\61\1\132\6"+
			"\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\4\61\1\133\25"+
			"\61",
			"",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"",
			"",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\63\1\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"",
			"",
			""
	};

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	protected class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | VARIABLEID | ARRAYLENGTH | NUMBER | WHITESPACE | COMMENT );";
		}
	}

}
