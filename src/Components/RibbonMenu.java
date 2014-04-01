package Components;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import core.TextPanel;

import IOFactory.Reader;
import Layouts.FlowCustomLayout;

public class RibbonMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	private RibbonButton newtab,open,save,saveas,close,closeall,undo,redo,help;

	public RibbonMenu(){
		init();
		addToRibbon();
		addIcons();
		addActions();
	}

	public void init(){

		newtab = new RibbonButton("New tab","Open a new tab");
		open = new RibbonButton("Open" , "Open an existing file");
		save = new RibbonButton("Save" , "Save the current file");
		saveas = new RibbonButton("Save As" , "Save the current file under a new name");
		close = new RibbonButton("Close" , "Close the current file");
		closeall = new RibbonButton("Close All", "Close all the files");;
		undo = new RibbonButton("Undo" , "Undo the last action");
		redo = new RibbonButton("Redo" , "Redo the last action");
		help = new RibbonButton("Help", "open the help dialog");

	}

	public void addToRibbon(){
		
		setLayout(new FlowCustomLayout(FlowLayout.LEFT));
		
		add(newtab);
		add(open);
		add(new CSeparator());
		add(save);
		add(saveas);
		add(new CSeparator());
		add(close);
		add(closeall);
		add(new CSeparator());
		add(undo);
		add(redo);
		add(new CSeparator());
		add(help);
	}

	public void addIcons(){
		newtab.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/newtab.png"))));
		open.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/open.png"))));
		save.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/save.png"))));
		saveas.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/saveas.png"))));
		close.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/close.png"))));
		closeall.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/closeall.png"))));
		undo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/undo.png"))));
		redo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/redo.png"))));
		help.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/help.png"))));
	}

	public void addActions(){
		newtab.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CTabbedPane.getInstance().addTab("Untitled", new TextPanel());
			}
		});
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reader.openDialog();
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		saveas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		closeall.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
