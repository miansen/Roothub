package wang.miansen.roothub.common.util;

/**
 * 断言工具类，有助于验证参数。
 * @author: miansen.wang
 * @date: 2019-12-07
 */
public abstract class Assert {

	/**
	 * 断言一个对象为空。
	 * @param object 要断言对象
	 * @param message 断言失败时要使用的异常消息
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言一个对象不为空。
	 * @param object 要断言对象
	 * @param message 断言失败时要使用的异常消息
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言一个字符串为空
	 * @param txt 要断言的字符串
	 * @param message 断言失败时要使用的异常消息
	 */
	public static void isEmpty(String txt, String message) {
		if (!StringUtils.isEmpty(txt)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言一个字符串不为空
	 * @param txt 要断言的字符串
	 * @param message 断言失败时要使用的异常消息
	 */
	public static void notEmpty(String txt, String message) {
		if (StringUtils.isEmpty(txt)) {
			throw new IllegalArgumentException(message);
		}
	}
}
