package Utility;

import java.awt.Dimension;

import javax.swing.JLabel;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import Components.BottomPanel;
import Components.CTabbedPane;
import Gui.JEditor;
import OptionDialogs.Dialogs;
import core.TextPanel;

public class EditorUtilities {
	
	public static void updateInfo (String filePath, CTabbedPane tabs) {
		
		JLabel labelToSet = BottomPanel.fileType;

		if(!filePath.contains(".")){

			if (filePath.substring(filePath.lastIndexOf("/") + 1).equals("Makefile")){
				labelToSet.setText("Makefile");
				((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MAKEFILE);
			}

			return;
		}

		if (filePath.substring(filePath.lastIndexOf('.')).equals(".txt")) {
			labelToSet.setText("Simple text file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".c")) {
			labelToSet.setText("C Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".cpp")) {
			labelToSet.setText("C++ Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".cc")) {
			labelToSet.setText("C++ Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".hpp")) {
			labelToSet.setText("C++ Header file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".h")) {
			labelToSet.setText("C Header file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".java")) {
			labelToSet.setText("Java Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".py")) {
			labelToSet.setText("Python Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".rb")) {
			labelToSet.setText("Ruby file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".js")) {
			labelToSet.setText("Java Script file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".sml")) {
			labelToSet.setText("Standard ML Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".html")) {
			labelToSet.setText("HyperText Markup Language");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".xml")) {
			labelToSet.setText("XML file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".m")) {
			labelToSet.setText("MATLAB function file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".tex")) {
			labelToSet.setText("LaTex source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".css")) {
			labelToSet.setText("CSS styling file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".bat")) {
			labelToSet.setText("Windows batch file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".f90")) {
			labelToSet.setText("Fortran Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_FORTRAN);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".pl")) {
			labelToSet.setText("Perl Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PERL);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".cs")) {
			labelToSet.setText("C# Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".json")) {
			labelToSet.setText("JSON file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".lsp")) {
			labelToSet.setText("LISP Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".scala")) {
			labelToSet.setText("Scala Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SCALA);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".groovy")) {
			labelToSet.setText("Groovy Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".dtd")) {
			labelToSet.setText("Document type definition file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_DTD);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".clj")) {
			labelToSet.setText("Clojure Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".lua")) {
			labelToSet.setText("LUA Source file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".sas")) {
			labelToSet.setText("SAS file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SAS);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".sql")) {
			labelToSet.setText("SQL file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
		}
		else if (filePath.substring(filePath.lastIndexOf('.')).equals(".php")) {
			labelToSet.setText("PHP file");
			((TextPanel)tabs.getSelectedComponent()).getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
		}

		tabs.setTitleAt(tabs.getSelectedIndex(), filePath.substring(filePath.lastIndexOf("/")+1));
		tabs.setToolTipTextAt(tabs.getSelectedIndex(), filePath);
		tabs.getPanel().setCurrentFilePath(filePath);

	}
	
	public static void exitApplication(){
		boolean isNeedToBeSaved = false;
		
		for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++){
			if(((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).isNeedsToBeSaved()){
				isNeedToBeSaved = true;
			}
		}
		
		if(isNeedToBeSaved){
			int result = Dialogs.showConfirmationDialog(JEditor.frame, "Some of the files need to be saved. Are you sure you want to exit?", "Confirm", Dialogs.YES_NO_OPTION, new Dimension(530,530));
			
			if(result == Dialogs.NO_OPTION){
				return;
			}
		}
		BackUp.getInstance().releaseBackup();
		System.exit(0);
	}

}
