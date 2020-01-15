package wang.miansen.roothub.common.controller;

import wang.miansen.roothub.common.util.CookieAndSessionUtil;
import wang.miansen.roothub.modules.user.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author miansen.wang
 * @date 2020-01-15 9:48
 */
public class SessionController {

    /**
     * 获取当前登录用户的信息
     *
     * @param request
     * @return
     */
    public User getUser(HttpServletRequest request) {
        return CookieAndSessionUtil.getSession(request, "user");
    }
}
