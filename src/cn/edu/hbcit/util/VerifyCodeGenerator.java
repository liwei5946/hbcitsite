
package cn.edu.hbcit.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
* @(#)VerifyCodeServlet.java Dec 9, 2007 8:14:14 PM
*
* @author liwei
* ��֤����������ʹ�ô�����Ҫ���������֤��������name������Ϊ"verifycode"
*/
public class VerifyCodeGenerator {

   //private static final VerifyCodeGenerator generator = new VerifyCodeGenerator();
   public static final VerifyCodeGenerator generator = new VerifyCodeGenerator();
   private final String ATTRIBUTE_NAME = "verifycode";
   //ͼƬ�Ŀ��
   private final int WIDTH = 15;
   //ͼƬ�ĸ߶�
   private final int HEIGHT = 22;
   //�ַ�������
   private final int CODE_LENGTH = 4;
   //����ַ�����Χ
   private final String RAND_RANGE = "abcdefghijklmnopqrstuvwxyz"
       + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
       + "1234567890"
       + "@#quot;";

   private final char[] CHARS = RAND_RANGE.toCharArray();

   private Random random = new Random();

   public VerifyCodeGenerator(){
       //
   }

   public static VerifyCodeGenerator getInstance(){
       return generator;
   }

   /**
    * ��������ַ���
    * @return ����ַ���
    */
   private String getRandString(){
     StringBuffer sb = new StringBuffer();
     //  StringBuilder sb = new StringBuilder();
       for (int i = 0; i < CODE_LENGTH; i++)
           sb.append(CHARS[random.nextInt(CHARS.length)]);
       return sb.toString();
   }

   /**
    * ���������ɫ
    * @param ll ������ɫֵ����(lower limit)
    * @param ul ������ɫֵ����(upper limit)
    * @return ���ɵ������ɫ����
    */
   private Color getRandColor(int ll, int ul){
       if (ll > 255) ll = 255;
       if (ll < 1) ll = 1;
       if (ul > 255) ul = 255;
       if (ul < 1) ul = 1;
       if (ul == ll) ul = ll + 1;
       int r = random.nextInt(ul - ll) + ll;
       int g = random.nextInt(ul - ll) + ll;
       int b = random.nextInt(ul - ll) + ll;
       Color color = new Color(r,g,b);
       return color;
   }

   /**
    * ����ָ���ַ�����ͼ������
    * @param verifyCode ��������ӡ������ַ���
    * @return ���ɵ�ͼ������
    * */
   private BufferedImage getImage(String verifyCode){

       BufferedImage image = new BufferedImage(WIDTH * CODE_LENGTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

       //��ȡͼ��������
       Graphics graphics = image.getGraphics();

       //���ñ���ɫ
       graphics.setColor(getRandColor(1,50));
       //��䱳��ɫ
       graphics.fillRect(0, 0, WIDTH * 4, HEIGHT);

       //���ñ߿���ɫ
       graphics.setColor(new Color(0,255,0));
       //���߿�
       for (int i=0; i<2; i++)
           graphics.drawRect(i, i, WIDTH * CODE_LENGTH - i * 2 - 1, HEIGHT - i * 2 - 1);

       //�����������������ɫ
       graphics.setColor(getRandColor(50,100));
       //����50����������
       for (int i=0; i<50; i++){
           int x1 = random.nextInt(WIDTH * CODE_LENGTH - 4) + 2;
           int y1 = random.nextInt(HEIGHT - 4) + 2;
           int x2 = random.nextInt(WIDTH * CODE_LENGTH - 2 - x1) + x1;
           int y2 = y1;
           graphics.drawLine(x1, y1, x2, y2);
       }

       //��������
       graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
       //���ַ���
       for (int i=0; i<this.CODE_LENGTH; i++){
           String temp = verifyCode.substring(i, i+1);
           graphics.setColor(getRandColor(100,255));
           graphics.drawString(temp, 13 * i + 6, 16);
       }

       //ͼ����Ч
       graphics.dispose();

       return image;
   }

   /**
    * ����֤���ͼ�����
    * @param request �û����������
    * @param response �û�����Ӧ����
    * */
   public void printImage(HttpServletRequest request,
           HttpServletResponse response){
       //��ContentType��Ϊ"image/jpeg"���������ʶ��ͼ���ʽ��
       response.setContentType("image/jpeg");
       //����ҳ�治����
       response.setHeader("Pragma", "No-cache");
       response.setHeader("Cache-Control", "no-cache");
       response.setDateHeader("Expires", 2000);

       //��������֤��
       String verifyCode = this.getRandString();
       String str = "ssss";
       for(int i=0; i<10; i++)
           str = str + str;
       //�����֤���ͼ������
       BufferedImage bi = this.getImage(verifyCode);
       //����֤�����session
       request.getSession().setAttribute(ATTRIBUTE_NAME, verifyCode);
       try{
           //���Servlet�����
           ServletOutputStream outStream = response.getOutputStream();
           //������������ͼ�����ݱ���ΪJPEG�������ı�����
           JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);
           //��ͼ�����ݽ��б���
           encoder.encode(bi);
           //ǿ�н����������������뵽ҳ��
           outStream.flush();
           //�ر������
           outStream.close();
       }catch(IOException ex){
           ex.printStackTrace();
       }
   }

   /**
    * ����������֤���Ƿ���ȷ�����û��������֤�������ɵ���֤������򷵻�true�����򷵻�false��
    * @param request �û����������
    * @return ��֤���
    * */
   public boolean check(HttpServletRequest request){
       if (((String)request.getParameter(ATTRIBUTE_NAME))
               .equalsIgnoreCase((String)request.getSession().getAttribute(ATTRIBUTE_NAME))){
           request.getSession().removeAttribute(ATTRIBUTE_NAME);
           return true;
       }
       return false;
   }
}
/**�˴���Ϊ����С������д������qqȺJ��**/
