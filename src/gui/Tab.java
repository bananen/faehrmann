package gui;
import interpreter.InterpreterState;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import verifier.Result;
import core.Code;

/**
 * This class represents a tab, including messages panel, variable table and an editor text field.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */

public class Tab extends JPanel {

	private int tabID;
	private Editor editor;
	private Messages messages;
	private VariableTable variableTable;
	private String path;
	private boolean hasFile;
  
  
	public Tab(int tabID, String path) {
		super(new GridBagLayout());
		this.tabID = tabID;
		editor = new Editor();
		messages = new Messages();
		variableTable = new VariableTable();  
		this.path = path;
		hasFile = false;

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0.25;
		constraints.weighty = 0.65;
		add(variableTable, constraints);
	  
		constraints.gridy = 2;
		constraints.weighty = 0.35;
		add(messages, constraints);
	  
		constraints.gridheight = 2;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.weightx = 0.75;
		constraints.weighty = 1;
		add(editor, constraints);
	}

	public int getTabID() {
		return tabID;
	}

	public void undo() {
		editor.undo();
	}

	public void redo() {
		editor.redo();	
	}

	public boolean isSaved() {
		return editor.isSaved();
	}

	public void setCode(Code project) {
		editor.setText(project.getLines());
		editor.setBreakpoints(project.getBreakpoints());
		editor.setSaved(true);
		hasFile = true;
	}

	public boolean hasFile() {
		return hasFile;
	}
	
	public void clearMessages() {
		messages.clear();
	}
	
	public void printTimestamp() {
		messages.printTimestamp();
	}

	public Code saveCode(String path) {
		hasFile = true;
		this.path = path;
		editor.setSaved(true);
		return getCode();
	}

	public Code getCode() {
		Code code = new Code();
		code.setLines(editor.getText());
		code.setLinesWithBreakpoints(editor.getBreakpoints());
		return code;
	}

	public String getPath() {
		return path;
	}

	public void setInterpreterState(InterpreterState interpreterState) {
		variableTable.writeVariables(interpreterState);
		messages.printInterpreterState(interpreterState);
		editor.setBreakLine(interpreterState);
		editor.setErrorLines(interpreterState);
	}

	public void refreshFontSize(int fontSize) {
		editor.refreshFontSize(fontSize);
		messages.refreshFontSize(fontSize);
		variableTable.refreshFontSize(fontSize);
	}

	public void setVerifyResult(Result result) {
		messages.printVerifyResult(result);
	}

	public JTextPane getEditor() {
		return editor.getEditor();
	}

	public void removeLineMarkers() {
		editor.removeErrorLines();
		editor.removeBreakLine();
	}

	/**
	 * prints out a message in the message window
	 * @param messages text to be printed out
	 * @param errorLines lines to be highlighted (in which an error occured)
	 */
	public void sendMessages(LinkedList<String> messages, LinkedList<Integer> errorLines) {
		this.messages.print(messages);
		editor.setErrorLines(errorLines);
	}
	
	public void sendMessage(String message) {
		messages.printMessage(message);
	}
}