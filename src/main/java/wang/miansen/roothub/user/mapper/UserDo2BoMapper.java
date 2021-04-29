package wang.miansen.roothub.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import wang.miansen.roothub.user.bo.UserBO;
import wang.miansen.roothub.user.entity.UserDO;

/**
 * DO 转 BO
 *
 * @author miansen.wang
 * @since 2021-04-17 17:08
 */
@Mapper(componentModel = "spring")
public interface UserDo2BoMapper {

    /**
     * 用户 DO 转 BO
     *
     * @param userDo 用户 DO
     * @return UserBO
     */
    UserBO userDo2Bo(UserDO userDo);

    /**
     * 用户 DO List 转 BO List
     *
     * @param userDoList 用户 DO List
     * @return UserBO List
     */
    List<UserBO> userDoList2BoList(List<UserDO> userDoList);
}
