package component_listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;


public class ScrollbarListener extends MouseAdapter implements MouseMotionListener{
	
	private static ScrollbarListener instance;
	private JPopupMenu menu;
	private JLabel label;
	
	public static ScrollbarListener getInstance(){
		if(instance == null){
			instance = new ScrollbarListener();
		}
		return instance;
	}
	
	private ScrollbarListener(){
		menu = new JPopupMenu();
		label = new JLabel();
		menu.add(label);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		JScrollBar bar = (JScrollBar)e.getSource();
		double value = bar.getValue();
        double max = bar.getMaximum() - bar.getVisibleAmount();
        double h = bar.getHeight();
        label.setText("<html><font color=\"white\" face=\"URW Chancery L\" size=4.5>" + (int) (100*value/max) + "%  </font></html>");
		menu.show(bar.getParent(), (int)bar.getLocation().getX() - menu.getWidth()-2, (int) ((h - menu.getHeight())*value/max));
        
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		menu.setVisible(false);
	}

	
}
