package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import org.springframework.util.StreamUtils;

/**
 * 模拟HTTP请求 ---> 工具类 HttpUtil
 * 
 * @author zte
 *
 */
public class HttpTest 
{
    public static void main(String[] args) throws MalformedURLException, IOException
    {
		 // 1.定义需要访问的服务器的地址https://way.jd.com/turing/turing
    	 URL url = new URL("https://way.jd.com/turing/turing");
    	 
    	 // 2.开启连接  HttpURLConnection extends URLConnection
    	 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    	 
    	 // 3.设置请求方式
    	 conn.setRequestMethod("POST");
    	 
    	 // 4.请求时需要带输出参数  https://way.jd.com/turing/turing?info=今天天气如何&loc=南京市雨花台区&userid=222&appkey=72dcaf86e38e19a4ffe6529f494add88
    	 conn.setDoOutput(true);
    	 
    	 // 5.请求参数设置
    	 String sbParams = new StringBuilder()
			    	         .append("info=").append("你是男的还是女的")
					    	 .append("&loc=").append("南京市雨花台区")
					    	 .append("&userid=").append("222")
					    	 .append("&appkey=").append("72dcaf86e38e19a4ffe6529f494add88")
					    	 .toString();
    	 
    	 // 6. 写出请求参数
    	 conn.getOutputStream().write(sbParams.getBytes("utf-8"));
    	 
    	 // 7. 发起请求
    	 conn.connect();
    	 
    	 // 8. 接收对方服务器发送过来的响应信息
    	 InputStream in = conn.getInputStream();
    	 
    	 // 将响应过来的输入流  ----> String
    	 String responseStr = StreamUtils.copyToString(in, Charset.forName("utf-8"));
    	 System.out.println(responseStr);
	}
}
