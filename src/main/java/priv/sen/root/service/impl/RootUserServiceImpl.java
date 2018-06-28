package priv.sen.root.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.sen.root.dao.RootUserDao;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.dto.RootUserExecution;
import priv.sen.root.entity.RootUser;
import priv.sen.root.enums.InsertRootUserEnum;
import priv.sen.root.enums.UpdateRootUserEnum;
import priv.sen.root.exception.OperationFailedException;
import priv.sen.root.exception.OperationRepeaException;
import priv.sen.root.exception.OperationSystemException;
import priv.sen.root.service.RootUserService;

@Service
public class RootUserServiceImpl implements RootUserService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RootUserDao rootUserDao;
	
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
					return new RootUserExecution(user.getUserName(),UpdateRootUserEnum.SUCCESS,rootUser);
				}
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		}catch (OperationFailedException e2) {
			throw e2;
		}catch (Exception e) {
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

	/**
	 * 统计所有注册会员的数量
	 */
	@Override
	public int countUserAll() {
		return rootUserDao.countUserAll();
	}

}
