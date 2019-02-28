package cn.roothub.dao;

import java.io.Serializable;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public interface CustomerDao {

	int insertBatch(@Param("customers") Collection<? extends Serializable> customers);
}
