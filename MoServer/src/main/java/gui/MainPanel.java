package gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午2:50:00 
* 类说明: 
*/
public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane tabbedPane;
	public static PreviewPanel videopane;
	//缓冲区用于缓冲图像流
	public static BufferedImage image1, image2;

	//视频显示面板
	public static PlayVideo VideoPanel1, VideoPanel2;

	//绘画编辑面板
	public static DrawPanel drawpane1;
		
	public MainPanel() {
		setLayout(new BorderLayout());
		tabbedPane = new JTabbedPane();
		
		VideoPanel1 = new PlayVideo();
		tabbedPane.addTab("当前监控画面", VideoPanel1);
		add(tabbedPane, BorderLayout.CENTER);
	}
}
