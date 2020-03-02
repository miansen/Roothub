package wang.miansen.roothub.common.ui.support;

/**
 * 表格标签 td 节点扩展接口
 * 
 * @author miansen.wang
 * @date 2020-03-02
 * @since 3.0
 */
public interface TableTagTdSupport {

	/**
	 * 生成 td 节点
	 * @param object 当前迭代的对象
	 * @return String
	 */
	String apply(Object object);
	
}
