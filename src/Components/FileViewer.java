package Components;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import core.TextPanel;

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
		new Thread(new Runnable() {
			@Override
			public void run() {

				addChangeListener();
			}
		}).start();
	}
	
	public void init(){
		tree = new JTree();
		tree.setCellRenderer(new TreeCellRenderer());
		tree.setComponentPopupMenu(new JTreePopUpMenu());
		root = new DefaultMutableTreeNode("Files opened: ");
		model = new DefaultTreeModel(root);
		tree.setModel(model);
	}
	
	public void addToTree(String filename , int unique){

		root.add(new TreeNode(filename, unique));
		model.reload();

	}
	
	public void setSelectedFile(int unique){
		
		for(int i = 0 ; i < model.getChildCount(model.getRoot()) ; i++){
			
			if(((TreeNode)model.getChild(model.getRoot(), i)).unique == unique){
				tree.setSelectionRow(i+1);
			}
		}
	}
	
	public void removeFromTree(int unique){

		for(int i = 0 ; i < root.getChildCount() ; i++){

			if(((TreeNode)root.getChildAt(i)).unique == unique){
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
					if(((TextPanel)pane.getComponentAt(i)).unique == ((TreeNode)node).unique){
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
