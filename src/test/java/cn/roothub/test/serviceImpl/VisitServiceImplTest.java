package cn.roothub.test.serviceImpl;

import java.util.Date;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import cn.roothub.dto.DMLExecution;
import cn.roothub.dto.PageDataBody;
import cn.roothub.entity.User;
import cn.roothub.entity.Visit;
import cn.roothub.service.VisitService;
import cn.roothub.test.base.BaseTest;

public class VisitServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VisitService visitService;
	
	/**
	 * 分页查询访问记录
	 * @throws Exception
	 */
	@Test
	public void pageTest() throws Exception{
		PageDataBody<User> page = visitService.page(3, 1, 10);
		Gson gson = new Gson();
		String json = gson.toJson(page);
		logger.info(page.toString());
		logger.info(json);
	}
	
	/**
	 * 添加访问记录
	 * @throws Exception
	 */
	@Test
	public void saveTest() throws Exception{
		Visit visit = new Visit();
		visit.setUid(1);
		visit.setVid(1);
		visit.setCreateDate(new Date());
		visit.setDelete(false);
		DMLExecution save = visitService.save(visit);
		logger.info(save.toString());
	}
}
