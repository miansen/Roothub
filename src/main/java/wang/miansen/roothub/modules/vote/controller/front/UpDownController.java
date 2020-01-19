package wang.miansen.roothub.modules.vote.controller.front;

import java.util.Date;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.AbstractBaseController;
import wang.miansen.roothub.common.controller.SessionController;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.dto.DMLExecution;
import wang.miansen.roothub.common.service.BaseService;
import wang.miansen.roothub.modules.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.miansen.roothub.modules.vote.model.UpDown;
import wang.miansen.roothub.modules.vote.service.UpDownService;

/**
 * 点赞或者点踩
 * @author sen
 * 2018年8月11日
 * 下午3:14:18
 * TODO
 */
@RestController
@RequestMapping(value = "/topic")
public class UpDownController extends SessionController {

	@Autowired
	private UpDownService upDownService;
	
	@RequestMapping(value = "/vote",method = RequestMethod.GET)
	private Result<DMLExecution> up(Integer tid, boolean vote, HttpServletRequest request){
		User user = getUser(request);
		if(user == null) return new Result<>(201, false,"未登录");
		UpDown upDown = new UpDown();
		upDown.setUid(user.getUserId());
		upDown.setTid(tid);
		upDown.setUpDown(vote);
		upDown.setCreateDate(new Date());
		upDown.setDelete(false);
		DMLExecution save = upDownService.save(upDown);
		return new Result<DMLExecution>(true,save);
	}
	
	@RequestMapping(value = "/vote/count",method = RequestMethod.GET)
	private Result<Integer> count(Integer tid,boolean vote){
		int countUpOrDown = upDownService.countUpOrDown(tid, vote?1:0);
		Integer integer = new Integer(countUpOrDown);
		return new Result<Integer>(true, integer);
	}


}
