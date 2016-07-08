package com.tust.lamp.utils;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 处理数据的工具类
 * 
 * @author bairui
 *
 */
public class DataProcessUtils {
	
	/**
	 * 将图片按本来长宽比压缩100宽度的jpg
	 * 
	 * @param inputStream
	 * @param realPath
	 * @return
	 */
	public static String resizeImages(InputStream inputStream, String realPath) {
		OutputStream out = null;
		// 构造原始图像对应的Image对象
		try {
			BufferedImage sourceImage = ImageIO.read(inputStream);
			// 获取原始图片高宽
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();
			// 计算目标图片高宽
			int targetWidth = sourceWidth;
			int targetHeight = sourceHeight;
			if (sourceWidth > 1000) {
				targetWidth = 1000;
				targetHeight = sourceHeight / (sourceWidth / 1000);
			}
			// 创建压缩后目标图片对应Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			// 绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
			// 构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";
			// 创建目标图片输出流
			out = new FileOutputStream(realPath + "/" + targetFileName);
			// 获取jpeg图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			// JPEG编码
			encoder.encode(targetImage);
			// 返回文件名
			return "images/" + targetFileName;
		} catch (Exception e) {
			return null;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
