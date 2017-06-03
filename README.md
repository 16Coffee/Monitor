# Monitor

软件分为客户端和服务器端

服务器端采用maven管理

将项目下载导入即可运行

软件主要功能已经实现，但是还有不足之处，谅解

1、	软件概述
编写目的：目前校园安全事件突发，有时当学校做出反应的时候已经为时过晚。本软件在局网内通过多台机器的多个摄像头对校园环境进行实时监控，以便对校园安全事件做出及时的响应措施，保证学生和公共财产的安全。
开发环境：Eclipse-Mars、OpenCV 2.4.8、JavaCV-0.7、JDK1.8
系统环境：Windows XP以上或Linux
运行方式：先启动服务器，再启动各个客户端
2、原理简介
	软件采用C/S模式编写，同步阻塞I/O模型，服务器端可以连接多个客户端，客户端将摄像头实时获取的画面通过UDP传输方式发送到服务器，服务器端可以实时查看来自客户端的画面及各种数据，也可以进行画面编辑、拍照，录像等操作。
	软件采用的是内网传输方式，可以跨网段进行数据传输，所有数据都保存在局域网内，避免了软件被攻击的风险，这样一定程度上可以保证数据的安全，另外也保证了画面的清晰流畅。

3、实现过程
	软件采用java语言编写并配合使用OpenCV，实现了单台主机打开多个摄像头，提高了单台主机的利用率，节省硬件资源。下面为调用多个摄像头的主要代码：
OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);    
//调用摄像头 
grabber.start();    
//将所获取摄像头数据放入IplImage 
IplImage image =grabber.grab(); 
其中OpenCVFrameGrabber类的参数表示摄像头打开的顺序，
	介于软件需要传输画面与各种参数，所以采用了TCP和UDP两种传输方式，其中TCP主要用于传输服务器与客户端之间的各种参数，以保证数据的准确性，而UDP主要用于画面的传输，确保画面完整流畅，下面为UDP传输画面的部分代码：
bs1=new ByteArrayOutputStream();	
//将图像以jpg格式写入字节缓冲流
ImageIO.write(grabbedImage1.getBufferedImage(),"jpg",bs1);
by1=bs1.toByteArray();
//构造数据包，将其发送到指定主机的10100端口
data1=new DatagramPacket(by1,by1.length,addr, 10100);
datagramsocket.send(data1);
由于一台机器需要同时传输多个画面与参数，客户端与服务器端均使用了多线程以确保软件的执行效率。
实时画面编辑是本软件的特色功能，基于Swing中JPanel双缓冲机制实现了画面可实时编辑，由于AWT组件中的Canvas较重，会遮盖住所有的其他组件，所以视频的显示是在JPanel上实现的，这样可以在视频显示的JPanel上嵌套一层透明的JPanel以此达到画面实时编辑的目的，下面为双缓冲机制实现的代码：
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
	视频录制功能是在客户端进行的，当服务器点击视频录制功能之后，客户端的即刻开始录制视频，考虑到软件的执行速度，当多个客户端进行连接时，这样做可以减轻服务器磁盘读写的压力，下面为录制视频的主要代码：
recorder1 = new FFmpegFrameRecorder(FILENAME1, grabber1.getImageWidth(), grabber1.getImageHeight() );
recorder1.setFormat("mp4"); 
recorder1.setFrameRate(30);  
recorder1.setVideoBitrate(10 * 1024 * 1024);
拍照功能类似于视频录制，将摄像头获取到的视频流截取即可生成一张照片，可指定单一摄像头进行拍摄。
4、总结
	软件还有诸多没有完善的地方，比如当开启画面编辑之后，主播放界面的画面将会出现掉帧现象，不能对摄像头采集到的画面进行实时识别，以便更快作出响应，例如明火识别等，这些功能将会在后期进行改进添加。
