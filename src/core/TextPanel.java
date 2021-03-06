package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.LinePainter;
import Components.SearchPanel;
import Components.TextPanelHeader;
import Components.TextPanelPopupMenu;
import Gui.JEditor;
import Utility.WordSuggestions;
import alarm.Alarm;
import alarm.AlarmUtilities;
import alarm.ShowAlarmPanel;

import com.inet.jortho.SpellChecker;
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
	private int searchForwardIndex, searchBackwardIndex;
	private WordSuggestions suggestions;
	private boolean isReadOnly = false;
	public int unique;
	private ShowAlarmPanel alarmPanel;

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
		scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				textArea.setFont(textFont);
				textArea.setSelectionColor(new Color(215, 72, 20));
				textArea.setAntiAliasingEnabled(true);
				textArea.setUseSelectedTextColor(true);
				textArea.setSelectedTextColor(Color.WHITE);
				textArea.getDocument().addDocumentListener(new TextAreaDocumentListener());
				textArea.getPopupMenu().removeAll();
				textArea.setComponentPopupMenu(new TextPanelPopupMenu());
				textArea.addCaretListener(new TextPanelCaretListener());
				panelHeader = new TextPanelHeader(textArea, 6);
				scrollPane.setRowHeaderView(panelHeader);
				scrollPane.getVerticalScrollBar().addMouseMotionListener(ScrollbarListener.getInstance());
				scrollPane.getVerticalScrollBar().addMouseListener(ScrollbarListener.getInstance());
				linePainter = new LinePainter(textArea, new Color(240, 237, 240));
				addHyperListener();
				searchForwardIndex = 0;
				searchBackwardIndex = 0;
				
			}
		}).start();
		
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

	public void setSearchForwardIndex(int newindex){
		searchForwardIndex = newindex;
	}

	public int getSearchForwardIndex(){
		return searchForwardIndex;
	}
	
	public void setSearchBackwardIndex(int newIndex){
		searchBackwardIndex = newIndex;
	}
	
	public int getSearchBackwardIndex(){
		return searchBackwardIndex;
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
		add(SearchPanel.getInstance().getSearchPanelOnly(), BorderLayout.SOUTH);

		if(textArea.getSelectedText() != null){
			SearchPanel.getInstance().getSearchText().setText(textArea.getSelectedText());
		}
		JEditor.frame.validate();
		SearchPanel.getInstance().getSearchText().requestFocus();
	}

	public void removeSearchPanel(){
		remove(SearchPanel.getInstance().getSearchPanelOnly());
		JEditor.frame.validate();
		textArea.requestFocus();
	}
	
	public void addAlarmPanel(Alarm alarm){
		add(alarmPanel = new ShowAlarmPanel(alarm), BorderLayout.NORTH);
		AlarmUtilities.getInstance().getTimer().stop();
	}
	
	public void removeAlarmPanel(){
		remove(alarmPanel);
		validate();
		textArea.requestFocus();
		AlarmUtilities.getInstance().getTimer().start();
	}

	public void registerSpellChecker(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				SpellChecker.register(textArea);
				registerEnglish();
			}
		});
		
		thread.start();
	}

	public void unregisterSpellChecker(){
		SpellChecker.unregister(textArea);
	}

	public JMenu getSpellingMenu(){
		return SpellChecker.createCheckerMenu();
	}

	public void registerEnglish(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "en");
	}

	public void registerGerman(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "de");
	}

	public void registerFrench(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "fr");
	}

	public void registerSpanish(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "es");
	}

	public void registerRussian(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "ru");
	}

	public void registerPolish(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "pl");
	}

	public void registerDutch(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "nl");
	}

	public void registerItalien(){
		SpellChecker.registerDictionaries(this.getClass().getResource("/spelling/"),  "it");
	}
}
