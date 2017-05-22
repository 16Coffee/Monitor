package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import action.SwitchListener;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午2:51:11 
* 类说明: 
*/
public class ScrollPanel extends JScrollPane {
	private static final long serialVersionUID = 1L;

	//视频显示面板
	public static PreviewPanel VideoPanel1, VideoPanel2;
	
	SwitchListener stlistener;
	
	JPanel panel;
	public ScrollPanel() {
		//存放监控缩略图的面板
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 5, 10, 2));
		
		stlistener = new SwitchListener();
		
		VideoPanel1 = new PreviewPanel();
		VideoPanel1.setI(1);
		VideoPanel1.addMouseListener(stlistener);
		VideoPanel1.setSize(330,220);
		
		VideoPanel2 = new PreviewPanel();
		VideoPanel2.setI(2);
		VideoPanel2.addMouseListener(stlistener);
		VideoPanel2.setSize(330,220);
		
		panel.add(VideoPanel1);
		panel.add(VideoPanel2);
		
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setViewportView(panel);
	}
}
