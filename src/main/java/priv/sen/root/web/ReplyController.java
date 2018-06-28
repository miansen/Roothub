package priv.sen.root.web;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.sen.root.dto.Result;
import priv.sen.root.dto.RootReplyExecution;
import priv.sen.root.entity.RootNotice;
import priv.sen.root.entity.RootReply;
import priv.sen.root.entity.RootTopic;
import priv.sen.root.entity.RootUser;
import priv.sen.root.service.RootNoticeService;
import priv.sen.root.service.RootReplyService;
import priv.sen.root.service.RootTopicService;
import priv.sen.root.service.RootUserService;
import priv.sen.root.util.Base64Util;
import priv.sen.root.util.CookieAndSessionUtil;

@Controller
public class ReplyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootReplyService rootReplyService;
	@Autowired
	private RootNoticeService rootNoticeService;
	@Autowired
	private RootTopicService rootTopicService;
	@Autowired
	private RootUserService rootUserService;
	
	@RequestMapping(value = "/reply/save", method = RequestMethod.POST)
	@ResponseBody
	private Result<RootReplyExecution> save(HttpServletRequest request, 
			@RequestParam("topicId") Integer topicId,
			@RequestParam("content") String content){
		String cookie = CookieAndSessionUtil.getCookie(request, "user");
		RootUser user = rootUserService.findByName(Base64Util.decode(cookie));//当前用户
			RootReply reply = new RootReply();
			reply.setTopicId(topicId);//话题id
			reply.setReplyContent(content);//回复内容
			reply.setCreateDate(new Date());//回复时间
			reply.setUpdateDate(new Date());//更新时间
			reply.setReplyAuthorId(user.getUserId());//当前回复用户ID
			reply.setReplyAuthorName(user.getUserName());//当前回复用户昵称
			reply.setIsDelete(false);//是否删除 0:默认 1:删除
			reply.setIsRead(false);//是否已读 0:默认 1:未读
			reply.setReplyShow(false);//是否可见 0:默认 1:不可见
			reply.setReplyGoodCount(0);//点赞
			reply.setReplyBadCount(0);//踩数
			reply.setReplyType("");
			reply.setReplyReadCount(0);
			reply.setStatusCd("1000");//回复状态 1000:有效 1100:无效 1200:未生效
			RootReplyExecution save = rootReplyService.save(reply);//添加回复
			RootTopic findByTopicId = rootTopicService.findByTopicId(topicId);
			findByTopicId.setReplyCount(findByTopicId.getReplyCount()+1);//回复量+1
			findByTopicId.setLastReplyAuthor(user.getUserName());//最后回复人昵称
			findByTopicId.setLastReplyTime(new Date());//最后回复时间
			rootTopicService.updateTopic(findByTopicId);//更新话题
			//回复者与话题作者不是同一个人的时候发送通知
			if(!user.getUserName().equals(findByTopicId.getAuthor())) {
				RootNotice notice = new RootNotice();
				//notice.setNoticeTitle(noticeTitle);//通知标题
				notice.setIsRead(false);//是否已读：0:默认 1:已读
				notice.setNoticeAuthorId(user.getUserId());//发起通知用户ID
				notice.setNoticeAuthorName(user.getUserName());//发起通知用户昵称
				notice.setTargetAuthorName(findByTopicId.getAuthor());//要通知用户的昵称
				notice.setCreateDate(new Date());//创建时间
				notice.setUpdateDate(new Date());//更新时间
				notice.setNoticeAction("");//通知动作
				notice.setTopicId(findByTopicId.getTopicId());//话题ID
				notice.setNoticeContent(content);//通知内容
				notice.setStatusCd("1000");//通知状态 1000:有效 1100:无效 1200:未生效
				rootNoticeService.save(notice);//添加通知
			}
			return new Result<RootReplyExecution>(true, save);
	}
}
