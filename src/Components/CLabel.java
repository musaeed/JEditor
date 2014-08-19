package Components;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;

public class CLabel extends JLabel{


	private static final long serialVersionUID = 1L;

	public CLabel(){
		addListener();
		addMenu();
	}

	public CLabel(String text){
		setText(text);
		addListener();
		addMenu();
	}

	public void addListener(){
		
		setForeground(Color.BLACK);
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(new Color(215, 72, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.BLACK);
			}

		});
	}
	
	public void addMenu(){
		JPopupMenu menu = new JPopupMenu();
		final CMenuItem cut = new CMenuItem("Cut text", "cut the text to the clipboard", 'C', null);
		final CMenuItem copy = new CMenuItem("Copy text", "copy the text to clipboard", 'o', null);
		
		cut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				copy.doClick();
			}
		});
		
		copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(getText()), null);
			}
		});
		
		menu.add(cut);
		menu.add(copy);
		
		setComponentPopupMenu(menu);
	}


}
