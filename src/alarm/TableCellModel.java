package alarm;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Utility.ImageLoader;


public class TableCellModel  extends DefaultTableCellRenderer{

	private static final long serialVersionUID = -8169450133615777227L;
	private String resource;
	
	public TableCellModel(String resource){
		super();
		this.resource = resource;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
		setText((String)value);
		setIcon(ImageLoader.loadImage(resource));
		return this;
	}
}
