package cn.roothub.bbs.common.dao.wrapper;

import java.io.Serializable;

/**
 * @Author: miansen.wang
 * @Date: 2019/9/13 18:44
 */
@FunctionalInterface
public interface ISqlSegment extends Serializable{

    String getSqlSegment();

}
