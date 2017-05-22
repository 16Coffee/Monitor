package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;

import gui.InfoPanel;

public class ShootListener implements MouseListener{

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0; i<InfoPanel.socketlist.size(); i++) {
			 // 回应一下客户端  
	        try {
	        	PrintWriter pw = new PrintWriter(InfoPanel.socketlist.get(i).getOutputStream(), true);
				pw.println("StartVideo");
				pw.flush();
		        System.out.println("To Cliect[port:" + InfoPanel.socketlist.get(i).getPort() + "] 回复客户端的消息发送成功"); 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	     }
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
