package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;

import gui.PreviewPanel;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午8:47:04 
* 类说明: 
*/
public class SwitchListener implements MouseListener{
	ByteArrayInputStream in1;
	DatagramPacket data1;
	CameraListener pl;
	
	public void mouseClicked(MouseEvent e) {
		//获取缩略图面板编号，绑定摄像头编号，VideoPanel显示选中的监控画面
		PreviewPanel p = (PreviewPanel) e.getSource();
		
		switch(p.getI()) {
		case 1:
			//更改VideoPanel当前显示的监控画面
			ReceiveThread1.flag = true;
			ReceiveThread2.flag = false;
			//绑定需要拍摄照片的摄像头编号
			PhotoListener.i = p.getI();
			break;
		case 2:
			ReceiveThread2.flag = true;
			ReceiveThread1.flag = false;
			PhotoListener.i = p.getI();
			break;
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
