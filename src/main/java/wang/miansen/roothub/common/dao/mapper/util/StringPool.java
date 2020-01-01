package wang.miansen.roothub.common.dao.mapper.util;

/**
 * BaseMapper 常量池
 * @Author: miansen.wang
 * @Date: 2019/9/15 14:26
 */
public interface StringPool {

    String AMPERSAND = "&";
    String AND = "and";
    String AT = "@";
    String ASTERISK = "*";
    String STAR = ASTERISK;
    String BACK_SLASH = "\\";
    String COLON = ":";
    String COMMA = ",";
    String DASH = "-";
    String DOLLAR = "$";
    String DOT = ".";
    String DOTDOT = "..";
    String DOT_CLASS = ".class";
    String DOT_JAVA = ".java";
    String DOT_XML = ".xml";
    String EMPTY = "";
    String EQUALS = "=";
    String FALSE = "false";
    String SLASH = "/";
    String HASH = "#";
    String HAT = "^";
    String LEFT_BRACE = "{";
    String LEFT_BRACKET = "(";
    String LEFT_CHEV = "<";
    String DOT_NEWLINE = ",\n";
    String NEWLINE = "\n";
    String N = "n";
    String NO = "no";
    String NULL = "null";
    String OFF = "off";
    String ON = "on";
    String PERCENT = "%";
    String PIPE = "|";
    String PLUS = "+";
    String QUESTION_MARK = "?";
    String EXCLAMATION_MARK = "!";
    String QUOTE = "\"";
    String RETURN = "\r";
    String TAB = "\t";
    String RIGHT_BRACE = "}";
    String RIGHT_BRACKET = ")";
    String RIGHT_CHEV = ">";
    String SEMICOLON = ";";
    String SINGLE_QUOTE = "'";
    String BACKTICK = "`";
    String SPACE = " ";
    String TILDA = "~";
    String LEFT_SQ_BRACKET = "[";
    String RIGHT_SQ_BRACKET = "]";
    String TRUE = "true";
    String UNDERSCORE = "_";
    String UTF_8 = "UTF-8";
    String US_ASCII = "US-ASCII";
    String ISO_8859_1 = "ISO-8859-1";
    String Y = "y";
    String YES = "yes";
    String ONE = "1";
    String ZERO = "0";
    String DOLLAR_LEFT_BRACE = "${";
    String HASH_LEFT_BRACE = "#{";
    String CRLF = "\r\n";

    String HTML_NBSP = "&nbsp;";
    String HTML_AMP = "&amp";
    String HTML_QUOTE = "&quot;";
    String HTML_LT = "&lt;";
    String HTML_GT = "&gt;";

    String SPACE_COMMA_SPACE = " , ";

    // 实体类的别名
    String ENTITY = "entity";

    // 实体类的别名带后缀
    String ENTITY_DOT = ENTITY + StringPool.DOT;
    
    String WRAPPER = "wrapper";
    
    String WRAPPER_DOT = WRAPPER + StringPool.DOT;
    
    String COLLECTION = "ids";

    // ---------------------------------------------------------------- array

    String[] EMPTY_ARRAY = new String[0];

    byte[] BYTES_NEW_LINE = StringPool.NEWLINE.getBytes();

}
