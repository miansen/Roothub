package wang.miansen.roothub.modules.user.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wang.miansen.roothub.config.SiteConfig;
import wang.miansen.roothub.modules.reply.model.ReplyAndTopicByName;
import wang.miansen.roothub.modules.topic.model.Topic;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.integral.model.Top100;
import wang.miansen.roothub.modules.collect.service.CollectService;
import wang.miansen.roothub.modules.follow.service.FollowService;
import wang.miansen.roothub.modules.notice.service.NoticeService;
import wang.miansen.roothub.modules.reply.service.ReplyService;
import wang.miansen.roothub.modules.topic.service.TopicService;
import wang.miansen.roothub.modules.user.service.UserService;
import wang.miansen.roothub.modules.visit.service.VisitService;
import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.BaseController;
import wang.miansen.roothub.common.util.ApiAssert;

/**
 * 
 * @author sen
 * 2018年7月21日
 * 下午10:58:12
 * TODO
 */
@RestController
public class UserApiController extends BaseController{

	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private FollowService followService;
	@Autowired
	private VisitService visitService;
	@Autowired
	private SiteConfig citeConfig;
	
	/**
	 * 用户的收藏
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/collect",method = RequestMethod.GET)
	private Result<Page> collectList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		User user = userService.findByName(name);
		/*if(user == null) {
			return new Result<PageDataBody>(true, "用户不存在");
		}*/
		ApiAssert.notNull(user, "用户不存在");
		Page<Topic> page = collectDaoService.page(p, 20, user.getUserId());
		return new Result<Page>(true, page);
	}
	
	/**
	 * 用户的主题
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/topic",method = RequestMethod.GET)
	private Result<Page> topicList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		Page<Topic> page = topicService.pageByAuthor(p, 20, name);
		return new Result<Page>(true, page);
	}
	
	/**
	 * 用户的评论
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/reply",method = RequestMethod.GET)
	private Result<Page> replyList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		Page<ReplyAndTopicByName> page = replyService.findAllByNameAndTopic(name, p, 20);
		return new Result<Page>(true, page);
	}
	
	/**
	 * 用户的关注
	 * @param uid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/follow/topic",method = RequestMethod.GET)
	private Result<Page> followList(@RequestParam(value = "uid",defaultValue = "-1") Integer uid,@RequestParam(value = "p",defaultValue = "1") Integer p){
		Page<Topic> page = followService.pageTopic(p, 20, uid);
		return new Result<Page>(true, page);
	}
	
	/**
	 * 用户的粉丝
	 * @param fid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/fans",method = RequestMethod.GET)
	private Result<Page> fansList(@RequestParam(value = "fid",defaultValue = "-1") Integer fid,@RequestParam(value = "p",defaultValue = "1") Integer p){
		Page<User> page = followService.followMe(p, 20, fid);
		return new Result<Page>(true, page);
	}
	
	/**
	 * 用户的提问
	 * @param name
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/topic/qna",method = RequestMethod.GET)
	private  Result<Page> qnaTopicList(@RequestParam(value = "name",defaultValue = "-1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		Page<Topic> page = topicService.pageAllByPtabAndAuthor(p, 20, "qna", name);
		return new Result<Page>(true, page);
	}
	
	/**
	 * 用户的访客
	 * @param vid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/visit",method = RequestMethod.GET)
	private Result<Page> visitList(@RequestParam(value = "vid",defaultValue = "-1") Integer vid,
										   @RequestParam(value = "p",defaultValue = "1") Integer p){
		Page<User> page = visitService.page(vid, p, 10);
		return new Result<Page>(true, page);
	}
	
	@RequestMapping(value = "/api/test",method = RequestMethod.GET)
	private Result<Integer> test(Integer p){
		return new Result<Integer>(true, p);
	}
	
	/**
	 * 用户发表话题或者回复的数量
	 * @param type
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/api/numberOfCountTopicsOrReply",method = RequestMethod.GET)
	private Map<String,Object> numberOfCountTopicsOrReply(String type,String userName){
		Map<String, Object> map = new HashMap<String, Object>();
		if(userName == null) {
			map.put("success", false);
			map.put("msg", "用户名不能为空");
			return map;
		}else if(type.equals("reply")){
			int countByName = replyService.countByName(userName);
			map.put("success", true);
			map.put("msg", countByName);
			return map;
		}else {
			int countByUserName = topicService.countByUserName(userName);
			map.put("success", true);
			map.put("msg", countByUserName);
			return map;
		}
	}
	
	/**
	 * Top100积分榜
	 * @return
	 */
	@RequestMapping(value = "/api/user/top100",method = RequestMethod.GET)
	private Result<List> top100(@RequestParam(value = "limit",defaultValue = "100")Integer limit){
		List<Top100> scores = userService.scores(limit);
		return new Result<List>(true, scores);
	}
	
	/**
	 * 用户的登录信息
	 * @return
	 */
	@RequestMapping(value = "/api/user/logininfo",method = RequestMethod.GET)
	private Result<Map> LoginInfo(HttpServletRequest request){
		User user = getUser(request);
		HashMap<String,Object> map = new HashMap<>();
		if(user == null) {
			map.put("intro", citeConfig.getIntro());
			return new Result<>(false, map);
		}else {
			map.put("userName", user.getUserName());
			map.put("avatar", user.getAvatar());
			map.put("signature", user.getSignature());
			int countTopic = topicService.countByUserName(user.getUserName());
			int countCollect = collectDaoService.count(user.getUserId());
			int countFollow = followService.countByUid(user.getUserId());
			int countNotReadNotice = noticeService.countNotReadNotice(user.getUserName());
			int countScore = userService.countScore(user.getUserId());
			map.put("countTopic", countTopic);
			map.put("countCollect", countCollect);
			map.put("countFollow", countFollow);
			map.put("countNotReadNotice", countNotReadNotice);
			map.put("countScore", countScore);
			return new Result<Map>(true, map);
		}
	}
	
	/**
	 * 作者的其他话题
	 * @param userName
	 * @param topicId
	 * @return
	 */
	@RequestMapping(value = "/api/user/other/topic",method = RequestMethod.GET)
	private Result<List> otherTopic(String userName,Integer topicId){
		List<Topic> list = topicService.findOther(userName, topicId);
		return new Result<List>(true, list);
	}
}
