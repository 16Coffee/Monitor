package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;


/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月4日 下午4:25:48 
* 类说明: 
*/
public class PhotoListener implements MouseListener {
	//指定摄像头编号
	static int i;
	//指定照片名称
	String savedImagename = null;
	CameraListener pl;
	
	public PhotoListener () {
		Calendar now = Calendar.getInstance();
		int year =now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DATE);
		int hour  = now.get(Calendar.HOUR);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		savedImagename = String.valueOf("e:\\"+year+month+day+hour+minute+second+"摄像头"+i+".jpg");
	}

	public void mouseClicked(MouseEvent e) {
		switch (i)
		{
		case 1:
			try {
				ImageIO.write(ReceiveThread1.bufimage1, "jpg",new File(savedImagename));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case 2:
			try {
				ImageIO.write(ReceiveThread2.bufimage2, "jpg",new File(savedImagename));
			} catch (IOException e1) {
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
