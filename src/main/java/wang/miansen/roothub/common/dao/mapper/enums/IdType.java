package wang.miansen.roothub.common.dao.mapper.enums;

/**
 * 数据库主键类型
 * @Author: miansen.wang
 * @Date: 2019/10/20 17:58
 */
public enum IdType {

    /**
     * 数据库自增
     */
    AUTO(0),

    /**
     * 未设置
     */
    NONE(1),

    /**
     * 手动输入
     */
    INPUT(2),

    /**
     * 全局唯一 ID (UUID)
     */
    UUID(3);

    private int type;

    IdType(int type) {
        this.type = type;
    }
}
