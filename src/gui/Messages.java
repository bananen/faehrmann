package gui;

import interpreter.InterpreterState;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import verifier.Result;

import java.awt.Font;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * This class represents the messages panel.
 * It displays all (error)messages that can occur in the execution of the programm.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 *
 */
public class Messages extends JScrollPane {

	//The textfield which displays the (error)messages
	private JTextPane msgContent;
	
	public Messages() {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		msgContent = new JTextPane();
		msgContent.setText("Messages");
		msgContent.setEditable(false);
		
		super.getViewport().add(msgContent);
	}
		
	/**
	 * adds the input string to the text of the textfield
	 * @param content the string to be added
	 */
	@SuppressWarnings("deprecation")
	public void printMessage(String content) {
		if (msgContent.getText().equals("") || msgContent.getText().equals("Messages")) {
			msgContent.setText(content);
		} else {
			msgContent.setText(msgContent.getText() + "\n" + content);
		}
	}
	
	/**
	 * automatically prints the messages of the input interpreterstate
	 * @param interpreterState the interpreterstate containing the (error) messages
	 */
	public void printInterpreterState(InterpreterState interpreterState) {
		LinkedList<String> messages = interpreterState.getErrorMessages();
		print(messages);
	}
	
	/**
	 * prints a list of strings
	 * @param messages the list of strings
	 */
	public void print(LinkedList<String> messages) {
		for (String message : messages) {
			printMessage(message);
		}
	}
	
	public void clear() {
		msgContent.setText("");
	}

	public void printTimestamp() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String content = sdf.format(now) + ":";
		printMessage(content);
	}
	
	/**
	 * automatically prints the result of the verifying component 
	 * @param result the result containing the messages to be printed
	 */
	public void printVerifyResult(Result result) {
		ArrayList<String> messages = result.getMessages();
		for (String message : messages) {
			printMessage(message);
		}
	}

	public void refreshFontSize(int fontSize) {
		Font editorFont = msgContent.getFont();
		Font newFont = new Font(editorFont.getName(), editorFont.getStyle(), fontSize);
		msgContent.setFont(newFont);
	}

}