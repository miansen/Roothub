package wang.miansen.roothub.common.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private static final char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f' };
	private static final char[] digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static final Random rand = new Random();
	private static final String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 驼峰转下划线
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (isBlank(param)) {
			return "";
		} else {
			int len = param.length();
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; ++i) {
				char c = param.charAt(i);
				if (Character.isUpperCase(c) && i > 0) {
					sb.append('_');
				}

				sb.append(Character.toLowerCase(c));
			}
			return sb.toString();
		}
	}

	/**
	 * 字符串为 null 或者为  "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 字符串不为 null 而且不为  "" 时返回 true
	 */
	public static boolean notBlank(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}

	public static boolean isEmail(String email) {
		if (isBlank(email)) {
			return false;
		} else {
			Pattern pattern = Pattern.compile(check);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String randomString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int loop = 0; loop < length; ++loop) {
			sb.append(hexDigits[rand.nextInt(hexDigits.length)]);
		}
		return sb.toString();
	}

	public static String randomNumber(int length) {
		StringBuffer sb = new StringBuffer();
		for (int loop = 0; loop < length; ++loop) {
			sb.append(digits[rand.nextInt(digits.length)]);
		}
		return sb.toString();
	}

	public static String getEncryptionToken(String token) {
		for (int i = 0; i < 6; i++) {
			token = EncryptionUtil.encoderBase64(token.getBytes());
		}
		return token;
	}

	public static String getDecryptToken(String encryptionToken) {
		for (int i = 0; i < 6; i++) {
			encryptionToken = EncryptionUtil.decoderBase64(encryptionToken.getBytes());
		}
		return encryptionToken;
	}

	public static String noHtml(String s) {
		if (isBlank(s))
			return "";
		else
			return s.replaceAll("<[.[^<]]*>", "");
	}

	public static String transHtml(String s) {
		if (isBlank(s))
			return "";
		else
			return s.replace("<", "&lt;").replace(">", "&gt;");
	}

	public static List<String> fetchUsers(String str) {
		List<String> ats = new ArrayList<String>();
		String pattern = "@([a-zA-Z_0-9-/b]+)\\s";
		Pattern regex = Pattern.compile(pattern);
		Matcher regexMatcher = regex.matcher(str);
		while (regexMatcher.find()) {
			ats.add(regexMatcher.group(1));
		}
		return ats;
	}

	public static int str2int(String s) {
		if (s == null || s.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(s);
		}
	}

	/**
	 * 格式化url参数部分返回map
	 * params格式：a=1&b=2&c=3
	 * 返回：{a: 1, b: 2, c: 3}
	 * @param params
	 * @return
	 */
	public static Map<String, Object> formatParams(String params) {
		if (isEmpty(params))
			return null;
		Map<String, Object> map = new HashMap<>();
		for (String s : params.split("&")) {
			String[] ss = s.split("=");
			map.put(ss[0], ss[1]);
		}
		return map;
	}

	/**
	 * 检查给定的字符串是否为空。
	 * @param str 要检查的字符串
	 * @return boolean
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

}
