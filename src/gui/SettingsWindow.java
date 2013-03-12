package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import core.Settings;


/**
 * This class represents the settings window, which is used to change the settings of the programm, including text size, standard file save location, 
 * a maximum loop count for z3, z3 parameters
 * 
 * @author Florian Pohl
 * @author Marc Beuter 
 */

public class SettingsWindow extends JFrame{
	
	private MainWindow mainWndw;
	private Settings settings;
	private String[] sizes = {"8", "10", "12", "14", "18", "24", "36"};
	private JComboBox<String> fontSize = new JComboBox<String>(sizes);
	private JTextField path = new JTextField();
	private JButton pathButton = new JButton("browse");
	private JSlider maxLoopCount = new JSlider();
	private JTextField maxLoopField = new JTextField();
	private JTextField z3Parameter = new JTextField();
	private JTextField timeout = new JTextField();
	private JButton okButton = new JButton("Ok");
	private JButton cancelButton = new JButton("Cancel");
	
	private JFileChooser fileChooser;
	
	public SettingsWindow(Settings settings, MainWindow mainWndw) {
		super("Settings");
		
		this.mainWndw = mainWndw;
		this.settings = settings;
				
		JPanel window = new JPanel(new GridBagLayout());
		add(window);
		
		//overriding the standard close function
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            quit();
	        }
	    });
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		setResizable(false);
		
		fileChooser = new JFileChooser(new File(settings.getDefaultPath()));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		JLabel fontSizeLabel = new JLabel("Font Size: ");
		window.add(fontSizeLabel, constraints);
		
		constraints.gridx = 3;
		String size = String.valueOf(settings.getFontSize());
		for (int i = 0; i < fontSize.getItemCount(); i++) {
			if (size.equals(fontSize.getItemAt(i))) {
				fontSize.setSelectedIndex(i);
			}
		}
		window.add(fontSize, constraints);
		
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		JLabel pathLabel = new JLabel("Default Save Location: ");
		window.add(pathLabel, constraints);
		
		constraints.gridx = 2;
		constraints.weightx = 1;
		path.setText(settings.getDefaultPath());
		path.addFocusListener(pathChangeListener);
		window.add(path, constraints);
		
		constraints.gridx = 3;
		constraints.weightx = 0;
		pathButton.addActionListener(pathButtonListener);
		window.add(pathButton, constraints);
		
		constraints.gridx = 1;		
		constraints.gridy = 3;
		JLabel maxLoopLabel = new JLabel("Maximal Loop Repetition For Z3: ");
		window.add(maxLoopLabel, constraints);
		
		constraints.gridx = 2;
		constraints.weightx = 1;
		maxLoopCount.setMajorTickSpacing(5);
		maxLoopCount.setMinorTickSpacing(1);
		maxLoopCount.setMaximum(50);
		maxLoopCount.setMinimum(1);
		maxLoopCount.setPaintTicks(true);
		maxLoopCount.addChangeListener(maxLoopListener);
		maxLoopCount.setValue(settings.getMaxLoopCount());
		window.add(maxLoopCount, constraints);
		
		constraints.gridx = 3;
		constraints.weightx = 0;
		maxLoopField.setEditable(false);
		maxLoopField.setText(String.valueOf(settings.getMaxLoopCount()));
		window.add(maxLoopField, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		JLabel z3ParameterLabel = new JLabel("Z3 Call: ");
		window.add(z3ParameterLabel, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 2;
		z3Parameter.setText(settings.getz3Path());
		z3Parameter.setToolTipText("Path for Z3 component and Z3 parameters e.g. /home/Max/bin/z3 -smt2");
		window.add(z3Parameter, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		JLabel timeoutLabel = new JLabel("Timeout for Z3: ");
		window.add(timeoutLabel, constraints);
		
		constraints.gridx = 2;
		timeout.addFocusListener(timeoutChangeListener);
		timeout.setText(String.valueOf(settings.getZ3Timeout()));
		window.add(timeout, constraints);
		
		constraints.gridx = 3;
		JLabel timeoutLabel2 = new JLabel("msec");
		window.add(timeoutLabel2, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		window.add(buttonPanel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		okButton.addActionListener(okButtonListener);
		buttonPanel.add(okButton, constraints);		
		
		constraints.gridx = 2;
		cancelButton.addActionListener(cancelButtonListener);
		buttonPanel.add(cancelButton, constraints);
		
		
		setSize(520, 220);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	}
	
	//Listeners for each settings function
	
	ChangeListener maxLoopListener = new ChangeListener() {
	    public void stateChanged(ChangeEvent slide) {
	       maxLoopField.setText(String.valueOf(((JSlider)slide.getSource ()).getValue()));
	       }
	   };
	
	FocusListener pathChangeListener = new FocusListener() { 
		    
		@Override
		public void focusGained(FocusEvent e) {}
		
		@Override
		public void focusLost(FocusEvent e) {
			pathChange();
		};
	};
	
	FocusListener timeoutChangeListener = new FocusListener() {
		 
		@Override
		public void focusGained(FocusEvent e) {}

		@Override
		public void focusLost(FocusEvent e) {
			timeoutChange();
		};
	};

	AbstractAction pathButtonListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pathButton();
		}
		
	};
	
	AbstractAction okButtonListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			okButton();
		}
		
	};
	
	AbstractAction cancelButtonListener = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			quit();
		}
		
	};
	
	/**
	 * opens the dialog to choose the standard file saving location
	 */
	private void pathButton() {
		int button = fileChooser.showDialog(null, "Choose");
		if (button == JFileChooser.APPROVE_OPTION) {
			path.setText(fileChooser.getSelectedFile().getPath());
		}
	}
	
	private void pathChange() {
		if ((new File(path.getText())).isDirectory()) {
        	fileChooser.setSelectedFile(new File(path.getText()));
        } else {
        	JOptionPane.showMessageDialog(this, "Not a valid path.\n Please enter a " +
        			"valid filedirectory.", "Error", JOptionPane.WARNING_MESSAGE);
        	path.setText(fileChooser.getCurrentDirectory().getAbsolutePath());
        }
	}
	
	private void timeoutChange() {
		if (!timeout.getText().matches("[\\d]+")) {
			JOptionPane.showMessageDialog(this, "Not a integer.\n Please enter a " +
        			"valid integer.", "Error", JOptionPane.WARNING_MESSAGE);
			timeout.setText(String.valueOf(settings.getZ3Timeout()));
		} else if (timeout.getText().length() > 8) {
			JOptionPane.showMessageDialog(this, "The time is too long.\n Please enter a " +
        			"shorter Time.", "Error", JOptionPane.WARNING_MESSAGE);
			timeout.setText(String.valueOf(settings.getZ3Timeout()));
		}
	}
	
	/**
	 * saves the settings into the settings object and refreshes them in the main window
	 */
	private void okButton() {
		if ((new File(path.getText())).isDirectory() && timeout.getText().matches("[\\d]+")) {
			settings.setFontSize(Integer.parseInt((String)fontSize.getSelectedItem()));
			settings.setDefaultPath(path.getText());
			settings.setMaxLoopCount(maxLoopCount.getValue());
			settings.setz3Path(z3Parameter.getText());
			settings.setZ3Timeout(Integer.parseInt(timeout.getText()));
			mainWndw.refreshSettings();
			quit();
		}
	}
	
	private void quit() {
		mainWndw.reactivate();
	    dispose();	
	}

	
	

}