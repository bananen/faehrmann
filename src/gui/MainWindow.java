package gui;

import interpreter.InterpreterState;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.GUIInterface;
import core.Settings;

/**
 * This class represents the main window of Faehrmann.
 * It includes the menus, the editor panel, the messages pane and the variable table (all united in a tab).
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 * 
 */

public class MainWindow extends JFrame{

	private GUIInterface core;
	
	//Menu buttons, sorted by the dropdown menu context
	private JMenuItem newProject = new JMenuItem("New Project");
	private JMenuItem open = new JMenuItem("Open Project");
	private JMenuItem save = new JMenuItem("Save Project");
	private JMenuItem saveAs = new JMenuItem("Save Project as ...");
	private JMenuItem close = new JMenuItem("Close Project");
	private JMenuItem quit = new JMenuItem("Quit");
	
	private JMenuItem undo = new JMenuItem("Undo");
	private JMenuItem redo = new JMenuItem("Redo");
	private JMenuItem search = new JMenuItem("Search");
	private JMenuItem settings = new JMenuItem("Settings");
	
	private JMenuItem runProject = new JMenuItem("Run");
	private JMenuItem debug = new JMenuItem("Debug");
	private JMenuItem resume = new JMenuItem("Resume");
	private JMenuItem terminate = new JMenuItem("Terminate");
	private JMenuItem verify = new JMenuItem("Verify");
	
	//Toolbar buttons for quick access of necessary function
	private JButton newButton = new JButton("New Project");
	private JButton saveButton = new JButton("Save Project");
	private JButton closeButton = new JButton("Close Project");
	private JButton undoButton = new JButton("Undo");
	private JButton redoButton = new JButton("Redo");
	private JButton runButton = new JButton("Run");
	private JButton debugButton = new JButton("Debug");
	private JButton resumeButton = new JButton("Resume");
	private JButton terminateButton = new JButton("Terminate");
	private JButton verifyButton = new JButton("Verify");
	
	private JButton clearMsgWndwButton = new JButton("Clear Messages");
	
	private JCheckBox informationFlow = new JCheckBox("Check Informationflow");
	
	
	private TabManager tabs = new TabManager();
	
	//Load, Save dialog
	private JFileChooser fileChooser;
	
	private SearchWindow searchWindow;
	
	
	public MainWindow(GUIInterface guiInterface) {
		//Sets the title
		super("Faehrmann");
		this.core = guiInterface;
		super.setSize(900, 650);
		super.setLocationRelativeTo(null);
		//To override the standard quit function
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		super.add(menu(), BorderLayout.NORTH);
		super.add(center(), BorderLayout.CENTER);
		normalButtons();
				 
		tabs.newTab(guiInterface.newProject(), core.getSettings().getDefaultPath());
		super.setVisible(true);
		
		refreshSettings();
	}
	
	/**
	 * Creates a new filechooser dialog to have the overwrite-question function.
	 * The standard filechooser simply overwrites a file if it is already existing, we added a question window if the user really wants to 
	 * overwrite the existing file
	 */
	private void createFileChooser() {
		fileChooser = new JFileChooser(new File(core.getSettings().getDefaultPath())) {
			@Override
			public void approveSelection() {
				if ((getSelectedFile().exists() || new File(getSelectedFile().getPath() + ".fm").exists()) 
						&& getDialogType() == SAVE_DIALOG) {				
					int pressedButton = JOptionPane.showConfirmDialog(this, "Do you want to overwrite the existing File: " 
							+ getSelectedFile().getName() + "?", "Overwrite?", JOptionPane.YES_NO_CANCEL_OPTION);
					if(pressedButton == JOptionPane.YES_OPTION) {
						super.approveSelection();
					} else if (pressedButton == JOptionPane.CANCEL_OPTION) {
						cancelSelection();
					}
					
				} else {
					super.approveSelection();
				}
			}
		};
		fileChooser.setFileFilter(new FileNameExtensionFilter("Faehrmann", "fm"));
	}

	
	
	//Button Listeners 
	
