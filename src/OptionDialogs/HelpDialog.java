package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import Components.BottomPanel;
import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class HelpDialog extends JDialog{

	private static final long serialVersionUID = -2692829247428807137L;
	private static HelpDialog instance = null;
	private CButton send , cancel;
	private JTabbedPane tabs;
	private JScrollPane scroll;
	private JTable table;
	private JEditorPane epane;
	
	public static HelpDialog getInstance(){
		if(instance == null){
			instance = new HelpDialog();
		}
		
		return instance;
	}
	
	private HelpDialog(){
		init();
		addToDialog();
	}
	
	public void init(){
		setSize(500,500);
		setTitle("Help");
		setModal(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(JEditor.frame);
	}
	
	public void addToDialog(){
		add(getTabs() , BorderLayout.CENTER);
		add(getBPanel() , BorderLayout.SOUTH);
	}
	
	public JTabbedPane getTabs(){
		tabs = new JTabbedPane();
		tabs.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() > 0){
					tabs.setSelectedIndex(1);
				}
				else{
					tabs.setSelectedIndex(0);
				}
			}
		});
		tabs.addTab("Shorcuts", getTable());
		tabs.addTab("Developer", getDeveloperPane());
		return tabs;
	}
	
	public JScrollPane getTable(){
		
		String columnNames[] = {"Key" , "Function"};
		Object [][] data = {{"Ctrl + N","New alarm"},
				{"Ctrl + E","Edit the selected alarm"},
				{"Ctrl + A" , "Select all alarms"},
				{"Ctrl + Z","Clear Selection"},
				{"Delete","Delete the selected alarm"},
				{"Ctrl + P","Preferences"},
				{"Alt + F4" , "Exit the application"}};
		table = new JTable(data, columnNames){

			private static final long serialVersionUID = -8625726229160953589L;

			public boolean isCellEditable(int data , int col){
				return false;
			}
		};
		
		scroll = new JScrollPane(table);
		
		return scroll;
	}

	public JEditorPane getDeveloperPane(){
		
		epane = new JEditorPane();
		epane.setBackground(getBackground());
		epane.setOpaque(false);
		epane.setEditable(false);

		InputStream input = BottomPanel.class.getResourceAsStream("/other/developer.html");
		Scanner in = new Scanner(input);
		String temp = "";
		while(in.hasNext()){
			temp += in.nextLine();
		}
		in.close();
		epane.setContentType("text/html");
		epane.setText(temp);

		epane.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		return epane;
	}
	
	public JPanel getBPanel(){
	
		JPanel bPanel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		send = new CButton("Send email", "send an email to the developer", 'S', null, null);
		cancel = new CButton("Close", "close the dialog", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("mailto:muhammad.omar555@gmail.com"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		bPanel.add(send);
		bPanel.add(cancel);
		
		return bPanel;
	}
}
