package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.DefaultHighlighter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.LinePainter;
import Components.SearchPanel;
import Components.TextPanelHeader;
import Components.TextPanelPopupMenu;
import Utility.WordSuggestions;

import component_listeners.ScrollbarListener;
import component_listeners.SpaceListenerForAddingSuggestions;
import component_listeners.TabDropTargetListener;
import component_listeners.TextAreaDocumentListener;
import component_listeners.TextPanelCaretListener;

public class TextPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private RSyntaxTextArea textArea;
	private Font textFont = new Font("Ubuntu Mono", Font.PLAIN, 15);
	private TextPanelHeader panelHeader;
	private LinePainter linePainter;
	private String currentFilePath = null;
	private boolean needsToBeSaved = false;
	private int searchIndex;
	private WordSuggestions suggestions;
	private boolean isReadOnly = false;
	public int unique;

	public TextPanel(int unique){
		init();
		this.unique = unique;
	}

	public void init(){
		initTextArea();
		addToPane();
		suggestions = new WordSuggestions(textArea);
		new DropTarget(textArea , new TabDropTargetListener());
		textArea.addKeyListener(new SpaceListenerForAddingSuggestions(this));
	}

	public void initTextArea(){
		textArea = new RSyntaxTextArea();
		textArea.setFont(textFont);
		textArea.setSelectionColor(new Color(215, 72, 20));
		textArea.setAntiAliasingEnabled(true);
		textArea.setUseSelectedTextColor(true);
		textArea.setSelectedTextColor(Color.WHITE);
		textArea.getDocument().addDocumentListener(new TextAreaDocumentListener());
		textArea.getPopupMenu().removeAll();
		textArea.setComponentPopupMenu(new TextPanelPopupMenu());
		textArea.addCaretListener(new TextPanelCaretListener());
		((DefaultHighlighter)textArea.getHighlighter()).setDrawsLayeredHighlights(false);
		scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelHeader = new TextPanelHeader(textArea, 6);
		scrollPane.setRowHeaderView(panelHeader);
		scrollPane.getVerticalScrollBar().addMouseMotionListener(ScrollbarListener.getInstance());
		scrollPane.getVerticalScrollBar().addMouseListener(ScrollbarListener.getInstance());
		linePainter = new LinePainter(textArea, new Color(240, 237, 240));
		addHyperListener();
		searchIndex = 0;
	}

	public void addToPane(){
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		setSize(new Dimension(720, 680));
	}

	public RSyntaxTextArea getTextArea () {
		return textArea;
	}

	public String getCurrentFilePath () {
		return currentFilePath;
	}

	public void setCurrentFilePath (String path) {
		currentFilePath = path;
	}

	public void setTextFont (Font f) {
		textFont = f;
	}
	
	public Font getFont(){
		return textFont;
	}
	
	public LinePainter getPainter () {
		return linePainter;
	}
	
	public JScrollPane getScrollPane () {
		return scrollPane;
	}

	public boolean isNeedsToBeSaved() {
		return needsToBeSaved;
	}

	public void setNeedsToBeSaved(boolean needsToBeSaved) {
		this.needsToBeSaved = needsToBeSaved;
	}

	public TextPanelHeader getPanelHeader() {
		return panelHeader;
	}

	public void setPanelHeader(TextPanelHeader panelHeader) {
		this.panelHeader = panelHeader;
	}
	
	public void setSearchIndex(int newindex){
		searchIndex = newindex;
	}
	
	public int getSearchIndex(){
		return searchIndex;
	}
	
	public WordSuggestions getWordSuggestions(){
		return suggestions;
	}
	
	public void setReadOnly(boolean b){
		textArea.setEditable(b);
		isReadOnly = !b;
	}
	
	public boolean isReadAble(){
		return isReadOnly;
	}
	
	public void addHyperListener(){
		textArea.addHyperlinkListener(new HyperlinkListener() {
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public void addSearchPanel(){
		add(SearchPanel.getInstance().getPanel(), BorderLayout.SOUTH);
		if(textArea.getSelectedText() != null){
			SearchPanel.getInstance().getSearchText().setText(textArea.getSelectedText());
		}
		SearchPanel.getInstance().getSearchText().requestFocus();
	}
	
	public void removeSearchPanel(){
		remove(SearchPanel.getInstance().getPanel());
		textArea.requestFocus();
	}
	
}
