package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import gui.InfoPanel;
import gui.ToolPanel;

/** 
* @author 张亚军
* @mailbox 2082202747@qq.com 
* @version 2017年5月6日 下午2:39:45 
* 类说明: 
*/
public class Server {  
	ServerSocket server;
	ToolPanel tool;
	DefaultMutableTreeNode newNode;
	
    public Server() throws IOException {  
        // 为了简单起见，所有的异常信息都往外抛  
        int port = 8899;  
        // 定义一个ServiceSocket监听在端口8899上  
        server = new ServerSocket(port);  
        System.out.println("等待与客户端建立连接...");
        
       // tool = new ToolPanel();
       // infopane = new InfoPanel();
        
        while (true) {  
            // server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
            Socket socket = server.accept(); 
            InfoPanel.socketlist.add(socket);

            newNode = new DefaultMutableTreeNode(socket.getInetAddress());
            newNode.setAllowsChildren(true);
            
            InfoPanel.treeroot.insertNodeInto(newNode, InfoPanel.root, InfoPanel.root.getChildCount());
            
            /** 
             * tree的scrollPathToVisible()方法在使Tree会自动展开文件夹以便显示所加入的新节点。
             * 若没加这行则加入的新节点会被 包在文件夹中，必须自行展开文件夹才看得到。
             */
            InfoPanel.tree.scrollPathToVisible(new TreePath(newNode.getPath()));
            
            // 每接收到一个Socket就建立一个新的线程来处理它  
            new Thread(new Task(socket)).start();
        }  
        // server.close();  
    }  
  
    /** 
     * 处理Socket请求的线程类 
     */  
    static class Task implements Runnable {  
  
        private Socket socket;  
  
        public Task(Socket socket) {  
            this.socket = socket;  
        }  
  
        public void run() {  
            try {  
                handlerSocket();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
        /** 
         * 跟客户端Socket进行通信 
         */  
        private void handlerSocket() throws Exception {  
            //跟客户端建立好连接之后，获取socket的InputStream，并从中读取客户端发过来的信息了 
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
            StringBuilder sb = new StringBuilder();  
            String temp;  
            int index;  
            while ((temp = br.readLine()) != null) {  
                if ((index = temp.indexOf("eof")) != -1) { // 遇到eof时就结束接收  
                    sb.append(temp.substring(0, index));  
                    break;  
                }  
                sb.append(temp);  
            }  
          //  System.out.println("Form Cliect[port:" + socket.getPort()+ "] 消息内容:" + sb.toString());  
        }  
  
    }  
  
}  