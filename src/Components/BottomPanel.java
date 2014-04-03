package Components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Layouts.FlowCustomLayout;

public class BottomPanel extends JPanel{

	private static final long serialVersionUID = 4573410495714065035L;
	public static JLabel fileType;
	public static JLabel rowCount;
	
	public BottomPanel(){
		init();
		addToPanel();
	}
	
	public void init(){
		fileType = new JLabel("Untitled");
		rowCount = new JLabel("Row: 1 Column: 1");
	}
	
	public void addToPanel(){
		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel(new BorderLayout()) , p2 = new JPanel(new FlowLayout()), p3 = new JPanel(new FlowCustomLayout(FlowLayout.LEFT));
		
		p1.add(fileType , BorderLayout.CENTER);
		p2.add(CProgressBar.getInstance());
		p3.add(rowCount , BorderLayout.CENTER);
		
		add(p1 , BorderLayout.WEST);
		add(p2 , BorderLayout.CENTER);
		add(p3 , BorderLayout.EAST);
	}

}
