package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * This class represents the search window, which is used to search for text in
 * the editor panel. It highlights the first occurrence of the input word. If
 * the user clicks the search button again, the next occurrence of the word will
 * be highlighted. The search window can replace text as well.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */
public class SearchWindow extends JFrame {
	JTextField findText;
	JTextField replaceText;

	JButton findButton;
	JButton replaceButton;
	JButton replaceAllButton;
	JButton cancelButton;

	int currentPosition;
	int lastIndex;
	String lastSearch;
	boolean highlighted;

	// an object containing the editor text pane, allowing the searchwindow to
	// set colors and highlightings
	JTextPane editorTextPane;
	Style highlightedText;

	public SearchWindow(JTextPane textPane) {
		super("Search");
		generateItems();
		addItems();
		setSize(430, 130);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		refreshEditor(textPane);
		highlightedText = editorTextPane.addStyle("Standard", null);

		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	/**
	 * a keylistener listening for keystrokes to remove the highlighting in the
	 * editor textfield
	 */
	private KeyListener highlightRemoveKeyListener = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent keyEvent) {
			if (highlighted) {
				try {
					highlightText(lastSearch, lastIndex, false);
				} catch (BadLocationException e) {
				}
			}
			lastIndex = 0;
			lastSearch = null;
			removeListeners();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {

		}

		@Override
		public void keyTyped(KeyEvent arg0) {

		}

	};

	/**
	 * adds one instance of the keylistener to the textfield of the editor
	 */
	private void addListeners() {
		boolean containsListener = false;
		for (KeyListener test : editorTextPane.getKeyListeners()) {
			if (test.equals(highlightRemoveKeyListener)) {
				containsListener = true;
			}
		}
		if (!containsListener) {
			editorTextPane.addKeyListener(highlightRemoveKeyListener);
		}
	}

	/**
	 * removes the listeners from the textfield of the editor
	 */
	private void removeListeners() {
		editorTextPane.removeKeyListener(highlightRemoveKeyListener);
	}

	/**
	 * refreshes the editor textfield to the input textpane allowing to switch
	 * tabs without having to create new searchwindows every time
	 * 
	 * @param textPane
	 *            the current textpane
	 */
	public void refreshEditor(JTextPane textPane) {
		this.editorTextPane = textPane;
	}

	// addRemoveToggle = true => highlight
	// addRemoveToggle = false => unhighlight
	/**
	 * adds or removes a highlighting of a word
	 * 
	 * @param search
	 *            the string which should be (de)highlighted
	 * @param index
	 *            the index where the search starts
	 * @param addRemoveToggle
	 *            a boolean giving the option the highlight or unhighlight the
	 *            text (as the only difference is the coloring and a setting a
	 *            bool if something in the text is highlighted, we put it in the
	 *            same function)
	 * @return the index where the new search should start, depending on wether
	 *         or not text has been highlighted or a highlighting has been
	 *         removed
	 * @throws BadLocationException
	 *             if the index is too high
	 */
	private int highlightText(String search, int index, boolean addRemoveToggle)
			throws BadLocationException {
		if (addRemoveToggle) {
			StyleConstants.setBackground(highlightedText, Color.LIGHT_GRAY);
		} else {
			StyleConstants.setBackground(highlightedText, Color.WHITE);
		}
		StyleConstants.setForeground(highlightedText, Color.BLACK);
		StyledDocument text = editorTextPane.getStyledDocument();
		index = text.getText(0, text.getLength()).indexOf(search, index);
		text.remove(index, search.length());
		text.insertString(index, search, highlightedText);
		highlighted = addRemoveToggle;
		if (addRemoveToggle) {
			addListeners();
			return index;
		} else {
			return index + search.length();
		}
	}

	/**
	 * replaces searchtext with replacetext in the editor textfield
	 * 
	 * @param searchText
	 *            text to be replaced
	 * @param replaceText
	 *            text to replaced searchtext
	 * @param index
	 *            index to start the search from
	 * @param noError
	 *            a boolean to catch the end of the replaceAll method
	 * @return the index where the new search should start
	 * @throws BadLocationException
	 */
	private int replace(String searchText, String replaceText, int index,
			boolean noError) throws BadLocationException {
		if (!highlighted || (highlighted && !searchText.equals(lastSearch))) {
			search(searchText, noError);
			index = lastIndex;
		}
		if (searchText.equals(lastSearch) && highlighted) {
			StyleConstants.setForeground(highlightedText, Color.BLACK);
			StyleConstants.setBackground(highlightedText, Color.WHITE);
			StyledDocument text = editorTextPane.getStyledDocument();
			index = text.getText(0, text.getLength())
					.indexOf(searchText, index);
			text.remove(index, searchText.length());
			text.insertString(index, replaceText, highlightedText);
			highlighted = false;
			search(searchText, noError);
		}
		return lastIndex;
	}

