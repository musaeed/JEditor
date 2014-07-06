package component_listeners;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;

import Components.CTabbedPane;


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
		label.setForeground(Color.WHITE);
		menu.add(label);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
        generateBar(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		menu.setVisible(false);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		generateBar(e);
	}

	public void generateBar(MouseEvent e){		
		
		JScrollBar bar = (JScrollBar)e.getSource();
        int y1 = CTabbedPane.getInstance().getPanel().getTextArea().getVisibleRect().y;
        int y2 = y1 + CTabbedPane.getInstance().getPanel().getTextArea().getVisibleRect().height;
        int lineHeight = CTabbedPane.getInstance().getPanel().getTextArea().getFontMetrics(CTabbedPane.getInstance().getPanel().getTextArea().getFont()).getHeight();
        int startRow = (int) Math.ceil((double) y1 / lineHeight);
        int endRow = (int) Math.floor((double) y2 / lineHeight);
        label.setText("<html><font color=\"white\" face=\"Ubuntu L\" size=3>" + startRow + "<hr>"+ endRow+ "</font></html>");
        menu.setSize(new Dimension(label.getSize()));
		menu.show(bar.getParent(), (int)bar.getLocation().getX() - menu.getWidth()-7, bar.getY());
		menu.pack();
	}
	
}