	AbstractAction newProjectListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			newProject();
		}
		
	};
	
	AbstractAction openListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			open();
		}
		
	};
	
	AbstractAction saveListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			save();
		}
		
	};
	
	AbstractAction saveAsListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			saveAs();
		}
		
	};

	AbstractAction closeListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			closeTab(false);	
		}
		
	};

	AbstractAction quitListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			quit();
		}
		
	};

	AbstractAction undoListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			undo();
		}
		
	};

	AbstractAction redoListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			redo();
		}	
		
	};

	//To avoid multiple instances and to keep the last word the user looked for, the searchwindow is only defined once and after that, 
	//Only the visibility of the window is changed (meaning the window doesn't get closed if the application is running, it only gets hidden
	
	AbstractAction searchListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			search();
		}	
	};

	AbstractAction settingsListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			callSettings();
		}	
		
	};

	AbstractAction runProjectListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			run();
		}	
	};

	AbstractAction debugListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			debug();
		}
		
	};

	AbstractAction resumeListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			resume();
		}
		
	};

	AbstractAction terminateListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			terminate();
		}
		
	};

	AbstractAction verifyListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			verify();
		}
		
	};
	
	AbstractAction clearListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			clearMessages();
		}
		
	};
	
	
	

	/**
	 * Creates and returns a panel containing everything except the dropdown menus
	 * @return the JPanel containing the basic window items
	 */
	private JPanel center() {
		JPanel center = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		//Toolbar with shortcuts to functions which are used frequently
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		newButton.addActionListener(newProjectListener);
		toolBar.add(newButton);
		saveButton.addActionListener(saveListener);
		toolBar.add(saveButton);
		closeButton.addActionListener(closeListener);
		toolBar.add(closeButton);
		undoButton.addActionListener(undoListener);
		toolBar.add(undoButton);
		redoButton.addActionListener(redoListener);
		toolBar.add(redoButton);
		runButton.addActionListener(runProjectListener);
		toolBar.add(runButton);
		debugButton.addActionListener(debugListener);
		toolBar.add(debugButton);
		resumeButton.addActionListener(resumeListener);
		toolBar.add(resumeButton);
		terminateButton.addActionListener(terminateListener);
		toolBar.add(terminateButton);
		verifyButton.addActionListener(verifyListener);
		toolBar.add(verifyButton);
		toolBar.add(informationFlow);
		clearMsgWndwButton.addActionListener(clearListener);
		toolBar.add(clearMsgWndwButton);
		
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 0;
		center.add(toolBar, constraints);
		
		constraints.gridy = 2;
		constraints.weighty = 1;
		
		//adds everything except the toolbar (VariableTable, Messages, Editor)
		center.add(tabs, constraints);
		
		return center;
	}

	protected void search() {
		try {
			searchWindow.setVisible(true);
			searchWindow.refreshEditor(tabs.getCurrentEditor());
		} catch (NullPointerException nullPointerException) {
			searchWindow = new SearchWindow(tabs.getCurrentEditor());
		}
	}
	
	protected void clearMessages() {
		tabs.clearMessages();
	}
	
	protected void printTimestamp() {
		tabs.printTimestamp();
	}

	protected void redo() {
		tabs.redo();
		
	}

	protected void undo() {
		tabs.undo();
		
	}

	private void newProject() {
		tabs.newTab(core.newProject(), core.getSettings().getDefaultPath());		
	}

	
	/**
	 * Creates and returns the dropdown menu bar
	 * @return the JMenuBar containing the dropdown menus
	 */
	private JMenuBar menu() {

		JMenuBar menu = new JMenuBar();
		
		JMenu project = new JMenu("Project");	
		
		newProject.addActionListener(newProjectListener);
		project.add(newProject);		
		open.addActionListener(openListener);
		project.add(open);
		save.addActionListener(saveListener);
		project.add(save);
		saveAs.addActionListener(saveAsListener);
		project.add(saveAs);
		close.addActionListener(closeListener);
		project.add(close);
		quit.addActionListener(quitListener);
		project.add(quit);
		menu.add(project);
		
		JMenu edit = new JMenu("Edit");
		
		undo.addActionListener(undoListener);
		edit.add(undo);
		redo.addActionListener(redoListener);
		edit.add(redo);
		search.addActionListener(searchListener);
		edit.add(search);
		settings.addActionListener(settingsListener);
		edit.add(settings);
		menu.add(edit);
		
		JMenu run = new JMenu("Run");
		
		runProject.addActionListener(runProjectListener);
		run.add(runProject);
		debug.addActionListener(debugListener);
		run.add(debug);
		resume.addActionListener(resumeListener);
		run.add(resume);
		terminate.addActionListener(terminateListener);
		run.add(terminate);
		verify.addActionListener(verifyListener);
		run.add(verify);
		menu.add(run);
		
		return menu;
	}
	
	/**
	 * Opens the filechooser dialog and loads the file into the current tab
	 */
	private void open() {
		int button = fileChooser.showOpenDialog(null);
		if (button == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getPath();
			if (path.endsWith(".fm")) {
				tabs.openProject(core.newProject(), path, core.getCode(path));
				refreshSettings();
			} else {
				tabs.sendMessage("Wrong File-Type. Please open a Faehrmann-File!");
			}
		}
	}
	
	/**
	 * Saves the currently open file, depending on if it has been saved already or not, it asks for a place to save it at
	 */
	private void save() {
		if (tabs.hasFile()) {
			core.saveCode(tabs.getProjectID(), tabs.getPath(), tabs.getCode());
		} else {
			saveAs();
		}
	}
	
	/**
	 * save as function, opening the filechooser dialog, setting the tabname according to the filename
	 */
	private void saveAs() {
		int button = fileChooser.showSaveDialog(null);
		if (button == JFileChooser.APPROVE_OPTION) {
			if (!fileChooser.getSelectedFile().getPath().toLowerCase().endsWith(".fm")) {
				fileChooser.setSelectedFile(new File(fileChooser.getSelectedFile().getPath() + ".fm"));
			}
			String name = fileChooser.getSelectedFile().getName();
			name = name.substring(0, name.length() - 3);
			tabs.setNameOfTab(name);
			
			String path = fileChooser.getSelectedFile().getPath();
			core.saveCode(tabs.getProjectID(), path, tabs.saveProject(path));
		}
	}
	
	/**
	 * Closes the tab, requests a save if the code hasn't been saved yet
	 * @param quit a boolean stating if the programm is getting shut down or if just a tab is closed
	 * @return if the user wanted to save the code or not
	 */
	private boolean closeTab(boolean quit) {
		boolean aborted = false;
		if (!tabs.isSaved()) {
			aborted = askSave();
		}
		if (!aborted) {
			core.closeProject(tabs.closeTab());
			if (tabs.getTabCount() == 0 && !quit) {
				tabs.newTab(core.newProject(), core.getSettings().getDefaultPath());
			}
		}
		return aborted;
	}
	
	/**
	 * The request dialog if the user wants to save the file or not
	 * @return a boolean containing if the user wants to save or not
	 */
	private boolean askSave() {
		int pressedButton = JOptionPane.showConfirmDialog(this, "Do you want to save " 
				+ tabs.getNameOfTab() + " before closing it?", "Save?", JOptionPane.YES_NO_CANCEL_OPTION);
		if (pressedButton == JOptionPane.YES_OPTION) {
			save();
		}
		return (pressedButton == JOptionPane.CANCEL_OPTION);
	}
	
	/**
	 * Opens the setting window
	 */
	private void callSettings() {
		this.setEnabled(false);
		new SettingsWindow(core.getSettings(), this);
	}

	/**
	 * Closes each tab and quits the programm and possibly the searchwindow
	 */
	private void quit() {
		int tabCount = tabs.getTabCount();
		boolean aborted = false;
		for (int i = 0; !aborted && i < tabCount; i++) {
			aborted = closeTab(true);	
		}
		if (!aborted) {
			try {
				searchWindow.dispose();
			} catch (NullPointerException e) {}
			dispose();
		}	
	}
	
	/**
	 * requests a value for a variable which has been declared but not defined in the source code, setting 1 as default
	 * @param variableName the name of the variable 
	 * @return the value of the variable
	 */
    public BigInteger requestVariable(String variableName, int lineNumber) {
    	if(variableName.contains(".length[")) {
    		String temp[] = variableName.split(".length");
    		if (temp.length >= 2) {
        		variableName = temp[0] + temp[1];
    		}
    	}
        String input = (String) JOptionPane.showInputDialog(null, "Please state a value for int " + variableName +
        	" in line " + lineNumber + ": ", "Requesting Variable", JOptionPane.QUESTION_MESSAGE, null, null, 0);
        if (input == null) {
        	return null;
        }
        if  (input.matches("[-]?" + "[\\d]+")) {
            return new BigInteger(input);
        } else {
        	JOptionPane.showMessageDialog(this, "Not allowed input format.\n " +
                    "Please state integer.", "Error", JOptionPane.WARNING_MESSAGE);
        	return requestVariable(variableName, lineNumber);
        }
    }
    
    public void reactivate() {
    	this.setEnabled(true);
    }
	
    /**
     * the run method to interpret the source code
     */
	protected void run() {
		tabs.clearMessages();
		tabs.printTimestamp();
		tabs.removeLineMarkers();
		tabs.setInterpreterState(core.interpret(tabs.getCode()));
	}
	
	/**
	 * debug method, (de)activates the buttons to only allow valid input
	 */
	private void debug() {
		tabs.clearMessages();
		tabs.printTimestamp();
		tabs.removeLineMarkers();
		InterpreterState interpreterState = core.debug(tabs.getCode());
		if (!interpreterState.isDone() && interpreterState.isParserErrorFree()) {
			debugButtons();
		}
		tabs.setInterpreterState(interpreterState);
	}

	/**
	 * activates the debugging buttons, deactivates run, debug and verify
	 */
	private void debugButtons() {
		
		resume.setEnabled(true);
		terminate.setEnabled(true);
		resumeButton.setEnabled(true);
		terminateButton.setEnabled(true);
		
		runProject.setEnabled(false);
		debug.setEnabled(false);
		verify.setEnabled(false);
		runButton.setEnabled(false);
		debugButton.setEnabled(false);
		verifyButton.setEnabled(false);
	}

	/**
	 * restores the original buttons
	 */
	private void normalButtons() {
		resume.setEnabled(false);
		terminate.setEnabled(false);
		resumeButton.setEnabled(false);
		terminateButton.setEnabled(false);
		
		runProject.setEnabled(true);
		debug.setEnabled(true);
		verify.setEnabled(true);
		runButton.setEnabled(true);
		debugButton.setEnabled(true);
		verifyButton.setEnabled(true);
	}
	
	/**
	 * resume function if a breakpoint has been hit
	 */
	private void resume() {
		this.clearMessages();
		this.printTimestamp();
		tabs.removeLineMarkers();
		InterpreterState interpreterState = core.resume(tabs.getCode().getBreakpoints());
		if (interpreterState.isDone()) {
			normalButtons();
		}
		tabs.setInterpreterState(interpreterState);
	}
	
	/**
	 * terminates the debugging
	 */
	private void terminate() {
		core.terminate();
		tabs.removeLineMarkers();
		normalButtons();
	}

	/**
	 * verifies the source code
	 */
	protected void verify() {
		tabs.clearMessages();

		tabs.printTimestamp();
		tabs.setVerifyResult(core.verify(tabs.getCode(), informationFlow.isSelected()));
	}

	/**
	 * refreshes the settings, especially the fontsize of each tab
	 */
	public void refreshSettings() {
		Settings settings = core.getSettings();
		createFileChooser();
		
		int fontSize = settings.getFontSize();
        tabs.refreshFontSize(fontSize);
        core.saveSettings();
	}

	public void sendMessages(LinkedList<String> messages, LinkedList<Integer> errorLines) {
		tabs.sendMessages(messages, errorLines);
	}

	public boolean askResume() {
		int answer = JOptionPane.showConfirmDialog(null, "A Loop repeated a million times. Do you want to continue?");
	    boolean result = false;
	    if (answer == JOptionPane.YES_OPTION) {
	    	result = true;
	    }
	    return result;
	}
}
