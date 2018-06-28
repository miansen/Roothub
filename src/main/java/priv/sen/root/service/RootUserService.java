package priv.sen.root.service;

import java.util.List;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.dto.RootUserExecution;
import priv.sen.root.entity.RootUser;

/**
 * RootUserService
 * @author sen
 * 2018年5月6日
 * 下午9:10:40
 * TODO
 */
public interface RootUserService {

	/**
	 * 根据ID查找用户
	 * @param userId
	 * @return
	 */
	RootUser findById(Integer userId);
	
	/**
	 * 根据昵称查找用户
	 * @param userName
	 * @return
	 */
	RootUser findByName(String userName);
	
	/**
	 * 根据email查找用户
	 * @param email
	 * @return
	 */
	RootUser findByEmail(String email);
	
	/**
	 * 通过昵称和密码查找用户
	 * @param UserName
	 * @param password
	 * @return
	 */
	RootUser findByUserNameAndPassword(String userName,String password);
	
	/**
	 * 根据邮箱和密码查找用户
	 * @param email
	 * @param password
	 * @return
	 */
	RootUser findByEmailAndPassword(String email, String password);
	
	/**
	 * 积分榜用户
	 * @param limit
	 * @return
	 */
	List<RootUser> scores(Integer limit);
	
	/**
	 * 分页查询所有用户，倒序
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<RootUser> page(Integer pageNumber, Integer pageSize);
	
	/**
	 * 更新用户
	 * @param user
	 */
	RootUserExecution updateUser(RootUser user);
	
	/**
	 * 根据ID删除用户
	 * @param userId
	 */
	void deleteUserById(Integer userId);
	
	/**
	 * 根据昵称删除用户
	 * @param userName
	 */
	void deleteUserByName(String userName);
	
	/**
	 * 用户注册
	 * @param user
	 */
	RootUserExecution save(RootUser user);
	
	/**
	 * 统计所有注册会员的数量
	 * @return
	 */
	int countUserAll();
}
