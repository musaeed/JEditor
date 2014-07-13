package Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;

public class CTabPopupMenu extends JPopupMenu{

	private static final long serialVersionUID = -8372031322026456797L;
	private CMenuItem close,closeAll,closeOthers;


	public CTabPopupMenu(){
		init();
		addActions();
		addToMenu();
	}

	public void init(){
		close = new CMenuItem("Close", "close the current tab", 'C', null);
		closeAll = new CMenuItem("Close all", "close all the tabs", 'L', null);
		closeOthers = new CMenuItem("Close other tabs", "close all the other tabs except for the current one", 'O', null);
	}

	public void addActions(){
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				RibbonMenu.close.doClick();
			}
		});

		closeAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RibbonMenu.closeall.doClick();
			}
		});
		
		closeOthers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}

	public void addToMenu(){
		add(close);
		add(closeAll);
		add(closeOthers);
	}
}
