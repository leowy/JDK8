package com.lz.string;


/**  
 *
 *  @author	Leowy Zhang
 */
public class UnicodeUtils {
	
	/**
	 * Unicode编码转汉字
	 * @param unicodeStr
	 * @return
	 */
	public static String unicodeToHanzi(String unicodeStr) {
		String[] strs = unicodeStr.toUpperCase().split("\\\\U");
		String result = "";
		for (int i = 1; i < strs.length; i++) {
			result += (char) Integer.valueOf(strs[i], 16).intValue();
		}
		return result;
	}
	
	/**
	 * 汉字转Unicode编码
	 * @param hanzi
	 * @return
	 */
	public static String hanziToUnicode(String hanzi) {
		char[] chars = hanzi.toCharArray();
		String result = "";
		for (char c : chars) {
			result += "\\u"+Integer.toString(c, 16);
		}
		return result;
	}
}
