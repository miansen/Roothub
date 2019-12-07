package cn.roothub.bbs.common.util;


/**
 * 断言工具类，有助于验证参数。
 * @author: miansen.wang
 * @date: 2019-12-07
 */
public abstract class Assert {

	/**
	 * 断言一个对象不为空。
	 * @param object 要检查对象
	 * @param message 断言失败时要使用的异常消息
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
}
