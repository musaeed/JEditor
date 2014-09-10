package Components;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Gui.JEditor;
import IOFactory.Reader;
import IOFactory.Writer;
import Layouts.FlowCustomLayout;
import Menus.TimePopUpMenu;
import OptionDialogs.HelpDialog;
import Utility.EditorUtilities;
import Utility.ImageLoader;

public class RibbonMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	public static RibbonButton newtab,open,save,saveas,close,closeall,undo,redo,help;
	public static RibbonButton time;
	private Timer timer;
	private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
	
	private static RibbonMenu instance = null;
	
	public static RibbonMenu getInstance(){
		
		if(instance == null){
			instance = new RibbonMenu();
		}
		
		return instance;
	}
	
	private RibbonMenu(){
		init();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Setting up ribbon menu...");
				addToRibbon();
				addIcons();
				addActions();
			}
		}).start();

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
		time = new RibbonButton(sdf.format(Calendar.getInstance().getTime()), "the current time");
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				time.setText(sdf.format(Calendar.getInstance().getTime()));
				
			}
		});
		
		timer.start();
	}

	public void addToRibbon(){

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel left = new JPanel(new FlowCustomLayout(FlowLayout.LEFT));
		JPanel right = new JPanel();
		FlowCustomLayout fl = new FlowCustomLayout(FlowLayout.RIGHT);
		fl.setVgap(12);
		right.setLayout(fl);
		
		left.add(newtab);
		left.add(open);
		left.add(new CSeparator());
		left.add(save);
		left.add(saveas);
		left.add(new CSeparator());
		left.add(close);
		left.add(closeall);
		left.add(new CSeparator());
		left.add(undo);
		left.add(redo);
		left.add(new CSeparator());
		left.add(help);
		
		right.add(time);
		
		add(left);
		add(right);
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
		time.setIcon(ImageLoader.loadImage("images_small/clock.png"));
	}

	public void addActions(){
		newtab.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CTabbedPane.getInstance().addTab("Untitled");
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
				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
					saveas.doClick();
					return;
				}

				Writer.saveFile(CTabbedPane.getInstance().getPanel().getCurrentFilePath());
			}
		});

		saveas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(!Writer.showSaveDialog()){
					return;
				}

				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						JEditor.frame.setTitle("JEditor - " + CTabbedPane.getInstance().getPanel().getCurrentFilePath());
						CTabbedPane.getInstance().setTitleAt(CTabbedPane.getInstance().getSelectedIndex(), new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).getName());
						CTabbedPane.getInstance().setToolTipTextAt(CTabbedPane.getInstance().getSelectedIndex(), CTabbedPane.getInstance().getPanel().getCurrentFilePath());
						EditorUtilities.updateInfo(CTabbedPane.getInstance().getPanel().getCurrentFilePath(), CTabbedPane.getInstance());
						FileViewer.getInstance().addToTree(CTabbedPane.getInstance().getTitleAt(CTabbedPane.getInstance().getSelectedIndex()), CTabbedPane.getInstance().getPanel().unique);
						FileViewer.getInstance().setSelectedFile(CTabbedPane.getInstance().getPanel().unique);
						CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
					}
				});
			}
		});

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CTabbedPane.getInstance().closeCurrentTab();

			}
		});

		closeall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CTabbedPane.getInstance().closeAllTabs();
			}
		});

		redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (CTabbedPane.getInstance().getPanel().getTextArea().canRedo()) {
					CTabbedPane.getInstance().getPanel().getTextArea().redoLastAction();
				}
			}
		});

		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (CTabbedPane.getInstance().getPanel().getTextArea().canUndo()) {
					CTabbedPane.getInstance().getPanel().getTextArea().undoLastAction();
				}
			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				HelpDialog.getInstance().setVisible(true);

			}
		});
		
		time.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new  TimePopUpMenu().show(time, time.getX()+15, time.getY()+10);
			}
		});
	}

}
