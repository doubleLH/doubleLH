package util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * 为后期文件上传服务
 * 文件名的拼接
 * @author zte
 *
 */
public class FileNameUtil extends Object
{
	public static String renameFileName(String fileName)
	{
	    
	    if (StringUtils.isEmpty(fileName))
	    {
	        return null;
	    }
	    
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
		
		return uploadServerFileName;
		
	}
} 
