package alarm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;
import Utility.ImageLoader;


public class Preferences{

	private static Preferences instance = null;
	MP3 player;
	JRadioButton def ,user;
	private JDialog dialog;
	CButton save,cancel;
	
	public static Preferences getInstance(JDialog d){
		if(instance == null){
			instance = new Preferences(d);
		}
		
		return instance;
	}

	private Preferences(JDialog d){
		init(d);
	}

	public void init(JDialog d){
		
		dialog = new JDialog();
		dialog.setLayout(new BorderLayout());
		dialog.add(getButtonPanel() , BorderLayout.SOUTH);
		dialog.add(getMainPanel() , BorderLayout.CENTER);
		dialog.setTitle("Alarm settings");
		dialog.setSize(new Dimension(600,200));
		dialog.setModal(true);
		dialog.setLocationRelativeTo(d);
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
		JPanel mPanel = new JPanel(new BorderLayout()) , tPanel = new JPanel(new FlowLayout()) , bPanel = new JPanel(new FlowLayout());

		def = new JRadioButton("Default");
		user = new JRadioButton("User defined");	
		final JButton browse = new JButton("Browse") , play = new JButton(ImageLoader.loadImage("images/play.png")) , stop = new JButton(ImageLoader.loadImage("images/stop.png"));
		final JLabel path = new JLabel();

		play.setToolTipText("play");
		stop.setToolTipText("stop");
		bPanel.add(play);
		bPanel.add(stop);
		tPanel.add(def);
		tPanel.add(user);
		tPanel.add(browse);
		tPanel.add(path);
		mPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Alarm tone"));
		def.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				browse.setEnabled(false);
				user.setSelected(false);
				play.setEnabled(false);
				stop.setEnabled(false);
				path.setText("");
			}
			
		});
		user.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				browse.setEnabled(true);
				def.setSelected(false);
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
				
			}
		});

		mPanel.add(tPanel , BorderLayout.NORTH);
		mPanel.add(bPanel , BorderLayout.CENTER);
		
		panel.add(mPanel , BorderLayout.CENTER);

		return panel;
	}

	public JPanel getButtonPanel(){
		
		JPanel panel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		save = new CButton("Save", "save the settings", 'S', null, null);
		cancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		panel.add(save);
		panel.add(cancel);
		
		return panel;
	}
	
	public void setPref(){
		
	}
	
	public void show(){
		dialog.setVisible(true);
	}

}
