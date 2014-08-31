package alarm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import Utility.ImageLoader;


public class Preferences extends JDialog{

	private static final long serialVersionUID = 6533874348170575322L;
	MP3 player;
	JRadioButton def ,user;

	public Preferences(JDialog d){
		init(d);
	}

	public void init(JDialog d){

		setLayout(new BorderLayout());
		add(getButtonPanel() , BorderLayout.SOUTH);
		add(getMainPanel() , BorderLayout.CENTER);
		setTitle("Alarm settings");
		setSize(new Dimension(600,400));
		setModal(true);
		setLocationRelativeTo(d);
		setVisible(true);
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
		mPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY.brighter()), "Alarm tone"));
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
				
				FileDialog fd = new FileDialog(new JDialog());
				
			}
		});

		mPanel.add(tPanel , BorderLayout.NORTH);
		mPanel.add(bPanel , BorderLayout.CENTER);
		
		panel.add(mPanel , BorderLayout.CENTER);

		return panel;
	}

	public JPanel getButtonPanel(){
		JPanel panel = new JPanel();
		return panel;
	}

}
