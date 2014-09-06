package OptionDialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import Components.BottomPanel;
import Components.CButton;
import Components.CProgressBar;
import Components.CTabbedPane;
import Gui.JEditor;
import core.TextPanel;

public class HtmlDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private JLabel lable;
	private JTextField field;
	private CButton getHtml;
	private JRadioButton http;
	private JRadioButton https;
	private CButton cancel;
	private JPanel adPanel;
	private JPanel bPanel;
	private JPanel oPanel;

	public HtmlDialog(){
		init();
		addActions();
	}

	public void init(){

		lable = new JLabel("Enter address:");
		field = new JTextField("www.", 25);
		getHtml = new CButton("Get html", "get the html from the www", 'G', KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), null);
		http = new JRadioButton("HTTP");
		https = new JRadioButton("HTTPS");
		cancel = new CButton("Cancel", "cancel and close the dialog", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), null);
		adPanel = new JPanel();
		bPanel = new JPanel();
		oPanel = new JPanel();

		http.setSelected(true);

		adPanel.setLayout(new FlowLayout());
		adPanel.add(lable);
		adPanel.add(field);

		FlowLayout f = new FlowLayout();
		f.setAlignment(FlowLayout.RIGHT);
		bPanel.setLayout(f);
		bPanel.add(getHtml);
		bPanel.add(cancel);

		FlowLayout of = new FlowLayout();
		of.setAlignment(FlowLayout.LEFT);
		oPanel.setLayout(of);
		oPanel.add(http);
		oPanel.add(https);

		setLayout(new BorderLayout());
		add(adPanel , BorderLayout.NORTH);
		add(oPanel,BorderLayout.CENTER);
		add(bPanel , BorderLayout.SOUTH);

		setSize(new Dimension(480,138));
		setLocationRelativeTo(JEditor.frame);
		setTitle("Html from web");
		setModal(true);
	}

	public void addActions(){

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		http.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(http.isSelected()){
					https.setSelected(false);
				}
			}
		});

		https.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(https.isSelected()){
					http.setSelected(false);
				}
			}
		});

		getHtml.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				URL website = null;
				Scanner sc = null;
				boolean isGreater = true;
				int count = 0;

				try {

					if(http.isSelected())
						website = new URL("http://"+field.getText()+"/");
					else
						website = new URL("https://"+field.getText()+"/");

					URLConnection u = website.openConnection();
					sc = new Scanner(u.getInputStream());

				} catch (MalformedURLException e1) {

					JOptionPane.showMessageDialog(JEditor.frame, "The address entered is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
					return;

				} catch (IOException e1) {

					JOptionPane.showMessageDialog(JEditor.frame, "Could not connect to the address.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				StringBuffer bf = new StringBuffer("");

				while(sc.hasNext()){
					CProgressBar.getInstance().setValue(++count);
					CProgressBar.getInstance().update(CProgressBar.getInstance().getGraphics());
					String temp = sc.nextLine();
					if(temp.length() > 200){
						if(isGreater){
							JOptionPane.showMessageDialog(JEditor.frame, "This URL Html has some lines greater in lenght than the maximum supported. These lines will be truncated." ,"Message" , JOptionPane.INFORMATION_MESSAGE);
							isGreater = false;
						}
						while(temp.length() > 600){

							bf.append(temp.substring(0, 600)+"\n");
							temp = temp.substring(0, 600);
						}
					}
					else{
						bf.append(temp + "\n");
					}
				}

				TextPanel panel = CTabbedPane.getInstance().getPanel();

				panel.getTextArea().setText(bf.toString());
				try{
					panel.getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
				} catch(Exception ex){
					ex.printStackTrace();
				}
				panel.getTextArea().setCaretPosition(0);
				panel.setNeedsToBeSaved(false);
				panel.setCurrentFilePath(null);
				CTabbedPane.getInstance().setTitleAt(CTabbedPane.getInstance().getSelectedIndex(), field.getText());
				CTabbedPane.getInstance().setIconAt(CTabbedPane.getInstance().getSelectedIndex(),  new ImageIcon(Toolkit.getDefaultToolkit().getImage(HtmlDialog.class.getClassLoader().getResource("images/document_small.png"))));
				JEditor.frame.setTitle("JEditor - " + field.getText());
				panel.getTextArea().requestFocusInWindow();
				panel.getTextArea().discardAllEdits();

				BottomPanel.fileType.setText("HTML file");
				sc.close();
				CProgressBar.getInstance().setValue(0);
				CProgressBar.getInstance().update(CProgressBar.getInstance().getGraphics());
			}
		});

	}

}
