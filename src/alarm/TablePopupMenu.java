package alarm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import Utility.ImageLoader;

import Components.CMenuItem;

public class TablePopupMenu extends JPopupMenu{
	

	private static final long serialVersionUID = -8582866773216034702L;
	private CMenuItem edit,remove,removeAll,clearSelection,selectAll;
	
	public TablePopupMenu(){
		init();
		addComponents();
		addIcons();
		addActions();
	}
	
	public void init(){
		edit = new CMenuItem("Edit", "edit the alarm", 'E', KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		remove = new CMenuItem("Remove", "remove the selected alarm", 'R', KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		removeAll = new CMenuItem("Remove all", "remove all the alarms", 'A', KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK));
		selectAll = new CMenuItem("Select all", "select all the alarms", 'S', KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		clearSelection = new CMenuItem("Clear selection", "clear the selection form the tabkle", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
	}
	
	public void addComponents(){
		
		add(edit);
		add(remove);
		add(removeAll);
		addSeparator();
		add(selectAll);
		add(clearSelection);
		
	}
	
	public void addIcons(){
		edit.setIcon(ImageLoader.loadImage("images_small/edit.png"));
		remove.setIcon(ImageLoader.loadImage("images_small/remove.png"));
		removeAll.setIcon(ImageLoader.loadImage("images_small/removeall.png"));
		selectAll.setIcon(ImageLoader.loadImage("images_small/selectall.png"));
		clearSelection.setIcon(ImageLoader.loadImage("images_small/clear.png"));	
	}

	public void addActions(){
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AlarmDialog.edit.doClick();
			}
		});
		
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AlarmDialog.remove.doClick();
			}

		});
		
		removeAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				int result = JOptionPane.showConfirmDialog(AlarmDialog.getInstance().getDialog(), "Are you sure you want to delete all the alarms?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(result == JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION){
					return;
				}
				
				AlarmUtilities.getInstance().getList().clear();
				AlarmTable.getInstance().getRealTable().removeAll();
				((TableModel)AlarmTable.getInstance().getRealTable().getModel()).updateAlarms();
			}
		});
		
		selectAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AlarmTable.getInstance().getRealTable().selectAll();
			}
		});
		
		clearSelection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AlarmTable.getInstance().getRealTable().clearSelection();
			}
		});
	}
}
