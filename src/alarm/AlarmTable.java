package alarm;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	
	public JScrollPane getTable(){
		return scroll;
	}

}
