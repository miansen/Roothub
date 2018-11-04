package priv.sen.root.dao.test;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.NodeDao;
import cn.roothub.entity.Node;

/**
 * @author miansen.wang
 * @date 2018年11月3日 上午11:51:43
 */
public class NodeDaoTest extends BaseTest{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NodeDao nodeDao;
	
	@Test
	public void selectAll() throws Exception {
		List<Node> list = nodeDao.selectAllByTab("lang", 0, 10);
		logger.debug(list.toString());
	}
}
