package util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import util.FileNameUtil;
/**
 * 
 * 文件上传
 * http://localhost:8080/file_oper/upload.jsp
 * @author Administrator
 *使用Servlet  3.0做文件上传要依赖于 @MultipartConfig   Part --->  part.write(saveServerPath);
 */

@WebServlet("/uploadFile")
@MultipartConfig
public class UploadServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		upload(request,response);
	}
/**
 * 
 * @param request
 * @param response
 * @throws IOException
 * @throws ServletException
 */
	private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		Part part =request.getPart("pic");
		System.out.println(part);
		
		
		 // form-data; name="pic"; filename="baby.jpg"
		String headerInfo = part.getHeader("content-disposition");
		
		//截取headerInfo 字符串   --》 baby.jpg
		String fileName = headerInfo.substring(headerInfo.lastIndexOf("=")+2,headerInfo.length() - 1);
		System.out.println(headerInfo);
		String renameFileName = FileNameUtil.renameFileName(fileName);
		
		
		// 我们本地的图片要上传到 --->   web服务器的地址
        String saveServerBasePath = getServletContext().getRealPath("/upload");
        String saveServerPath = saveServerBasePath + File.separator + renameFileName;
        System.out.println(saveServerPath);
		
        // !!! 注意:如果getServletContext().getRealPath("/upload")的值所对应的目录没有  Servlet 3.0 文件上传 会帮我们主动创建
        
        File file = new File(saveServerBasePath + File.separator);
        file.mkdirs();
       
        
        // 将本地的文件  上传到  saveServerPath这个位置
        part.write(saveServerPath);
        
        response.getWriter().println("文件上传成功...");
        
    }
	
}
