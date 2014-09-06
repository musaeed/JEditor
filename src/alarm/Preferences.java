package alarm;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import Components.CButton;
import Components.CLabel;
import Gui.JEditor;
import Layouts.FlowCustomLayout;
import Utility.ImageLoader;


public class Preferences{

	private static Preferences instance = null;
	MP3 player;
	private JCheckBox def ,user;
	private JDialog dialog;
	CButton save,cancel;
	private String lastPath = "";
	private JButton play,stop;

	public static Preferences getInstance(){
		if(instance == null){
			instance = new Preferences();
		}

		return instance;
	}

	private Preferences(){
		init();
	}

	public void init(){

		dialog = new JDialog();
		dialog.setLayout(new BorderLayout());
		dialog.add(getButtonPanel() , BorderLayout.SOUTH);
		dialog.add(getMainPanel() , BorderLayout.CENTER);
		dialog.setTitle("Alarm settings");
		dialog.setSize(new Dimension(600,200));
		dialog.setModal(true);
		dialog.setLocationRelativeTo(AlarmDialog.dialog);
		
	}


	public JPanel getMainPanel(){

		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane tabs = new JTabbedPane();
		tabs.setTabPlacement(JTabbedPane.LEFT);
		tabs.addTab("General", getGeneralPanel());

		panel.setLayout(new BorderLayout());
		panel.add(tabs,BorderLayout.CENTER);
		return panel;
	}

	public JPanel getGeneralPanel(){
		JPanel panel = new JPanel(new BorderLayout());
		JPanel mPanel = new JPanel(new BorderLayout()) , tPanel = new JPanel(new FlowLayout()) , bPanel = new JPanel(new FlowLayout()), lPanel = new JPanel(new BorderLayout());

		def = new JCheckBox("Default");
		user = new JCheckBox("User defined");	
		final JButton browse = new JButton("Browse");
		play = new JButton(ImageLoader.loadImage("images/play.png"));
		stop = new JButton(ImageLoader.loadImage("images/stop.png"));
		
		final CLabel path = new CLabel();
		path.setToolTipText("click to open the folder contaning file");

		addListener(path);
		
		lPanel.add(path, BorderLayout.CENTER);

		play.setToolTipText("play");
		stop.setToolTipText("stop");
		bPanel.add(play);
		bPanel.add(stop);
		tPanel.add(def);
		tPanel.add(user);
		tPanel.add(browse);

		mPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Alarm tone"));
		def.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				browse.setEnabled(false);
				user.setSelected(false);
				play.setEnabled(false);
				stop.setEnabled(false);
				path.setText("");
				
				if(player != null){
					player.close();
				}
			}

		});
		
		user.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				browse.setEnabled(true);
				def.setSelected(false);
				play.setEnabled(true);
				path.setText(lastPath);
			}
		});

		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(path.getText() == null || path.getText().equals("")){
					JOptionPane.showMessageDialog(dialog, "There is no mp3 file selected to play!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					player = new MP3(new FileInputStream(new File(path.getText())));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return;
				}
				
				player.play();
				play.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.close();
				stop.setEnabled(false);
				play.setEnabled(true);
			}
		});
		
		browse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FileDialog fd = new FileDialog(JEditor.frame);
				fd.setTitle("Open mp3 file");

				fd.setFilenameFilter(new FilenameFilter(){
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".mp3");
					}
				});

				fd.setVisible(true);

				File file = null;

				try{
					
					file = fd.getFiles()[0];
					
				} catch(Exception ex){
					
					System.out.println("User pressed cancel and no file was selected!");
					return;
				}
				
				path.setText(file.getAbsolutePath());
				lastPath = file.getAbsolutePath();
			}
		});

		mPanel.add(tPanel , BorderLayout.NORTH);
		mPanel.add(bPanel , BorderLayout.CENTER);
		mPanel.add(lPanel , BorderLayout.SOUTH);

		panel.add(mPanel , BorderLayout.CENTER);
		
		play.setEnabled(false);
		stop.setEnabled(false);
		browse.setEnabled(false);

		return panel;
	}

	public JPanel getButtonPanel(){

		JPanel panel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		save = new CButton("Save", "save the settings", 'S', null, null);
		cancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(player != null){
					player.close();
				}
				
				if(user.isSelected()){
					
					play.setEnabled(true);
					stop.setEnabled(false);
					
				}
				
				JOptionPane.showMessageDialog(dialog, "The settings are saved!", "Information", JOptionPane.INFORMATION_MESSAGE);
				dialog.dispose();
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dialog.dispose();
				
				if(player != null){
					player.close();
				}
				
			}
		});

		panel.add(save);
		panel.add(cancel);

		return panel;
	}

	public JCheckBox getDef() {
		return def;
	}

	public void setDef(JCheckBox def) {
		this.def = def;
	}

	public String getLastPath() {
		return lastPath;
	}

	public void setLastPath(String lastPath) {
		this.lastPath = lastPath;
	}

	public void setPref(Scanner sc){
		
		if(sc.nextLine().equals("true")){
			def.doClick();
			lastPath = sc.nextLine();
		}
		else{
			
			lastPath = sc.nextLine();
			user.doClick();
			
		}
		
		
		if(!sc.nextLine().equals("end")){
			JOptionPane.showMessageDialog(JEditor.frame, "There was some problem reading the alarm settings!" +
					" The settings might not be properly loaded.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void addListener(final CLabel path){
		
		path.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
				path.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				path.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				File file = new File(path.getText());
				
				if(!file.exists()){
					JOptionPane.showMessageDialog(dialog, "No file exists with the specified path on the hard drive!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					Runtime.getRuntime().exec(new String[] {"nautilus",file.getParent()});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public void show(){
		dialog.setVisible(true);
	}

}
