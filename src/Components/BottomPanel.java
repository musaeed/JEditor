package Components;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Layouts.FlowCustomLayout;

public class BottomPanel extends JPanel{

	private static final long serialVersionUID = 4573410495714065035L;
	public static JLabel fileType;
	public static JLabel rowCount;
	public static JLabel progressLabel;
	
	public BottomPanel(){
		init();
		addToPanel();
	}
	
	public void init(){
		fileType = new JLabel("Untitled");
		fileType.setFont(new Font("Ubuntu", Font.PLAIN, 15));
		rowCount = new JLabel("Row: 1 Column: 1");
		rowCount.setFont(new Font("Ubuntu", Font.PLAIN, 15));
		progressLabel = new JLabel();
	}
	
	public void addToPanel(){
		setLayout(new GridLayout(1,4));
		
		JPanel p1 = new JPanel(new FlowCustomLayout(FlowLayout.LEFT)) , p2 = new JPanel(new FlowLayout()), p3 = new JPanel(new FlowLayout()) , p4 = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		
		p1.add(fileType);
		p2.add(progressLabel);
		p3.add(CProgressBar.getInstance());
		p4.add(rowCount);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
	}

}
