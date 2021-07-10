package wang.miansen.roothub.sys.portal.ui;

/**
 * 布局标签
 *
 * @author miansen.wang
 * @date 2020-03-11
 */
public class LayoutTag extends AbstractLayoutTag {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4579456493652107541L;

    @Override
    protected String getBodyContentString(String bodyContent) {
        String contextPath = getContextPath();

        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html>").append("\t\n");
        sb.append("<html style=\"height: auto; min-height: 100%;\">").append("\t\n");
        sb.append(getHead(contextPath, getTitle())).append("\t\n");
        // body start
        sb.append("<body class=\"skin-blue fixed layout-top-nav\" style=\"height: auto; min-height: 100%;\">").append("\t\n");
        // wrapper start
        sb.append("<div class=\"wrapper\" style=\"height: auto; min-height: 100%;\">").append("\t\n");

        // header start
        sb.append(getHeader(contextPath)).append("\t\n");
        // header end

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

    /**
     * 获取导航栏
     *
     * @param contextPath 上下文路径
     * @return 导航栏
     */
    protected String getHeader(String contextPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("<header class=\"main-header\">").append("\t\n");
        sb.append("<nav class=\"navbar navbar-static-top\">").append("\t\n");
        sb.append("<div class=\"container\">").append("\t\n");
        sb.append(getLogo()).append("\t\n");
        sb.append(getNavbarLeftMenu()).append("\t\n");
        sb.append(getNavbarRightMenu(contextPath)).append("\t\n");
        sb.append("</div>").append("\t\n");
        sb.append("</nav>").append("\t\n");
        sb.append("</header>").append("\t\n");
        return sb.toString();
    }

    /**
     * 获取 LOGO
     *
     * @return LOGO
     */
    protected String getLogo() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"navbar-header\">").append("\t\n");
        sb.append("<a href=\"https://adminlte.io/themes/AdminLTE/index2.html\" class=\"navbar-brand\"><b>Admin</b>LTE</a>").append("\t\n");
        sb.append("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar-collapse\">").append("\t\n");
        sb.append("<i class=\"fa fa-bars\"></i>").append("\t\n");
        sb.append("</button>").append("\t\n");
        sb.append("</div>").append("\t\n");
        return sb.toString();
    }

}
