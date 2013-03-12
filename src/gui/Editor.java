package gui;

import interpreter.InterpreterState;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * The editor class represents an editor window with its components.
 * It consists of the editor window in which you type your source code and of the sidebar that shows the
 * linenumbers. These linenumbers are toggleable to implicate where you want your breakpoints to be set.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */
public class Editor extends JScrollPane {

	private boolean saved = true;
	private UndoManager undoManager = new UndoManager();
	private JTextPane editorContent;
	private JTextPane lineNumbers;
	private Style currentStyle;
	private int lineCount;
	private HashMap<Integer, String> markedLines;
	private LinkedList<Integer> breakpoints;
	private int breakLine = -1;
	
	
	public Editor() {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		editorContent = new JTextPane();
		editorContent.getDocument().addUndoableEditListener(undoManager);
		editorContent.addKeyListener(lineCountListener);
		editorContent.setMargin(new Insets(5, 5, 0, 10));
		lineNumbers = new JTextPane();
		lineNumbers.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lineNumbers.addMouseListener(breakpointListener);
		lineNumbers.setMargin(new Insets(5, 5, 0, 10));
		lineNumbers.setEditable(false);
		currentStyle = lineNumbers.addStyle("Standard", null);
		refreshLines();
		super.setViewportView(editorContent);
		super.setRowHeaderView(lineNumbers);
		breakpoints = new LinkedList<Integer>();
		markedLines = new HashMap<Integer, String>();
	}
	
