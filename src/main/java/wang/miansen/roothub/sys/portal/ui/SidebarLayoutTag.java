package wang.miansen.roothub.sys.portal.ui;

import wang.miansen.roothub.common.ui.exception.BaseTagException;

/**
 * 侧边栏布局标签
 *
 * @author miansen.wang
 * @date 2021-07-10 18:47
 */
public class SidebarLayoutTag extends AbstractLayoutTag {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2962993635886309426L;

    @Override
    protected String getBodyContentString(String bodyContent) throws BaseTagException {
        String contextPath = getContextPath();

        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html>").append("\t\n");
        sb.append("<html style=\"height: auto; min-height: 100%;\">").append("\t\n");

        // head start
        sb.append(getHead(contextPath, getTitle())).append("\t\n");
        // head end

        // body start
        sb.append("<body class=\"skin-blue fixed sidebar-mini sidebar-mini-expand-feature\" style=\"height: auto; min-height: 100%;\">").append("\t\n");
        // wrapper start
        sb.append("<div class=\"wrapper\" style=\"height: auto; min-height: 100%;\">").append("\t\n");

        // header start
        sb.append(getHeader(contextPath)).append("\t\n");
        // header end

        // sidebar start
        sb.append(getSidebar(contextPath)).append("\t\n");
        // sidebar end

        // content wrapper start
        sb.append("<div class=\"content-wrapper\" style=\"min-height: 656px;\">").append("\t\n");
        sb.append(bodyContent).append("\t\n");
        sb.append("</div>").append("\t\n");
        // content wrapper end

        // footer start
        sb.append(getFooter()).append("\t\n");
        // footer end

        sb.append("</div>").append("\t\n");
        // wrapper end

        sb.append(getScript(contextPath)).append("\t\n");
        sb.append("</body>").append("\t\n");
        // body end
        sb.append("</html>");
        return sb.toString();
    }

    private String getHeader(String contextPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("<header class=\"main-header\">").append("\t\n");
        sb.append(getLogo()).append("\t\n");
        sb.append("<nav class=\"navbar navbar-static-top\">").append("\t\n");
        sb.append(getSidebarToggleButton()).append("\t\n");
        sb.append(getNavbarLeftMenu()).append("\t\n");
        sb.append(getNavbarRightMenu(contextPath)).append("\t\n");
        sb.append("</nav>").append("\t\n");
        sb.append("</header>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取侧边栏折叠按钮
     *
     * @return 侧边栏折叠按钮
     */
    private String getSidebarToggleButton() {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">").append("\t\n");
        sb.append("<span class=\"sr-only\">Toggle navigation</span>").append("\t\n");
        sb.append("<span class=\"icon-bar\"></span>").append("\t\n");
        sb.append("<span class=\"icon-bar\"></span>").append("\t\n");
        sb.append("<span class=\"icon-bar\"></span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取 LOGO
     *
     * @return LOGO
     */
    private String getLogo() {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/index2.html\" class=\"logo\">").append("\t\n");
        sb.append("<span class=\"logo-mini\"><b>A</b>LT</span>").append("\t\n");
        sb.append("<span class=\"logo-lg\"><b>Admin</b>LTE</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        return sb.toString();
    }
    /**
     * 获取侧边栏
     *
     * @param contextPath 上下文路径
     * @return 侧边栏
     */
    private String getSidebar(String contextPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("<aside class=\"main-sidebar\">").append("\t\n");
        // sb.append("<div class=\"slimScrollDiv\" style=\"position: relative; overflow: hidden; width: auto; height: 707px;\">").append("\t\n");
        sb.append("<section class=\"sidebar\" style=\"overflow: hidden; width: auto; height: 707px;\">").append("\t\n");
        sb.append("<div class=\"user-panel\">").append("\t\n");
        sb.append("<div class=\"pull-left image\">").append("\t\n");
        sb.append("<img src=\"").append(contextPath).append("/resources/images/user2-160x160.jpg\" class=\"img-circle\" alt=\"User Image\">").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("<div class=\"pull-left info\">").append("\t\n");
        sb.append("<p>Alexander Pierce</p>").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\"><i class=\"fa fa-circle text-success\"></i> Online</a>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</div>").append("\t\n");

        // search form start
        sb.append("<form action=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\" method=\"get\" class=\"sidebar-form\">").append("\t\n");
        sb.append("<div class=\"input-group\">").append("\t\n");
        sb.append("<input type=\"text\" name=\"q\" class=\"form-control\" placeholder=\"Search...\">").append("\t\n");
        sb.append("<span class=\"input-group-btn\">").append("\t\n");
        sb.append("<button type=\"submit\" name=\"search\" id=\"search-btn\" class=\"btn btn-flat\"><i class=\"fa fa-search\"></i>").append("\t\n");
        sb.append("</button>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</form>").append("\t\n");
        // search form end

        // sidebar menu start
        sb.append("<ul class=\"sidebar-menu tree\" data-widget=\"tree\">").append("\t\n");
        sb.append("<li class=\"header\">MAIN NAVIGATION</li>").append("\t\n");
        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");


        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<i class=\"fa fa-angle-left pull-right\"></i>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v1</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/index2.html\"><i class=\"fa fa-circle-o\"></i> Dashboard v2</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");

        sb.append("<li class=\"treeview active menu-open\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html#\">").append("\t\n");
        sb.append("<i class=\"fa fa-files-o\"></i>").append("\t\n");
        sb.append("<span>Layout Options</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<span class=\"label label-primary pull-right\">4</span>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("<ul class=\"treeview-menu\">").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html\"><i class=\"fa fa-circle-o\"></i> Top Navigation</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/boxed.html\"><i class=\"fa fa-circle-o\"></i> Boxed</a></li>").append("\t\n");
        sb.append("<li class=\"active\"><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/fixed.html\"><i class=\"fa fa-circle-o\"></i> Fixed</a></li>").append("\t\n");
        sb.append("<li><a href=\"https://adminlte.io/themes/AdminLTE/pages/layout/collapsed-sidebar.html\"><i class=\"fa fa-circle-o\"></i> Collapsed Sidebar</a></li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("<li>").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/pages/widgets.html\">").append("\t\n");
        sb.append("<i class=\"fa fa-th\"></i> <span>Widgets</span>").append("\t\n");
        sb.append("<span class=\"pull-right-container\">").append("\t\n");
        sb.append("<small class=\"label pull-right bg-green\">new</small>").append("\t\n");
        sb.append("</span>").append("\t\n");
        sb.append("</a>").append("\t\n");
        sb.append("</li>").append("\t\n");
        sb.append("</ul>").append("\t\n");
        // sidebar menu end

        sb.append("</section>").append("\t\n");
        // sb.append("<div class=\"slimScrollBar\" style=\"background: rgb(0, 0, 0); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 506.946px;\"></div>").append("\t\n");
        // sb.append("<div class=\"slimScrollRail\" style=\"width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;\"></div>").append("\t\n");
        // sb.append("</div>").append("\t\n");
        sb.append("</aside>").append("\t\n");
        return sb.toString();
    }
}
