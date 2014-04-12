package Components;

import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileViewer {

	private JTree tree;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private static FileViewer instance = null;
	
	public static FileViewer getInstance(){
		if(instance == null){
			instance = new FileViewer();
		}
		
		return instance;
	}
	
	private FileViewer(){
		init();
		addChangeListener();
	}
	
	public void init(){
		tree = new JTree();
		root = new DefaultMutableTreeNode("Files opened: ");
		model = new DefaultTreeModel(root);
		tree.setModel(model);
	}
	
	public void addToTree(String filename){

		root.add(new DefaultMutableTreeNode(filename));
		model.reload();

	}
	
	public void setSelectedFile(String filename){
		
		if(filename == null){
			return;
		}
		
		filename = new File(filename).getParent();

		for(int i = 0 ; i < model.getChildCount(model.getRoot()) ; i++){
			if(model.getChild(model.getRoot(), i).toString().equals(filename)){
				tree.setSelectionRow(i+1);
			}
		}
	}
	
	public void removeFromTree(String filename){

		for(int i = 0 ; i < root.getChildCount() ; i++){

			if(root.getChildAt(i).toString().equals(filename)){
				root.remove(i);
			}
		}
		
		model.reload();
	}
	
	public void removeAllFiles(){
		root.removeAllChildren();
		model.reload();
	}
	
	public void addChangeListener(){
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
						tree.getLastSelectedPathComponent();

				if(node == null){
					return;
				}

				CTabbedPane pane = CTabbedPane.getInstance();
				
				for(int i = 0 ; i < pane.getTabCount() ; i++){
					if(pane.getTitleAt(i).equals(node.toString())){
						pane.setSelectedIndex(i);
					}
				}
			}
		});
	}
	
	
	public JTree getTree(){
		return tree;
	}
}
