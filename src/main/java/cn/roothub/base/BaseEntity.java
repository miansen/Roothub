package cn.roothub.base;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class BaseEntity {

	private static final long MINUTE = 60 * 1000;
	private static final long HOUR = 60 * MINUTE;
	private static final long DAY = 24 * HOUR;
	private static final long WEEK = 7 * DAY;
	private static final long MONTH = 31 * DAY;
	private static final long YEAR = 12 * MONTH;

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public static String formatDate(Date date) throws Exception {
		
		String str = "个月前";
		byte[] bytes = str.getBytes();
		str = new String(bytes,"UTF-8");
		
		String str2 = "周前";
		byte[] bytes2 = str2.getBytes();
		str2 = new String(bytes2,"UTF-8");
		
		if (date == null)
			return "";

		long offset = System.currentTimeMillis() - date.getTime();
		if (offset > YEAR) {
			return (offset / YEAR) + "年前";
		} else if (offset > MONTH) {
			return (offset / MONTH) + str;
		} else if (offset > WEEK) {
			return (offset / WEEK) + str2;
		} else if (offset > DAY) {
			return (offset / DAY) + "天前";
		} else if (offset > HOUR) {
			return (offset / HOUR) + "小时前";
		} else if (offset > MINUTE) {
			return (offset / MINUTE) + "分钟前";
		} else {
			return "刚刚";
		}
	}
}
