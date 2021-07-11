package wang.miansen.roothub.security.service;

import java.util.List;

import wang.miansen.roothub.security.bo.ResourceBO;

/**
 * 资源 Service 接口
 *
 * @author miansen.wang
 * @date 2020-12-27 15:09
 */
public interface ResourceService {

    /**
     * 查询所有的资源
     *
     * @return 资源列表
     */
    List<ResourceBO> list();
}
