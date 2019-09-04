package cn.roothub.bbs.common.dao.enums;

/**
 * @Author: miansen.wang
 * @Date: 2019/9/4 22:14
 */
public enum SqlMethod {

    SELECT_ONE("selectOne", "查询满足条件一条数据", "<script>\nSELECT %s FROM %s %s\n</script>");

    private final String method;

    private final String desc;

    private final String sql;

    private SqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}
