package alarm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import Components.CButton;
import Components.CLabel;
import Components.CTabbedPane;
import Gui.JEditor;
import Layouts.FlowCustomLayout;
import Utility.ImageLoader;

public class AlarmDialog {
	
	private static AlarmDialog instance;
	
	public static JDialog dialog;
	private CButton close;
	public static CButton add,remove,edit,settings;
	private CLabel timeLabel;
	private Timer timer;
	
	public static AlarmDialog getInstance(){
		if(instance == null){
			instance = new AlarmDialog();
		}
		return instance;
	}
	
	private AlarmDialog(){
		init();
	}
	
	public void init(){
		
		dialog = new JDialog();
		dialog.setLayout(new BorderLayout());
		dialog.setTitle("Alarms");
		dialog.setSize(new Dimension(800, 500));
		dialog.setModal(true);
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.add(getButtonPanel() , BorderLayout.SOUTH);
		dialog.add(getMenuPanel() , BorderLayout.WEST);
		dialog.add(getMainPanel() , BorderLayout.CENTER);
	}
	
	public JPanel getMenuPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		add = new CButton("Add alarm", "add a new alarm", '0', null, null);
		add.setIcon(ImageLoader.loadImage("images/add.png"));
		remove = new CButton("Remove alarm", "remove an alarm", '0', null, null);
		remove.setIcon(ImageLoader.loadImage("images/remove.png"));
		edit = new CButton("Edit alarm", "edit and change an alarm", '0', null, null);
		edit.setIcon(ImageLoader.loadImage("images/edit.png"));
		settings = new CButton("Settings", "change the preferences", '0', null, null);
		settings.setIcon(ImageLoader.loadImage("images/settings.png"));
		
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AlarmUtilities.addAlarmDialog();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(AlarmTable.getInstance().getRealTable().getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(AlarmDialog.getInstance().getDialog(), "There is no row selected to edit.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(AlarmTable.getInstance().getRealTable().getSelectedRowCount() > 1){
					JOptionPane.showMessageDialog(AlarmDialog.getInstance().getDialog(), "Please select a single row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				AlarmUtilities.showEditDialog(AlarmUtilities.getInstance().getList().get(AlarmTable.getInstance().getRealTable().getSelectedRow()));
				
			}
		});
		
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(AlarmTable.getInstance().getRealTable().getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(AlarmDialog.getInstance().getDialog(), "There is no row selected to remove.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(AlarmTable.getInstance().getRealTable().getSelectedRowCount() > 1){
					JOptionPane.showMessageDialog(AlarmDialog.getInstance().getDialog(), "Please select a single row to remove.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int result = JOptionPane.showConfirmDialog(AlarmDialog.getInstance().getDialog(), "Are you sure you want to remove the selected alarm?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(result != JOptionPane.YES_OPTION ){
					return;
				}
				
				int[] rows = AlarmTable.getInstance().getRealTable().getSelectedRows();
				
					for(Integer i : rows){
						AlarmUtilities.getInstance().getList().remove(AlarmUtilities.getInstance().getList().get(i));
					}
				
				   for(int i=0;i<rows.length;i++){
				     ((TableModel)AlarmTable.getInstance().getRealTable().getModel()).removeRow(rows[i]-i);
				   }
			}
		});
		
		settings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Preferences.getInstance().show();	
			}
		});
		
		
		panel.add(add);
		panel.add(remove);
		panel.add(edit);
		panel.add(settings);
		return panel;
	}
	
	public JPanel getMainPanel(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(AlarmTable.getInstance().getTable());
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Alarms"));
		return panel;
	}
	
	public JPanel getButtonPanel(){
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel left = new JPanel(new FlowCustomLayout(FlowCustomLayout.LEFT));
		JPanel right = new JPanel(new FlowCustomLayout(FlowCustomLayout.RIGHT));
		
		close = new CButton("Close", "close and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
				CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
			}
		});
		
		timeLabel = new CLabel(Calendar.getInstance().getTime().toString());
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeLabel.setText(Calendar.getInstance().getTime().toString());
			}
		});
		timer.start();
		
		right.add(timeLabel);
		left.add(close);
		
		panel.add(right,BorderLayout.WEST);
		panel.add(left , BorderLayout.EAST);
		return panel;
		
	}
	
	public void show(){
		dialog.setVisible(true);
	}
	
	public JDialog getDialog(){
		return dialog;
	}

}
