package toolPack.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QrCodeDecode {

	public String decode(String filePathStr) throws IOException, NotFoundException {
		File file = new File(filePathStr);
		return decode(file);
	}

	public String decode(File qrCodeimage) throws IOException, NotFoundException {
		BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
		return decode(bufferedImage);
	}

	public String decode(BufferedImage bufferedImage) throws NotFoundException {
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.displayName());

		Result result = new MultiFormatReader().decode(bitmap, hints);
		return result.getText();
	}

}
