package gui;

import interpreter.InterpreterState;

import java.io.File;
import java.util.LinkedList;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import verifier.Result;

import core.Code;

/**
 * This class keeps track of all the tabs in the program. It extends JTabbedPane which already implements most functionalities, 
 * but adds certain functions for our program.
 * 
 * @author Florian Pohl
 * @author Marc Beuter
 */

public class TabManager extends JTabbedPane{

	public TabManager(){
		super();
		super.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	public void newTab(int tabID, String path) {
		createTab("unnamed", tabID, path);
	}
	
	/**
	 * creates a tab by an already existing project
	 * @param name name of the project
	 * @param tabID id of the project
	 * @param path path to the file
	 */
	private void createTab(String name, int tabID, String path) {
		Tab panel = new Tab(tabID, path);
		this.addTab(name, panel);
		this.setSelectedIndex(this.getTabCount() - 1);
	}
	
	public int closeTab() {
		int id = this.getCurrentTabId();
		this.removeTabAt(this.getSelectedIndex());
		return (id);
	}
	
	public void clearMessages() {
		this.getCurrentTab().clearMessages();
	}
	
	public void printTimestamp() {
		this.getCurrentTab().printTimestamp();
	}
	
	private Tab getCurrentTab() {
		return (Tab)this.getSelectedComponent();
	}
	
	public int getCurrentTabId() {
		return getCurrentTab().getTabID();
	}
	
	public void undo() {
		getCurrentTab().undo();
	}
	
	public void redo() {
		getCurrentTab().redo();
	}

	public boolean isSaved() {
		return getCurrentTab().isSaved();
	}

	/**
	 * loads code into the editor textfield
	 * @param project the project which holds the code
	 */
	private void setCode(Code project) {
		getCurrentTab().setCode(project);
	}
	
	public void openProject(int tabID, String path, Code project) {
		String fileName = new File(path).getName();
		createTab(fileName.substring(0, fileName.length() - 3), tabID, path); //cut file-ending
		setCode(project);
	}

	public boolean hasFile() {
		return getCurrentTab().hasFile();
	}

	public Code saveProject(String path) {
		return getCurrentTab().saveCode(path);
	}

	public Code getCode() {
		return getCurrentTab().getCode();
	}

	public String getPath() {
		return getCurrentTab().getPath();
	}

	public void setInterpreterState(InterpreterState interpreterState) {
		getCurrentTab().setInterpreterState(interpreterState);
	}
	
	public int getProjectID() {
		return getCurrentTab().getTabID();
	}
	
	public void setNameOfTab(String name) {
		this.setTitleAt(this.getSelectedIndex(), name);
	}
	
	public String getNameOfTab() {
		return this.getTitleAt(this.getSelectedIndex());
	}

	/**
	 * refreshes the fontsize in each tab
	 * @param fontSize the desired font size
	 */
	public void refreshFontSize(int fontSize) {
		for (int i = 0; i < this.getTabCount(); i++) {
			((Tab)this.getComponentAt(i)).refreshFontSize(fontSize);
		}	
	}

	public void setVerifyResult(Result result) {
		getCurrentTab().setVerifyResult(result);
	}

	public JTextPane getCurrentEditor() {
		return getCurrentTab().getEditor();
	}

	public void removeLineMarkers() {
		getCurrentTab().removeLineMarkers();
	}

	public void sendMessages(LinkedList<String> messages, LinkedList<Integer> errorLines) {
		getCurrentTab().sendMessages(messages, errorLines);
	}
	
	public void sendMessage(String message) {
		getCurrentTab().sendMessage(message);
	}
}
