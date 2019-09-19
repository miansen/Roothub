package cn.roothub.bbs.common.util;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/31 11:33
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }
}
