package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import util.HttpUtil;

public class HttpTest02 
{
     public static void main(String[] args) throws MalformedURLException, IOException
     {
    	 Map<String, String> paramsMap = new HashMap<String, String>();
    	 paramsMap.put("info", "你是男的还是女的");
    	 paramsMap.put("loc", "南京市雨花台区");
    	 paramsMap.put("userid", "222");
    	 paramsMap.put("appkey", "72dcaf86e38e19a4ffe6529f494add88");
    	
		 String sendHttpRequest = HttpUtil.sendHttpRequest("https://way.jd.com/turing/turing", paramsMap);
		 System.out.println(sendHttpRequest);
	 }
}
