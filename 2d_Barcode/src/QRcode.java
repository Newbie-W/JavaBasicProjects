/*更多资料请见：
   Java实现简单二维码制作 - JAVA编程语言程序开发技术文章 - 红黑联盟  
   https://www.2cto.com/kf/201608/533065.html
 * */
//import com.swetake.util.Qrcode;
//package .jar;
import java.io.*;
import java.awt.*;
import java.awt.image.*;//BufferedImage类

import javax.imageio.*;

import java.util.Random;

//import com.*;//引入的jar包中
import com.swetake.util.Qrcode;  
//import com.swetake.util.*;
class Createqrcode{
	Qrcode qrcode=new Qrcode();
	Createqrcode() throws IOException{
		qrcode.setQrcodeErrorCorrect('L');//纠错率15%
		qrcode.setQrcodeVersion(7);//版本
		qrcode.setQrcodeEncodeMode('B');//编码
		int width=67+12*(7-1);
		int height=67+12*(7-1);
		//随机设定内容
		String[] str1 = { "采取一个大胆的态度","继续前进","稍后处理它","相信你最起初的想法","让它过去吧","你不要真正的在意","等待","要有耐心点",""
				+ "放弃老的解决方案","结果会是积极的","你必须现在行动","不要被迫太快行动","你一定可以的","加油","别放弃",
				"恭喜你抽到了彩蛋，送给幸运的你最最美好的祝福，另外二维码内容只是娱乐，想做什么，纠结什么，一切看你的态度和理解，加油。一切不开心都会过去" };
    	long t=System.currentTimeMillis();//获取当前时间的毫秒
		Random ran=new Random(t);
    	int n=ran.nextInt(str1.length);/*
		String qrData = ""+str1[n];//二维码存储的信息11http://www.baidu.com*/
		String qrData = "2018！\r\n五一快乐！"+"\r\n"+str1[n];//先回车，后换行
		BufferedImage bufferImage=
				new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D gs=bufferImage.createGraphics();//绘制
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.black);//前景色
		gs.clearRect(0, 0, width, height);
		//设置下偏移量,如果不加偏移量，有时会导致出错。  
	    int pixoff = 2;  
		try{
			byte[] contentBytes=qrData.getBytes("utf-8");//gb2312
			boolean[][] codeOut=qrcode.calQrcode(contentBytes);//
			//System.out.println(codeOut.length+"");
			if(contentBytes.length > 0 && contentBytes.length <120){  
				for (int i=0;i<codeOut.length;i++){
					for (int j=0;j<codeOut.length;j++){
						if (codeOut[j][i]){
							gs.fillRect(j*3+pixoff, i*3+pixoff,3,3);//填充黑色
						}
					}
				}
			}
			gs.dispose();
			bufferImage.flush();
			ImageIO.write(bufferImage, "png", new File("E:/qrcode.png"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
}
public class QRcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Createqrcode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
