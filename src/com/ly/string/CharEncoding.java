package com.ly.string;

import java.io.UnsupportedEncodingException;

/**  
 *
 *  @author	Leowy Zhuang
 */
public class CharEncoding {
	
	public static void main(String[] args) {
		
		String str = "测试字符编码：1234abcd？，。；【】｛｝?,.;[] {}";
		
		try {
			byte[] dst = str.getBytes("GBK");
			System.out.println("GBK编码:" + bytesToHexStr(dst));
			
			String src = new String(dst,"GBK");
			System.out.println("GBK解码："+ src);
			
			dst = str.getBytes("GB18030");
			System.out.println("GB18030编码："+bytesToHexStr(dst));
			
			src = new String(dst,"GB18030");
			System.out.println("GB18030解码："+src);
			
			dst = str.getBytes("GB2312");
			System.out.println("GB2312编码："+bytesToHexStr(dst));
			
			src = new String(dst,"GB2312");
			System.out.println("GB2312解码："+src);
			
			System.out.println("---------------------------------------");
			
			dst = str.getBytes("UTF-8");
			System.out.println("UTF-8编码："+bytesToHexStr(dst));
			
			src = new String(dst,"UTF-8");
			System.out.println("UTF-8解码："+src);
			
			dst = str.getBytes("UTF-16");
			System.out.println("UTF-16编码："+bytesToHexStr(dst));
			
			src = new String(dst,"UTF-16");
			System.out.println("UTF-16解码："+src);
			
			dst = str.getBytes("UnicodeBigUnmarked");
			System.out.println("UCS2编码："+bytesToHexStr(dst));
			
			src = new String(dst,"UnicodeBigUnmarked");
			System.out.println("UCS2解码："+src);
			
			dst = str.getBytes("Unicode");
			System.out.println("Unicode编码："+bytesToHexStr(dst));
			
			src = new String(dst,"Unicode");
			System.out.println("Unicode解码："+src);
			
			System.out.println("---------------------------------------");
			
			dst = str.getBytes("ASCII");
			System.out.println("ASCII编码："+bytesToHexStr(dst));
			
			src = new String(dst,"ASCII");
			System.out.println("ASCII解码："+src);
			
			dst = str.getBytes("ISO-8859-1");
			System.out.println("ISO-8859-1编码："+bytesToHexStr(dst));
			
			src = new String(dst,"ISO-8859-1");
			System.out.println("ISO-8859-1解码："+src);
			
			System.out.println("---------------------------------------");
			
			dst = str.getBytes();
			System.out.println("默认编码："+bytesToHexStr(dst));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String bytesToHexStr(byte[] b) {
		if (b == null)
			return " ";
		StringBuffer sb = new StringBuffer(b.length * 3);
		for (int i = 0; i < b.length; i++) {
			sb.append(Integer.toHexString(b[i] & 0xff));
			sb.append(" ");
		}
		return sb.toString();
	}
}
