package wang.miansen.roothub.test.util;

import org.junit.Test;

import wang.miansen.roothub.common.dao.mapper.wrapper.SFunction;
import wang.miansen.roothub.common.util.LambdaUtils;
import wang.miansen.roothub.common.util.support.SerializedLambda;
import wang.miansen.roothub.test.BaseTest;

/**
 * Lambda 解析工具类，单元测试
 *
 * @author miansen.wang
 * @date 2021-04-12 21:12
 */
public class LambdaUtilsTest extends BaseTest {

    @Test
    public void resolveTest() {
        SFunction<String, String> f1 = s -> s.toUpperCase();
        SFunction<String, String> f2 = String::toUpperCase;
        SerializedLambda lambda1 = LambdaUtils.resolve(f1);
        SerializedLambda lambda2 = LambdaUtils.resolve(f2);
        String methodName1 = lambda1.getImplMethodName();
        String methodName2 = lambda2.getImplMethodName();
        logger.info("{}", methodName1);
        logger.info("{}", methodName2);
    }
}
