package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取properties文件的工具类
 * @author zte
 *
 */
public class ReadPropertiesUtil 
{
	private static Map<String, String> map = new HashMap<String, String>();
	
    public static Map<String, String> readProps() throws IOException
    {
    	InputStream in = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream("dataSource.properties");
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        String len = null;
        while ((len = br.readLine()) != null)
        {
        	String[] split = len.split("=");
        	map.put(split[0],split[1]);
        };
        
        return map;
    }
}
