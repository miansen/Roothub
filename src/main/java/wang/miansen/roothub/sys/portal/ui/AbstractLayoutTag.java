package wang.miansen.roothub.sys.portal.ui;

import wang.miansen.roothub.common.ui.AbstractBaseTag;

/**
 * 抽象的父类布局标签。
 * <p>
 *     这个抽象父类定义了头部、脚部、导航栏、JS脚本等通用的标签，子类可按需获取。
 * </p>
 *
 * @author miansen.wang
 * @date 2021-07-10 18:49
 */
public abstract class AbstractLayoutTag extends AbstractBaseTag {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3231876483978346445L;

    /**
     * 页面的标题
     */
    protected String title = "Roothub";

    /**
     * 获取头部
     *
     * @param contextPath 上下文路径
     * @param title 标题
     * @return 头部
     */
    protected String getHead(String contextPath, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("<head>").append("\t\n");
        sb.append("<meta charset=\"utf-8\">").append("\t\n");
        sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">").append("\t\n");
        sb.append("<title>").append(title).append("</title>").append("\t\n");
        // Tell the browser to be responsive to screen width
        sb.append("<meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">").append("\t\n");
        // Bootstrap 3.4.1
        sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath).append("/resources/libs/bootstrap-v3.4.1/dist/css/bootstrap.min.css\">").append("\t\n");
        // Font Awesome 4.7.0
        sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath).append("/resources/libs/font-awesome-v4.7.0/css/font-awesome.min.css\">").append("\t\n");
        // Ionicons 2.0.0
        sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath).append("/resources/libs/Ionicons-v2.0.0/css/ionicons.min.css\">").append("\t\n");
        // AdminLTE 2.4.18
        sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath).append("/resources/libs/AdminLTE-v2.4.18/dist/css/AdminLTE.min.css\">").append("\t\n");
        // AdminLTE 2.4.18 skins
        sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath).append("/resources/libs/AdminLTE-v2.4.18/dist/css/skins/_all-skins.min.css\">").append("\t\n");
        // Google Font
        sb.append("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic\">").append("\t\n");
        sb.append("</head>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取页脚
     *
     * @return 页脚
     */
    protected String getFooter() {
        StringBuilder sb = new StringBuilder();
        sb.append("<footer class=\"main-footer\">").append("\t\n");
        sb.append("<div class=\"container\">").append("\t\n");
        sb.append("<div class=\"pull-right hidden-xs\">").append("\t\n");
        sb.append("<b>Version</b> 2.4.13").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("<strong>Copyright &copy; 2014-2019 <a href=\"https://adminlte.io\">AdminLTE</a>.</strong> All rights reserved.").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</footer>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取脚本
     *
     * @param contextPath 上下文路径
     * @return 脚本
     */
    protected String getScript(String contextPath) {
        StringBuilder sb = new StringBuilder();
        // jQuery 3.4.1
        sb.append("<script src=\"").append(contextPath).append("/resources/libs/jquery-v3.4.1/dist/jquery.min.js\"></script>").append("\t\n");
        // Bootstrap 3.4.1
        sb.append("<script src=\"").append(contextPath).append("/resources/libs/bootstrap-v3.4.1/dist/js/bootstrap.min.js\"></script>").append("\t\n");
        // SlimScroll 1.3.8
        sb.append("<script src=\"").append(contextPath).append("/resources/libs/SlimScroll-v1.3.8/jquery.slimscroll.min.js\"></script>").append("\t\n");
        // FastClick
        sb.append("<script src=\"").append(contextPath).append("/resources/libs/FastClick/fastclick.js\"></script>").append("\t\n");
        // AdminLTE 2.4.18
        sb.append("<script src=\"").append(contextPath).append("/resources/libs/AdminLTE-v2.4.18/dist/js/adminlte.min.js\"></script>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取左侧导航栏
     *
     * @return 左侧导航栏
     */
    protected String getNavbarLeftMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"collapse navbar-collapse pull-left\" id=\"navbar-collapse\">").append("\t\n");
        sb.append("<ul class=\"nav navbar-nav\">").append("\t\n");
        sb.append("<li class=\"active\"><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Link <span class=\"sr-only\">(current)</span></a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Link</a></li>").append("\t\n");
        sb.append("<li class=\"dropdown\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown <span class=\"caret\"></span></a>").append("\t\n");
        sb.append("<ul class=\"dropdown-menu\" role=\"menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Action</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Another action</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Something else here</a></li>").append("\t\n");
        sb.append("<li class=\"divider\"></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Separated link</a></li>").append("\t\n");
        sb.append("<li class=\"divider\"></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">One more separated link</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("<form class=\"navbar-form navbar-left\" role=\"search\">").append("\t\n");
        sb.append("<div class=\"form-group\">").append("\t\n");
        sb.append("<input type=\"text\" class=\"form-control\" id=\"navbar-search-input\" placeholder=\"Search\">").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</form>").append("\t\n");
        sb.append("</div>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取右侧导航栏
     *
     * @param contextPath 上下文路径
     * @return 右侧导航栏
     */
    protected String getNavbarRightMenu(String contextPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"navbar-custom-menu\">").append("\t\n");
        sb.append("<ul class=\"nav navbar-nav\">").append("\t\n");
        sb.append(getMessagesMenu(contextPath)).append("\t\n");
        sb.append(getNotificationsMenu()).append("\t\n");
        sb.append(getTasksMenu()).append("\t\n");
        sb.append(getUserAccountMenu(contextPath)).append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</div>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取消息菜单
     *
     * @param contextPath 上下文路径
     * @return 消息菜单
     */
    protected String getMessagesMenu(String contextPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"dropdown messages-menu\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" aria-expanded=\"false\">").append("\t\n");
        sb.append("<i class=\"fa fa-envelope-o\"></i>").append("\t\n");
        sb.append("<span class=\"label label-success\">4</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"dropdown-menu\">").append("\t\n");
        sb.append("<li class=\"header\">You have 4 messages</li>").append("\t\n");
        sb.append("<li>").append("\t\n");
        sb.append("<ul class=\"menu\">").append("\t\n");
        sb.append("<li>").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">").append("\t\n");
        sb.append("<div class=\"pull-left\">").append("\t\n");
        sb.append("<img src=\"").append(contextPath).append("/resources/images/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\">").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("<h4>").append("\t\n");
        sb.append("Support Team").append("\t\n");
        sb.append("<small><i class=\"fa fa-clock-o\"></i> 5 mins</small>").append("\t\n");
        sb.append("</h4>").append("\t\n");
        sb.append("<p>Why not buy a new awesome theme?</p>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("<li class=\"footer\"><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">See All Messages</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取通知菜单
     *
     * @return 通知菜单
     */
    protected String getNotificationsMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"dropdown notifications-menu\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">").append("\t\n");
        sb.append("<i class=\"fa fa-bell-o\"></i>").append("\t\n");
        sb.append("<span class=\"label label-warning\">10</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"dropdown-menu\">").append("\t\n");
        sb.append("<li class=\"header\">You have 10 notifications</li>").append("\t\n");
        sb.append("<li>").append("\t\n");
        sb.append("<ul class=\"menu\">").append("\t\n");
        sb.append("<li>").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-users text-aqua\"></i>").append("\t\n");
        sb.append("5 new members joined today").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("<li class=\"footer\"><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">View all</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取任务菜单
     *
     * @return 任务菜单
     */
    protected String getTasksMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"dropdown tasks-menu\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">").append("\t\n");
        sb.append("<i class=\"fa fa-flag-o\"></i>").append("\t\n");
        sb.append("<span class=\"label label-danger\">9</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"dropdown-menu\">").append("\t\n");
        sb.append("<li class=\"header\">You have 9 tasks</li>").append("\t\n");
        sb.append("<li>").append("\t\n");
        sb.append("<ul class=\"menu\">").append("\t\n");
        sb.append("<li>").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">").append("\t\n");
        sb.append("<h3>").append("\t\n");
        sb.append("Design some buttons").append("\t\n");
        sb.append("<small class=\"pull-right\">20%</small>").append("\t\n");
        sb.append("</h3>").append("\t\n");
        sb.append("<div class=\"progress xs\">").append("\t\n");
        sb.append("<div class=\"progress-bar progress-bar-aqua\" style=\"width: 20%\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\">").append("\t\n");
        sb.append("<span class=\"sr-only\">20% Complete</span>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("<li class=\"footer\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">View all tasks</a>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取用户帐号菜单
     *
     * @param contextPath 上下文路径
     * @return 用户帐号菜单
     */
    protected String getUserAccountMenu(String contextPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("<li class=\"dropdown user user-menu\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">").append("\t\n");
        sb.append("<img src=\"").append(contextPath).append("/resources/images/user2-160x160.jpg\" class=\"user-image\" alt=\"User Image\">").append("\t\n");
        sb.append("<span class=\"hidden-xs\">Alexander Pierce</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"dropdown-menu\">").append("\t\n");
        sb.append("<li class=\"user-header\">").append("\t\n");
        sb.append("<img src=\"").append(contextPath).append("/resources/images/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\">").append("\t\n");
        sb.append("<p>").append("\t\n");
        sb.append("Alexander Pierce - Web Developer").append("\t\n");
        sb.append("<small>Member since Nov. 2012</small>").append("\t\n");
        sb.append("</p>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("<li class=\"user-body\">").append("\t\n");
        sb.append("<div class=\"row\">").append("\t\n");
        sb.append("<div class=\"col-xs-4 text-center\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Followers</a>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("<div class=\"col-xs-4 text-center\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\">Sales</a>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("<div class=\"col-xs-4 text-center\">").append("\t\n");
        sb.append("<a href=\"").append(contextPath).append("/admin/sys/portal/index\" target=\"_blank\">管理后台</a>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("<li class=\"user-footer\">").append("\t\n");
        sb.append("<div class=\"pull-left\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#\" class=\"btn btn-default btn-flat\">Profile</a>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("<div class=\"pull-right\">").append("\t\n");
        sb.append("<a href=\"").append(contextPath).append("/logout\" class=\"btn btn-default btn-flat\">登出</a>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
