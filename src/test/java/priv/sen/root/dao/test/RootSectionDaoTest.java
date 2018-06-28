package priv.sen.root.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import priv.sen.root.dao.RootSectionDao;
import priv.sen.root.entity.RootSection;

public class RootSectionDaoTest extends BaseTest{
	
	@Autowired
	private RootSectionDao rootSectionDao;
	
	@Test
	public void selectAllTest() throws Exception {
		List<RootSection> selectAll = rootSectionDao.selectAll();
		System.out.println(selectAll);
	}
}
