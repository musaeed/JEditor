package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.LinePainter;
import Components.TextPanelHeader;

public class TextPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private RSyntaxTextArea textArea;
	private Font textFont = new Font("WenQuanYi Micro Hei Mono", Font.PLAIN, 14);
	private TextPanelHeader panelHeader;
	private LinePainter linePainter;
	private String currentFilePath = null;

	public TextPanel(){
		init();
	}

	public void init(){
		initTextArea();
		addToPane();
	}

	public void initTextArea(){
		textArea = new RSyntaxTextArea();
		textArea.setFont(textFont);
		textArea.setSelectionColor(new Color(215, 72, 20));
		textArea.setAntiAliasingEnabled(true);
		textArea.setUseSelectedTextColor(true);
		textArea.setSelectedTextColor(Color.WHITE);
		scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelHeader = new TextPanelHeader(textArea, 6);
		scrollPane.setRowHeaderView(panelHeader);
		linePainter = new LinePainter(textArea, new Color(240, 237, 240));
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
}
