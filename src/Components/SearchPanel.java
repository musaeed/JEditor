package Components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Gui.JEditor;
import Layouts.FlowCustomLayout;
import Utility.ImageLoader;

public class SearchPanel {
	
	private static SearchPanel instance;
	private JPanel panel;
	private JTextField searchText,replaceText;
	private CButton next,previous,replace,replaceAll,close;
	private CLabel searchLabel,replaceLabel;
	
	public static SearchPanel getInstance(){
		if(instance == null){
			instance = new SearchPanel();
		}
		return instance;
	}
	
	public SearchPanel(){
		init();
	}
	
	public void init(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getClosePanel() , BorderLayout.WEST);
		panel.add(getMainPanel() , BorderLayout.CENTER);
	}
	
	public JPanel getMainPanel(){
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(getSearchPanel());
		return p;
	}
	
	public JPanel getSearchPanel(){
		JPanel p = new JPanel(new FlowCustomLayout(FlowLayout.LEFT));
		searchLabel = new CLabel("Search:");
		searchText = new JTextField(60);
		next = new CButton("Next", "go to the next item", 'N', KeyStroke.getKeyStroke(KeyEvent.VK_ENTER , 0), null);
		next.setIcon(ImageLoader.loadImage("images/arrow-right.png"));
		previous = new CButton("Previous", "go to the previous item", 'P', null, null);
		previous.setIcon(ImageLoader.loadImage("images/arrow-left.png"));
		
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String toFind = "";
				String text = "";
				
				toFind = searchText.getText();
				text = CTabbedPane.getInstance().getPanel().getTextArea().getText();
				

				if(toFind == null || text == null){
					return;
				}
				
				if(toFind.equals("") || text.equals("")){
					return;
				}
				
				int newindex = text.indexOf(toFind, CTabbedPane.getInstance().getPanel().getSearchIndex());
				
				if(newindex == -1){
					JOptionPane.showMessageDialog(JEditor.frame, "Reached the end of file while searching.", "Search", JOptionPane.INFORMATION_MESSAGE);
					CTabbedPane.getInstance().getPanel().setSearchIndex(0);
					return;
				}
				
				CTabbedPane.getInstance().getPanel().getTextArea().setCaretPosition(newindex);
				CTabbedPane.getInstance().getPanel().getTextArea().setSelectionStart(newindex);
				CTabbedPane.getInstance().getPanel().getTextArea().setSelectionEnd(newindex + toFind.length());

				CTabbedPane.getInstance().getPanel().setSearchIndex(newindex + searchText.getText().length());
			}
		});
		
		previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Still have to complete
			}
		});
		
		p.add(searchLabel);
		p.add(searchText);
		p.add(next);
		p.add(previous);
		return p;
	}
	
	public JPanel getReplacePanel(){
		JPanel p = new JPanel(new FlowCustomLayout(FlowLayout.LEFT));
		replaceLabel = new CLabel("Replace:");
		replaceText = new JTextField(60);
		replace = new CButton("Replace", "replace the text", 'R', null, null);
		replaceAll = new CButton("Replace all", "replace all the text", 'a', null, null);
		p.add(replaceLabel);
		p.add(replaceText);
		p.add(replace);
		p.add(replaceAll);
		return p;
	}
	
	public JPanel getClosePanel(){
		JPanel p = new JPanel(new FlowLayout());
		close = new CButton("", "close the search panel", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CTabbedPane.getInstance().getPanel().removeSearchPanel();
			}
		});
		
		close.setContentAreaFilled(false);
		close.addListener();
		close.setIcon(ImageLoader.loadImage("images/close-panel.png"));
		
		p.add(close);
		return p;
	}
	
	public JPanel getPanel(){
		return panel;
	}

	public JTextField getSearchText() {
		return searchText;
	}

	public JTextField getReplaceText() {
		return replaceText;
	}

	
	

}
