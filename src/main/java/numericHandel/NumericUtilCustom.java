package numericHandel;

import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

public class NumericUtilCustom {

	public static final String simpleNumberRegex = "-?\\d+($|\\.?\\d+)";
	public static final String integerRegex = "-?\\d+";
	public static final String positiveIntegerRegex = "\\d+";
	public static final String decimalRegex = "-?\\d+\\.\\d+";
	public static final String ipRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
	public static final String mobileRegex = "^1[345789]\\d{9}$";

	public static Number strToNumber(String numberStr) {

		if (matchSimpleNumber(numberStr)) {
			System.out.println("not a simple number");
			return null;
		}

		Number num = null;

		try {
			num = NumberFormat.getInstance().parse(numberStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return num;
	}

	public static boolean matchSimpleNumber(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(numberStr);
	}

	public static boolean matchInteger(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(integerRegex);
	}

	public static boolean matchPositiveInteger(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(positiveIntegerRegex);
	}

	public static boolean matchDecimal(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(decimalRegex);
	}

	private static boolean isEmpty(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean matchIpRegex(String ipAddress) {
		if(isEmpty(ipAddress)) {
			return false;
		}
		try {
			String[] ipAddressInArray = ipAddress.split("\\.");

			if (ipAddressInArray.length != 4) {
				return false;
			}

			for (int i = 0; i < ipAddressInArray.length; i++) {

				int ip = Integer.parseInt(ipAddressInArray[i]);
				if (ip > 255) {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Long ipToLong(String ipAddress) {
		if (!matchIpRegex(ipAddress)) {
			return 0L;
		}

		// ipAddressInArray[0] = 192
		String[] ipAddressInArray = ipAddress.split("\\.");

		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {

			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);
			// 1. 192 * 256^3
			// 2. 168 * 256^2
			// 3. 1 * 256^1
			// 4. 2 * 256^0
			result += ip * Math.pow(256, power);
		}

		return result;
	}

	public static long ipToLong2(String ipAddress) {
		if (!matchIpRegex(ipAddress)) {
			return 0L;
		}

		long result = 0;

		String[] ipAddressInArray = ipAddress.split("\\.");

		for (int i = 3; i >= 0; i--) {

			long ip = Long.parseLong(ipAddressInArray[3 - i]);

			// left shifting 24,16,8,0 and bitwise OR

			// 1. 192 << 24
			// 1. 168 << 16
			// 1. 1 << 8
			// 1. 2 << 0
			result |= ip << (i * 8);

		}

		return result;
	}

	public static String longToIp(long i) {

		return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);

	}

	public static String longToIp2(long ip) {
		StringBuilder sb = new StringBuilder(15);

		for (int i = 0; i < 4; i++) {

			// 1. 2
			// 2. 1
			// 3. 168
			// 4. 192
			sb.insert(0, Long.toString(ip & 0xff));

			if (i < 3) {
				sb.insert(0, '.');
			}

			// 1. 192.168.1.2
			// 2. 192.168.1
			// 3. 192.168
			// 4. 192
			ip = ip >> 8;

		}

		return sb.toString();
	}

	public static boolean matchMobile(String str) {
		if(StringUtils.isBlank(str)) {
			return false;
		}
		
		return str.matches(mobileRegex);
	}
}