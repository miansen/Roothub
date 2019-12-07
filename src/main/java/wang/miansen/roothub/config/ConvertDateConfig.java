package wang.miansen.roothub.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * <p>
 * 全局日期转换，将 String 类型的时间数据转换为 Date 类型
 * 前端 From 表单传过来 String 类型的时间数据，如：2019-5-1
 * Controller 类中的接收的是 Date 类型
 * </p>
 * @author: miansen.wang
 * @date: 2019-05-02
 */
@Component
public class ConvertDateConfig implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 转换日期，注意这里的转化要和传进来的字符串的格式一直 如 2015-9-9 就应该为 yyyy-MM-dd
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// CustomDateEditor 为自定义日期编辑器
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
