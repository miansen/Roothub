package cn.roothub.web.front;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.base.BaseEntity;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.entity.Follow;
import cn.roothub.entity.Topic;
import cn.roothub.entity.User;
import cn.roothub.service.CollectService;
import cn.roothub.service.FollowService;
import cn.roothub.service.NoticeService;
import cn.roothub.service.TopicService;

/**
 * 
 * @author sen
 * 2018年7月3日
 * 上午10:15:55
 * TODO
 */
@Controller
public class FollowController extends BaseController{

	@Autowired
	private FollowService followService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private NoticeService rootNoticeService;
	
	/**
	 * 是否已关注
	 * @param fid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/follow/isFollow",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> isFollow(Integer fid,HttpServletRequest request){
		User user = getUser(request);
		if(user == null) return new Result<>(false, "未关注");
		if(user.getUserId() == fid) {
			return new Result<>(false, "同一用户");
		}
		int follow = followService.isFollow(user.getUserId(), fid);
		if(follow == 0) {
			return new Result<>(false, "未关注");
		}
		return new Result<>(true, follow);
	}
	
	/**
	 * 关注
	 * @param fid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/follow/save",method = RequestMethod.POST)
	@ResponseBody
	private Result<Integer> save(Integer fid,HttpServletRequest request){
		Follow follow = new Follow();
		follow.setUid(getUser(request).getUserId());
		follow.setFid(fid);
		follow.setCreateDate(new Date());
		int insert = followService.insert(follow);
		if(insert == 1) {
			String info = "关注成功";
			return new Result<Integer>(true,info);
		}
		return new Result<>(false,"关注失败");
	};
	
	/**
	 * 取消关注
	 * @param fid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/follow/delete",method = RequestMethod.POST)
	@ResponseBody
	private Result<Integer> delete(Integer fid,HttpServletRequest request){
		int delete = followService.delete(getUser(request).getUserId(), fid);
		if(delete == 1) {
			String info = "取消关注成功";
			return new Result<Integer>(true,info);
		}
		return new Result<>(false,"取消关注失败");
	}
	
	/**
	 * 我关注的数量
	 * @param fid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/follow/count/for",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> countByUid(Integer uid,HttpServletRequest request){
		int countByUid = followService.countByUid(uid);
		return new Result<Integer>(true,countByUid);
	}
	
	/**
	 * 关注我的数量
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/follow/count/to",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> countByFid(Integer fid,HttpServletRequest request){
		int countByFid = followService.countByFid(fid);
		return new Result<Integer>(true,countByFid);
	}
	
	/**
	 * 特别关注
	 * @param request
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/follow/topics",method = RequestMethod.GET)
	private String followTopics(HttpServletRequest request,@RequestParam(value = "p",defaultValue = "1")Integer p) {
		User user = getUser(request);
		if(user == null) return "error-page/404.jsp";
		int countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
		int countTopicByUserName = rootTopicService.countByUserName(user.getUserName());//用户发布的主题的数量
		int notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());//未读通知的数量
		PageDataBody<Topic> pageTopic = followService.pageTopic(p, 20, user.getUserId());
		BaseEntity baseEntity = new BaseEntity();
		request.setAttribute("baseEntity", baseEntity);
		request.setAttribute("countCollect", countCollect);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("notReadNotice", notReadNotice);
		request.setAttribute("user", user);
		request.setAttribute("page", pageTopic);
		return "user/follow_topics";
	}
}
