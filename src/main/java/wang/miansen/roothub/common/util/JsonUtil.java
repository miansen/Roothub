package wang.miansen.roothub.common.util;

import java.lang.reflect.Type;
import com.google.gson.Gson;

/**
 * json工具类
 * @author sen
 * 2018年7月12日
 * 下午9:48:33
 * TODO
 */
public class JsonUtil {

	public final static Gson gson = new Gson();
	
	/**
	 * 对象转json
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) {
		return gson.toJson(object);
	}
	
	/**
	 * json转对象
	 * @param json
	 * @param object
	 * @return
	 */
	public static <T> T jsonToObject(String json,Class<T> object) {
		return gson.fromJson(json, object);
	}
	
	/**
	 * json转对象
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T jsonToObject(String json,Type type) {
		return gson.fromJson(json, type);
	}
	
}
