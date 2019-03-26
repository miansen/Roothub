package cn.roothub.web.front;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.roothub.service.StorageService;

@Controller
public class CommonController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired  
	private HttpServletRequest request;  
	
	@Autowired
	private StorageService storageService;
	
	@ResponseBody
	@RequestMapping(value = "/common/wangEditorUpload", method = RequestMethod.POST)
	private Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		// 修改上传文件的路径 1.windows的路径和linux的不一样 2.文件应用"/"符号 2018.06.03 15:53
		String filePath = request.getSession().getServletContext().getRealPath("/") + "/resources/images/";
		String fileName = storageService.store(file, Paths.get(filePath));
		String[] data = { "/resources/images/" + fileName };
		jsonObject.put("errno", 0);
		jsonObject.put("data", data);
		return jsonObject;
	}
	
	/* @RequestMapping("/fileUpload")  
	    public String fileUpload(@RequestParam("file") MultipartFile file) {  
	        // 判断文件是否为空  
	        if (!file.isEmpty()) {  
	            try {  
	                // 文件保存路径  F:\Tomcat\apache-tomcat-8.5.16\wtpwebapps\ROOT\resources/images/3.png
	            	//12:08:28.168 [http-nio-8080-exec-24] DEBUG priv.sen.root.web.CommonController - 
	            	//F:\Tomcat\apache-tomcat-8.5.16\wtpwebapps\ROOT\resources\images\3.png
	                String filePath = request.getSession().getServletContext().getRealPath("/") + "resources\\images\\"  
	                        + file.getOriginalFilename(); 
	                logger.debug(filePath);
	                // 转存文件  
	                file.transferTo(new File(filePath));
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        // 重定向  
	        return "topic/detail";  
	    }  */
}
