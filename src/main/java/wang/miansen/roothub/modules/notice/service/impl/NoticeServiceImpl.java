package wang.miansen.roothub.modules.notice.service.impl;

import java.util.List;

import wang.miansen.roothub.core.base.PageDataBody;
import wang.miansen.roothub.modules.notice.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.miansen.roothub.modules.notice.dao.NoticeDao;
import wang.miansen.roothub.modules.notice.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao rootNoticeDao;

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
	public List<Notice> selectAll(String author) {
		return rootNoticeDao.selectAll(author);
	}

	/**
	 * 根据昵称分页查询通知列表
	 */
	@Override
	public PageDataBody<Notice> pageByAuthor(Integer pageNumber, Integer pageSize, String author) {
		int totalRow = rootNoticeDao.countByAuthor(author);
		List<Notice> list = rootNoticeDao.selectAll(author, (pageNumber - 1) * pageSize, pageSize);
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
	public void save(Notice notice) {
		rootNoticeDao.insert(notice);
	}

	@Override
	public int countByAuthor(String author) {
		return rootNoticeDao.countByAuthor(author);
	}

	/**
	 * 根据 要通知用户的昵称 删除
	 */
	@Override
	public void deleteByTargetAuthorName(String targetAuthorName) {
		rootNoticeDao.deleteByTargetAuthorName(targetAuthorName);
	}
	
}
