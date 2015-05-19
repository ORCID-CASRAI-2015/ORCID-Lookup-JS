package com.fest.utils;

import java.util.List;

public class StringUtils {

	public static String convertListToStringCS(List<String> list) {
		StringBuffer strBuf = new StringBuffer();
		if(list != null) {
			int length = list.size();
			int count = 0;
			for(String val : list) {
				strBuf.append(val);
				if(count != length - 1) {
					strBuf.append(",");
				}
			}
		}
		return strBuf.toString();
	}
	
}
