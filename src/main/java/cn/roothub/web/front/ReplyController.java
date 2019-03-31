package cn.roothub.web.front;

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

import cn.roothub.dto.Result;
import cn.roothub.dto.ReplyExecution;
import cn.roothub.entity.Notice;
import cn.roothub.entity.Reply;
import cn.roothub.entity.Topic;
import cn.roothub.entity.User;
import cn.roothub.service.NoticeService;
import cn.roothub.service.ReplyService;
import cn.roothub.service.TopicService;
import cn.roothub.service.UserService;
import cn.roothub.util.Base64Util;
import cn.roothub.util.CookieAndSessionUtil;

@Controller
public class ReplyController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReplyService rootReplyService;
	@Autowired
	private NoticeService rootNoticeService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private UserService rootUserService;
	
	@RequestMapping(value = "/reply/save", method = RequestMethod.POST)
	@ResponseBody
	private Result<ReplyExecution> save(HttpServletRequest request, 
			@RequestParam("topicId") Integer topicId,
			@RequestParam("content") String content){
			//String cookie = CookieAndSessionUtil.getCookie(request, "user");
			//RootUser user = rootUserService.findByName(Base64Util.decode(cookie));//当前用户
			User user = getUser(request);
			Reply reply = new Reply();
			reply.setTopicId(topicId);//话题id
			reply.setReplyContent(content);//回复内容
			reply.setCreateDate(new Date());//回复时间
			reply.setUpdateDate(new Date());//更新时间
			reply.setReplyAuthorId(user.getUserId());//当前回复用户ID
			reply.setReplyAuthorName(user.getUserName());//当前回复用户昵称
			reply.setIsDelete(false);//是否删除 0:默认 1:删除
			reply.setIsRead(false);//是否已读 0:默认 1:未读
			reply.setIsShow(false);//是否可见 0:默认 1:不可见
			reply.setReplyGoodCount(0);//点赞
			reply.setReplyBadCount(0);//踩数
			reply.setReplyType(null);
			reply.setReplyReadCount(0);
			reply.setStatusCd("1000");//回复状态 1000:有效 1100:无效 1200:未生效
			ReplyExecution save = rootReplyService.save(reply);//添加回复
			Topic findByTopicId = rootTopicService.findByTopicId(topicId);
			findByTopicId.setReplyCount(findByTopicId.getReplyCount()+1);//回复量+1
			findByTopicId.setLastReplyAuthor(user.getUserName());//最后回复人昵称
			findByTopicId.setLastReplyTime(new Date());//最后回复时间
			rootTopicService.updateTopic(findByTopicId);//更新话题
			//回复者与话题作者不是同一个人的时候发送通知
			if(!user.getUserName().equals(findByTopicId.getAuthor())) {
				Notice notice = new Notice();
				//notice.setNoticeTitle(noticeTitle);//通知标题
				notice.setIsRead(false);//是否已读：0:默认 1:已读
				notice.setNoticeAuthorId(user.getUserId());//发起通知用户ID
				notice.setNoticeAuthorName(user.getUserName());//发起通知用户昵称
				notice.setTargetAuthorName(findByTopicId.getAuthor());//要通知用户的昵称
				notice.setCreateDate(new Date());//创建时间
				notice.setUpdateDate(new Date());//更新时间
				notice.setNoticeAction("reply");//通知动作
				notice.setTopicId(findByTopicId.getTopicId());//话题ID
				notice.setNoticeContent(content);//通知内容
				notice.setStatusCd("1000");//通知状态 1000:有效 1100:无效 1200:未生效
				rootNoticeService.save(notice);//添加通知
			}
			return new Result<ReplyExecution>(true, save);
	}
}
