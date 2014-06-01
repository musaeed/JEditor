package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import Components.BottomPanel;
import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class UpdateInstructionsDialog {

	private static UpdateInstructionsDialog instance;
	private JDialog dialog;
	private JEditorPane pane;
	private CButton close;
	
	public static UpdateInstructionsDialog getInstance(){
		
		if(instance == null){
			instance = new UpdateInstructionsDialog();
		}
		
		return instance;
	}
	
	public UpdateInstructionsDialog(){
		init();
	}
	
	public void init(){
		
		dialog = new JDialog();
		pane = new JEditorPane();
		close = new CButton("Close", "close the dialog and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		setPaneProperties();
		setDialogProperties();	
	}
	
	public void setPaneProperties(){
		pane.setEditable(false);
		pane.setBackground(dialog.getBackground());
		pane.setOpaque(false);
		
		InputStream input = BottomPanel.class.getResourceAsStream("/other/update_instructions.html");
		Scanner in = new Scanner(input);
		String temp = "";
		
		while(in.hasNext()){
			temp += in.nextLine();
		}
		in.close();
		
		pane.setContentType("text/html");
		pane.setText(temp);
	}
	
	public void setDialogProperties(){
		dialog.setTitle("How to update JEditor?");
		dialog.setSize(new Dimension(800,600));
		dialog.setLayout(new BorderLayout());
		dialog.add(getInstructionsPanel() , BorderLayout.CENTER);
		dialog.add(getButtonPanel() , BorderLayout.SOUTH);
		dialog.setLocationRelativeTo(JEditor.frame);
	}
	
	public JPanel getInstructionsPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Instructions"));
		panel.setLayout(new BorderLayout());
		panel.add(pane , BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel getButtonPanel(){
		JPanel panel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		
		panel.add(close);
		return panel;
	}
	
	public void show(){
		dialog.setVisible(true);
	}
}
