package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import Components.CButton;
import Components.CLabel;
import Components.CTabbedPane;
import Gui.JEditor;
import Layouts.FlowCustomLayout;


public class FilePropertiesDialog {
	/*
	 		if(filepath == null){
			JOptionPane.showMessageDialog(frame, "There is no file opened in the current tab");
			return;
		}
		
		File file = new File(filepath);
		
		final JDialog fileDialog = new JDialog();
		fileDialog.setSize(new Dimension(600,670));
		fileDialog.setLocationRelativeTo(frame);
		fileDialog.setModal(true);
		fileDialog.setTitle("File properties");
		fileDialog.setIconImage(frame.getIconImage());
		
		////// file attributes
		JLabel fileName = new JLabel("<html><font color=\"red\"><b>" + "   File name: "  + "</b></font>" + file.getName() + "</html>");
		JLabel fileType = new JLabel("<html><font color=\"red\"><b>" +"File Type: "  + "</b></font>" + editorUtilities.getFileType(filepath) + "</html>");
		JLabel fileLocation = new JLabel("<html><font color=\"red\"><b>" +"File location: "  + "</b></font>" + filepath + "</html>");
		JLabel fileSize = new JLabel("<html><font color=\"red\"><b>" +"File size: " +   "</b></font>" + editorUtilities.getFileSize(filepath) + "</html>");
		JLabel filelastModified = new JLabel("<html><font color=\"red\"><b>" +"Last modified: "  + "</b></font>" + new Date(file.lastModified()).toString() + "</html>");
		JLabel fileOwner = null,fileIsHidden = null,filePermission = null;
		
		try {
			fileOwner = new JLabel("<html><font color=\"red\"><b>" +"Owner: " +  "</b></font>"  +Files.getOwner(file.toPath()).getName() + "</html>");
			fileIsHidden = new JLabel("<html><font color=\"red\"><b>" +"Visibility: "  + "</b></font>" + (Files.isHidden(file.toPath())? "Hidden" : "Visible") + "</html>");
			filePermission = new JLabel("<html><font color=\"red\"><b>" +"Permissions: "  + "</b></font>" + editorUtilities.getFilePermissions(filepath) + "</html>");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Cannot create the property window for this file", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
		////////////////////////
		
		final JButton close = new JButton("<html><font color=\"black\"><b>" +"close" + "</b></font></html>");
		close.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(MDialogs.class.getClassLoader().getResource("delete2.png"))));
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileDialog.dispose();
				
			}
		});
		
		close.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 1);
		close.getActionMap().put(1, new AbstractAction() {
			
			private static final long serialVersionUID = -2678738666645159691L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				close.doClick();
				
			}
		});
		
		fileDialog.setLayout(new BorderLayout());
		
		JPanel atts = new JPanel();
		
		atts.setLayout(new GridLayout(8,1));
		atts.add(fileName);
		atts.add(fileType);
		atts.add(fileLocation);
		atts.add(fileSize);
		atts.add(fileIsHidden);
		atts.add(filelastModified);
		atts.add(fileOwner);
		atts.add(filePermission);
		
		
		
		JPanel bPanel = new JPanel();
		FlowLayout f = new FlowLayout();
		f.setAlignment(FlowLayout.RIGHT);
		bPanel.setLayout(f);
		bPanel.add(close);
		
		
		fileDialog.add(atts , BorderLayout.CENTER);
		fileDialog.add(bPanel , BorderLayout.SOUTH);
		fileDialog.setVisible(true);
	 */
	
	private JDialog dialog;
	private JLabel name,type,location,size,lastModified,owner;
	private CButton close;
	
	public FilePropertiesDialog(){
		init();
	}
	
	public void init(){
		dialog = new JDialog();
		dialog.setModal(true);
		dialog.setTitle("File properties");
		dialog.setSize(new Dimension(600,500));
		dialog.setLocationRelativeTo(JEditor.frame);
		dialog.setLayout(new BorderLayout());
		dialog.add(getMainPanel() , BorderLayout.CENTER);
		dialog.add(getBPanel() , BorderLayout.SOUTH);
		setProperties();
		dialog.setVisible(true);
	}
	
	public JPanel getMainPanel(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel pname = new JPanel(new FlowLayout());
		pname.add(name = new CLabel());
		pname.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Name"));
		
		JPanel ptype = new JPanel(new FlowLayout());
		ptype.add(type = new CLabel());
		ptype.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Type"));
		
		JPanel plocation = new JPanel(new FlowLayout());
		plocation.add(location = new CLabel());
		plocation.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Location"));
		
		JPanel psize = new JPanel(new FlowLayout());
		psize.add(size = new CLabel());
		psize.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Size"));
		
		JPanel plast = new JPanel(new FlowLayout());
		plast.add(lastModified = new CLabel());
		plast.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Last modified"));
		
		JPanel powner = new JPanel(new FlowLayout());
		powner.add(owner = new CLabel());
		powner.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Owner"));
		
		panel.add(pname);
		panel.add(ptype);
		panel.add(plocation);
		panel.add(psize);
		panel.add(plast);
		panel.add(powner);

		return panel;
	}
	
	public JPanel getBPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowCustomLayout(FlowLayout.RIGHT));
		close = new CButton("Close", "close the dialog and go back", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		panel.add(close);
		return panel;
	}
	
	public void setProperties(){
		
		File file = new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath());
		
		name.setText(file.getName());
		lastModified.setText(new Date(file.lastModified()).toString());
		location.setText(file.getAbsolutePath());
		
		try {
			owner.setText(Files.getOwner(file.toPath()).getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		type.setText(getFileType(file.getAbsolutePath()));
		size.setText(getFileSize(file));
		
	}
	
	public static String getFileSize(File file){

		if(file.length() < 1000){
			return file.length() + " bytes";
		}
		else {
			return ( (double)file.length() / 1000.0 ) + " kilobytes";
		}
	}
	
	public static String getFileType(String filePath){
		if(!filePath.contains(".")){
			return filePath.substring(filePath.lastIndexOf("/")+1);
		}

		if (filePath.substring(filePath.lastIndexOf('.')).equals(".txt")) {
			return ("Simple text file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".c")) {
			return ("C Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".cpp")) {
			return ("C++ Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".cc")) {
			return  ("C++ Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".java")) {
			return ("Java Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".py")) {
			return ("Python Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".rb")) {
			return ("Ruby file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".js")) {
			return ("Java Script file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".sml")) {
			return ("Standard ML Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".html")) {
			return ("HyperText Markup Language");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".xml")) {
			return ("XML file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".m")) {
			return ("MATLAB function file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".tex")) {
			return ("LaTex source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".css")) {
			return ("CSS styling file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".bat")) {
			return ("Windows batch file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".f90")) {
			return ("Fortran Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".pl")) {
			return ("Perl Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".cs")) {
			return ("C# Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".json")) {
			return ("JSON file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".lsp")) {
			return ("LISP Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".scala")) {
			return ("Scala Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".groovy")) {
			return ("Groovy Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".dtd")) {
			return ("Document type definition file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".clj")) {
			return ("Clojure Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".lua")) {
			return ("LUA Source file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".sas")) {
			return ("SAS file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".sql")) {
			return ("SQL file");
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".php")) {
			return("PHP file");
		}
		else {
			return filePath.substring(filePath.lastIndexOf('.')+1).toUpperCase() + " file" ;
		}
	}

}
