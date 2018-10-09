package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

/**
 * HttpURLConnection:提供了访问HTTP协议基本功能
 *     向指定的服务器发送GET/POST请求
 *     
 * @author zte
 *
 */
public class HttpUtil 
{
	 /**
	  * 模拟HTTP请求
	  * @param httpURL     请求URL
	  * @param paramsMap   请求参数
	  * @return String     服务器返回的信息
	  * @throws MalformedURLException   请求URL地址错误异常    MalformedURLException extends IOException
	  * @throws IOException  IO读写异常        MalformedURLException,IOException都是编译期异常
	  */
     public static String sendHttpRequest(String httpURL, Map<String, String> paramsMap) throws MalformedURLException, IOException
     {
    	 
    	 // 1.定义需要访问的服务器的地址https://way.jd.com/turing/turing
    	 URL url = new URL(httpURL);
    	 
    	 // 2.开启连接  HttpURLConnection extends URLConnection
    	 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    	 
    	 // 3.设置请求方式
    	 conn.setRequestMethod("POST");
    	 
    	 // 4.请求时需要带输出参数  https://way.jd.com/turing/turing?info=今天天气如何&loc=南京市雨花台区&userid=222&appkey=72dcaf86e38e19a4ffe6529f494add88
    	 conn.setDoOutput(true);
    	 
    	 // 5. 判断是否有请求参数
    	 // if (paramsMap != null && paramsMap.size() != 0)
    	 if (CollectionUtils.isEmpty(paramsMap))
    	 {
    		return null;
    	 }
    	 
    	 StringBuilder sb = new StringBuilder();
		 
		 // 遍历map
		 for (Entry<String, String> entry: paramsMap.entrySet())
		 {
			 sb.append("&")
			   .append(entry.getKey())
			   .append("=")
			   .append(entry.getValue());
		 }
		 
		
		 if (StringUtils.isNotEmpty(sb.toString()) && sb.toString().startsWith("&"))
		 {
			 // 6. 写出请求参数
        	 conn.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
		 }
    	 
    	
    	 
    	 // 7. 发起请求
    	 conn.connect();
    	 
    	 // 8. 接收对方服务器发送过来的响应信息
    	 InputStream in = conn.getInputStream();
    	 
    	 // 将响应过来的输入流  ----> String
    	 String responseStr = StreamUtils.copyToString(in, Charset.forName("utf-8"));
		 return responseStr;
     }
}
