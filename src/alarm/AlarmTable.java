package alarm;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableCellRenderer;

public class AlarmTable {

	private static AlarmTable instance;

	private JTable table;
	private JScrollPane scroll;

	public static AlarmTable getInstance(){
		if(instance == null){
			instance = new AlarmTable();
		}
		return instance;
	}

	private AlarmTable(){
		init();
		addFeatures();
	}

	public void init(){
		
		table = new JTable(){

			private static final long serialVersionUID = 4162413631142423664L;

			@Override
			public Component prepareRenderer(TableCellRenderer r , int row , int column){

				Component c = super.prepareRenderer(r, row, column);
				c.setForeground(Color.BLACK);

				if(column % 2 !=0){
					c.setBackground(Color.LIGHT_GRAY);
				}
				else{
					c.setBackground(Color.WHITE);
				}

				if(isCellSelected(row, column)){
					c.setBackground(new Color(221, 72, 20));
					c.setForeground(Color.WHITE);
				}

				return c;
			}
		};

		table.setModel(new TableModel());
		table.getColumnModel().getColumn(0).setCellRenderer(new TableCellModel("images_small/alarm.png"));
		table.getColumnModel().getColumn(1).setCellRenderer(new TableCellModel("images_small/message.png"));
		table.getColumnModel().getColumn(2).setCellRenderer(new TableCellModel("images_small/priority.png"));
		table.setComponentPopupMenu(new TablePopupMenu());

		scroll = new JScrollPane(table);
		scroll.setComponentPopupMenu(new TablePopupMenu());
	}
	
	public void addFeatures(){
		
		scroll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK), 0);
		scroll.getActionMap().put(0, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.selectAll();
			}
		});
		
		scroll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), 1);
		scroll.getActionMap().put(1, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.clearSelection();
			}
		});
	}
	
	public JScrollPane getTable(){
		return scroll;
	}
	
	public JTable getRealTable(){
		return table;
	}

}
