package Components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import Menus.CurrentFileMenu;
import Menus.FormatMenu;
import Menus.HelpMenu;
import Menus.SourceMenu;
import Menus.ToolMenu;

public class QuickPanel{
	
	private static QuickPanel instance;
	
	private JPanel panel;
	private ColoredButton font,colors,zoomin,zoomout,search,replace,statistics,properties,terminal,browser,html,update;
	
	public static QuickPanel getInstance(){
		
		if(instance == null){
			instance = new QuickPanel();
		}
		
		return instance;
	}
	
	private QuickPanel(){
		init();
	}
	
	public void init(){
		
		panel = new JPanel(new GridLayout(3, 4));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Quick panel"));
		initButtons();
		addToPanel();
		addActions();
	}
	
	public void initButtons(){
		font = ColoredButton.GetRandomButtonAd("", "set the font of the text panel", "");
		font.setIcon(FormatMenu.chooseFont.getIcon());
		colors = ColoredButton.GetRandomButtonAd("", "change the color options of the JEditor", "");
		colors.setIcon(FormatMenu.colorOptions.getIcon());
		zoomin = ColoredButton.GetRandomButtonAd("", "zoom in the text", "");
		zoomin.setIcon(ToolMenu.zoomin.getIcon());
		zoomout = ColoredButton.GetRandomButtonAd("", "zoom out the text", "");
		zoomout.setIcon(ToolMenu.zoomout.getIcon());
		search = ColoredButton.GetRandomButtonAd("", "search for text in the JEditor", "");
		search.setIcon(ToolMenu.search.getIcon());
		replace = ColoredButton.GetRandomButtonAd("", "replace the text in the text area", "");
		replace.setIcon(ToolMenu.replace.getIcon());
		statistics = ColoredButton.GetRandomButtonAd("", "show the document statistics", "");
		statistics.setIcon(ToolMenu.stats.getIcon());
		properties = ColoredButton.GetRandomButtonAd("", "show the file properties", "");
		properties.setIcon(CurrentFileMenu.details.getIcon());
		terminal = ColoredButton.GetRandomButton("", "open the terminal");
		terminal.setIcon(SourceMenu.terminal.getIcon());
		browser = ColoredButton.GetRandomButton("", "open the browser");
		browser.setIcon(SourceMenu.browser.getIcon());
		html = ColoredButton.GetRandomButton("", "render html");
		html.setIcon(SourceMenu.renderHtml.getIcon());
		update = ColoredButton.GetRandomButton("", "update the JEditor");
		update.setIcon(HelpMenu.updates.getIcon());
	}
	
	public void addToPanel(){
		panel.add(font);
		panel.add(colors);
		panel.add(zoomin);
		panel.add(zoomout);
		panel.add(search);
		panel.add(replace);
		panel.add(statistics);
		panel.add(properties);
		panel.add(terminal);
		panel.add(browser);
		panel.add(html);
		panel.add(update);
	}
	
	public void addActions(){
		
		font.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				font.setSelected(false);
				FormatMenu.chooseFont.doClick();
			}
		});
		
		colors.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				colors.setSelected(false);
				FormatMenu.colorOptions.doClick();
			}
		});
		
		zoomin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ToolMenu.zoomin.doClick();
			}
		});
		
		zoomout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ToolMenu.zoomout.doClick();
			}
		});
		
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search.setSelected(false);
				ToolMenu.search.doClick();
			}
		});
		
		replace.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				replace.setSelected(false);
				ToolMenu.replace.doClick();
			}
		});
		
		statistics.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				statistics.setSelected(false);
				ToolMenu.stats.doClick();
			}
		});
		
		properties.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				properties.setSelected(false);
				CurrentFileMenu.details.doClick();
			}
		});
		
		terminal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SourceMenu.terminal.doClick();
			}
		});
		
		browser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SourceMenu.browser.doClick();
			}
		});
		
		html.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				html.setSelected(false);
				SourceMenu.renderHtml.doClick();
			}
		});
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update.setSelected(false);
				HelpMenu.updates.doClick();
			}
		});
		
	}
	
	public JPanel getPanel(){
		return panel;
	}

}
