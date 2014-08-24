package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Components.ColoredButton;
import Gui.JEditor;
import Utility.Themes;

public class ThemesDialog {

	private static ThemesDialog instance = null;
	private JDialog dialog;
	private ColoredButton system,nimbus,weblaf;

	public static ThemesDialog getInstance(){

		if(instance == null){
			instance = new ThemesDialog();
		}

		return instance;
	}

	public ThemesDialog(){
		init();
		addRActions();
	}

	public void init(){
		dialog = new JDialog();
		dialog.setModal(true);
		dialog.setSize(new Dimension(500,500));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setTitle("Themes");
		dialog.setLayout(new BorderLayout());
		dialog.add(getMainPanel() , BorderLayout.CENTER);
	}

	public void saveTheme(){
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				File file = new File(System.getProperty("user.home")+"/.cache/JEditor/themes.jeditor");
				PrintWriter o = null;

				try {
					o = new PrintWriter(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				switch(Themes.CURRENT){

				case Themes.NIMBUS:
					o.println(Themes.NIMBUS);
					break;
				case Themes.SYSTEM:
					o.println(Themes.SYSTEM);
					break;
				case Themes.WEBLAF:
					o.println(Themes.WEBLAF);
					break;
				default:
					o.println(Themes.SYSTEM);
					break;	
				}

				o.flush();
				o.close();

			}
		});
		
		thread.start();
		
		JOptionPane.showMessageDialog(dialog, "The theme will come in effect after you restart the JEditor!", "Theme", JOptionPane.INFORMATION_MESSAGE);

	}

	public JPanel getMainPanel(){

		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new GridLayout(3,1));

		system = ColoredButton.GetRandomButton("System", "set the system look and feel");
		nimbus = ColoredButton.GetRandomButton("Nimbus", "set the nimbus look and feel");
		weblaf = ColoredButton.GetRandomButton("Web laf", "set the web look and feel");
		
		system.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 1);
		system.getActionMap().put(1, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});

		panel.add(system);
		panel.add(nimbus);
		panel.add(weblaf);

		return panel;
	}

	public void addRActions(){
		system.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Themes.CURRENT = Themes.SYSTEM;
				saveTheme();
			}
		});

		nimbus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Themes.CURRENT = Themes.NIMBUS;
				saveTheme();
			}
		});

		weblaf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Themes.CURRENT = Themes.WEBLAF;
				saveTheme();
			}
		});

	}


	public void show(){
		dialog.setVisible(true);
	}
}
