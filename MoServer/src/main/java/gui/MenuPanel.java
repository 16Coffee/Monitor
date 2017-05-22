package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午2:42:13 
* 类说明: 
*/
public class MenuPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	//菜单条
	private JMenuBar menuBar;
	//菜单选项(菜单,设置,关于)
	private JMenu menuMenu, setMenu, aboutMenu;
	//菜单的子菜单(用户登录,用户切换,会员中心,客服中心)
	private JMenuItem menuItem0, menuItem1, menuItem2, menuItem3;
	//设置的子菜单(软件设置,用户设置,其它设置)
	private JMenuItem setItem0, setItem1, setItem2;
	//关于的子菜单(软件说明,软件版本)
	private JMenuItem aboutItem0, aboutItem1;
	
	MenuListener mlistener = new MenuListener();
	
	public MenuPanel() {
		setBounds(0,0,1080,40);
		setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 250, 40);
		add(menuBar);
		
		menuMenu = new JMenu("  菜单  ");
		menuBar.add(menuMenu);
		
		menuItem0 = new JMenuItem("用户登录");
		menuMenu.add(menuItem0);
		menuItem0.addActionListener(mlistener);
		
		menuItem1 = new JMenuItem("用户切换");
		menuMenu.add(menuItem1);
		
		menuItem2 = new JMenuItem("会员中心");
		menuMenu.add(menuItem2);
		
		menuItem3 = new JMenuItem("客服中心");
		menuMenu.add(menuItem3);
		
		setMenu = new JMenu("  设置  ");
		menuBar.add(setMenu);
		
		setItem0 = new JMenuItem("软件设置");
		setMenu.add(setItem0);
		
		setItem1 = new JMenuItem("用户设置");
		setMenu.add(setItem1);
		
		setItem2 = new JMenuItem("其它设置");
		setMenu.add(setItem2);
		
		aboutMenu = new JMenu("  关于  ");
		menuBar.add(aboutMenu);
		
		aboutItem0 = new JMenuItem("软件说明");
		aboutMenu.add(aboutItem0);
		
		aboutItem1 = new JMenuItem("软件版本");
		aboutMenu.add(aboutItem1);
		aboutItem1.addActionListener(mlistener);
	}
	
	class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object JItem = e.getSource();
			if(JItem == menuItem0) {
				//card.show(infoPanel, "登录");
			} else if (JItem == aboutItem1) {
				//card.show(infoPanel, "软件版本");
			}
		}
	}

}
