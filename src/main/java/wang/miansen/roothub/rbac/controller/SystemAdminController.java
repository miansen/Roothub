package wang.miansen.roothub.rbac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.miansen.roothub.rbac.bo.SystemBO;
import wang.miansen.roothub.rbac.service.SystemService;

/**
 * System Admin Controller
 *
 * @author miansen.wang
 * @date 2021-06-20 21:38
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        List<SystemBO> systems = systemService.list();
        request.setAttribute("systems", systems);
        return "/admin/console";
    }
}
