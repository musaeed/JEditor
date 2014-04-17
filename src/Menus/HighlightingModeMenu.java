package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import Components.CMenu;
import Components.CTabbedPane;

public class HighlightingModeMenu extends CMenu{
	

	private static final long serialVersionUID = 1L;
	private JMenuItem plain,c,cpp,java,js,py,fortran,perl,html,xml,tex,css,ruby,bat,cs,json,scala,lisp,groovy,dtd,clj,lua,sas;

	public HighlightingModeMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addActions();
	}
	
	public void init(){
		add(plain = new JMenuItem("Plain Text"));
		addSeparator();
		add(c = new JMenuItem("C"));
		add(cpp = new JMenuItem("C Plus Plus"));
		add(java = new JMenuItem("Java"));
		add(py = new JMenuItem("Python"));
		add(fortran = new JMenuItem("Fortran"));
		add(perl = new JMenuItem("Perl"));
		add(cs = new JMenuItem("C#"));
		add(lisp = new JMenuItem("LISP"));
		add(scala = new JMenuItem("Scala"));
		add(groovy = new JMenuItem("Groovy"));
		add(clj = new JMenuItem("Clojure"));
		add(lua = new JMenuItem("LUA"));
		addSeparator();
		add(ruby = new JMenuItem("Ruby"));
		add(bat = new JMenuItem("Windows batch Script"));
		add(js = new JMenuItem("Java Script"));
		addSeparator();
		add(json = new JMenuItem("JSON"));
		add(xml = new JMenuItem("XML"));
		add(dtd = new JMenuItem("DTD"));
		add(html = new JMenuItem("HTML"));
		add(css = new JMenuItem("CSS"));
		addSeparator();
		add(tex = new JMenuItem("LaTex"));
		add(sas = new JMenuItem("SAS"));
	}

	public void addActions(){
		
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(plain)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
				}
				if(e.getSource().equals(c)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
				}

				if(e.getSource().equals(cpp)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);					
				}

				if(e.getSource().equals(java)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
				}

				if(e.getSource().equals(py)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
				}
				if(e.getSource().equals(fortran)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_FORTRAN);
				}
				if(e.getSource().equals(perl)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PERL);
				}
				if(e.getSource().equals(ruby)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
				}

				if(e.getSource().equals(bat)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH);
				}

				if(e.getSource().equals(js)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
				}

				if(e.getSource().equals(xml)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
				}

				if(e.getSource().equals(html)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
				}
				if(e.getSource().equals(css)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
				}
				if(e.getSource().equals(tex)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
				}
				if(e.getSource().equals(cs)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
				}
				if(e.getSource().equals(lisp)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LISP);
				}
				if(e.getSource().equals(json)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
				}
				if(e.getSource().equals(scala)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SCALA);
				}
				if(e.getSource().equals(groovy)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
				}
				if(e.getSource().equals(dtd)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DTD);
				}
				if(e.getSource().equals(clj)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE);
				}
				if(e.getSource().equals(lua)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
				}
				if(e.getSource().equals(sas)){
					CTabbedPane.getInstance().getPanel().getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SAS);
				}
			}
		};

		plain.addActionListener(action);
		c.addActionListener(action);
		cpp.addActionListener(action);
		java.addActionListener(action);
		py.addActionListener(action);
		fortran.addActionListener(action);
		perl.addActionListener(action);
		cs.addActionListener(action);
		ruby.addActionListener(action);
		bat.addActionListener(action);
		js.addActionListener(action);
		xml.addActionListener(action);
		html.addActionListener(action);
		css.addActionListener(action);
		tex.addActionListener(action);
		lisp.addActionListener(action);
		scala.addActionListener(action);
		json.addActionListener(action);
		groovy.addActionListener(action);
		dtd.addActionListener(action);
		clj.addActionListener(action);
		lua.addActionListener(action);
		sas.addActionListener(action);
	}

}
