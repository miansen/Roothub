package cn.roothub.test.dao;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.google.gson.Gson;
import cn.roothub.dao.VisitDao;
import cn.roothub.entity.User;
import cn.roothub.entity.Visit;
import cn.roothub.test.base.BaseTest;

public class VisitDaoTest extends BaseTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VisitDao visitDao;
	@Value("${cookie.domain}")
	private String domain;
	
	/**
	 * 添加访问记录
	 * @throws Exception
	 */
	@Test
	public void insertTest() throws Exception{
		Visit v = new Visit();
		v.setUid(2);//访问者
		v.setVid(3);//被访问者
		v.setCreateDate(new Date());
		v.setDelete(false);
		int insert = visitDao.insert(v);
		System.out.println(insert);
	}
	
	/**
	 * 查询访问记录
	 * @throws Exception
	 */
	@Test
	public void selectTest() throws Exception{
		List<User> select = visitDao.select(3, 0, 10);
		Gson gson = new Gson();
		String json = gson.toJson(select);
		logger.debug(json);
		logger.debug(select.toString());
	}
	
	/**
	 * 统计被访问的数量
	 * @throws Exception
	 */
	@Test
	public void countTest() throws Exception{
		int count = visitDao.count(3);
		System.out.println(count);
	}
	
	/**
	 * 是否已存在访问记录
	 * @throws Exception
	 */
	@Test
	public void isVisit() throws Exception{
		int visit = visitDao.isVisit(1, 10);
		System.out.println(visit);
	}
	/**
	 * 更新访问记录
	 * @throws Exception
	 */
	@Test
	public void updateTest() throws Exception{
		Visit visit = new Visit();
		visit.setUid(1);
		visit.setVid(3);
		visit.setCreateDate(new Date());
		visit.setDelete(false);
		int update = visitDao.update(visit);
		System.out.println(update);
	}
	
	@Test
	public void test01() throws Exception{
		logger.debug(domain);
	}
	
}
