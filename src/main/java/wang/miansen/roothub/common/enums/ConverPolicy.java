package wang.miansen.roothub.common.enums;

/**
 * 转换的策略
 * @author miansen.wang
 * @date 2020-02-09
 * @since 3.0
 */
public enum ConverPolicy {

	/**
	 * ID 转换成 DTO
	 */
	ID2DTO,
	
	/**
	 * DTO 转换成 ID
	 */
	DTO2ID,
	
	/**
	 * DTO 转换成 属性
	 */
	DTO2PROPERTY,
	
	/**
	 * 字符串转换成时间
	 */
	STRING2DATE,
	
	/**
	 * 时间转换成字符串
	 */
	DATE2STRING,
	
	PROPERTY_CONVER_MASTERDATA;
}
