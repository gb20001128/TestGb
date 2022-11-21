package com.testgb.utils;
//截取视频里的一帧当封面
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;


public class GetDoorImageByVideo {


	public static void method(String fileUrl,String doorImagePath)throws Exception{

		FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(fileUrl);
		fFmpegFrameGrabber.start();
		Frame frame = null;
		//获取视频总帧数
		int frames = fFmpegFrameGrabber.getLengthInFrames();
		int i = 0;
		while (i <= frames){
			frame = fFmpegFrameGrabber.grabImage();
			//截取第10帧
			if(frame!=null && i==7){

				String fileName = doorImagePath+System.currentTimeMillis()+".jpg";
				File file = new File(fileName);
				//创建BufferedImage对象
				Java2DFrameConverter converter = new Java2DFrameConverter();
				BufferedImage bufferedImage = converter.getBufferedImage(frame);
				ImageIO.write(bufferedImage,"jpg",file);
				break;
			}
			i++;
		}


	}


	public static void main(String[] args) throws Exception{

		method("http://1.15.73.202:8080/mv/1.mp4","F:\\");

	}
}
