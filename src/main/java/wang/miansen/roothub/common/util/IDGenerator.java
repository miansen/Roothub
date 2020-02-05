package wang.miansen.roothub.common.util;

import java.util.Date;
import java.util.UUID;

/**
 * 主键生成器
 * 
 * @author miansen.wang
 * @date 2020-02-05
 * @since 3.0
 */
public class IDGenerator {

	/**
	 * 生成主键（32位）
	 * @return String
	 */
	public static String generateID() {
		return generateID(System.currentTimeMillis()).substring(0, 32);
	}
	
	/**
	 * 根据指定时间戳生成主键
	 * 
	 * @param time 时间戳
	 * @return String
	 */
	public static String generateID(long time) {
		String rtnVal = Long.toHexString(time);
		rtnVal += UUID.randomUUID();
		rtnVal = rtnVal.replaceAll("-", "");
		return rtnVal;
	}
	
	/**
	 * 获取主键创建的时间
	 * 
	 * @param id 主键
	 * @return Date
	 */
	public static Date getIDCreateTime(String id) {
		String timeInfo = id.substring(0, 11);
		return new Date(Long.parseLong(timeInfo, 16));
	}
	
}
