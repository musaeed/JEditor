package Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.TextPanel;


public class TabClosePanel extends JPanel {

	private static final long serialVersionUID = 3375988660604064366L;
	private JLabel label;
	private JButton close;
	private int uniqueInt;

	public TabClosePanel(String title, int unique){
		init(title);
		addToPanel();
		addActions();
		addListeners();
		this.uniqueInt = unique;
	}

	public void init(String title){
		setOpaque(false);
		label = new JLabel(title);
		label.setOpaque(false);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		label.setComponentPopupMenu(new CTabPopupMenu());
		close = new JButton("<html><font size=3><b>X</b></font></html>");
		close.setContentAreaFilled(false);
		close.setToolTipText("close this tab");
		
	}

	public void addToPanel(){
		setLayout(new BorderLayout());
		add(label , BorderLayout.CENTER);
		add(close, BorderLayout.EAST);
	}

	public void addActions(){
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++){

					if(uniqueInt == ((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).unique){
						CTabbedPane.getInstance().setSelectedIndex(i);
						CTabbedPane.getInstance().closeCurrentTab();
					}

				}
			}
		});
	}

	public void addListeners(){

		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				label.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++){

					if(uniqueInt == ((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).unique){
						CTabbedPane.getInstance().setSelectedIndex(i);
					}
				}
				
				if(e.getButton() == MouseEvent.BUTTON2){
					CTabbedPane.getInstance().closeCurrentTab();
				}
				
			}

			});
		

		close.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				close.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				close.setForeground(Color.BLACK);
			}

		});
		}

		public void setIcon(Icon icon){
			label.setIcon(icon);
		}

		public void setTitle(String title){
			label.setText(title);
		}
		
		public void setToolTip(String tip) {
			label.setToolTipText(tip);
		}
		
		public String getTitle(){
			return label.getText();
		}

	}