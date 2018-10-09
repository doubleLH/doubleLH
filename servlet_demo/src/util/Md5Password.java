package util;

import com.ndktools.javamd5.Mademd5;

public class Md5Password {

	public static String getMd5(String str) {
		Mademd5 md5 = new Mademd5();
		
		return md5.toMd5(str);
		}
	}


