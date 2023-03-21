package toolPack.encryptHandle;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class EncryptUtil {
	
	public String sha1(String str) {
		return DigestUtils.sha1Hex(str);
	}
	
	public byte[] toMd5ByteArray(String str) {
		
		try {
			byte[] bytesOfMessage = str.getBytes(StandardCharsets.UTF_8);
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md.digest(bytesOfMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String toMd5String(String str) { 
		if(toMd5ByteArray(str) == null || toMd5ByteArray(str).length == 0 ) {
			return null;
		}
		try {
			return new String(toMd5ByteArray(str));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String aesEncrypt(String key, String initVector, String value) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		
		byte[] encrypted = cipher.doFinal(value.getBytes());
		
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public String aesDecrypt(String key, String initVector, String encrypted) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		
		byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
		
		return new String(original);
	}
	
	public String customEncrypt(List<List<Character>> keys, String inputStr) {
		StringBuffer builder = new StringBuffer();
		int keyIndex = 0;
		for(int i = 0; i < inputStr.length(); i++) {
			if(i == inputStr.length() - 1) {
				builder.append(inputStr.charAt(0));
				keyIndex = Integer.parseInt(String.valueOf(inputStr.charAt(0)));
				builder.append(keys.get(keyIndex).get(Integer.parseInt(String.valueOf(inputStr.charAt(i)))));
			} else {
				builder.append(inputStr.charAt(i + 1));
				keyIndex = Integer.parseInt(String.valueOf(inputStr.charAt(i + 1)));
				builder.append(keys.get(keyIndex).get(Integer.parseInt(String.valueOf(inputStr.charAt(i)))));
			}
		}
		
		for(int i = 0; i < builder.length(); i = i + 2) {
			builder.replace(i, i+1, keys.get(0).get(Integer.parseInt(String.valueOf(builder.charAt(i)))).toString());
		}
		
		return builder.toString();
	}
	
	public String customDecrypt(List<List<Character>> keys, String inputStr) {
		if(keys == null || keys.size() < 1 || StringUtils.isBlank(inputStr) || inputStr.length() % 2 != 0) {
			return null;
		}
		
		if(!allInKeys(keys, inputStr)) {
			return null;
		}
		
		StringBuffer builder = new StringBuffer(inputStr);
		int keyIndex = 0;
		String tmpChar = null;
		for(int i = 0; i < builder.length(); i = i + 2) {
			tmpChar = String.valueOf(keys.get(0).indexOf((builder.charAt(i))));
			builder.replace(i, i+1, tmpChar);
		}
		
		StringBuffer resultBuilder = new StringBuffer();
		for(int i = 0; i < builder.length(); i = i + 2) {
			keyIndex = Integer.parseInt(String.valueOf(builder.charAt(i)));
			resultBuilder.append(keys.get(keyIndex).indexOf(builder.charAt(i + 1)));
		}
		
		return resultBuilder.toString();
	}
	
	private boolean allInKeys(List<List<Character>> keys, String inputStr) {
		if(keys == null || keys.size() < 1 || StringUtils.isBlank(inputStr)) {
			return false;
		}
		
		List<String> keywords = new ArrayList<String>();
		for(int i = 0; i < keys.size(); i++) {
			for(int j = 0; j < keys.get(i).size(); j++) {
				keywords.add(keys.get(i).get(j).toString());
			}
		}
		
		for(int i = 0; i < inputStr.length(); i++) {
			if(!keywords.contains(String.valueOf(inputStr.charAt(i)))) {
				return false;
			}
		}
		
		return true;
		
	}

}
