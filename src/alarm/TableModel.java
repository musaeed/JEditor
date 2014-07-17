package alarm;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel{

	private static final long serialVersionUID = 5828057532423477166L;

	public TableModel(){
		init();
	}

	public void init(){
		addColumn("Time");
		addColumn("Alert Message");
		addColumn("Priority");
		
	}

	public void updateAlarms(){
		
		ArrayList<Alarm> alarms = AlarmUtilities.getInstance().getList();
		
		setRowCount(0);
		
		for(int i = 0 ; i < alarms.size(); i++){
			Object [] data = {alarms.get(i).getTime().toString() , alarms.get(i).getAlertMessage() , alarms.get(i).getPriority()};
			addRow(data);
		}
		
	}
	
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}