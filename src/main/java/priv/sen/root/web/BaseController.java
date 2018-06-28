package priv.sen.root.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import priv.sen.root.dto.ResponseDataBody;
import priv.sen.root.entity.RootUser;
import priv.sen.root.service.RootUserService;
import priv.sen.root.util.WebUtil;

@Controller
public class BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootUserService rootUserService;
	
	
}
