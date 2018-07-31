package priv.sen.root.serviceImpl.test;

import java.util.Date;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.RootReplyExecution;
import cn.roothub.entity.ReplyAndTopicByName;
import cn.roothub.entity.RootReply;
import cn.roothub.service.RootReplyService;
import priv.sen.root.dao.test.BaseTest;

/**
 * 测试评论
 * @author sen
 * 2018年5月25日
 * 下午11:21:55
 * TODO
 */
public class RootReplyServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootReplyService rootReplyService;
	
	@Test
	public void savetest() throws Exception{
		RootReply reply = new RootReply();
		reply.setTopicId(49);//话题id
		reply.setReplyContent("测试回复内容04");//回复内容
		reply.setCreateDate(new Date());//回复时间
		reply.setUpdateDate(new Date());//更新时间
		reply.setReplyAuthorName("private");//当前回复用户昵称
		reply.setIsDelete(false);//是否删除 0:默认 1:删除
		reply.setIsRead(false);//是否已读 0:默认 1:未读
		reply.setReplyShow(false);//是否可见 0:默认 1:不可见
		reply.setReplyGoodCount(0);//点赞
		reply.setReplyBadCount(0);//踩数
		reply.setReplyType("");
		reply.setReplyReadCount(0);
		reply.setStatusCd("1000");//回复状态 1000:有效 1100:无效 1200:未生效
		logger.debug(reply.toString());
		RootReplyExecution save = rootReplyService.save(reply);//添加回复
		logger.debug(save.toString());
	}
	/**
	 * 测试根据评论人昵称关联话题表分页查询评论
	 * @throws Exception
	 */
	@Test
	public void findAllByNameAndTopic() throws Exception {
		PageDataBody<ReplyAndTopicByName> page = rootReplyService.findAllByNameAndTopic("void", 1, 20);
		logger.debug(page.toString());
	}
}
