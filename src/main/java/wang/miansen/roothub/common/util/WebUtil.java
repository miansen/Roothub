package wang.miansen.roothub.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wang.miansen.roothub.modules.user.model.User;


public class WebUtil {

	private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Get cookie value by cookie name.
     */
    public static String getCookie(HttpServletRequest request, String name, String defaultValue) {
        Cookie cookie = getCookieObject(request, name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * Get cookie value by cookie name.
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, name, null);
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public static Integer getCookieToInt(HttpServletRequest request, String name) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public Integer getCookieToInt(HttpServletRequest request, String name, Integer defaultValue) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public static Long getCookieToLong(HttpServletRequest request, String name) {
        String result = getCookie(request, name);
        return result != null ? Long.parseLong(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public static Long getCookieToLong(HttpServletRequest request, String name, Long defaultValue) {
        String result = getCookie(request, name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    /**
     * Get cookie object by cookie name.
     */
    public static Cookie getCookieObject(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    /**
     * Get all cookie objects.
     */
    public static Cookie[] getCookieObjects(HttpServletRequest request) {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    /**
     * Set Cookie.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param isHttpOnly      true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
        doSetCookie(response, name, value, maxAgeInSeconds, null, null, isHttpOnly);
    }

    /**
     * Set Cookie.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        doSetCookie(response, name, value, maxAgeInSeconds, null, null, null);
    }

    /**
     * Set Cookie to response.
     */
    public static void setCookie(HttpServletResponse response, Cookie cookie) {
        response.addCookie(cookie);
    }

    /**
     * Set Cookie to response.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path            see Cookie.setPath(String)
     * @param isHttpOnly      true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
        doSetCookie(response, name, value, maxAgeInSeconds, path, null, isHttpOnly);
    }

    /**
     * Set Cookie to response.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path            see Cookie.setPath(String)
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path) {
        doSetCookie(response, name, value, maxAgeInSeconds, path, null, null);
    }

    /**
     * Set Cookie to response.
     *
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path            see Cookie.setPath(String)
     * @param domain          the domain name within which this cookie is visible; form is according to RFC 2109
     * @param isHttpOnly      true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, String domain, boolean isHttpOnly) {
        doSetCookie(response, name, value, maxAgeInSeconds, path, domain, isHttpOnly);
    }

    /**
     * Remove Cookie.
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        doSetCookie(response, name, null, 0, null, null, null);
    }

    /**
     * Remove Cookie.
     */
    public static void removeCookie(HttpServletResponse response, String name, String path) {
        doSetCookie(response, name, null, 0, path, null, null);
    }

    /**
     * Remove Cookie.
     */
    public static void removeCookie(HttpServletResponse response, String name, String path, String domain) {
        doSetCookie(response, name, null, 0, path, domain, null);
    }

    private static void doSetCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        // set the default path value to "/"
        if (path == null) {
            path = "/";
        }
        cookie.setPath(path);

        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly != null) {
            cookie.setHttpOnly(isHttpOnly);
        }
        response.addCookie(cookie);
    }

    /**
     * Render with text. The contentType is: "text/plain".
     *//*
    public static void renderText(HttpServletResponse response, String text) throws IOException {
        OutputStream out = null;
        try {
            response.setHeader("Pragma", "no-cache");    // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(ContentType.TEXT.value());
            response.setCharacterEncoding(DEFAULT_ENCODING);    // 与 contentType 分开设置
            out = response.getOutputStream();
            out.write(text.getBytes(Charset.forName(DEFAULT_ENCODING)));
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }*/

    /**
     * Render with text and content type.
     * <p>
     * Example: renderText("&lt;user id='5888'&gt;James&lt;/user&gt;", "application/xml");
     */
    public static void renderText(HttpServletResponse response, String text, String contentType) throws IOException {
        OutputStream out = null;
        try {
            response.setHeader("Pragma", "no-cache");    // HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(contentType);
            response.setCharacterEncoding(DEFAULT_ENCODING);    // 与 contentType 分开设置
            out = response.getOutputStream();
            out.write(text.getBytes(Charset.forName(DEFAULT_ENCODING)));
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }


    /**
     * Render with file
     */
    public static void renderFile(HttpServletRequest request, HttpServletResponse response, File file) throws IOException {
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-disposition", "attachment; filename=" + encodeFileName(file.getName()));
        if (StringUtils.isBlank(request.getHeader("Range"))) {
            normalRender(response, file);
        } else {
            rangeRender(request, response, file);
        }
    }

    private static String encodeFileName(String fileName) {
        try {
            // return new String(fileName.getBytes("GBK"), "ISO8859-1");
            return new String(fileName.getBytes(DEFAULT_ENCODING), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            return fileName;
        }
    }

    private static void normalRender(HttpServletResponse response, File file) throws IOException {
        response.setHeader("Content-Length", String.valueOf(file.length()));
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            for (int len = -1; (len = inputStream.read(buffer)) != -1; ) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private static void rangeRender(HttpServletRequest request, HttpServletResponse response, File file) throws IOException {
        Long[] range = {null, null};
        processRange(request, range, file);

        String contentLength = String.valueOf(range[1].longValue() - range[0].longValue() + 1);
        response.setHeader("Content-Length", contentLength);
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);    // status = 206

        // Content-Range: bytes 0-499/10000
        StringBuilder contentRange = new StringBuilder("bytes ").append(String.valueOf(range[0])).append("-").append(String.valueOf(range[1])).append("/").append(String.valueOf(file.length()));
        response.setHeader("Content-Range", contentRange.toString());

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            long start = range[0];
            long end = range[1];
            inputStream = new BufferedInputStream(new FileInputStream(file));
            if (inputStream.skip(start) != start) {
                throw new RuntimeException("File skip error");
            }
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            long position = start;
            for (int len; position <= end && (len = inputStream.read(buffer)) != -1; ) {
                if (position + len <= end) {
                    outputStream.write(buffer, 0, len);
                    position += len;
                } else {
                    for (int i = 0; i < len && position <= end; i++) {
                        outputStream.write(buffer[i]);
                        position++;
                    }
                }
            }
            outputStream.flush();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Examples of byte-ranges-specifier values (assuming an entity-body of length 10000):
     * The first 500 bytes (byte offsets 0-499, inclusive): bytes=0-499
     * The second 500 bytes (byte offsets 500-999, inclusive): bytes=500-999
     * The final 500 bytes (byte offsets 9500-9999, inclusive): bytes=-500
     * Or bytes=9500-
     */
    private static void processRange(HttpServletRequest request, Long[] range, File file) {
        String rangeStr = request.getHeader("Range");
        int index = rangeStr.indexOf(',');
        if (index != -1) {
            rangeStr = rangeStr.substring(0, index);
        }
        rangeStr = rangeStr.replace("bytes=", "");

        String[] arr = rangeStr.split("-", 2);
        if (arr.length < 2) {
            throw new RuntimeException("Range error");
        }
        long fileLength = file.length();
        for (int i = 0; i < range.length; i++) {
            if (StringUtils.notBlank(arr[i])) {
                range[i] = Long.parseLong(arr[i].trim());
                if (range[i] >= fileLength) {
                    range[i] = fileLength - 1;
                }
            }
        }

        // Range format like: 9500-
        if (range[0] != null && range[1] == null) {
            range[1] = fileLength - 1;
        }
        // Range format like: -500
        else if (range[0] == null && range[1] != null) {
            range[0] = fileLength - range[1];
            range[1] = fileLength - 1;
        }

        // check final range
        if (range[0] == null || range[1] == null || range[0].longValue() > range[1].longValue()) {
            throw new RuntimeException("Range error");
        }
    }
    
    /**
     * getSession
     * @param request
     * @return
     */
    private static HttpSession getHttpSession(HttpServletRequest request) {
        return request.getSession();
    }
    
    /**
     * setSession
     * @param request
     * @param user
     */
    public static void setSession(HttpServletRequest request,User user) {
    	if(user != null) {
    		getHttpSession(request).setAttribute("user", user);
    	}
    }
    
    /**
     * removeSession
     * @param request
     */
    public static void removeSession(HttpServletRequest request) {
    	getHttpSession(request).removeAttribute("user");
    }
}
