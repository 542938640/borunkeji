package com.taro.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * <p>Title:IdentifyingCode.java</p>
 * <p>Description:验证码图片生成器 </p>
 * <p>Copyright:Copyright (c) 2014</p>
 * @author 张宇唯
 * @version 1.0 2017-11-14
 */
public class IdentifyingCode {
	/**
	 * 验证码图片的宽度。
	 */
	private int width = 80;
	/**
	 * 验证码图片的高度。
	 */
	private int height = 40;
	/**
	 * 验证码的数量。
	 */
	private Random random = new Random();

	public IdentifyingCode() {
		
	}
	public IdentifyingCode(int width,int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * 生成随机颜色
	 * @param fc 前景色
	 * @param bc 背景色
	 * @return Color对象，此Color对象是RGB形式的。
	 */
	public Color getRandomColor(int fc, int bc) {
		if (fc > 255){
			fc = 200;
		}
		if (bc > 255){
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 绘制干扰线
	 * @param g Graphics2D对象，用来绘制图像
	 * @param nums 干扰线的条数
	 */
	public void drawRandomLines(Graphics2D g, int nums) {
		float thick = 1f; //设置画刷的粗细
		Stroke stroke = g.getStroke(); //得到当前的画刷 
		g.setStroke(new BasicStroke(thick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)); //设置新的画刷 
		
		g.setColor(this.getRandomColor(100, 250));
		for (int i = 0; i < nums; i++) {
			int x1 = 0;
			int y1 = random.nextInt(height);
			int x2 = (width/2)+random.nextInt(width);
			int y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		
		g.setStroke( stroke ); //将画刷复原 
	}

	/**
	 * 获取随机字符串， 此函数可以产生由大小写字母，汉字，数字组成的字符串
	 * @param length 随机字符串的长度
	 * @param type 0:生成数字，1：生成A～Z,a~z 的字母，2:数字  + 字母，3：汉字，4：数字+字母+汉字
	 * @return 随机字符串
	 */
	public String drawRandomString(Graphics2D g, int length, int type) {
		StringBuffer strbuf = new StringBuffer();
		String temp = "";
		for (int i = 0; i < length; i++) {
			if(type == 0){	//数字
				temp = this.getNum();
			} else if(type == 1){	//生成A～Z,a~z 的字母
				switch (random.nextInt(2)) {
					case 0: // 生成数字
						temp = this.getULetter();
						break;
					case 1:	//大写字母
						temp = this.getLLetter();
						break;
				}
			} else if(type == 2) {	//生成数字  + 字母
				switch (random.nextInt(3)) {
					case 0: // 生成数字
						temp = this.getNum();
						break;
					case 1:	//大写字母
						temp = this.getULetter();
						break;
					case 2: //小写字母
						temp = this.getLLetter();
						break;
				}
			} else if(type == 3){//中文
				temp = this.getChinese();
			} else if(type == 4){//数字，字母，汉字组合
				switch (random.nextInt(4)) {
					case 0: // 生成数字
						temp = this.getNum();
						break;
					case 1:	//大写字母
						temp = this.getULetter();
						break;
					case 2: //小写字母
						temp = this.getLLetter();
						break;
					case 3: //中文
						temp = this.getChinese();
						break;
				}
			} else {
				temp = this.getULetter();
			}
			Color color = new Color(20 + random.nextInt(100),20 + random.nextInt(100), 20 + random.nextInt(100));
			g.setColor(color);
			// 想文字旋转一定的角度
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
			// 缩放文字
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f){
				scaleSize = 1f;
			}
			trans.scale(scaleSize, scaleSize);
			g.setTransform(trans);
			g.drawString(temp, 15 * i + 10, 14);

			strbuf.append(temp);
		}
		g.dispose();
		return strbuf.toString();
	}
	//生成数字
	private String getNum(){
		String num = String.valueOf((char)(random.nextInt(10) + 48));
		while(num.equals("0") || num.equals("1") ) {
			num = String.valueOf((char)(random.nextInt(10) + 48));
		}
		return num;
	}
	//生成小写字母
	private String getLLetter(){
		String str = String.valueOf((char)(random.nextInt(26) + 97));
		while(str.equals("o") || str.equals("l")) {
			str = String.valueOf((char)(random.nextInt(26) + 97));
		}
		return str;
	}
	//生成大写字母
	private String getULetter(){
		String str = String.valueOf((char)(random.nextInt(26) + 65));
		while(str.equals("O") || str.equals("I")) {
			str = String.valueOf((char)(random.nextInt(26) + 65));
		}
		return str;
	}
	//生成中文汉字
	private String getChinese(){
		String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "a", "b", "c", "d", "e", "f" };
		int r1 = random.nextInt(3) + 11; // 生成第1位的区码
		String strR1 = rBase[r1]; // 生成11～14的随机数
		int r2; // 生成第2位的区码
		if (r1 == 13) {
			r2 = random.nextInt(7); // 生成0～7的随机数					
		} else {
			r2 = random.nextInt(16); // 生成0～16的随机数
		}
		String strR2 = rBase[r2];
		int r3 = random.nextInt(6) + 10; // 生成第1位的位码
		String strR3 = rBase[r3];
		int r4; // 生成第2位的位码
		if (r3 == 10){
			r4 = random.nextInt(15) + 1; // 生成1～16的随机数					
		} else if (r3 == 15) {
			r4 = random.nextInt(15); // 生成0～15的随机数
		} else {
			r4 = random.nextInt(16); // 生成0～16的随机数					
		}
		String strR4 = rBase[r4];
		// 将生成的机内码转换成数字
		byte[] bytes = new byte[2];
		String strR12 = strR1 + strR2; // 将生成的区码保存到字节数组的第1个元素中
		int tempLow = Integer.parseInt(strR12, 16);
		bytes[0] = (byte) tempLow;
		String strR34 = strR3 + strR4; // 将生成的区码保存到字节数组的第2个元素中
		int tempHigh = Integer.parseInt(strR34, 16);
		bytes[1] = (byte) tempHigh;
		String temp = new String(bytes);
		return temp;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
