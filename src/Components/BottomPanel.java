package Components;

import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class BottomPanel extends JPanel{

	private static final long serialVersionUID = 4573410495714065035L;
	private CButton increase,decrease;
	public static JLabel fileType;
	public static JLabel rowCount;
	
	public BottomPanel(){
		init();
		addToPanel();
	}
	
	public void init(){
		increase = new CButton("", "increase the text size", '1', KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_DOWN_MASK), "");
		increase = new CButton("", "decrease the text size", '1', KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK), "");
		fileType = new JLabel("");
		rowCount = new JLabel("Row: 0 Column: 0");
	}
	
	public void addToPanel(){
		setLayout(new BorderLayout());
		add(fileType , BorderLayout.WEST);
		add(rowCount , BorderLayout.EAST);
	}

}
