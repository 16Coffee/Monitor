package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月5日 上午10:49:11 
* 类说明: 
*/
public class PlayVideo extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private BufferedImage img;
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 900, 560, null);
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
		repaint();
	} 
}
