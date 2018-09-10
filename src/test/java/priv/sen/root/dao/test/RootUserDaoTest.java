package priv.sen.root.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dao.RootUserDao;
import cn.roothub.entity.RootUser;

public class RootUserDaoTest extends BaseTest{

	
	@Autowired
	private RootUserDao rootUserDao;
	
	@Test
	public void testInsertUser() throws Exception{
		RootUser rootUser = new RootUser();
		rootUser.setUserName("void");
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
		int insertUser = rootUserDao.insertUser(rootUser);
		System.out.println(insertUser);
	}
	
	@Test
	public void testSelctAll() throws Exception{
		List<RootUser> selctAll = rootUserDao.selectAll(0, 5);
		System.out.println(selctAll);
	}
	
	@Test
	public void testInsertByUserNameAndPassword() throws Exception{
		int insertByUserNameAndPassword = rootUserDao.insertByUserNameAndPassword("public", "123");
		System.out.println(insertByUserNameAndPassword);
	}
	
	/**
	 * 更新积分
	 * @throws Exception
	 */
	@Test
	public void updateScoreTest() throws Exception{
		rootUserDao.updateScore(1, 1);
	}
	
	/**
	 * 更新积分
	 * @throws Exception
	 */
	@Test
	public void updateScoreByNameTest() throws Exception{
		rootUserDao.updateScoreByName(3, "public");
	}
}
