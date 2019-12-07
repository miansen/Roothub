package wang.miansen.roothub.common.dao.mapper.util;

/**
 * 数组工具类
 *
 * @Author: miansen.wang
 * @Date: 2019/8/31 11:33
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * 校验数组是否为空
     * @param array 入参
     * @return boolean
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 校验数组是否不为空
     * @param array 入参
     * @return boolean
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }
}
