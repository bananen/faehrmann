package gui;

import interpreter.InterpreterState;
import interpreter.Array;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.Font;
import java.math.BigInteger;

/**
 * This class represents a variable table for the debugging process of a program
 * @author Florian Pohl
 * @author Marc Beuter
 */

public class VariableTable extends JScrollPane {

	private JTable table;
	
	private DefaultTableModel tableModel;
	
	public VariableTable() {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		super.getViewport().add(table);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(50, 50));
	}
	
	/**
	 * prints out the variables the interpreterstate contains
	 * @param interpreterState the interpreterstate which contains the variables
	 */
	public void writeVariables(InterpreterState interpreterState) {
		String[] data = new String[4];
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Scope");
		tableModel.addColumn("Type");
		tableModel.addColumn("Name");
		tableModel.addColumn("Value");

		//For each scope in the interpreterstate
		for (int i = 0; i < interpreterState.getScopeCount(); i++) {
			//For each variable in the scope
			for (int j = 0; j < interpreterState.getScopeSize(i); j++) {
				//print out the data in the variable table
				data[0] = String.valueOf(i);
				data[1] = interpreterState.getVariable(i,  j).getType();
				data[2] = interpreterState.getVariable(i,  j).getName();
				if (!data[1].equals("bool")) {
					data[3] = interpreterState.getVariable(i,  j).getValue().toString();
					data[1] = "int";
				} else if (interpreterState.getVariable(i,  j).getValue().equals(new BigInteger("0"))) {
					data[3] = "false";
				} else {
					data[3] = "true";
				}
					
				tableModel.addRow(data);
				
				//In case the variable is an array, check every arrayvalue as well
				if (data[2].endsWith(".length")) {
					Array temp = (Array)interpreterState.getVariable(i, j);
					for (int arrayCounter = 0; arrayCounter < temp.getValue().intValue(); arrayCounter++) {
						BigInteger bigIntCounter = BigInteger.valueOf((long)arrayCounter);
						data[0] = String.valueOf(i); 
						data[1] = temp.getType();
						data[2] = temp.getName().split(".length")[0] + "[" + arrayCounter + "]";
						data[3] = temp.getValue(bigIntCounter).toString();
						
						if (data[1].equals("bool[]")) { 
							if (data[3].equals("0")) {
								data[3] = "false";
							} else {
								data[3] = "true";
							}
						}
						tableModel.addRow(data);
					}
				}
			}
				
		}
		
		table.setModel(tableModel);
		table.doLayout();
		table.setEnabled(false);
		 
		super.getViewport().add(table);
		 
	}

	public void refreshFontSize(int fontSize) {
		Font editorFont = table.getFont();
		Font newFont = new Font(editorFont.getName(), editorFont.getStyle(), fontSize);
		table.setFont(newFont);
		table.setRowHeight(fontSize + 5);
	}
}