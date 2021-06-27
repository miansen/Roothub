package wang.miansen.roothub.common.ui;

import wang.miansen.roothub.common.util.StringUtils;

/**
 * 布局标签
 * 
 * @author miansen.wang
 * @date 2020-03-11
 */
@SuppressWarnings("serial")
public class LayoutTag extends AbstractBaseTag {

    /**
     * 页面的标题，默认是 {@link #DEFAULT_TITLE}
     */
    private String title;

    /**
     * 皮肤样式，默认是 {@link #DEFAULT_SKIN}
     */
    private String skin;

    /**
     * 是否使用顶部导航栏（true：使用，false，不使用。默认是 false）
     */
    private boolean useTopNav;

    /**
     * 默认的页面标题：Roothub
     */
    private final static String DEFAULT_TITLE = "Roothub";

    /**
     * 默认的皮肤样式：skin-blue
     */
    private final static String DEFAULT_SKIN = "skin-blue";

    /**
     * 顶部导航栏 class 元素：layout-top-nav
     */
    private final static String TOP_NAV = "layout-top-nav";

	@Override
	protected String getBodyContentString(String bodyContent) {
		String contextPath = getContextPath();
		StringBuilder sb = new StringBuilder();
		sb.append("<!doctype html>");
		sb.append("\t\n");
		sb.append("<html lang=\"zh-CN\">");
		sb.append("\t\n");
		sb.append("<head>");
		sb.append("\t\n");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("\t\n");
		sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
		sb.append("\t\n");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">");
		sb.append("\t\n");
		sb.append("<title>").append(getTitle()).append("</title>");
		sb.append("\t\n");
		sb.append("<link rel=\"icon\" href=\"").append(contextPath).append("/admin/resource/images/favicon.ico\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/bootstrap-v3.4.1/dist/css/bootstrap.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/font-awesome-v4.7.0/css/font-awesome.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/Ionicons-v2.0.0/css/ionicons.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/AdminLTE-v2.4.18/dist/css/AdminLTE.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/AdminLTE-v2.4.18/dist/css/skins/_all-skins.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/bootstrap-datepicker-v1.9.0/dist/css/bootstrap-datepicker.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/cropper-v3.1.3/cropper.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath)
			.append("/resources/libs/wangEditor/wangEditor.min.css\">");
		sb.append("\t\n");
		sb.append("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic\">");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath).append("/resources/libs/jquery-v3.4.1/dist/jquery.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath)
			.append("/resources/libs/bootstrap-v3.4.1/dist/js/bootstrap.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath)
			.append("/resources/libs/AdminLTE-v2.4.18/dist/js/adminlte.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath)
			.append("/resources/libs/bootstrap-datepicker-v1.9.0/dist/js/bootstrap-datepicker.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath)
			.append("/resources/libs/bootstrap-paginator-1.0.2/bootstrap-paginator.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath).append("/resources/libs/bootbox-v5.3.2/bootbox.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath).append("/resources/libs/cropper-v3.1.3/cropper.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath).append("/resources/libs/wangEditor/wangEditor.min.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath).append("/resources/js/dialog.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath).append("/resources/js/list.js\"></script>");
		sb.append("\t\n");
		sb.append("<script src=\"").append(contextPath).append("/resources/js/avatar.js\"></script>");
		sb.append("\t\n");
		sb.append("</head>");
		sb.append("\t\n");
		sb.append("<body class=\"").append(getSkin()).append(" ").append(getTopNav()).append("\">");
		sb.append("\t\n");
		sb.append("<div class=\"wrapper\">");
		sb.append("\t\n");
		sb.append(bodyContent);
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</body>");
		sb.append("\t\n");
		sb.append("</html>");
		return sb.toString();
	}

    public String getTitle() {
	    if (StringUtils.isEmpty(this.title)) {
	        return DEFAULT_TITLE;
        }
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkin() {
	    if (StringUtils.isEmpty(this.skin)) {
	        return DEFAULT_SKIN;
        }
        return this.skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public boolean getUseTopNav() {
        return this.useTopNav;
    }

    public void setUseTopNav(boolean useTopNav) {
        this.useTopNav = useTopNav;
    }

    public String getTopNav() {
	    if (useTopNav) {
	        return TOP_NAV;
        }
	    return "";
    }
}
