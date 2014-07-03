package OptionDialogs;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Components.CLabel;
import Components.ColoredButton;
import Utility.ImageLoader;


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
		
		final JDialog dialog = new JDialog();
		
		dialog.setModal(true);
		dialog.setLayout(new GridLayout(3,1));
		dialog.setTitle(title);
		dialog.setUndecorated(true);
		
		final JLabel info = new CLabel(" " + message);
		info.setOpaque(true);
		
		ColoredButton yes = ColoredButton.GetOkButton("Yes", null);
		ColoredButton no = ColoredButton.GetCancelButton("No", null);
		
		yes.setIcon(ImageLoader.loadImage("images/yes.png"));
		no.setIcon(ImageLoader.loadImage("images/no.png"));
		
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
		dialog.setUndecorated(true);
		
		final JLabel info = new CLabel(" " + message);
		
		info.setOpaque(false);
		
		ColoredButton yes = ColoredButton.GetOkButton("Yes", null);
		ColoredButton no = ColoredButton.GetCancelButton("No", null);
		ColoredButton cancel = ColoredButton.GetRandomButton("Cancel",null);
		
		yes.setIcon(ImageLoader.loadImage("images/yes.png"));
		no.setIcon(ImageLoader.loadImage("images/no.png"));
		cancel.setIcon(ImageLoader.loadImage("images/cancel.png"));
		
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
		
		
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		
		return result;
	}
}
