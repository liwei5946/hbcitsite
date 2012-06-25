package cn.edu.hbcit.servlets;

import java.io.IOException;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Random;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		OutputStream out = response.getOutputStream();
		int width = 80, height = 16;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		
		String sRand ="";
		
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 20 * i + 6, 16);
		}
		//
        HttpSession session = request.getSession(); 
        //
        if(session.getAttribute("valCode")!=null)
        	session.removeAttribute("valCode");
        session.setAttribute("valCode", sRand);  
        //
		g.dispose();
		ImageIO.write(image, "JPEG", out);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

