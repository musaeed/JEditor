package Components;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeNode extends DefaultMutableTreeNode{

	private static final long serialVersionUID = 1L;
	public int unique;
	
	public  TreeNode(String name, int unique) {
		super(name);
		this.unique = unique;
	}
	
}
