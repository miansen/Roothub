package cn.roothub.web.front;

import java.nio.file.Paths;
import java.util.Map;
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
import cn.roothub.store.StorageService;

@Controller
public class CommonController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StorageService storageService;
	
	/**
	 * 文件上传接口
	 * @param file: 要上传的文件对象
	 * @param customPath: 自定义存放路径，格式 xxx/xxx/xxx，最后一个目录没有 "/"
	 * @return 返回的是JSON，{errno: 状态，data: 文件访问URL}
	 */
	@ResponseBody
	@RequestMapping(value = "/common/upload", method = RequestMethod.POST)
	private Map<String, Object> upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "customPath",defaultValue = "node") String customPath) {
		JSONObject jsonObject = new JSONObject();
		String[] data = {storageService.store(file, Paths.get(customPath))};
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
