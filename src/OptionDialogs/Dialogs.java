package OptionDialogs;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Components.ColoredButton;
import Gui.JEditor;


public class Dialogs {
	
	public static final int YES_NO_OPTION = 0;
	public static final int YES_NO_CANCEL_OPTION = 1;
	public static final int YES_OPTION = 2;
	public static final int NO_OPTION = 3;
	public static final int CANCEL_OPTION = 4;
	public static final int CPLUSPLUS = 5;
	public static final int JAVA = 6;
	
	static int result;
	/**
	 * This will show a confirmation dialog on top of the frame specified
	 * 
	 * @param frame the parent frame 
	 * @param message the message to be shown
	 * @param title the title of the dialog
	 * @param type the type of the dialog
	 * @param dimension the size of the dialog
	 * @return the option selected from the dialog
	 * @author Muhammmad Omer Saeed
	 */
	public static int showConfirmationDialog(JFrame frame , String message , String title , int type , Dimension dimension){
		if(type == YES_NO_OPTION){
			return showYesNoDialog(frame, message, title , dimension);
		}
		if(type == YES_NO_CANCEL_OPTION){
			return showYesNoCancelDialog(frame, message, title, dimension);
		}
		throw new IllegalArgumentException();
	}
	
	
	private static int showYesNoDialog(JFrame frame , String message , String title , Dimension dimension){
		
		if(frame == null){
			System.out.println("frame is null");
		}
		
		final JDialog dialog = new JDialog();
		
		dialog.setModal(true);
		dialog.setLayout(new GridLayout(3,1));
		dialog.setTitle(title);
		//dialog.setResizable(false);
		
		JLabel info = new JLabel(" " + message);
		info.setOpaque(true);
		
		
		ColoredButton yes = ColoredButton.GetOkButton("Yes", null);
		ColoredButton no = ColoredButton.GetCancelButton("No", null);
		
//		yes.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TextEditor.class.getClassLoader().getResource("check2.png"))));
//		no.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TextEditor.class.getClassLoader().getResource("delete2.png"))));
		
		yes.setIconTextGap(50);
		no.setIconTextGap(50);
		
		dialog.add(info);
		dialog.add(yes);
		dialog.add(no);
		
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = YES_OPTION;
				dialog.dispose();
			}
		});
		
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = NO_OPTION;
				dialog.dispose();
			}
		});
		
		dialog.setSize(dimension);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);

		return result;


	}
	
	
	private static int showYesNoCancelDialog(JFrame frame , String message , String title , Dimension dimension){
		
		final JDialog dialog = new JDialog();
		dialog.setModal(true);
		dialog.setTitle(title);
		
		JLabel info = new JLabel(" " + message);
		ColoredButton yes = ColoredButton.GetOkButton("Yes", null);
		ColoredButton no = ColoredButton.GetRandomButton("No", null);
		ColoredButton cancel = ColoredButton.GetCancelButton("Cancel",null);
		
		dialog.setLayout(new GridLayout(4 , 1));
		dialog.setSize(dimension);
		dialog.add(info);
		dialog.add(yes);
		dialog.add(no);
		dialog.add(cancel);
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				result = YES_OPTION;
				dialog.dispose();
			}
		});
		
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = NO_OPTION;
				dialog.dispose();
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = CANCEL_OPTION;
				dialog.dispose();
				
			}
		});
		
	//	yes.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TextEditor.class.getClassLoader().getResource("check2.png"))));
	//	no.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TextEditor.class.getClassLoader().getResource("delete2.png"))));
	//	cancel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(TextEditor.class.getClassLoader().getResource("delete_large.png"))));
		
		
		yes.setIconTextGap(30);
		no.setIconTextGap(30);
		cancel.setIconTextGap(15);
		
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		
		return result;
	}
	
	public static int showLanguageSelectionDialog(){
		
		final JDialog dialog = new JDialog();
		ColoredButton cplusplus = ColoredButton.GetRandomButton("C++ Source code", "Select this option to show the code in C++");
		ColoredButton java = ColoredButton.GetRandomButton("Java Source code", "Select this option to show the code in Java");
		
	//	java.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Algorithms.class.getClassLoader().getResource("java.png"))));
	//	cplusplus.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Algorithms.class.getClassLoader().getResource("cplusplus.gif"))));
		
		cplusplus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				result = CPLUSPLUS;
				dialog.dispose();
			}
		});
		
		java.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				result = JAVA;
				dialog.dispose();
			}
		});
		
		
		dialog.setLayout(new GridLayout(2,1));
		dialog.add(cplusplus);
		dialog.add(java);
		
		dialog.setTitle("Select an option");
		dialog.setModal(true);
	//	dialog.setIconImage(TextEditor.frame.getIconImage());
		dialog.setSize(new Dimension(450,300));
		dialog.setLocationRelativeTo(JEditor.frame);
		
		dialog.setVisible(true);
		
		return result;
	}

	/*
	public static void showExportDialog(){
		final JDialog dialog = new JDialog();
		JButton html = ColoredButton.GetRandomButton("HTML document", "Export the current file as a HTML document");
		JButton pdf = ColoredButton.GetRandomButton("PDF document", "Export the current document as a PDF document");
		
		dialog.setLayout(new GridLayout(2,1));
		dialog.add(html);
		dialog.add(pdf);
		
		html.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 1);
		html.getActionMap().put(1, new AbstractAction() {

			private static final long serialVersionUID = 6856107061529836704L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		
		html.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
				
				if(((TextPanel)TextEditor.tabs.getSelectedComponent()).getCurrentFilePath() == null){
					JOptionPane.showMessageDialog(TextEditor.frame, "There is no file opened in the current tab.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(((TextPanel)TextEditor.tabs.getSelectedComponent()).getFileName().contains(".html")){
					JOptionPane.showMessageDialog(TextEditor.frame, "The current file is already a html file. You can view the rendered html in the built-in html renderer.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				HTMLRenderer.getInstance().show(TextEditor.frame);
				
				String text = ((TextPanel)TextEditor.tabs.getSelectedComponent()).getTextArea().getText();
				
				text = "<html>" + text + "</html>";
				
				HTMLRenderer.getInstance().setText(text);
			}
		});
		
		pdf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		dialog.setSize(new Dimension(500,400));
		dialog.setIconImage(TextEditor.frame.getIconImage());
		dialog.setTitle("Export as ..");
		dialog.setModal(true);
		dialog.setLocationRelativeTo(TextEditor.frame);
		dialog.setVisible(true);
	}*/
}
