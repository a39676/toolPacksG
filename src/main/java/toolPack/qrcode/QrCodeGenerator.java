package toolPack.qrcode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import toolPack.constant.FileSuffixNameConstant;

public class QrCodeGenerator {

	private static final int defaultX = 200;
	private static final int defaultY = 200;

	public void generation(String str, String filePathStr, Integer x, Integer y) throws WriterException, IOException {
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
		
		OutputStream output = new FileOutputStream(filePathStr);
		MatrixToImageWriter.writeToStream(matrix, FileSuffixNameConstant.PNG, output);
	}

	public void generation(String str, String filePathStr) throws WriterException, IOException {
		generation(str, filePathStr, defaultX, defaultY);
	}
}
