package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import Components.CButton;
import Components.CTabbedPane;
import Gui.JEditor;
import Layouts.FlowCustomLayout;

public class PreferencesDialog {

	private JDialog dialog;
	private CButton ok,apply,cancel;
	private JRadioButton west,east,north,south;
	private JList<String> list;

	public PreferencesDialog(){
		init();
	}

	public void init(){
		dialog = new JDialog();
		dialog.setTitle("Preferences");
		dialog.setSize(new Dimension(600,150));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setModal(true);
		dialog.setLayout(new BorderLayout());
		dialog.add(getListComponent() , BorderLayout.WEST);
		dialog.add(getMainPanel() , BorderLayout.CENTER);
		dialog.add(getBPanel() , BorderLayout.SOUTH);
		dialog.setVisible(true);
	}

	public JList<String> getListComponent(){
		list = new JList<String>(new String[] {"General"});
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setSelectedIndex(0);
		list.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
		return list;
	}

	public JPanel getMainPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(getTabAlignmentPanel());
		return panel;
	}

	public JPanel getTabAlignmentPanel(){
		JPanel panel = new JPanel(new FlowLayout());
		west = new JRadioButton("West");
		east = new JRadioButton("East");
		north = new JRadioButton("North");
		south = new JRadioButton("South");

		west.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				east.setSelected(false);
				north.setSelected(false);
				south.setSelected(false);
			}
		});

		east.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				west.setSelected(false);
				north.setSelected(false);
				south.setSelected(false);
			}
		});

		north.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				east.setSelected(false);
				west.setSelected(false);
				south.setSelected(false);
			}
		});

		south.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				east.setSelected(false);
				north.setSelected(false);
				west.setSelected(false);
			}
		});

		switch(CTabbedPane.getInstance().getTabPlacement()){
		
			case JTabbedPane.NORTH:
				north.setSelected(true);
				break;
			case JTabbedPane.SOUTH:
				south.setSelected(true);
				break;
			case JTabbedPane.EAST:
				east.setSelected(true);
				break;
			case JTabbedPane.WEST:
				west.setSelected(true);
				break;
		}

		panel.add(west);
		panel.add(east);
		panel.add(north);
		panel.add(south);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Tab alignment"));
		return panel;
	}

	public JPanel getBPanel(){
		JPanel panel = new JPanel(new FlowCustomLayout(FlowLayout.RIGHT));
		ok = new CButton("Ok", "save the settings and go back", 'O', null, null);
		apply = new CButton("Apply", "apply the settings", 'A', null, null);
		cancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setTabAlignment();
				dialog.dispose();
				CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
			}
		});

		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setTabAlignment();
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
			}
		});

		panel.add(ok);
		panel.add(apply);
		panel.add(cancel);

		return panel;
	}

	public void setTabAlignment(){

		if(west.isSelected()){
			CTabbedPane.getInstance().setTabPlacement(JTabbedPane.LEFT);
		}

		if(east.isSelected()){
			CTabbedPane.getInstance().setTabPlacement(JTabbedPane.RIGHT);
		}

		if(north.isSelected()){
			CTabbedPane.getInstance().setTabPlacement(JTabbedPane.TOP);
		}

		if(south.isSelected()){
			CTabbedPane.getInstance().setTabPlacement(JTabbedPane.BOTTOM);
		}
	}

}
