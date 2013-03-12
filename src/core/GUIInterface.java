package core;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.RecognitionException;

import verifier.Result;
import verifier.FormulaGenerator;
import interpreter.InterpretVisitor;
import interpreter.InterpreterState;
import gui.MainWindow;

/**
 * This class represents the interface between GUI and Core. 
 * 
 * @author Jürgen Schuck
 * @author Benedict Hauck
 * 
 */
public class GUIInterface {
	
	private final Faehrmann faehrmann;
	private final Projectmanager projectManager;
	private FormulaGenerator formulaGenerator;
	private final InterpretVisitor interpreter;
	private ANTLRParser parser;
	private final MainWindow mainWindow;
	
	public GUIInterface(Faehrmann faehrmann) {
		this.faehrmann = faehrmann;
		projectManager = new Projectmanager();
		interpreter = new InterpretVisitor();
		formulaGenerator = new FormulaGenerator(this.faehrmann.getSettings());
		mainWindow = new MainWindow(this); 
	}
	//for tests
	public GUIInterface() {	
		faehrmann = null;
		projectManager = null;
		interpreter = null;
		mainWindow = null;
	}


	public int newProject() {
		projectManager.newProject();
		return projectManager.getNewestProject().getId();
	}

	public void closeProject(int id) {
		projectManager.closeProject(id);
	}

	public void saveSettings() {
		faehrmann.saveSettings();
	}
	
	public Settings getSettings() {
		return faehrmann.getSettings();
	}
	
	public Code getCode(String path) {
		return projectManager.loadProject(path);
	}
	
	/**
	 * The method forward the resume command from the GUI
	 */
	public void saveCode(int id, String path, Code code) {
		projectManager.saveCode(id, path, code);
	}

	/**
	 * At first the method parse the given code and checking if there exists errors. 
	 * If errors occur a InterpreterState with the error strings will be returned. Else 
	 * the method forward the debug command from the GUI to the interpreter.
	 */
	public InterpreterState debug(Code code) {
		InterpreterState state = new InterpreterState();
		
		if (code != null) {
			List<String> errors;
			parser = new ANTLRParser();
			state.setDone();
			try {
				errors = parser.parse(code);
				if(errors.size() > 0) {
					for (String i : errors) {
						state.addMessage(i);
					}
					state.setParserErrors();
				}
				else {
					state = interpreter.startDebug(parser.getRoot(), this, code.getBreakpoints());
				}
			} catch (RecognitionException e) {
				e.printStackTrace();
			}
		}
		return state;	
	}

	/**
	 * The method forward the resume command from the GUI
	 * @param breakpoints 
	 */
	public InterpreterState resume(int[] breakpoints) {
		return interpreter.resumeDebug(breakpoints);
	}

	/**
	 * At first the method parse the given code and checking if there exists errors. 
	 * If errors occur a InterpreterState with the error strings will be returned. Else 
	 * the method forward the interpret command from the GUI to the interpreter.
	 */
	public InterpreterState interpret(Code code) {
		InterpreterState state = new InterpreterState();
		
		if (code != null) {
			List<String> errors;
			parser = new ANTLRParser();
			state.setDone();
			try {
				errors = parser.parse(code);
				if(errors.size() > 0) {
					for (String i : errors) {
						state.addMessage(i);
					}
				}
				else {
					state = interpreter.interprete(parser.getRoot(), this);
				}
			} catch (RecognitionException e) {
				e.printStackTrace();
			}
		}
		return state;
	}

	/**
	 * At first the method parse the given code and checking if there exists errors. 
	 * If errors occur a Result with the error strings will be returned. Else 
	 * the method forward the verify command from the GUI to the verifier.
	 * @param informationFlow 
	 */
	public Result verify(Code code, boolean informationFlow) {
		Result result = null;
		
		if (code != null) {
			List<String> errors;
			ArrayList<String> tempErrors = new ArrayList<String>();
			parser = new ANTLRParser();
			
			try {
				errors = parser.parse(code);
				for (int i = 0; i < errors.size(); i++) {
					tempErrors.add(errors.get(i));
				}
				if(errors.size() > 0) {
					result = new Result(tempErrors);
				}
				else {
					formulaGenerator = new FormulaGenerator(this.faehrmann.getSettings());
					result = formulaGenerator.verify(parser.getRoot(), informationFlow);
				}
			} catch (RecognitionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Forward a variable request to the GUI
	 */
	public BigInteger requestVariable(String variableName, int lineNumber) {
		return mainWindow.requestVariable(variableName, lineNumber);
	}

	public void terminate() {
		// TODO Auto-generated method stub
		
	}


	public boolean askResume() {
		return mainWindow.askResume();
	}	
	
	
}
