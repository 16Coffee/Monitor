import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FFmpegFrameRecorder;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class VideoSendForm {
    
	public static final String FILENAME1 = "e:\\output1.mp4";
    public static final String FILENAME2 = "e:\\output2.mp4"; 
	
	//服务器端的ip地址
	String ip;
	
	OpenCVFrameGrabber grabber1, grabber2;
	CanvasFrame canvasFrame;  
     
	//录像开关
	static boolean flag = false;
    InetAddress addr;
    JPanel pan;
    JButton startbt;
    JButton closebt;
    JTextField text;
    JLabel label;
    buttonListeenr listener = new buttonListeenr();
  	
    /*初始化面板函数*/
    public void init() {
    	pan = new JPanel();
    	pan.setLayout(null);
    	pan.setPreferredSize(new Dimension(300, 200));
    	canvasFrame.add(pan, BorderLayout.EAST);
    	
    	label = new JLabel("输入对方ip地址:");
    	label.setBounds(10, 10, 260, 30);
    	pan.add(label);
    	text = new JTextField("192.168.65.62");
    	pan.add(text);
    	text.setBounds(10, 40, 260, 30);
    	
    	startbt = new JButton("运行");
    	startbt.setBounds(40, 80, 100, 30);
    	startbt.addActionListener(listener);
    	pan.add(startbt);
    	closebt = new JButton("关闭");
    	closebt.setBounds(160, 80, 100, 30);
    	closebt.addActionListener(listener);
    	pan.add(closebt);
    	pan.repaint();
    }
    
    /*按钮监听事件*/
    class buttonListeenr implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		Object s = e.getSource();
    		ip = text.getText();//获取输入的ip地址
    		if(s == closebt) {
    			CloseThread ct1 = new CloseThread(grabber1);
    			ct1.start();
    			CloseThread ct2 = new CloseThread(grabber2);
    			ct2.start();
    			System.exit(0);
    		}
    		
    		if (ip.equals("")) {
    			JOptionPane.showMessageDialog(null, "地址为空或地址格式错误");
    		} else { 
	    		if (s == startbt) {
	    			//启动客户端，开始连接
	    			new Client(ip);
	    			
	    			grabber1 = new  OpenCVFrameGrabber(0);
	    			grabber2 = new  OpenCVFrameGrabber(1);
	    			try {
						addr = InetAddress.getByName(ip);
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}
	    			CVThread1 cv1 = new CVThread1();
	    			cv1.start();
	    		/*	CVThread2 cv2 = new CVThread2();
	    			cv2.start();*/
	    		}
    		}
    		
    	}
    }
    
	/*启动摄像头线程*/
	class CVThread1 extends Thread
	{
		DatagramPacket data1;
		DatagramSocket datagramsocket;
		IplImage grabbedImage1;
		ByteArrayOutputStream bs1;
		byte[] by1;
		FFmpegFrameRecorder recorder1;
		public CVThread1(){
        	by1=new byte[65600];
			try {
				datagramsocket = new DatagramSocket(9996);//发送端口
				
				grabber1.start();
		        grabbedImage1 = grabber1.grab();
		        grabber1.setFrameRate(grabber1.getFrameRate());
		        
		        recorder1 = new FFmpegFrameRecorder(FILENAME1, grabber1.getImageWidth(), grabber1.getImageHeight() );
		        recorder1.setFormat("mp4"); 
	            recorder1.setFrameRate(30);  
	            recorder1.setVideoBitrate(10 * 1024 * 1024);
	            recorder1.start();
	            
			} catch (SocketException e1) {
				e1.printStackTrace();
			} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (com.googlecode.javacv.FrameRecorder.Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public void run(){
			try{	
		        while (true) {
		        	if(canvasFrame.isVisible()  
		                    && (grabbedImage1 = grabber1.grab())!= null){
						bs1=new ByteArrayOutputStream();
						ImageIO.write(grabbedImage1.getBufferedImage(),"jpg",bs1);//将图像以jpg格式写入字节缓冲流
						by1=bs1.toByteArray();
			        	data1=new DatagramPacket(by1,by1.length,addr, 10100);//构造数据包，将其发送到指定主机的10100端口
					    datagramsocket.send(data1);
					    if(flag == true)
					    	recorder1.record(grabbedImage1);
		        	}else{
		        		recorder1.stop();  
		        		grabber1.stop();
		        		break;
		        	}
		        }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/*class CVThread2 extends Thread
	{
		DatagramPacket data2;
		DatagramSocket datagramsocket;
		IplImage grabbedImage2;
		ByteArrayOutputStream bs2;
		byte[] by2;
		FFmpegFrameRecorder recorder2;
		public CVThread2(){
			by2=new byte[65600];
			try {
				datagramsocket = new DatagramSocket(9997);//发送端口
				grabber2.start();
			    grabbedImage2 = grabber2.grab();
			    grabber1.setFrameRate(grabber1.getFrameRate());
		        
		        recorder2 = new FFmpegFrameRecorder(FILENAME2, grabber1.getImageWidth(), grabber1.getImageHeight() );
		        recorder2.setFormat("mp4"); 
	            recorder2.setFrameRate(30);  
	            recorder2.setVideoBitrate(10 * 1024 * 1024);
	            recorder2.start();
			} catch (SocketException e1) {
				e1.printStackTrace();
			} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (com.googlecode.javacv.FrameRecorder.Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void run(){
			try{
		        while (true) {
		        	if(canvasFrame.isVisible() && (grabbedImage2 = grabber2.grab())!= null){
		        		bs2=new ByteArrayOutputStream();
						ImageIO.write(grabbedImage2.getBufferedImage(),"jpg",bs2);//将图像以jpg格式写入字节缓冲流
						by2=bs2.toByteArray();
			        	data2=new DatagramPacket(by2,by2.length,addr, 10101);
					    datagramsocket.send(data2);
					    if(flag == true)
					    	recorder2.record(grabbedImage2);
		        	}else{
		        		recorder2.stop();  
		        		grabber2.stop();
		        		break;
		        	}
		        }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}*/

	/*关闭摄像头线程*/
	class CloseThread extends Thread {
		OpenCVFrameGrabber grabber;
		public CloseThread(OpenCVFrameGrabber grabber) {
			this.grabber = grabber;
		}
		public void run() {
			try {
				grabber.stop();
			} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
				e.printStackTrace();
			}
		}
	}
	
  	public VideoSendForm() throws UnknownHostException {
  		canvasFrame = new CanvasFrame("video1"); 
		canvasFrame.setCanvasSize(320, 160);
		
    	canvasFrame.setLayout(new BorderLayout());
    	canvasFrame.getCanvas().setSize(new Dimension(10, 200));
		init();
  	}

	public static void main(String[]args)throws Exception{
		new VideoSendForm();
	}
}
