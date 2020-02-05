package wang.miansen.roothub.common.vo;

import java.io.Serializable;

/**
 * VO 标记接口
 * 
 * @author miansen.wang
 * @date 2020-01-14 
 * @since 3.0
 */
public interface BaseVO extends Serializable {

	/**
	 * 获取主键
	 * @return
	 */
	String getPrimaryKey();
	
}
