package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.net.SocketException;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import action.Server;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午2:37:04 
* 类说明: 启动类
*/
public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	static JSplitPane splitPane;
	
	public MainFrame() throws SocketException {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		//菜单及其他帮助选项面板
		MenuPanel menupane = new MenuPanel();
		menupane.setPreferredSize(new Dimension(1400, 40));
		contentPane.add(menupane, BorderLayout.NORTH);
		
		//其他面板
		OtherPanel otherpane = new OtherPanel();
		otherpane.setPreferredSize(new Dimension(250, 820));
		contentPane.add(otherpane, BorderLayout.EAST);
		
		//工具类面板
		ToolPanel toolPane = new ToolPanel();
		toolPane.setPreferredSize(new Dimension(250, 820));
		contentPane.add(toolPane, BorderLayout.WEST);

		//主显示面板
		MainPanel mainpane = new MainPanel();
		
		//视频缩略显示面板
		ScrollPanel videopane = new ScrollPanel();
		videopane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		videopane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

		//存放leftsPane2和leftsPane3分割面板
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, mainpane, videopane);
     	splitPane.setDividerLocation(560);
		splitPane.setResizeWeight(0.60);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerSize(5);
		splitPane.setPreferredSize(new Dimension(900, 820));
		contentPane.add(splitPane, BorderLayout.CENTER);
				
		setSize(1400,860);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws Exception 
	{
		Font font = new Font("幼圆", Font.PLAIN, 12);
		@SuppressWarnings("rawtypes")
		Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
		/*
		 * 定义widnows界面*
		 */
		while (keys.hasMoreElements()) {
		Object key = keys.nextElement();
		if (UIManager.get(key) instanceof Font) {
		UIManager.put(key, font);
		}
		} 
		try{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		}catch(Exception el){
		System.exit(0); 
		}
		
		new MainFrame();
		new Server();
	}

}
