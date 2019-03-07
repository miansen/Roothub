package cn.roothub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.roothub.entity.SystemConfig;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-07
 */
public interface SystemConfigDao {

	List<SystemConfig> selectByPid(@Param("pid") Integer pid);
}
