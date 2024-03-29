package toolPack.qrcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QrCodeGenerator {

	private static final int defaultX = 200;
	private static final int defaultY = 200;

	@SuppressWarnings("unchecked")
	public BufferedImage generation(String str, Integer x, Integer y) throws WriterException {
		if (x == null) {
			x = defaultX;
		}

		if (y == null) {
			y = defaultY;
		}

		@SuppressWarnings("rawtypes")
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);

		BitMatrix matrix = new MultiFormatWriter().encode(
				new String((str).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), BarcodeFormat.QR_CODE, x, y,
				hints);

		return MatrixToImageWriter.toBufferedImage(matrix);
	}

	public BufferedImage generation(String str) throws WriterException, IOException {
		return generation(str, defaultX, defaultY);
	}
}
