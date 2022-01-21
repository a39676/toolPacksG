package toolPack.qrcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QrCodeGenerator {

	private static final int defaultX = 200;
	private static final int defaultY = 200;

	public BufferedImage generation(String str, String filePathStr, Integer x, Integer y) throws WriterException {
		if (x == null) {
			x = defaultX;
		}
		
		if (y == null) {
			y = defaultY;
		}
		
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String((str).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
				BarcodeFormat.QR_CODE, 
				x, y);
		
		return MatrixToImageWriter.toBufferedImage(matrix);
	}

	public BufferedImage generation(String str, String filePathStr) throws WriterException, IOException {
		return generation(str, filePathStr, defaultX, defaultY);
	}
}
