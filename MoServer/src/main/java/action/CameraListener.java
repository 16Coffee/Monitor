package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.imageio.ImageIO;

import gui.MainPanel;
import gui.ScrollPanel;
/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午4:26:11 
* 类说明: 
*/
public class CameraListener implements MouseListener {

	ReceiveThread1 ct1;
	ReceiveThread2 ct2;
	
	int i;
	

	public CameraListener() {
	}
	
	public CameraListener(int i) {
		this.i = i;
	} 

	public void mouseClicked(MouseEvent e) {
		ct1 = new ReceiveThread1(10101);
		ct2 = new ReceiveThread2(10100);
		
		switch (i)
		{
		case 1:
			try {		
				ct1.start();
				ct2.start();
				
				ScrollPanel.VideoPanel1.repaint();
				ScrollPanel.VideoPanel2.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case 2:
			try {
				ct1.wait();
				ct2.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		break;
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}

class ReceiveThread1 extends Thread {
	static boolean flag = false;
	CameraListener pl;
	static BufferedImage bufimage1;
	ByteArrayInputStream in1, in2;
	DatagramSocket datagramsocket1, datagramsocket2;
	DatagramPacket data1, data2;
	
	public ReceiveThread1(int i){
		try {
			
			pl = new CameraListener();
			datagramsocket1 = new DatagramSocket(i);//接收端口
			byte[] by1 = new byte[65600];
			data1 = new DatagramPacket(by1, by1.length);
		} catch (SocketException e1) {
			e1.printStackTrace();
		} 
	}
	
	public void run(){
		try{
			while(true){
				datagramsocket1.receive(data1);
				in1 = new ByteArrayInputStream(data1.getData());
				bufimage1 = ImageIO.read(in1);
				ScrollPanel.VideoPanel1.setImg(bufimage1);
			
				if(flag)
					MainPanel.VideoPanel1.setImg(bufimage1);
			}
		}catch(Exception e){
		}
	}
}

class ReceiveThread2 extends Thread {
	static boolean flag = false;
	static BufferedImage bufimage2;
	ByteArrayInputStream in1, in2;
	DatagramSocket datagramsocket1, datagramsocket2;
	DatagramPacket data1, data2;
	
	public ReceiveThread2(int i){
		try {
			datagramsocket2 = new DatagramSocket(i);//接收端口
			byte[] by1 = new byte[65600];
			data2 = new DatagramPacket(by1, by1.length);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
	}
	
	public void run(){
		try{
			while(true){
				datagramsocket2.receive(data2);
				ByteArrayInputStream in = new ByteArrayInputStream(data2.getData());
				bufimage2 = ImageIO.read(in);
				ScrollPanel.VideoPanel2.setImg(bufimage2);
				if(flag)
					MainPanel.VideoPanel1.setImg(bufimage2);
			}
		}catch(Exception e){
			
		}
	}
}