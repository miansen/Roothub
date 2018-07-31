package cn.roothub.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PtabUtil {

	private PtabUtil() {}
	
	public static void setPtab(String ptab,HttpServletResponse response) {
		CookieAndSessionUtil.setCookie("ptab", Base64Util.encode(ptab), 30 * 24 * 60 * 60, "/", true, response);
	}
	
	public static void editPtab(HttpServletRequest request,HttpServletResponse response,String ptab) {
		CookieAndSessionUtil.editCookie(request,response, "ptab", Base64Util.encode(ptab));
	}
	
	public static String getPtab(HttpServletRequest request,HttpServletResponse response,String ptab) {
		String str = null;
		str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "ptab"));
		if(str == null) {
			if(ptab.equals("def")) {
				setPtab("all",response);
				return str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "ptab"));
			}else {
				setPtab(ptab,response);
				return str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "ptab"));
			}
		}else {
			if(ptab.equals("def")) {
				return str;
			}else if(ptab.equals(str)) {
				return str;
			}else {
				editPtab(request,response,ptab);
				return str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "ptab"));
			}
		}
		
		
		
		
		
		/*String str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "ptab"));
		if(str == null) {
			setPtab(ptab,response);
			return Base64Util.decode(CookieAndSessionUtil.getCookie(request, "ptab"));
		}else if(str != null && str.equals(ptab)) {
			return str;
		}else {
			CookieAndSessionUtil.editCookie(request,response, "ptab", Base64Util.encode(ptab));
			return Base64Util.decode(CookieAndSessionUtil.getCookie(request, "ptab"));
		}*/
	}
	
	public static void removePtab(HttpServletResponse response) {
		CookieAndSessionUtil.removeCookie(response, "ptab", "/", true);
	}
}
