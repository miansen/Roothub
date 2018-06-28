package priv.sen.root.service;

import java.util.List;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.RootNotice;

public interface RootNoticeService {

	/**
	 * 查询未读通知数量
	 * @return
	 */
	int countNotReadNotice(String author);
	
	/**
	 * 统计所有通知的数量
	 * @param author
	 * @return
	 */
	int countByAuthor(String author);
	
	/**
	 * 根据昵称查询所有通知列表
	 * @param author
	 * @return
	 */
	List<RootNotice> selectAll(String author);
	
	/**
	 * 根据昵称分页查询所有通知列表
	 * @param pageNumber
	 * @param pageSize
	 * @param author
	 * @return
	 */
	PageDataBody<RootNotice> pageByAuthor(Integer pageNumber, Integer pageSize, String author);
	
	/**
	 * 将用户的通知都置为已读
	 * @param author
	 */
	void updateIsRead(String author);
	
	/**
	 * 添加通知
	 * @param notice
	 */
	void save(RootNotice notice);
}
