package wang.miansen.roothub.common.ui;

import wang.miansen.roothub.common.ui.support.TableTagTdSupport;
import wang.miansen.roothub.common.ui.support.TableTagThSupport;

/**
 * 默认的表格标签
 * 
 * @author miansen.wang
 * @date 2020-03-03
 * @since 3.0
 */
@SuppressWarnings("serial")
public class DefaultTableTag extends AbstractTableTag {

	@Override
	protected TableTagThSupport getTableTagThSupport() {
		return null;
	}

	protected TableTagTdSupport getTableTagTdSupport() {
		return null;
	}

}
