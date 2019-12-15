package wang.miansen.roothub.common.dao.jdbc.h2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * H2数据库函数扩展
 * @author: miansen.wang
 * @date: 2019-12-15
 */
public abstract class H2DatabaseFunctionExt {

	protected static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	protected static final SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
	
	protected static final long MINUTE = 60 * 1000;
	
	protected static final long HOUR = 60 * MINUTE;
	
	protected static final long DAY = 24 * HOUR;
	
	protected static final long WEEK = 7 * DAY;
	
	protected static final long MONTH = 31 * DAY;
	
	protected static final long YEAR = 12 * MONTH;
	
	public static String NOW() {
		return sdf.format(System.currentTimeMillis());
	}
	
	public static long TO_DAYS(String param) throws ParseException {
		Date date = sdf.parse(param);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long time = calendar.getTimeInMillis();
		return time / DAY;
	}
	
}
