package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.freixas.jcalendar.JCalendar;

import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class CalendarDialog {
	
	private JDialog dialog;
	private CButton close;
	private JCalendar calendar;
	
	public CalendarDialog(){
		System.out.println("Building calendar...");
		init();
	}
	
	public void init(){
		dialog = new JDialog();
		dialog.setSize(new Dimension(900,600));
		dialog.setTitle("Calendar");
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setModal(true);
		dialog.setLayout(new BorderLayout());
		dialog.add(getCalendar() , BorderLayout.CENTER);
		dialog.add(getButtonPanel() , BorderLayout.SOUTH);
	}
	
	public JPanel getCalendar(){
		JPanel panel = new JPanel(new BorderLayout());
		calendar = new JCalendar();
		panel.add(calendar , BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel getButtonPanel(){
		JPanel panel = new JPanel(new FlowCustomLayout(FlowCustomLayout.RIGHT));
		close = new CButton("Close", "close the calendar and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		
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
