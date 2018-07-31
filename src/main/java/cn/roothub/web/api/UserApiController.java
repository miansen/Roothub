package cn.roothub.web.api;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.Collect;
import cn.roothub.entity.ReplyAndTopicByName;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.service.CollectService;
import cn.roothub.service.FollowService;
import cn.roothub.service.RootNoticeService;
import cn.roothub.service.RootReplyService;
import cn.roothub.service.RootTopicService;
import cn.roothub.service.RootUserService;

/**
 * 
 * @author sen
 * 2018年7月21日
 * 下午10:58:12
 * TODO
 */
@RestController
public class UserApiController {

	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private RootTopicService rootTopicService;
	@Autowired
	private RootNoticeService rootNoticeService;
	@Autowired
	private RootUserService rootUserService;
	@Autowired
	private RootReplyService rootReplyService;
	@Autowired
	private FollowService followService;
	
	/**
	 * 用户的收藏
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/collect",method = RequestMethod.GET)
	private Result<PageDataBody> collectList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		RootUser user = rootUserService.findByName(name);
		if(user == null) {
			return new Result<PageDataBody>(true, "用户不存在");
		}
		PageDataBody<RootTopic> page = collectDaoService.page(p, 20, user.getUserId());
		return new Result<PageDataBody>(true, page);
	}
	
	/**
	 * 用户的主题
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/topic",method = RequestMethod.GET)
	private Result<PageDataBody> topicList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<RootTopic> page = rootTopicService.pageByAuthor(p, 1, name);
		return new Result<PageDataBody>(true, page);
	}
	
	/**
	 * 用户的评论
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/reply",method = RequestMethod.GET)
	private Result<PageDataBody> replyList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<ReplyAndTopicByName> page = rootReplyService.findAllByNameAndTopic(name, p, 20);
		return new Result<PageDataBody>(true, page);
	}
	
	/**
	 * 关注的人的主题
	 * @param uid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/follow/topic",method = RequestMethod.GET)
	private Result<PageDataBody> followList(@RequestParam(value = "uid",defaultValue = "-1") Integer uid,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<RootTopic> page = followService.pageTopic(p, 20, uid);
		return new Result<PageDataBody>(true, page);
	}
	
	/**
	 * 用户的粉丝
	 * @param fid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/fans",method = RequestMethod.GET)
	private Result<PageDataBody> fansList(@RequestParam(value = "fid",defaultValue = "-1") Integer fid,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<RootUser> page = followService.followMe(p, 20, fid);
		return new Result<PageDataBody>(true, page);
	}
	
	/**
	 * 用户的提问
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/topic/qna",method = RequestMethod.GET)
	private  Result<PageDataBody> qnaTopicList(@RequestParam(value = "name",defaultValue = "-1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<RootTopic> page = rootTopicService.pageAllByPtabAndAuthor(p, 20, "qna", name);
		return new Result<PageDataBody>(true, page);
	}
	
	@RequestMapping(value = "/api/test",method = RequestMethod.GET)
	private Result<Integer> test(Integer p){
		return new Result<Integer>(true, p);
	}
	
	
}
