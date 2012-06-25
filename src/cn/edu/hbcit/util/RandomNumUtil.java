package cn.edu.hbcit.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
//闅ㄦ鏁搁
public class RandomNumUtil {
	private ByteArrayInputStream image;// 鍥惧儚
	private String str;// 楠岃瘉鐮�

	private RandomNumUtil() {
		init();// 鍒濆鍖栧睘鎬�
	}

	/*
	 * 鍙栧緱RandomNumUtil瀹炰緥
	 */
	public static RandomNumUtil Instance() {
		return new RandomNumUtil();
	}

	/*
	 * 鍙栧緱楠岃瘉鐮佸浘鐗�
	 */
	public ByteArrayInputStream getImage() {
		return this.image;
	}

	/*
	 * 鍙栧緱鍥剧墖鐨勯獙璇佺爜()
	 */
	public String getString() {
		return this.str;
	}

	private void init() {
		// 鍦ㄥ唴瀛樹腑鍒涘缓鍥捐薄
		int width = 63, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 鑾峰彇鍥惧舰涓婁笅鏂�
		Graphics g = image.getGraphics();
		// 鐢熸垚闅忔満绫�
		Random random = new Random();
		// 璁惧畾鑳屾櫙鑹�
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 璁惧畾瀛椾綋
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 闅忔満浜х敓155鏉″共鎵扮嚎锛屼娇鍥捐薄涓殑璁よ瘉鐮佷笉鏄撹鍏跺畠绋嬪簭鎺㈡祴鍒�
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 鍙栭殢鏈轰骇鐢熺殑璁よ瘉鐮�6浣嶆暟瀛�
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 灏嗚璇佺爜鏄剧ず鍒板浘璞′腑
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 璋冪敤鍑芥暟鍑烘潵鐨勯鑹茬浉鍚岋紝鍙兘鏄洜涓虹瀛愬お鎺ヨ繎锛屾墍浠ュ彧鑳界洿鎺ョ敓鎴�
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 璧嬪�楠岃瘉鐮�
		this.str = sRand;

		// 鍥捐薄鐢熸晥
		g.dispose();
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO
					.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
			System.out.println("楠岃瘉鐮佸浘鐗囦骇鐢熷嚭鐜伴敊璇細" + e.toString());
		}

		this.image = input;/* 璧嬪�鍥惧儚 */
	}

	/*
	 * 缁欏畾鑼冨洿鑾峰緱闅忔満棰滆壊
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
