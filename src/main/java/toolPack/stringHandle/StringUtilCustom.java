package toolPack.stringHandle;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class StringUtilCustom {
	
	public static final HashMap<String, Character> bracketMap = new HashMap<String, Character>();
	
	static {{
		bracketMap.put("{", '}');
		bracketMap.put("[", ']');
		bracketMap.put("(", ')');
		bracketMap.put("<", '>');
		bracketMap.put("'", '\'');
		bracketMap.put("\"", '"');
	}};

	
	/**
	 * 从给定的keys, 收集对应的values, 如果找不到, 给空值.
	 * 2017年6月7日
	 * @param jsonStr
	 * @param keys
	 * @return
	 * HashMap<String,String>
	 */
	public HashMap<String, String> getValuesByKeys(String jsonStr, String... keys) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		for(String key : keys) {
			resultMap.put(key, getValuesByKey(jsonStr, key));
		}
		return resultMap;
	}
	
	
	/**
	 * 接收key 返回对应的value
	 * 2017年6月7日
	 * @param jsonStr
	 * @param key
	 * @return
	 * String
	 */
	public String getValuesByKey(String jsonStr, String key) {
		
		if(StringUtils.isEmpty(key) || !jsonStr.contains(key)){
			return null;
		}
		jsonStr = jsonStr.replaceAll("\\s", "");

		int keyBeginIndex = jsonStr.indexOf(key);
		int keyLastIndex = keyBeginIndex + key.length();
		int symbolBeginIndex = keyLastIndex + 2;
		int symbolEndIndex = matchEndSideSymbol(jsonStr, symbolBeginIndex);
		
		if(symbolEndIndex < keyLastIndex) {
			return null;
		}
		
		return jsonStr.substring(symbolBeginIndex, symbolEndIndex + 1); 
		
	}
	
	
	/**
	 * 通过输入的符号坐标,寻找对应的结尾符号坐标, 找不到返回0
	 * 2017年6月7日
	 * @param strInput
	 * @param symbolBeginIndex
	 * @return
	 * int
	 */
	private int matchEndSideSymbol(String strInput, int symbolBeginIndex) {

		int symbolEndIndex = 0;

		Character symbolLeft = strInput.charAt(symbolBeginIndex);
		Character symbolRight = bracketMap.get(String.valueOf(symbolLeft));
		
		if (symbolRight == null) {
			String symbolEnd = ",})]";
			for(int i = symbolBeginIndex + 1; i < strInput.length(); i++) {
				if (symbolEnd.contains(String.valueOf(strInput.charAt(i)))) {
					return --i;
				}
			}
		}
		
		int count = 0;
		if(symbolRight.equals('"')){
			
			for(int i = symbolBeginIndex + 1; i < strInput.length() && count >= 0; i++) {
				if (symbolRight.equals(strInput.charAt(i))) {
					count --;
					symbolEndIndex = i;
				}
			}
			
		} else {
			
			for(int i = symbolBeginIndex + 1; i < strInput.length() && count >= 0; i++) {
				if(symbolLeft.equals(strInput.charAt(i))) {
					count ++;
				} 
				else if (symbolRight.equals(strInput.charAt(i))) {
					count --;
					if(count < 0) {
						symbolEndIndex = i;
					}
				}
				
			}
			
		}
		
		return symbolEndIndex;
	}
	
	/**
	 * 下划线命名转驼峰命名
	 * @param strInput
	 * @return
	 */
	public String underLineToCamel(String strInput) {
		return delimiterToCamel(strInput, "_");
	}
	
	/**
	 * 驼峰转下划线
	 * @param strInput
	 * @return
	 */
	public String camelToUnderLine(String strInput) {
		return camelToDelimiter(strInput, "_");
	}
	
	/**
	 * 驼峰命名法转指定分隔符
	 * @param strInput 输入字符串
	 * @param delimiter 指定的分隔符
	 * 例: 输入(abCdeF, +) ----> ab+cde+f
	 * @return
	 */
	public String camelToDelimiter(String strInput, String delimiter) {
		if (strInput == null & strInput.length() <= 0) {
			return strInput;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strInput.length(); i++) {
			if ((String.valueOf(strInput.charAt(i))).matches("[A-Z]")) {
				sb.append(delimiter);
				sb.append(String.valueOf(strInput.charAt(i)).toLowerCase());
			} else {
				sb.append(strInput.charAt(i));
			}
		}

		return sb.toString();
	}
	
	/**
	 * 指定分隔符命名转驼峰命名
	 * @param strInput 输入字符串
	 * @param delimiter 指定的分隔符
	 * 例: 输入(ab+cde+f, +) ----> abCdeF
	 * @return
	 */
	public String delimiterToCamel(String strInput, String delimiter) {
		if (strInput == null & strInput.length() <= 0) {
			return strInput;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strInput.length(); i++) {
			if (delimiter.equals(String.valueOf(strInput.charAt(i)))) {
				i++;
				sb.append(String.valueOf(strInput.charAt(i)).toUpperCase());
			} else {
				sb.append(strInput.charAt(i));
			}
		}

		return sb.toString();
	}
	
	public String getSuffixName(String str) {
		if(StringUtils.isBlank(str)) {
			return "";
		}
		return str.substring(str.lastIndexOf("."));
	}
}
