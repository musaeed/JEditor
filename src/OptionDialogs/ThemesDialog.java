package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import Components.CButton;
import Gui.JEditor;
import Layouts.FlowCustomLayout;
import Utility.Themes;

public class ThemesDialog {

	private static ThemesDialog instance = null;
	private JDialog dialog;
	private CButton set,cancel;
	private JRadioButton system,nimbus,weblaf;

	public static ThemesDialog getInstance(){

		if(instance == null){
			instance = new ThemesDialog();
		}

		return instance;
	}

	public ThemesDialog(){
		init();
	}

	public void init(){
		dialog = new JDialog();
		dialog.setModal(true);
		dialog.setSize(new Dimension(700,500));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setTitle("Themes");
		dialog.setLayout(new BorderLayout());
		dialog.add(getMainPanel() , BorderLayout.CENTER);
		dialog.add(getButtonPanel() , BorderLayout.SOUTH);
	}

	public JPanel getButtonPanel(){

		JPanel panel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		set = new CButton("Set theme", "set the selected theme", 'S', null, null);
		cancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
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
				dialog.dispose();
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

		panel.add(set);
		panel.add(cancel);

		return panel;
	}

	public JPanel getMainPanel(){

		JPanel panel = new JPanel(new BorderLayout());

		system = new JRadioButton("System");
		nimbus = new JRadioButton("Nimbus");
		weblaf = new JRadioButton("Web laf");
		
		system.setSelected(Themes.CURRENT == Themes.SYSTEM);
		nimbus.setSelected(Themes.CURRENT == Themes.NIMBUS);
		weblaf.setSelected(Themes.CURRENT == Themes.WEBLAF);
		
		addRActions();

		JPanel rpanel = new JPanel();
		rpanel.setLayout(new BoxLayout(rpanel, BoxLayout.Y_AXIS));
		rpanel.add(system);
		rpanel.add(nimbus);
		rpanel.add(weblaf);

		rpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Themes"));

		JPanel lpanel = new JPanel();
		lpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Preview"));

		panel.add(rpanel , BorderLayout.WEST);
		panel.add(lpanel , BorderLayout.CENTER);

		return panel;
	}

	public void addRActions(){
		system.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				nimbus.setSelected(false);
				weblaf.setSelected(false);
				Themes.CURRENT = Themes.SYSTEM;

			}
		});

		nimbus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setSelected(false);
				weblaf.setSelected(false);
				Themes.CURRENT = Themes.NIMBUS;
			}
		});

		weblaf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setSelected(false);
				nimbus.setSelected(false);
				Themes.CURRENT = Themes.WEBLAF;
			}
		});
		
	}


	public void show(){
		dialog.setVisible(true);
	}

}
