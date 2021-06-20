package wang.miansen.roothub.rbac.service;

import java.util.List;

import wang.miansen.roothub.rbac.bo.SystemBO;

/**
 * 系统 Service 接口
 *
 * @author miansen.wang
 * @date 2021-06-20 21:29
 */
public interface SystemService {

    /**
     * 查询所有系统
     *
     * @return 所有系统
     */
    List<SystemBO> list();
}
