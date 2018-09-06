package cn.roothub.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.roothub.dao.RootUserDao;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.RootUserExecution;
import cn.roothub.entity.RootUser;
import cn.roothub.enums.InsertRootUserEnum;
import cn.roothub.enums.UpdateRootUserEnum;
import cn.roothub.exception.OperationFailedException;
import cn.roothub.exception.OperationRepeaException;
import cn.roothub.exception.OperationSystemException;
import cn.roothub.service.RootUserService;
import cn.roothub.util.JsonUtil;
import cn.roothub.util.StringUtil;

@Service
public class RootUserServiceImpl implements RootUserService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RootUserDao rootUserDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 根据ID查找用户
	 */
	@Override
	public RootUser findById(Integer userId) {	
		return rootUserDao.selectByUserId(userId);
	}

	/**
	 * 根据昵称查找用户
	 */
	@Override
	public RootUser findByName(String userName) {
		return rootUserDao.selectByUserName(userName);
	}

	/**
	 * 根据email查找用户
	 */
	@Override
	public RootUser findByEmail(String email) {
		return rootUserDao.selectByEmail(email);
	}

	/**
	 * 根据昵称和密码查找用户
	 */
	@Override
	public RootUser findByUserNameAndPassword(String userName, String password) {
		return rootUserDao.selectByUserNameAndPassword(userName, password);
	}

	/**
	 * 根据邮箱和密码查找用户
	 */
	@Override
	public RootUser findByEmailAndPassword(String email, String password) {
		return rootUserDao.selectByEmailAndPassword(email, password);
	}

	/**
	 * 积分榜用户
	 */
	@Override
	public List<RootUser> scores(Integer limit) {
		return rootUserDao.selectByScore(limit);
	}

	/**
	 * 分页查询所有用户，倒叙
	 */
	@Override
	public PageDataBody<RootUser> page(Integer pageNumber, Integer pageSize) {
		List<RootUser> list = rootUserDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int totalRow = rootUserDao.countUserAll();
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 更新用户
	 */
	@Transactional
	@Override
	public RootUserExecution updateUser(RootUser user) {
		try {
			if(user == null) {
				throw new OperationRepeaException("用户不存在");
			}else {
				int updateUser = rootUserDao.updateUser(user);
				RootUser rootUser = rootUserDao.selectByUserName(user.getUserName());
				if(updateUser <= 0) {
					throw new OperationFailedException("修改失败");
				}else {
					//更新redis
					ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
					opsForValue.set(rootUser.getThirdAccessToken(), JsonUtil.objectToJson(rootUser));
					return new RootUserExecution(user.getUserName(),UpdateRootUserEnum.SUCCESS,rootUser);
				}
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		} catch (OperationFailedException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new OperationSystemException("update RootUser erroe "+e.getMessage());
		}
	}

	/**
	 * 根据ID删除用户
	 */
	@Override
	public void deleteUserById(Integer userId) {
		rootUserDao.deleteUserByUserId(userId);	
	}

	/**
	 * 根据昵称删除用户
	 */
	@Override
	public void deleteUserByName(String userName) {
		rootUserDao.deleteUserByUserName(userName);
	}

	/**
	 * 注册用户
	 */
	@Transactional
	@Override
	public RootUserExecution save(RootUser user) {
		try {
			if(user.getUserName() == null && user.getUserName().equals("")) {
				throw new OperationRepeaException("用户名不能为空");
			}
			if(user.getPassword() == null && user.getPassword().equals("")) {
				throw new OperationRepeaException("密码不能为空");
			}
			RootUser userName = rootUserDao.selectByUserName(user.getUserName());
			if(userName != null) {
				throw new OperationRepeaException("昵称已存在");
			}else {
				int insertUser = rootUserDao.insertUser(user);
				RootUser rootUser = rootUserDao.selectByUserName(user.getUserName());
				if(insertUser <= 0) {
					throw new OperationFailedException("注册失败");
				}else {
					ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
					opsForValue.set(rootUser.getThirdAccessToken(), JsonUtil.objectToJson(rootUser));
					return new RootUserExecution(user.getUserName(),InsertRootUserEnum.SUCCESS,rootUser);
				}
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		} catch (OperationFailedException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new OperationSystemException("insert into RootUser error "+e.getMessage());
		}
	}

	public RootUserExecution createUser(String username,String password,String email) {
		RootUser user = new RootUser();
		user.setUserName(username);
		user.setPassword(password);
		user.setScore(0);
		user.setEmail(email);
		user.setUrl("https://www.roothub.cn/user/"+username);
		//user.setThirdId("GitHub");
		user.setReceiveMsg(false);
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setIsBlock(false);
		user.setThirdAccessToken(StringUtil.getUUID());
		user.setStatusCd("1000");
		user.setUserType("2");
		user.setAvatar("69290780aaafb00aa37ff2a61342dded.png");
		user.setSignature("这家伙很懒，什么都没留下");
		return save(user);
	}
	/**
	 * 统计所有注册会员的数量
	 */
	@Override
	public int countUserAll() {
		return rootUserDao.countUserAll();
	}

}
