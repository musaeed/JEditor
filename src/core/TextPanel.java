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

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.LinePainter;
import Components.TextPanelHeader;
import Components.TextPanelPopupMenu;

import component_listeners.TabDropTargetListener;
import component_listeners.TextAreaDocumentListener;
import component_listeners.TextPanelCaretListener;

public class TextPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private RSyntaxTextArea textArea;
	private Font textFont = new Font("WenQuanYi Micro Hei Mono", Font.PLAIN, 14);
	private TextPanelHeader panelHeader;
	private LinePainter linePainter;
	private String currentFilePath = null;
	private boolean needsToBeSaved = false;
	private int searchIndex;

	public TextPanel(){
		init();
	}

	public void init(){
		initTextArea();
		addToPane();
		new DropTarget(textArea , new TabDropTargetListener());
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
		scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelHeader = new TextPanelHeader(textArea, 6);
		scrollPane.setRowHeaderView(panelHeader);
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
	
}
