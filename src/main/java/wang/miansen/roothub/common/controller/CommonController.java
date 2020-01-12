package wang.miansen.roothub.common.controller;

import java.nio.file.Paths;
import java.util.Map;

import wang.miansen.roothub.store.StorageService;
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

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.util.ApiAssert;

@Controller
@RequestMapping("/common")
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
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	private Map<String, Object> upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "customPath",defaultValue = "topic") String customPath) {
		JSONObject jsonObject = new JSONObject();
		String[] data = {storageService.store(file, Paths.get(customPath))};
		jsonObject.put("errno", 0);
		jsonObject.put("data", data);
		return jsonObject;
	}
	
	/**
	 * base64格式的文件上传接口
	 * @param base64:base64格式的文件
	 * @param path:自定义的保存路径，如：user
	 * @return
	 */
	@RequestMapping(value = "/upload/base64",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> uploadBase64(String base64, String path){
		ApiAssert.notEmpty(base64, "文件不能为空");
		String url = storageService.store(base64, Paths.get(path));
		return new Result(true, url);
	}
}
