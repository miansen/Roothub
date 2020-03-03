package wang.miansen.roothub.common.ui;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.miansen.roothub.common.util.ApplicationContextUtils;
import wang.miansen.roothub.common.util.IDGenerator;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.config.ApplicationConfig;

/**
 * 自定义 JSP 标签的基础抽象父类
 * <p>此类定义了 HTML 元素常用的属性，比如：id, class, style, attr 等，子类可通过 get 方法直接获取。
 * <p>doStartTag() 方法和 doEndTag() 方法已默认实现，子类自需要重写 getBodyContentString() 方法即可。
 * <p>此外还实现了 setDynamicAttribute() 方法，vars 字段保存了自定义标签的动态属性名和属性值。
 * <p>如果子类自己定义了额外的字段，那么应该显示的调用 release() 方法重置字段的基础值。
 * 
 * @author miansen.wang
 * @date 2020-02-29
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractBaseTag extends BodyTagSupport implements DynamicAttributes, TryCatchFinally {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractBaseTag.class);
	
	/**
	 * HTML 元素的 type 属性
	 */
	protected String type;
	
	/**
	 * HTML 元素的 style 属性
	 */
	protected String style;
	
	/**
	 * HTML 元素的 attr 属性
	 */
	protected String attr;
	
	/**
	 * 保存自定义标签的动态属性
	 */
	protected Map<String, String> vars = new HashMap<>();
	
	protected ApplicationConfig applicationConfig;
	
	public AbstractBaseTag() {
		init();
	}

	/**
	 * 对象实例化时做一些初始化操作，子类可按需重写。
	 * <p>注意：子类如果重写，则必须显示的调用 {@code super.init()}。
	 */
	protected void init() {
		try {
			applicationConfig = ApplicationContextUtils.getBean(ApplicationConfig.class);
		} catch (Exception e) {
			logger.error("Failed get bean [ApplicationConfig]");
		}
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			String body = super.bodyContent == null ? "" : bodyContent.getString();
			super.pageContext.getOut().append(getBodyContentString(body));
		} catch (Exception e) {
			logger.error(getClass().getName() + " doEndTag error: " + e);
			throw new JspException(e);
		}
		release();
		return EVAL_PAGE;
	}

	@Override
	public void doCatch(Throwable t) throws Throwable {
		logger.error(getClass().getName() + " error: " + t);
		throw t;
	}

	@Override
	public void doFinally() {
		release();
	}

	/**
	 * 设置动态属性，保存到 {@code vars} 字段中。
	 */
	@Override
	public void setDynamicAttribute(String uri, String key, Object value) throws JspException {
		this.vars.put(key, value.toString());
	}
	
	/**
	 * 获取生成的 HTML 元素，由子类实现。
	 * 
	 * @param bodyContent 标签体中的内容
	 * @return String
	 */
	protected abstract String getBodyContentString(String bodyContent);

	/**
	 * 获取 HTML 元素的 id 属性
	 */
	@Override
	public String getId() {
		if (StringUtils.isEmpty(super.id)) {
			super.id = "rui_" + IDGenerator.generateID();
		}
		return super.id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.vars.put("type", type);
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
		this.vars.put("style", style);
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
		this.vars.put("attr", attr);
	}

	public Map<String, String> getVars() {
		return vars;
	}

	public void setVars(Map<String, String> vars) {
		this.vars = vars;
	}
	
}
