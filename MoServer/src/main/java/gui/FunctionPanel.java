package gui;

import java.awt.GridLayout;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JPanel;

import action.CameraListener;
import action.PhotoListener;
import action.ShootListener;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午6:00:42 
* 类说明: 
*/
public class FunctionPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//拍照事件
	PhotoListener plistener1, plistener2;
	
	//启动事件
	CameraListener blistener1, blistener2;
	
	//录像事件
	ShootListener slistener1, slistener2;
	
	public FunctionPanel() throws SocketException {
		setBounds(0,0,200,160);
		setLayout(new GridLayout(5,2,25,8));
		
		JButton startbt = new JButton("启动");
		blistener1 =  new CameraListener(1);
		startbt.addMouseListener(blistener1);
		add(startbt);

		JButton pausebt = new JButton("暂停");
		blistener2 =  new CameraListener(2);
		pausebt.addMouseListener(blistener2);
		add(pausebt);

		JButton closebt = new JButton("关闭");
		blistener2 =  new CameraListener(2);
		closebt.addMouseListener(blistener2);
		add(closebt);

		JButton editbt = new JButton("编辑");
		blistener2 =  new CameraListener(2);
		editbt.addMouseListener(blistener2);
		add(editbt);

		JButton photobt = new JButton("拍照");
		plistener1 = new PhotoListener();
		photobt.addMouseListener(plistener1);
		add(photobt);
		
		JButton videobt = new JButton("录像");
		slistener1 = new ShootListener();
		videobt.addMouseListener(slistener1);
		add(videobt);
	}
}
