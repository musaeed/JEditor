package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class SearchDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private static SearchDialog instance = null;
	private JLabel sLabel,rLabel;
	private JTextField sField,rField;
	private CButton search,replace,replaceAll,close;
	private JCheckBox cSensitive;
	
	public static SearchDialog getInstance(){
		if(instance == null){
			instance = new SearchDialog();
		}
		return instance;
	}
	
	private SearchDialog(){
		init();
		addToDialog();
	}
	
	public void init(){
		setTitle("Search");
		setSize(new Dimension(500,400));
		setLocationRelativeTo(JEditor.frame);
	}
	
	public void addToDialog(){
		setLayout(new BorderLayout());
		add(getClosePanel() , BorderLayout.SOUTH);
	}
	
	public JPanel getClosePanel(){
		JPanel jp = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		close = new CButton("Close", "dispose the dialog", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		jp.add(close);
		
		return jp;
	}

}
