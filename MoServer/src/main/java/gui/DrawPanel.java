package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午2:59:52 
* 类说明: 
*/
/*
 * *重绘JPanel，用于绘画
 */
class DrawPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//用于保存鼠标点击的初始位置和鼠标绘图终点的位置
	int x1,y1,x2,y2;
	//实例化一个缓冲区，用于绘制插件图片和视频
	BufferedImage screen = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_ARGB);
	
	public DrawPanel(){
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				x1=e.getX();
				y1=e.getY();
			} 
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				x2=e.getX();
				y2=e.getY();
				drawPath(x1, y1, x2 ,y2);
				repaint();
			}
		});
		thread();
	}
	
	public void paint(Graphics g){
		g.drawImage(screen, 0, 0, this);
	}
	
	//用于绘制拖进视频中的插件图片
	public void paintImage(int x, int y, Image iamge) {
		Graphics g = screen.getGraphics();
		g.drawImage(iamge, x, y, 50, 50, this);
		g.drawImage(iamge, x-400, y+300, 50, 50, this);
		g.drawImage(iamge, x, y+300, 50, 50, this);
		repaint();
	}
	
	//将图像绘制到缓冲区
	public void drawPath(int x1,int y1 ,int x2 , int y2){
		Graphics g = screen.getGraphics();   
		g.setColor(new Color(250,20,250));
		g.drawLine(x1,y1,x2,y2);
		this.x1=this.x2;
		this.y1=this.y2;
		repaint();
	}
	
	//设置一个线程，不停的重绘画图面板，目的是为了防止被视频面板的刷新而遮挡
	public void thread(){
		new Thread(){
			public void run(){
				while(true){
					repaint();
				}
			}
		}.start();
	}
}