	/**
	 * replaces all occurances of searchtext in the textpane
	 * 
	 * @param searchText
	 *            text to be replaced
	 * @param replaceText
	 *            text to replace searchText
	 */
	private void replaceAll(String searchText, String replaceText) {
		int index = -2;
		int indexOld = -1;
		while (index != indexOld) {
			try {
				indexOld = index;
				// replace is called with the bool set true to prevent an
				// infinite loop of message windows stating the search has ended
				index = replace(searchText, replaceText, index, true);
			} catch (BadLocationException e) {

			}
		}
	}

	/**
	 * searches for searchText in the editor textfield and highlights it
	 * 
	 * @param searchText
	 *            the text to be searched for
	 * @param noError
	 *            to differentiate between the replaceAll and replace method
	 */
	private void search(String searchText, boolean noError) {
		// If a word is highlighted
		if (highlighted) {
			try {
				// unhighlight the text
				lastIndex = highlightText(lastSearch, lastIndex, false);
			} catch (BadLocationException e) {
			}
		}
		// if the word is the same word as in the last search, use the same
		// index to find the next occurance of the word in the text
		if (searchText.equals(lastSearch)) {
			try {
				lastIndex = highlightText(searchText, lastIndex, true);
				lastSearch = searchText;
			} catch (BadLocationException e) {
				JOptionPane.showMessageDialog(null,
						"You have reached the end of file", "EOF",
						JOptionPane.PLAIN_MESSAGE);
				// reset
				lastIndex = 0;
				lastSearch = null;
			}
			// else search for the new word starting at the beginning of the
			// text
		} else {
			try {
				lastIndex = highlightText(searchText, 0, true);
				lastSearch = searchText;
			} catch (BadLocationException e) {
				if (!noError) {
					JOptionPane
							.showMessageDialog(
									null,
									"The word you are searching for does not exist in this file.",
									"No such word", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	/**
	 * creates the window layout
	 */
	private void addItems() {
		JPanel window = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		JLabel searchLabel = new JLabel("Find: ");
		window.add(searchLabel, constraints);

		constraints.gridx = 2;
		constraints.weightx = 1;
		window.add(findText, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 0;
		JLabel replaceLabel = new JLabel("Replace with: ");
		window.add(replaceLabel, constraints);

		constraints.gridx = 2;
		constraints.weightx = 1;
		window.add(replaceText, constraints);

		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 3;
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		window.add(buttonPanel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		findButton.addActionListener(findButtonListener);
		buttonPanel.add(findButton, constraints);

		constraints.gridx = 2;
		replaceButton.addActionListener(replaceButtonListener);
		buttonPanel.add(replaceButton, constraints);

		constraints.gridx = 3;
		replaceAllButton.addActionListener(replaceAllButtonListener);
		buttonPanel.add(replaceAllButton, constraints);

		constraints.gridx = 4;
		cancelButton.addActionListener(cancelButtonListener);
		buttonPanel.add(cancelButton, constraints);

		add(window);
	}

	// Button listeners

	AbstractAction findButtonListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			search(findText.getText(), false);
		}

	};

	AbstractAction replaceButtonListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				replace(findText.getText(), replaceText.getText(), lastIndex,
						false);
			} catch (BadLocationException e1) {

			}
		}

	};

	AbstractAction replaceAllButtonListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			replaceAll(findText.getText(), replaceText.getText());
		}

	};

	AbstractAction cancelButtonListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			quit();
		}

	};

	/**
	 * generates the items the window is consisting of
	 */

	private void generateItems() {
		findText = new JTextField();
		replaceText = new JTextField();
		findButton = new JButton("Find");
		replaceButton = new JButton("Replace");
		replaceAllButton = new JButton("Replace All");
		cancelButton = new JButton("Cancel");
		currentPosition = 0;
	}

	/**
	 * quit function, only sets visibility to false to avoid multiple instances
	 * and to keep the search word for the next search
	 */
	protected void quit() {
		this.setVisible(false);
	}

}
