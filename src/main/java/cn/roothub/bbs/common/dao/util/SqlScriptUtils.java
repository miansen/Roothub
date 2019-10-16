package cn.roothub.bbs.common.dao.util;

/**
 * @Author: miansen.wang
 * @Date: 2019/10/16 22:19
 */
public class SqlScriptUtils implements StringPool{

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
     * @return
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
        String s = convertForeach("${item}", "coll", "index",
                "item", ",", "(",")");
        System.out.println(s);
    }
}
