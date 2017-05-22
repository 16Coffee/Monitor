import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
  
public class Client {
	Socket socket;  
    InputStreamReader in;  
    DataOutputStream dos;
    BufferedReader bufread;

    public Client(String ip){  
        try {  
            //���ӵ�������  
			socket = new Socket(ip, 8899);  
            in = new InputStreamReader(socket.getInputStream());  
            dos = new DataOutputStream(socket.getOutputStream());
            bufread = new BufferedReader(in);
            listenServerReply(bufread);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    //��������˻ظ�����Ϣ  
    public void listenServerReply(BufferedReader br){  
        new Thread(){  
            @Override  
            public void run() {  
                super.run();  
                String line = null;  
                try {  
                    while((line = br.readLine()) != null){  
                    	System.out.print(line);
                        if(line.equals("StartVideo")) 
                        	VideoSendForm.flag = true;
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }.start();  
    }  
  
}  