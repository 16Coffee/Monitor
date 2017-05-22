package gui;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月6日 下午2:18:55 
* 类说明: 
*/
public class InfoPanel extends JScrollPane {

	private static final long serialVersionUID = 1L;
	
	public static ArrayList<Socket> socketlist = new ArrayList<Socket>();
	
	public static JTree tree;
	public static DefaultMutableTreeNode root;	//根节点
	public static DefaultTreeModel treeroot;
	
	public InfoPanel() {
		
		root = new DefaultMutableTreeNode("当前在线客户端");
		tree = new JTree(root);
		treeroot = (DefaultTreeModel) tree.getModel();
		tree.setEditable(true);
		
		setViewportView(tree);
	}
}
