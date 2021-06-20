package wang.miansen.roothub.user.entity;

import java.util.Date;

import lombok.Data;

import wang.miansen.roothub.common.dao.mapper.annotation.Id;
import wang.miansen.roothub.common.dao.mapper.annotation.Table;
import wang.miansen.roothub.common.dao.mapper.enums.IdType;

/**
 * 用户实体类
 *
 * @author miansen.wang
 * @since 2021-04-17 16:55
 */
@Data
@Table(value = "user")
public class UserDO {

    /**
     * 用户ID
     */
    @Id(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 性别，1：男，2：女
     */
    private Byte gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 地点
     */
    private String location;

    /**
     * 是否禁用（false：启用，true：禁用）
     */
    private Boolean isDisabled;

    /**
     * 是否删除（false：有效，true：删除）
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
