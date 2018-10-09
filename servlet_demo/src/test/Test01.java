package test;

import java.util.UUID;

/**
 * 为后期文件上传服务
 * 
 * @author zte
 *
 */
public class Test01 extends Object
{
	public static void main(String[] args)
	{
		// "nice.jpg"
		// 截取文件名
		String fileName = "nice.jpg";
		
		// "nice.txt" ---> "nice"
		// ctrl + 2  + l
		// int index = fileName.lastIndexOf(".");
		// System.out.println(index);
		
		/*
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		System.out.println(fileName);
		*/
		
		
		System.out.println("---------------------------------------");
		
		/*
		// "nice.jpg" ---> ".jpg"   左包含 右不包含
		fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		System.out.println(fileName);
		*/
		
		/**
		 *  "nice.jpg" ---> 原始文件名+UUID的字符串+原始后缀(niceUUID.jpg) ---> 为后期文件上传服务
		 */
		// (1)
		String oriName = fileName.substring(0, fileName.lastIndexOf("."));
		System.out.println(oriName);
		

		// (2)
		String randomUUID = UUID.randomUUID().toString();
		System.out.println(randomUUID);
		
		// (3) 后缀
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		System.out.println(suffix);
		
		// upload 上传
		String uploadServerFileName = oriName + randomUUID + suffix;
		
		System.out.println(uploadServerFileName);
		
		
	}
} 
