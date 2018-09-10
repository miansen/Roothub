package cn.roothub.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.roothub.config.SiteConfig;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.ReplyAndTopicByName;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.entity.Top100;
import cn.roothub.service.CollectService;
import cn.roothub.service.FollowService;
import cn.roothub.service.RootNoticeService;
import cn.roothub.service.RootReplyService;
import cn.roothub.service.RootTopicService;
import cn.roothub.service.RootUserService;
import cn.roothub.service.VisitService;
import cn.roothub.web.front.BaseController;

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
	private RootTopicService rootTopicService;
	@Autowired
	private RootNoticeService rootNoticeService;
	@Autowired
	private RootUserService rootUserService;
	@Autowired
	private RootReplyService rootReplyService;
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
		PageDataBody<RootTopic> page = rootTopicService.pageByAuthor(p, 20, name);
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
	
	/**
	 * 用户的访客
	 * @param vid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/api/user/visit",method = RequestMethod.GET)
	private Result<PageDataBody> visitList(@RequestParam(value = "vid",defaultValue = "-1") Integer vid,
										   @RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<RootUser> page = visitService.page(vid, p, 10);
		return new Result<PageDataBody>(true, page);
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
			int countByName = rootReplyService.countByName(userName);
			map.put("success", true);
			map.put("msg", countByName);
			return map;
		}else {
			int countByUserName = rootTopicService.countByUserName(userName);
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
		List<Top100> scores = rootUserService.scores(limit);
		return new Result<List>(true, scores);
	}
	
	/**
	 * 登录信息
	 * @return
	 */
	@RequestMapping(value = "/api/user/logininfo",method = RequestMethod.GET)
	private Result<Map> LoginInfo(HttpServletRequest request){
		RootUser user = getUser(request);
		HashMap<String,Object> map = new HashMap<>();
		if(user == null) {
			map.put("intro", citeConfig.getIntro());
			return new Result<>(false, map);
		}else {
			map.put("userName", user.getUserName());
			map.put("avatar", user.getAvatar());
			map.put("signature", user.getSignature());
			int countTopic = rootTopicService.countByUserName(user.getUserName());
			int countCollect = collectDaoService.count(user.getUserId());
			int countFollow = followService.countByUid(user.getUserId());
			int countNotReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());
			int countScore = rootUserService.countScore(user.getUserId());
			map.put("countTopic", countTopic);
			map.put("countCollect", countCollect);
			map.put("countFollow", countFollow);
			map.put("countNotReadNotice", countNotReadNotice);
			map.put("countScore", countScore);
			return new Result<Map>(true, map);
		}
	}
}
