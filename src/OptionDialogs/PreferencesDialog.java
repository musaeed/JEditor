package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class PreferencesDialog {
	
	private JDialog dialog;
	private CButton ok,apply,cancel;
	
	public PreferencesDialog(){
		init();
	}
	
	public void init(){
		dialog = new JDialog();
		dialog.setTitle("Preferences");
		dialog.setSize(new Dimension(600,500));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setModal(true);
		dialog.setLayout(new BorderLayout());
		dialog.add(getBPanel() , BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
	
	public JPanel getBPanel(){
		JPanel panel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		ok = new CButton("Ok", "save the settings and go back", 'O', null, null);
		apply = new CButton("Apply", "apply the settings", 'A', null, null);
		cancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		apply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		panel.add(ok);
		panel.add(apply);
		panel.add(cancel);
		
		return panel;
	}

}
