package cn.edu.hbcit.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import com.sun.image.codec.jpeg.*;

import org.apache.log4j.Logger;
/**
 * <p>Description: �ļ�����</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: �ӱ���ҵְҵ����ѧԺ</p>
 *
 * @author ���� : liwei5946@gmail.com
 * @version ����ʱ�䣺Feb 6, 2009 5:10:48 PM
 */

public class FileOperate  {
	protected final Logger log = Logger.getLogger(FileOperate.class.getName());

	public FileOperate() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��������ļ���
	 * �˷����Ѿ���ǰ׺������ˡ�.��
	 * @return String
	 */
	public String generateRandomFilename(){
		String RandomFilename = "";
		Random rand = new Random();//���������
		int random = rand.nextInt();
		
		Calendar calCurrent = Calendar.getInstance();
	    int intDay = calCurrent.get(Calendar.DATE);
	    int intMonth = calCurrent.get(Calendar.MONTH) + 1;
	    int intYear = calCurrent.get(Calendar.YEAR);
	    String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" +
	        String.valueOf(intDay) + "_";
	    log.debug("�����ڽ��յ��ļ���ǰ׺Ϊ��"+now);
	    
	    RandomFilename = now + String.valueOf(random > 0 ? random : ( -1) * random) + ".";
	    
	    return RandomFilename;
	}
	
	/**
	 * ɾ���ļ�
	 * @param fileName
	 * @return boolean
	 */
	public boolean deleteFile(String fileName){
		boolean flag = false;
		try{
			File delFile = new File(fileName);
			log.debug("����ɾ����"+fileName);
			flag = delFile.delete();
		}catch(Exception e){
			log.error(e);
		}
	    
	    return flag;
	}
	
	/**
	 * ΪͼƬ���ˮӡ
	 * @param s Ҫ��ӵ�ˮӡ
	 * @param ImgName Ҫ�޸ĵ�ͼƬ���Ƽ�·��
	 * @param bottom ˮӡ��ױߵľ���
	 * @param right ˮӡ���ұߵľ���
	 */
	public void imageWatermark(String s,String ImgName, int right, int bottom){ 
		try{ 
		File _file = new File(ImgName); 
		Image src = ImageIO.read(_file); 
		File img = new File(s); 
		Image imgsrc = ImageIO.read(img); 
		int wideth=src.getWidth(null); 
		int height=src.getHeight(null); 
		BufferedImage image=new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB); 
		Graphics g=image.createGraphics(); 
		g.drawImage(src,0,0,wideth,height,null); 
		//String s="Ҫ�ӵ�ˮӡ"; 
		g.setColor(Color.LIGHT_GRAY); 
		
		g.setFont(new Font("Britannic Bold",Font.ITALIC,18)); 
		//g.setFont(new Font("Arial Black",Font.ITALIC,18)); 
		//Font aa=new Font("����",Font.PLAIN,20); 

		//g.drawString(s,wideth-right,height-bottom);
		g.drawImage(imgsrc, wideth-right,height-bottom,null);
		g.dispose(); 
		FileOutputStream out=new FileOutputStream(ImgName); 
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
		encoder.encode(image); 
		out.close(); 
		} 
		catch(Exception e){ 
		System.out.println(e); 
		} 
		} 
	
}