	/**
	 * lineCountListener listens for keystrokes. If any key is pressed in combination with the CTRL-key or
	 * if the key is the enter key, it recounts the number of lines and refreshes the sidebar with the linenumbers
	 */
	private KeyListener lineCountListener = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent keyEvent) {
			if (keyEvent.isControlDown() || keyEvent.getKeyCode() == keyEvent.VK_ENTER) {
				refreshLines();
			}
		}

		@Override
		public void keyReleased(KeyEvent keyEvent) {
			if (keyEvent.isControlDown() || keyEvent.getKeyCode() == keyEvent.VK_ENTER) {
				refreshLines();
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			saved = false;
		}
		
	};
	
	/**
	 * The breakpointListener listens for a mouseclick and runs the breakpoint method, which checks if a 
	 * breakpoint has to be set
	 */
	private MouseListener breakpointListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
				breakpoint();
			} catch (BadLocationException e) {
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {	
		}

		@Override
		public void mousePressed(MouseEvent arg0) {	
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {	
		}
	};
	
	/**
	 * listens for a keystroke to remove the highlighting of lines in which errors occurred	
	 */
	private KeyListener errorEditorListener = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent keyEvent) {
			removeErrorLines();
			removeListeners();	
		}

		@Override
		public void keyReleased(KeyEvent keyEvent) {	
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
		
	};
	
	/**
	 * listens for a mouseclick to remove the highlighting of lines in which errors occurred
	 */
	private MouseListener errorLinesListener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			removeErrorLines();
			removeListeners();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {	
		}

		@Override
		public void mousePressed(MouseEvent arg0) {	
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {	
		}
	};
	
	/**
	 * Undo method for the editor
	 */
	public void undo() {
		try {
			undoManager.undo();
		} catch (CannotUndoException ex) {
		}
	}
	
	/**
	 * Redo method for the editor
	 */
	public void redo() {
		try {
			undoManager.redo();
		    } catch (CannotRedoException ex) {
		    }
	}

	/**
	 * Returns if the editor is saved or not, in order to ask the user to autosave on exit
	 * @return a bool wether the file is saved or not
	 */
	public boolean isSaved() {
		return saved;
	}
	
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
	/**
	 * This toggles a breakpoint highlighting at the linenumbers bar.
	 * @throws BadLocationException is thrown if the user clicks to a location where no text is at, e.g.
	 * below the last number
	 */
	private void breakpoint() throws BadLocationException {
		StyledDocument text = lineNumbers.getStyledDocument();
		//gets the indices to extract the correct number from the text
		int highIndex = getHighIndex();
		int lowIndex = getLowIndex(highIndex);
		int number = Integer.parseInt(text.getText(lowIndex, highIndex - lowIndex));
		//removes the text in order to rewrite it differently colored
		text.remove(lowIndex, highIndex - lowIndex);
		//toggles the actual breakpoint
		if (breakpoints.contains(number)) {
			removeBreakpoint(lowIndex, number);
		} else {
			addBreakpoint(lowIndex, number);
		}
	}
	
	private void addBreakpoint(int index, int number) throws BadLocationException {
		breakpoints.add(number);
		StyledDocument text = lineNumbers.getStyledDocument();
        StyleConstants.setForeground(currentStyle, Color.white);
        StyleConstants.setBackground(currentStyle, Color.black);

        text.insertString(index, String.valueOf(number), currentStyle);
	}

	private void removeBreakpoint(int index, int number) throws BadLocationException {
		breakpoints.remove((Integer)number);
        StyledDocument text = lineNumbers.getStyledDocument();
        StyleConstants.setForeground(currentStyle, Color.black);
        StyleConstants.setBackground(currentStyle, Color.white);

        text.insertString(index, String.valueOf(number), currentStyle);
	}

	/**
	 * returns the higher index for the breapoint-setting process.
	 * This is done by using the caret position, which is set by the user by clicking somewhere in the textfield
	 * @return the higher index depending where the user clicked
	 * @throws BadLocationException in case the user clicked somewhere where no text is standing
	 */
	private int getHighIndex() throws BadLocationException {
		int caretPosition = lineNumbers.getCaretPosition();
		StyledDocument text = lineNumbers.getStyledDocument();
		return text.getText(0, text.getLength()).indexOf("\n", caretPosition);
	}
	
	/** returns the lower index depending on the higher index which the user sets as a parameter
	 * 
	 * @param highIndex the higher index (the index to the right side of the number)
	 * @return the lower index (the index to the left side of the number)
	 * @throws BadLocationException in case the user clicked somewhere where no text is standing
	 */
	private int getLowIndex(int highIndex) throws BadLocationException{
		int result = 0;
		if (highIndex > 2) {
			result = lineNumbers.getStyledDocument().getText(0, highIndex).lastIndexOf("\n") + 1;
		}
		return result;
	}
	
	/**
	 * recounts the lines and refreshes the linenumber-bar
	 */
	private void refreshLines() {
		int newLineCount = countLines();
		if (newLineCount > lineCount) {
			addLines(newLineCount);
			lineCount = newLineCount;
		}
	}
	
	private void addLines(int newLineCount) {
		StyledDocument text = lineNumbers.getStyledDocument();
        StyleConstants.setForeground(currentStyle, Color.black);
        StyleConstants.setBackground(currentStyle, Color.white);
		for (int i = (lineCount + 1); i <= newLineCount; i++) {
			try {text.insertString(text.getLength(), String.valueOf(i) + "\n", currentStyle); }
	        catch (BadLocationException e){}
        }
	}
	
	private int countLines() {
		String text = editorContent.getText();
        int index = 0;
        int amount = 1;
        //indexOf(String, index) returns -1 if the string is not found in the textfield, starting at the index
        while (text.indexOf("\n", index) != -1) {
            index = text.indexOf("\n", index) + 1;
            amount++;
        }
        return amount;
	}

	public void setText(String text) {
		//resets the two textfields, sets the text for the editor textfield and refreshes the linenumber-bar
		editorContent.setText(text);
		lineNumbers.setText("");
		refreshLines();
	}

	/**
	 * converts the int array into the breakpoint list to allow public setting of the breakpoints
	 * @param linesWithBreakpoints
	 */
	public void setBreakpoints(int[] linesWithBreakpoints) {
		breakpoints = new LinkedList<Integer>();
		if (linesWithBreakpoints != null) {
			for (int i : linesWithBreakpoints) {
				if (!breakpoints.contains(i)) {
					breakpoints.add(i);	
				}
			}
		}
		try {
			refreshBreakpoints();
		} catch (BadLocationException e) {}
	}

	/**
	 * refreshes the breakpoint. This method is only used when loading breakpoints from the core, while loading
	 * an existing programm
	 * @throws BadLocationException in case a wrong index is inserted, in this method this should not happen
	 */
	private void refreshBreakpoints() throws BadLocationException {
		lineCount = 0;
		//removes the text from the linenumbers-bar
		StyledDocument text = lineNumbers.getStyledDocument();
		text.remove(0, text.getLength());
		//recounts the lines and writes them into the bar
		refreshLines();
		//for every breakpoint, the corresponding number is deleted from the bar and rewritten in another
		//background color
		for (int i : breakpoints) {
			String number = String.valueOf(i);
			int index = text.getText(0, text.getLength()).indexOf(number);
			text.remove(index, number.length());
	        StyleConstants.setForeground(currentStyle, Color.white);
	        StyleConstants.setBackground(currentStyle, Color.black);
			text.insertString(index, String.valueOf(i), currentStyle);
		}
	}

	public void refreshFontSize(int fontSize) {
		
		Font editorFont = editorContent.getFont();
		Font newFont = new Font(editorFont.getName(), editorFont.getStyle(), fontSize);
		editorContent.setFont(newFont);
		
		lineNumbers.setFont(newFont);
	}

	public String getText() {
		return editorContent.getText();
	}

	public int[] getBreakpoints() {
		int[] result = new int[breakpoints.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = breakpoints.get(i);
		}
		return result;
	}

	public JTextPane getEditor() {
		return editorContent;
	}

	private int toggleLine(int line) throws BadLocationException {
		StyledDocument text = lineNumbers.getStyledDocument();
		String number = String.valueOf(line);
		int index = text.getText(0, text.getLength()).indexOf(number);
		text.remove(index, number.length());
		text.insertString(index, number, currentStyle);
		return index;
	}

	private void unmarkAllLines() throws BadLocationException {
		StyledDocument text = lineNumbers.getStyledDocument();
		Set<Entry<Integer, String>> lines = markedLines.entrySet();
		for (Entry<Integer, String> line : lines) {
			text.remove((Integer)line.getKey(), ((String)line.getValue()).length());
			if (breakpoints.contains((Integer)Integer.parseInt((String) line.getValue()))) {
				StyleConstants.setForeground(currentStyle, Color.white);
		        StyleConstants.setBackground(currentStyle, Color.black);
			} else {
				StyleConstants.setForeground(currentStyle, Color.black);
		        StyleConstants.setBackground(currentStyle, Color.white);
			}
			text.insertString((Integer)line.getKey(), (String)line.getValue(), currentStyle);
		}
		markedLines = new HashMap<Integer, String>();
	}
	
	public void setErrorLines(InterpreterState interpreterState) {
		setErrorLines(interpreterState.getErrorLines());
	}

	public void setErrorLines(LinkedList<Integer> lines) {
		StyleConstants.setForeground(currentStyle, Color.black);
        StyleConstants.setBackground(currentStyle, Color.red);
        int index;
		for (int line : lines) {
			try {
				index = toggleLine(line);
				markedLines.put((Integer)index, String.valueOf(line));
			} catch (BadLocationException e) {}	
		}
		addListeners();
	}

	public void setBreakLine(InterpreterState interpreterState) {
		if (!interpreterState.isDone()) {
			StyleConstants.setForeground(currentStyle, Color.black);
			StyleConstants.setBackground(currentStyle, Color.cyan);
			try {
				breakLine = interpreterState.getCurrentLine();
				toggleLine(breakLine);	
			} catch (BadLocationException e) {}
		}
	}
	
	public void removeBreakLine() {
		StyleConstants.setForeground(currentStyle, Color.white);
        StyleConstants.setBackground(currentStyle, Color.black);
		try {
			toggleLine(breakLine);
		} catch (BadLocationException e) {}
	}

	public void removeErrorLines() {
		try {
			unmarkAllLines();
		} catch (BadLocationException e) {}
	}
	
	private void addListeners() {
		editorContent.addKeyListener(errorEditorListener);
		lineNumbers.addMouseListener(errorLinesListener);
	}
	
	private void removeListeners() {
		editorContent.removeKeyListener(errorEditorListener);
		lineNumbers.removeMouseListener(errorLinesListener);
	}
}