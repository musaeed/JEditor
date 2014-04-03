package OptionDialogs;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import Gui.JEditor;

public class StatisticsDialog{

	private JDialog dialog;
	
	public StatisticsDialog(){
		init();
		addMouseListener();
	}
	
	public void init(){
		dialog = new JDialog();
		dialog.setUndecorated(true);
		dialog.setSize(new Dimension(500,400));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setModal(true);
	}
	
	public void show(){
		dialog.setVisible(true);
	}
	
	public void addMouseListener(){
		dialog.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				dialog.dispose();
			}
			
		});
	}
}
