package wang.miansen.roothub.common.util.support;

import java.io.Serializable;
import java.lang.invoke.MethodHandleInfo;

/**
 * <p> copy from java.lang.invoke.SerializedLambda version JDK1.8 </p>
 * <p> 负责将一个支持序列化的 Lambda 对象序列化为 SerializedLambda </p>
 *
 * @author miansen.wang
 * @date 2021-04-12 20:23
 */
public class SerializedLambda implements Serializable {

    private static final long serialVersionUID = 8025925345765570181L;

    private Class<?> capturingClass;
    private String functionalInterfaceClass;
    private String functionalInterfaceMethodName;
    private String functionalInterfaceMethodSignature;
    private String implClass;
    private String implMethodName;
    private String implMethodSignature;
    private int implMethodKind;
    private String instantiatedMethodType;
    private Object[] capturedArgs;

    public String getCapturingClass() {
        return capturingClass.getName().replace('.', '/');
    }

    public String getFunctionalInterfaceClass() {
        return functionalInterfaceClass;
    }

    public String getFunctionalInterfaceMethodName() {
        return functionalInterfaceMethodName;
    }

    public String getFunctionalInterfaceMethodSignature() {
        return functionalInterfaceMethodSignature;
    }

    public String getImplClass() {
        return implClass;
    }

    public String getImplMethodName() {
        return implMethodName;
    }

    public String getImplMethodSignature() {
        return implMethodSignature;
    }

    public int getImplMethodKind() {
        return implMethodKind;
    }

    public final String getInstantiatedMethodType() {
        return instantiatedMethodType;
    }

    public int getCapturedArgCount() {
        return capturedArgs.length;
    }

    public Object getCapturedArg(int i) {
        return capturedArgs[i];
    }

    @Override
    public String toString() {
        String implKind = MethodHandleInfo.referenceKindToString(implMethodKind);
        return String.format("SerializedLambda[%s=%s, %s=%s.%s:%s, " +
                "%s=%s %s.%s:%s, %s=%s, %s=%d]",
            "capturingClass", capturingClass,
            "functionalInterfaceMethod", functionalInterfaceClass,
            functionalInterfaceMethodName,
            functionalInterfaceMethodSignature,
            "implementation",
            implKind,
            implClass, implMethodName, implMethodSignature,
            "instantiatedMethodType", instantiatedMethodType,
            "numCaptured", capturedArgs.length);
    }
}
