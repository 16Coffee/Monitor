package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午3:02:40 
* 类说明: 
*/
public class PreviewPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage img;
	
	private int i;
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 380, 220, null);
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
		repaint();
	}
}

