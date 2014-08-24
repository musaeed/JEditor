package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;

import Components.CButton;
import Components.CTabbedPane;
import Gui.JEditor;
import Utility.SpringUtilities;
import core.TextPanel;


public class SignatureDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField fname;
	private JTextField lname;
	private JTextField address;
	private JTextField pNumber;
	private JTextField occupation;
	private JTabbedPane tab;
	private CButton save;
	private CButton preview;
	private CButton cancel;
	private CButton insert;
	private CButton nCancel;
	private JEditorPane pane;

	public SignatureDialog(){
		init();
		addActions();
	}

	public void init(){
		tab = new JTabbedPane();

		JPanel bigPanel = new JPanel();
		bigPanel.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		fname = new JTextField(12);
		lname = new JTextField(12);
		address = new JTextField(12);
		pNumber = new JTextField(12);
		occupation = new JTextField(12);
		
		checkSaved();

		panel.add(new JLabel("First Name:"));
		panel.add(fname);

		panel.add(new JLabel("Last Name:"));
		panel.add(lname);

		panel.add(new JLabel("Address:"));
		panel.add(address);

		panel.add(new JLabel("Phone number:"));
		panel.add(pNumber);

		panel.add(new JLabel("Occupation:"));
		panel.add(occupation);

		SpringUtilities.makeCompactGrid(panel, 5, 2, 6, 6, 6, 6);

		JPanel bPanel = new JPanel();
		FlowLayout f = new FlowLayout();
		f.setAlignment(FlowLayout.RIGHT);
		bPanel.setLayout(f);

		save = new CButton("Save", "save the details", 'S', null, null);
		preview = new CButton("Preview", "preview the signature", 'P', null, null);
		cancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);

		bPanel.add(save);
		bPanel.add(preview);
		bPanel.add(cancel);


		bigPanel.add(panel , BorderLayout.CENTER);
		bigPanel.add(bPanel , BorderLayout.SOUTH);

		tab.addTab("Form", bigPanel);


		final JPanel prPanel = new JPanel();
		prPanel.setLayout(new BorderLayout());
		pane = new JEditorPane();
		pane.setEditable(false);
		prPanel.add(pane , BorderLayout.CENTER);

		JPanel nPanel = new JPanel();
		FlowLayout fb = new FlowLayout();
		fb.setAlignment(FlowLayout.RIGHT);
		nPanel.setLayout(fb);

		insert = new CButton("Insert", "insert the signature in the text area", 'I', null, null);
		nCancel = new CButton("Cancel", "cancel and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);

		nPanel.add(insert);
		nPanel.add(nCancel);



		prPanel.add(nPanel , BorderLayout.SOUTH);

		tab.addTab("Preview", prPanel);

		tab.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(tab.getSelectedComponent() == prPanel){
					pane.setText("\n"+fname.getText()+" "+lname.getText()+"\n"+
							pNumber.getText()+"\n"+	
							address.getText()+"\n"+
							occupation.getText()+"\n");
				}
			}
		});

		tab.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() > 0){
					tab.setSelectedIndex(1);
				}
				else{
					tab.setSelectedIndex(0);
				}
			}
		});

		setTitle("Signature");
		setLayout(new BorderLayout());
		add(tab,BorderLayout.CENTER);
		setSize(new Dimension(450,430));
		setModal(true);
		setLocationRelativeTo(JEditor.frame);
	}

	public void addActions(){
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = new File(System.getProperty("user.home") + "/.cache/JEditor/signature.jeditor");
				PrintWriter o = null;
				
				try {
					o = new PrintWriter(file);
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(JEditor.frame, "Unable to save the signature. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
					return;
				}
				
				o.println(fname.getText());
				o.println(lname.getText());
				o.println(address.getText());
				o.println(pNumber.getText());
				o.println(occupation.getText());
				
				o.flush();
				o.close();

				JOptionPane.showMessageDialog(JEditor.frame, "Information saved.", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextPanel cp = CTabbedPane.getInstance().getPanel();

				try {
					cp.getTextArea().getDocument().insertString(cp.getTextArea().getCaretPosition(), pane.getText(), null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

				dispose();
			}
		});

		nCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancel.doClick();
			}
		});

		preview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tab.setSelectedIndex(1);

			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public void checkSaved(){
		
		File file = new File(System.getProperty("user.home") + "/.cache/JEditor/signature.jeditor");
		
		if(!file.exists()){
			return;
		}
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fname.setText(sc.nextLine());
		lname.setText(sc.nextLine());
		address.setText(sc.nextLine());
		pNumber.setText(sc.nextLine());
		occupation.setText(sc.nextLine());
	}

}
