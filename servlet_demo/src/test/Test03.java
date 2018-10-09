package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;


public class Test03 
{
    public static void main(String[] args)
    {
		 String str = "[江苏-扬州]";
		 
		 // "[江苏-扬州]" ---> "江苏-扬州" ---> "江苏" 和   "扬州"
		 
		 /**
		  * 方法1
		  */
		 Pattern pattern = Pattern.compile("\\[(.*)-(.*)\\]");
		 Matcher matcher = pattern.matcher(str);
		 if (matcher.matches())
		 {
			 // (1)  "[江苏-扬州]" ---> "江苏-扬州"
			 String substr = StringUtils.substring(str, 1, str.length() - 1);
			 
			 // (2)   "江苏-扬州" ---> "江苏" 和   "扬州"
			 String[] splitArr = substr.split("-");
			 
			 if (splitArr != null && splitArr.length != 0)  // TODO  第三方中有没有关于数组工具类
			 {
				 String province = splitArr[0];
				 String city = splitArr[1];
				 
				 System.out.println(province);
				 System.out.println(city);
			 }
		 }
		 
		 
		 System.out.println("========================================================");
		 
		 /**
		  *  方法2  正则表达式
		  *    * 星有某
		  *    + 加一多
		  *    ? 问零一
		  *    . 百搭
		  */
		 // 该变量的名字  alt + shift + r(rename 重命名)
		 Pattern pattern2 = Pattern.compile("\\[(.*)-(.*)\\]");
		 Matcher matcher2 = pattern2.matcher(str);
		 if (matcher2.matches())
		 {
			 // group 组  下标从0开始
			 System.out.println(matcher2.group(0));
			 System.out.println(matcher2.group(1));
			 System.out.println(matcher2.group(2));
		 }
	}
}
