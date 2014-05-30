package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class MakeSuggestionDialog {
	
	private static MakeSuggestionDialog instance;
	private JDialog dialog;
	private JTextField summary;
	private JTextPane description;
	private CButton suggest,cancel;
	
	public static MakeSuggestionDialog getInstance(){
		
		if(instance == null){
			instance = new MakeSuggestionDialog();
		}
		return instance;
	}
	
	private MakeSuggestionDialog(){
		init();
	}
	
	public void init(){
		
		dialog = new JDialog();
		dialog.setModal(true);
		dialog.setSize(new Dimension(500,400));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setLayout(new BorderLayout());
		dialog.setTitle("Make a suggestion");
		dialog.add(getForm() , BorderLayout.CENTER);
		dialog.add(getButtons() , BorderLayout.SOUTH);
	}
	
	public JPanel getForm(){
		
		JPanel panel = new JPanel();
		summary = new JTextField(30);
		description = new JTextPane();
		panel.setLayout(new BorderLayout());
		JPanel sPanel = new JPanel(new BorderLayout());
		sPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Summary"));
		sPanel.add(summary , BorderLayout.CENTER);
		JPanel dPanel = new JPanel(new BorderLayout());
		dPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Description"));
		dPanel.add(description , BorderLayout.CENTER);
		panel.add(sPanel , BorderLayout.NORTH);
		panel.add(dPanel , BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel getButtons(){
		JPanel panel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		suggest = new CButton("Suggest", "make a suggestion to the developer", 'S', null, null);
		cancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		addActions();
		panel.add(suggest);
		panel.add(cancel);
		return panel;
	}
	
	public void addActions(){
		suggest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
	}
	
	public void show(){
		dialog.setVisible(true);
	}

}
