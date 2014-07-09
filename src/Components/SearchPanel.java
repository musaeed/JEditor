package Components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Gui.JEditor;
import Layouts.FlowCustomLayout;
import Utility.ImageLoader;
import Utility.SpringUtilities;

public class SearchPanel {

	private static SearchPanel instance;
	private JPanel panel;
	private JTextField searchText,replaceText;
	private CButton next,previous,replace,replaceAll,close;
	private CLabel searchLabel,replaceLabel;
	private JCheckBox match,fullWord;
	private JPanel mainPanel,searchPanel,optionPanel;

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
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(getSearchAndReplacePanel());
		mainPanel.add(getOptionsPanel());

		panel.add(getClosePanel() , BorderLayout.WEST);
		panel.add(mainPanel , BorderLayout.CENTER);
	}

	public JPanel getSearchAndReplacePanel(){

		if(searchPanel == null){

			searchPanel = new JPanel(new SpringLayout());
			
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

					toFind = searchText.getText().toLowerCase();
					text = CTabbedPane.getInstance().getPanel().getTextArea().getText().toLowerCase();


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

			searchPanel.add(searchLabel);
			searchPanel.add(searchText);
			searchPanel.add(next);
			searchPanel.add(previous);
			getReplacePanel();
			
			SpringUtilities.makeCompactGrid(searchPanel, 2, 4, 6, 6, 6, 6);
		}
		return searchPanel;
	}

	public void getReplacePanel(){

			replaceLabel = new CLabel("Replace:");
			replaceText = new JTextField(60);
			replace = new CButton("Replace", "replace the text", 'R', null, null);
			replaceAll = new CButton("Replace all", "replace all the text", 'a', null, null);
			
			replace.setIcon(ImageLoader.loadImage("images/replace.png"));
			replaceAll.setIcon(ImageLoader.loadImage("images/replaceall.png"));
			
			replace.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					RSyntaxTextArea textArea = CTabbedPane.getInstance().getPanel().getTextArea();
					
					if(textArea.getSelectedText() == null || replaceText.getText() == null){
						return;
					}
					
					int index = textArea.getCaretPosition() - textArea.getSelectedText().length();
					textArea.replaceSelection(replaceText.getText());
					textArea.setSelectionStart(index);
					textArea.setSelectionEnd(index + replaceText.getText().length());
					
				}
			});
			
			replaceAll.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					RSyntaxTextArea textArea = CTabbedPane.getInstance().getPanel().getTextArea();
					int index = textArea.getCaretPosition();
					textArea.setText(textArea.getText().replaceAll(searchText.getText(), replaceText.getText()));
					textArea.setCaretPosition(index);
				}
			});
			
			searchPanel.add(replaceLabel);
			searchPanel.add(replaceText);
			searchPanel.add(replace);
			searchPanel.add(replaceAll);

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

	public JPanel getOptionsPanel(){

		if(optionPanel == null){

			optionPanel = new JPanel(new FlowCustomLayout(FlowLayout.LEFT));
			match = new JCheckBox("Case sensitive");
			fullWord = new JCheckBox("Full word");
			optionPanel.add(match);
			optionPanel.add(fullWord);
		}
		return optionPanel;
	}

	public JPanel getSearchPanelOnly(){
		return panel;
	}


	public JTextField getSearchText() {
		return searchText;
	}

	public JTextField getReplaceText() {
		return replaceText;
	}

	public void resizePanel(Dimension d){
		int size = (int)d.getWidth();
		searchText.setColumns(size/25);
	}


}
