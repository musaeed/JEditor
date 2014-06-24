package Utility;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import Components.CButton;
import Components.CMenuItem;
import Components.CTabbedPane;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class HtmlRenderer {
	
	private JEditorPane renderer;
	private CButton reload,close;
	private JDialog dialog;
	private JScrollPane scroll;
	
	public HtmlRenderer(){
		init();
		addMenu();
	}
	
	public void init(){
		
		dialog = new JDialog();
		dialog.setTitle("Html renderer");
		dialog.setSize(new Dimension(1000,650));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setLayout(new BorderLayout());
		dialog.setModal(true);
		dialog.add(getBPanel() , BorderLayout.SOUTH);
		dialog.add(getMainPanel() , BorderLayout.CENTER);
	}
	
	public JPanel getMainPanel(){
		JPanel panel = new JPanel(new BorderLayout());
		renderer = new JEditorPane();
		renderer.setEditable(false);
		scroll = new JScrollPane(renderer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel getBPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowCustomLayout(FlowLayout.RIGHT));
		reload = new CButton("Reload", "reload the file and render again", 'R', null, null);
		close = new CButton("Close", "close the renderer and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		panel.add(reload);
		panel.add(close);
		return panel;
	}
	
	public void addMenu(){
		JPopupMenu menu = new JPopupMenu();
		CMenuItem browser,reload,close;
		browser = new CMenuItem("Open in browser", "open the html in the system browser", 'O', null);
		reload = new CMenuItem("Reload", "reload the text and render again", 'R', null);
		close = new CMenuItem("Close", "close and go back", 'C', null);
		
		browser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).toURI());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		menu.add(browser);
		menu.add(reload);
		menu.add(close);
		
		renderer.setComponentPopupMenu(menu);
	}
	
	public void load(){
		
		if(! new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).getName().contains(".html")){
			int result = JOptionPane.showConfirmDialog(JEditor.frame, "The current file is not a html file. Do you still want to render it?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(result == JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION){
				return;
			}
			
		}
		
		renderer.setContentType("text/html");
		renderer.setText(CTabbedPane.getInstance().getPanel().getTextArea().getText().replace("\n", "<br>"));
	}
	
	public void show(){
		dialog.setVisible(true);
	}

}
