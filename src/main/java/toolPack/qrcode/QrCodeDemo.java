package toolPack.qrcode;

import java.io.IOException;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

public class QrCodeDemo {

	public static void main(String[] args) throws WriterException, IOException, NotFoundException {

//		String pathStr = "d:/tmp/qrcodeDemo.png";
//		
//		String str = new String((args[0]).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
//		
//		System.out.println(str);
//		
//		QrCodeGenerator g = new QrCodeGenerator();
//		g.generation(str, pathStr);
//		
//		QrCodeDecode d = new QrCodeDecode();
//		System.out.println(d.decode(pathStr));

		String pathStr2 = "d:/tmp/tmpCode2.png";
		QrCodeDecode d = new QrCodeDecode();
		System.out.println(d.decode(pathStr2));

	}
}
