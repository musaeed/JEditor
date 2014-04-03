package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import Components.ColoredButton;
import Gui.JEditor;

public class ColorOptionDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private static ColorOptionDialog instance = null;
	private JScrollPane scroll;
	private ColoredButton arr[];
	private JPanel bPanel;
	
	public static ColorOptionDialog getInstance(){
		if(instance == null){
			instance = new ColorOptionDialog();
		}
		return instance;
	}
	
	private ColorOptionDialog(){
		init();
		addButtonsToScrollPane();
		addActions();
		disposeListener();
	}
	
	public void init(){
		
		arr = new ColoredButton[5];
		bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(5,1));
		
		setTitle("Select an option");
		setLayout(new BorderLayout());
		setSize(new Dimension(550,600));
		setModal(true);
		setLocationRelativeTo(JEditor.frame);
	}
	
	public void addButtonsToScrollPane(){
		arr[0] = ColoredButton.GetRandomButton("Background color", "change the background color");
		arr[1] = ColoredButton.GetRandomButton("Text color", "change the text color");
		arr[2] = ColoredButton.GetRandomButton("Highlighter color", "change the current line highlighter color");
		arr[3] = ColoredButton.GetRandomButton("Row number color", "change the current row number color");
		arr[4] = ColoredButton.GetRandomButton("Row number background color", "change the current row number background color");
		
		for(ColoredButton c : arr){
			bPanel.add(c);
		}
		
		scroll = new JScrollPane(bPanel , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll , BorderLayout.CENTER);
	}
	
	public void addActions(){
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				JColorChooser.showDialog(JEditor.frame, "Select color", null);		
			}
		};
		
		for(ColoredButton c : arr){
			c.addActionListener(action);
		}
	}
	
	public void disposeListener(){
		arr[0].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 1);
		arr[0].getActionMap().put(1, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

}
