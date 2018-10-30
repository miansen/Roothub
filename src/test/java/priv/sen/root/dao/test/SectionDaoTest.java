package priv.sen.root.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.SectionDao;
import cn.roothub.entity.Section;

public class SectionDaoTest extends BaseTest{
	
	@Autowired
	private SectionDao rootSectionDao;
	
	@Test
	public void selectAllTest() throws Exception {
		List<Section> selectAll = rootSectionDao.selectAll();
		System.out.println(selectAll);
	}
}
