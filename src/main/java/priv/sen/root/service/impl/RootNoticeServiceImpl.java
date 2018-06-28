package priv.sen.root.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.sen.root.dao.RootNoticeDao;
import priv.sen.root.dto.PageDataBody;
import priv.sen.root.entity.RootNotice;
import priv.sen.root.service.RootNoticeService;

@Service
public class RootNoticeServiceImpl implements RootNoticeService{

	@Autowired
	private RootNoticeDao rootNoticeDao;

	/**
	 * 根据昵称查询未读通知的数量
	 */
	@Override
	public int countNotReadNotice(String author) {
		return rootNoticeDao.countNotReadByAuthor(author);
	}

	/**
	 * 根据昵称查询通知列表
	 */
	@Override
	public List<RootNotice> selectAll(String author) {
		return rootNoticeDao.selectAll(author);
	}

	/**
	 * 根据昵称分页查询通知列表
	 */
	@Override
	public PageDataBody<RootNotice> pageByAuthor(Integer pageNumber, Integer pageSize, String author) {
		int totalRow = rootNoticeDao.countByAuthor(author);
		List<RootNotice> list = rootNoticeDao.selectAll(author, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	/**
	 * 根据昵称将通知都置为已读
	 */
	@Override
	public void updateIsRead(String author) {
		rootNoticeDao.updateByAuthorName(author);
	}

	/**
	 * 添加通知
	 */
	@Override
	public void save(RootNotice notice) {
		rootNoticeDao.insert(notice);
	}

	@Override
	public int countByAuthor(String author) {
		return rootNoticeDao.countByAuthor(author);
	}
	
}
