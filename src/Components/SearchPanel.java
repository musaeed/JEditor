package Components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
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
	private ColoredButton next,previous,replace,replaceAll;
	private CButton close;
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
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				init();
			}
		}).start();
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
			next = ColoredButton.GetRandomButton("Next", "search for the next item");
			next.setIcon(ImageLoader.loadImage("images/arrow-right.png"));
			previous = ColoredButton.GetRandomButton("Previous", "get the previous item");
			previous.setIcon(ImageLoader.loadImage("images/arrow-left.png"));
			addKeyStrokes();

			next.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String toFind = "";
					String text = "";
					
					toFind = searchText.getText();
					text = CTabbedPane.getInstance().getPanel().getTextArea().getText();
					
					if(!match.isSelected()){
						toFind = toFind.toLowerCase();
						text = text.toLowerCase();
					}

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
			replace = ColoredButton.GetRandomButton("Replace", "replace the selected text");
			replaceAll = ColoredButton.GetRandomButton("Replace all", "replace all the entries of the selected text");
			
			replace.setIcon(ImageLoader.loadImage("images/replace.png"));
			replaceAll.setIcon(ImageLoader.loadImage("images/replaceall.png"));
			
			replace.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					RSyntaxTextArea textArea = CTabbedPane.getInstance().getPanel().getTextArea();
					
					if(textArea.getSelectedText() == null || replaceText.getText() == null || replaceText.getText().equals("")){
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
					
					if(textArea.getSelectedText() == null || replaceText.getText() == null || replaceText.getText().equals("")){
						return;
					}
					
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

	public void addKeyStrokes(){
		
		next.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), 1);
		next.getActionMap().put(1, new AbstractAction() {
			private static final long serialVersionUID = 7741619779073458071L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				next.doClick();
			}
		});
		
	}


}
