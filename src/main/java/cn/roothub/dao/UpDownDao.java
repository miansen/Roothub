package cn.roothub.dao;

import org.apache.ibatis.annotations.Param;
import cn.roothub.entity.UpDown;

/**
 * 
 * @author sen
 * 2018年8月11日
 * 上午10:34:54
 * TODO
 */
public interface UpDownDao {

	/**
	 * 统计赞同或者反对的数量
	 * @param tid:主题ID
	 * @param upDown:0 反对 1 赞同
	 * @return
	 */
	int countUpOrDown(@Param("tid") Integer tid,@Param("upDown") Integer upDown);
	
	/**
	 * 是否已点赞或者点踩
	 * @param uid:用户ID
	 * @param tid:主题ID
	 * @return
	 */
	int isUpOrDown(@Param("uid") Integer uid,@Param("tid") Integer tid);
	
	/**
	 * 添加赞同或者反对的记录
	 * @param upDown
	 * @return
	 */
	int insert(UpDown upDown);
	
	/**
	 * 更新状态
	 * @param upDown
	 * @return
	 */
	int update(UpDown upDown);
}
