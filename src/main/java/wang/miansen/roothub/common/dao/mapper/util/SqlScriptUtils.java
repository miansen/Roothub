package wang.miansen.roothub.common.dao.mapper.util;

/**
 * 生成 Mybatis 标签的工具类
 * @Author: miansen.wang
 * @Date: 2019/10/16 22:19
 */
public class SqlScriptUtils implements StringPool {

    /**
     * 安全入参: #{参数}
     * @param param
     * @return #{参数} 脚本
     */
    public static String safeParam(final String param) {
        return HASH_LEFT_BRACE + param + RIGHT_BRACE;
    }

    /**
     * 非安全入参: ${参数}
     * @param param
     * @return ${参数} 脚本
     */
    public static String unSafeParam(final String param) {
        return DOLLAR_LEFT_BRACE + param + RIGHT_BRACE;
    }

    /**
     * 生成 if 标签的脚本
     * @param ifTest if 标签的 test 条件
     * @param ifSqlScript if 标签的内容
     * @return if 标签脚本
     */
    public static String convertIf(final String ifTest, final String ifSqlScript) {
        return String.format("<if test=\"%s\">%s</if>", ifTest, NEWLINE + ifSqlScript + NEWLINE);
    }

    /**
     * 生成 set 标签的脚本
     * @param setSqlScript set 标签的内容
     * @return set 标签脚本
     */
    public static String convertSet(final String setSqlScript) {
        return "<set>" + NEWLINE + setSqlScript + NEWLINE + "</set>";
    }

    /**
     * 生成 where 标签的脚本
     * @param whereSqlScript where 标签的内容
     * @return where 标签脚本
     */
    public static String convertWhere(final String whereSqlScript) {
        return "<where>" + NEWLINE + whereSqlScript + NEWLINE + "</where>";
    }

    /**
     * 生成 choose 标签的脚本
     * @param whenTest when 标签的 test 条件
     * @param whenSqlScript when 标签的内容
     * @param otherwise otherwise 标签的内容
     * @return choose 标签脚本
     */
    public static String convertChoose(final String whenTest, final String whenSqlScript, final String otherwise) {
        StringBuilder sb = new StringBuilder("<choose>");
        sb.append(NEWLINE);
        sb.append("<when test=").append(QUOTE).append(whenTest).append(QUOTE).append(RIGHT_CHEV);
        sb.append(NEWLINE);
        sb.append(whenSqlScript);
        sb.append(NEWLINE);
        sb.append("</when>");
        sb.append(NEWLINE);
        sb.append("<otherwise>").append(otherwise).append("</otherwise>");
        sb.append(NEWLINE);
        sb.append("</choose>");
        return sb.toString();
    }

    /**
     * 生成 trim 标签的脚本
     * @param sqlScript sql 脚本片段
     * @param prefix 以...开头
     * @param suffix 以...结尾
     * @param prefixOverrides 去掉最前面的哪个字符
     * @param suffixOverrides 去掉最后面的哪个字符
     * @return trim 标签脚本
     */
    public static String convertTrim(final String sqlScript, final String prefix, final String suffix,
                                     final String prefixOverrides, final String suffixOverrides) {
        StringBuilder sb = new StringBuilder("<trim");
        if (!StringUtils.isEmpty(prefix)) {
            sb.append(SPACE).append("prefix=").append(QUOTE).append(prefix).append(QUOTE);
        }
        if (!StringUtils.isEmpty(suffix)) {
            sb.append(SPACE).append("suffix=").append(QUOTE).append(suffix).append(QUOTE);
        }
        if (!StringUtils.isEmpty(prefixOverrides)) {
            sb.append(SPACE).append("prefixOverrides=").append(QUOTE).append(prefixOverrides).append(QUOTE);
        }
        if (!StringUtils.isEmpty(suffixOverrides)) {
            sb.append(SPACE).append("suffixOverrides=").append(QUOTE).append(suffixOverrides).append(QUOTE);
        }
        return sb.append(RIGHT_CHEV).append(NEWLINE).append(sqlScript).append(NEWLINE).append("</trim>").toString();
    }

    /**
     * 生成 foreach 标签的脚本
     *
     * @param sqlScript foreach 内部的 sql 脚本
     * @param collection 集合的名字
     * @param index 指定一个名字，用于表示在迭代过程中，每次迭代到的位置
     * @param item 集合中每一个元素进行迭代时的别名
     * @param separator 每次进行迭代之间以什么符号作为分隔符
     * @param open foreach 以什么开始
     * @param close foreach 以什么结束
     * @return foreach 标签脚本
     */
    public static String convertForeach(final String sqlScript, final String collection, final String index,
                                        final String item, final String separator, final String open,
                                        final String close) {
        StringBuilder sb = new StringBuilder("<foreach");
        if (!StringUtils.isEmpty(collection)) {
            sb.append(SPACE).append("collection=").append(QUOTE).append(collection).append(QUOTE);
        }
        if (!StringUtils.isEmpty(index)) {
            sb.append(SPACE).append("index=").append(QUOTE).append(index).append(QUOTE);
        }
        if (!StringUtils.isEmpty(item)) {
            sb.append(SPACE).append("item=").append(QUOTE).append(item).append(QUOTE);
        }
        if (!StringUtils.isEmpty(separator)) {
            sb.append(SPACE).append("separator=").append(QUOTE).append(separator).append(QUOTE);
        }
        if (!StringUtils.isEmpty(open)) {
            sb.append(SPACE).append("open=").append(QUOTE).append(open).append(QUOTE);
        }
        if (!StringUtils.isEmpty(close)) {
            sb.append(SPACE).append("close=").append(QUOTE).append(close).append(QUOTE);
        }
        return sb.append(RIGHT_CHEV).append(NEWLINE).append(sqlScript).append(NEWLINE).append("</foreach>").toString();
    }

    public static void main(String[] args) {
        String s = convertChoose("wrapper != null and wrapper.selectColumns != null", "${wrapper.selectColumns}", "id, name");
        System.out.println(s);
        String s1 = convertForeach("${item}", "coll", "index",
                "item", ",", "(",")");
        System.out.println(s1);
        String s2 = convertTrim("#{id},#{name}", "(", ")", ",", ",");
        System.out.println(s2);
        String s3 = convertIf("wrapper != null and wrapper.selectColumns != null and wrapper.selectColumns != ''", "${wrapper.selectColumns}");
        System.out.println(s3);
        String s4 = convertWhere(s3);
        System.out.println(s4);
    }
}
