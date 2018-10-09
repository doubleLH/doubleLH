package test;

import org.apache.commons.lang.StringUtils;



/**
 * 统计一个字符串"li"在一个指定字符串中出现的次数
 * 
 * @author zte
 *
 */
public class Test02 
{
    public static void main(String[] args)
    {
    	// String str = "a123li456li789li0l1i";
		String str = null;
		
		/*
		int count = getCount(str);
		// int count = getCount2(str);
		System.out.println(count);
		*/
		
		// 有jar包  commons-lang.jar StringUtils的工具类
		stringUtilsMedTest(str);
		
	}

	public static void stringUtilsMedTest(String str) 
	{
		// 首字母变大写
		System.out.println(StringUtils.capitalise(str));
		
		/*
		if (StringUtils.isNotEmpty(str))
		{
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
			System.out.println(str);
		}
		*/
	}

	public static int getCount(String str) 
	{
		// 统计li出现的次数
		int count = 0;
		
		// if (str != null && str.length() != 0)
		if (StringUtils.isNotEmpty(str))
		{
			// "123li456li789li0l1i"
			// 方法1 利用indexOf:"li"在一个指定字符串中第一次出现的索引
			// String str --> StringBuffer str
			StringBuffer sb = new StringBuffer(str);
			
			
			while (sb.indexOf("li") != -1)
			{
				// count = count + 1;
				++count; 
				
				// 容易出现字符串下标越界
				sb.delete(0, sb.indexOf("li") + "li".length());
			}
			
			return count;
		}
		
		return count;
	}
	
	public static int getCount2(String str) 
	{
		return StringUtils.countMatches(str, "li");
	}
}
