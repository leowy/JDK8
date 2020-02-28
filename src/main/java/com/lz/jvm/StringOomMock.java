package com.lz.jvm;

import java.util.ArrayList;
import java.util.List;

/**  
 *
 *  @author	Leowy Zhuang
 */
public class StringOomMock {
	
	static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
