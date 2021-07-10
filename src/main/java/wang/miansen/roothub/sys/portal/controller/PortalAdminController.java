package wang.miansen.roothub.sys.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wang.miansen.roothub.rbac.bo.SystemBO;
import wang.miansen.roothub.rbac.service.SystemService;

/**
 * Portal Admin Controller
 *
 * @author miansen.wang
 * @date 2021-06-20 21:38
 */
@Controller
@RequestMapping("/admin/sys/portal")
public class PortalAdminController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<SystemBO> systems = systemService.list();
        mv.addObject("systems", systems);
        mv.setViewName("/admin/index");
        return mv;
    }

    @RequestMapping(value = "/index3", method = RequestMethod.GET)
    public ModelAndView index3(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<SystemBO> systems = systemService.list();
        mv.addObject("systems", systems);
        mv.setViewName("/admin/index3");
        return mv;
    }
}
