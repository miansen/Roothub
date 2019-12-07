package wang.miansen.roothub.common.dao.mapper.util;

/**
 * @Author: miansen.wang
 * @Date: 2019/10/16 22:35
 */
public class StringUtils {

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
