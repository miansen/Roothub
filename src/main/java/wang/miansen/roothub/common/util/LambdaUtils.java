package wang.miansen.roothub.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;

import org.springframework.util.SerializationUtils;

import wang.miansen.roothub.common.util.support.SerializedLambda;

/**
 * Lambda 解析工具类
 *
 * @author miansen.wang
 * @date 2021-04-12 20:29
 */
public abstract class LambdaUtils {

    public static SerializedLambda resolve(Serializable s) {
        if (!s.getClass().isSynthetic()) {
            throw new IllegalArgumentException("该方法仅能传入 lambda 表达式产生的合成类");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(SerializationUtils.serialize(s))) {
            @Override
            protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                Class<?> clazz;
                try {
                    clazz = Class.forName(desc.getName(), false, Thread.currentThread().getContextClassLoader());
                } catch (ClassNotFoundException e) {
                    clazz = super.resolveClass(desc);
                }
                return clazz == java.lang.invoke.SerializedLambda.class ? SerializedLambda.class : clazz;
            }
        }) {
            return (SerializedLambda) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Exception in SerializedLambda.readResolve", e);
        }
    }
}
