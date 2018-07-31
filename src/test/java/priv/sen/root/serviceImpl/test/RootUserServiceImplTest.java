package priv.sen.root.serviceImpl.test;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.RootUserExecution;
import cn.roothub.entity.RootUser;
import cn.roothub.service.RootUserService;
import priv.sen.root.dao.test.BaseTest;

public class RootUserServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootUserService rootUserService;
	
	/**
	 * 测试注册用户
	 * @throws Exception
	 */
	@Test
	public void userSaveTest() throws Exception{
		RootUser rootUser = new RootUser();
		rootUser.setUserName("ruqin");
		rootUser.setPassword("123456");
		rootUser.setUserSex("男");
		rootUser.setUserAddr("长沙");
		rootUser.setScore(100);
		rootUser.setAvatar("无");
		rootUser.setEmail("123456@qq.com");
		rootUser.setUrl("www.baidu.com");
		rootUser.setSignature("哈哈");
		rootUser.setThirdId("1158827539");
		rootUser.setReceiveMsg(false);
		rootUser.setCreateDate(new Date());
		rootUser.setUpdateDate(new Date());
		rootUser.setIsBlock(false);
		rootUser.setStatusCd("1000");
		rootUser.setLoginIp("127.0.1");
		rootUser.setLastLoginIp("127.0.1");
		rootUser.setUserType("0");
		RootUserExecution save = rootUserService.save(rootUser);
		logger.debug(save.toString());
	}
	
	/**
	 * 测试更新用户
	 */
	@Test
	public void updateUserTest() throws Exception{
		RootUser rootUser = new RootUser();
		rootUser.setUserId(8);
		rootUser.setUserName("ruqin");
		rootUser.setPassword("000000");
		rootUser.setUserSex("女");
		rootUser.setUserAddr("武汉");
		rootUser.setScore(10);
		rootUser.setAvatar("无");
		rootUser.setEmail("12233444");
		rootUser.setUrl("33333");
		rootUser.setSignature("哈哈哈哈");
		rootUser.setThirdId("789");
		rootUser.setReceiveMsg(true);
		rootUser.setUpdateDate(new Date());
		rootUser.setStatusCd("1000");
		RootUserExecution updateUser = rootUserService.updateUser(rootUser);
		logger.debug(updateUser.toString());
	}
}
