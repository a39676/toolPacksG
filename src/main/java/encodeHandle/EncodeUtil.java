package encodeHandle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtil {

	/**
	 * 转16进制
	 * 
	 * @param byteArray
	 * @return
	 */
	public String byteArrayToHex16(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];

		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

	public byte[] byteArrayMD5(byte[] inputByteArray) {

		MessageDigest messageDigest;
		byte[] resultByteArray = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(inputByteArray);
			resultByteArray = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resultByteArray;
	}

	public String stringMD5(String input) {
		return byteArrayToHex16(byteArrayMD5(input.getBytes()));
	}

	public byte[] fileMD5(String inputFile) {

		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 1 * 1024 * 1024;

		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;

		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			// 使用DigestInputStream
			fileInputStream = new FileInputStream(inputFile);

			digestInputStream = new DigestInputStream(fileInputStream, messageDigest);

			// read的过程中进行MD5处理，直到读完文件
			byte[] buffer = new byte[bufferSize];

			while (digestInputStream.read(buffer) > 0)
				;

			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();

			// 拿到结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();

			return resultByteArray;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				digestInputStream.close();
				fileInputStream.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;
	}

}
