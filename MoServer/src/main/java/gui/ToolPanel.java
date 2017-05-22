package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.SocketException;

import javax.swing.JPanel;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午3:46:03 
* 类说明: 
*/
public class ToolPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public FunctionPanel functionpane;
	public InfoPanel infopane;
	
	public ToolPanel() throws SocketException {
		setLayout(new BorderLayout());
		
		functionpane = new FunctionPanel();
		functionpane.setPreferredSize(new Dimension(250, 200));
		
		infopane = new InfoPanel();
		infopane.setPreferredSize(new Dimension(250, 400));
		
		add(functionpane, BorderLayout.NORTH);
		add(infopane, BorderLayout.CENTER);
	}
}
