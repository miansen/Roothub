package wang.miansen.roothub.common.dao.mapper.wrapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import wang.miansen.roothub.common.dao.mapper.enums.SqlKeyword;
import wang.miansen.roothub.common.dao.mapper.exceptions.BaseMapperException;
import wang.miansen.roothub.common.dao.mapper.wrapper.conditions.Compare;
import wang.miansen.roothub.common.dao.mapper.wrapper.conditions.Func;
import wang.miansen.roothub.common.dao.mapper.wrapper.conditions.Join;
import wang.miansen.roothub.common.dao.mapper.wrapper.conditions.Page;
import wang.miansen.roothub.common.dao.mapper.wrapper.segments.ISqlSegment;
import wang.miansen.roothub.common.dao.mapper.wrapper.segments.SqlSegmentBuilder;
import wang.miansen.roothub.common.dao.mapper.util.ArrayUtils;
import wang.miansen.roothub.common.dao.mapper.util.StringPool;

/**
 * 条件包装器的抽象父类，已经实现了大部分的方法。
 * 
 * @param <T> 数据库表映射实体类的类型
 * @param <R> 返回结果的类型
 * @param <K> 数据库字段的类型
 * @param <V> 字段对应的值的类型
 *
 * @author miansen.wang
 * @date 2019-9-12 10:04
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractWrapper<T, R extends AbstractWrapper<T, R, K, V>, K, V>
		implements Compare<R, K, V>, Join<R>, Func<R, K, V>, Page<R>, ISqlSegment {

	/**
	 * {@link #paramNameValuePairs} key 的固定的前缀
	 */
	protected static final String BASE_MAPPER_PREFIX_PARAM = "BASEMAPPERVALUE";

	/**
	 * 别名
	 */
	protected static final String BASE_MAPPER_ALIAS_PARAM = "wrapper";

	protected static final String BASE_MAPPER_TOKEN = "#{%s.paramNameValuePairs.%s}";

	protected static final String PLACE_HOLDER = "(%s)";

	/**
	 * 数据库表映射实体类
	 */
	protected T model;

	/**
	 * 实体类型
	 */
	protected Class<T> modelClass;

	/**
	 * 占位符
	 */
	protected final R typedThis = (R) this;

	/**
	 * 存储参数的值
	 * K: {@link #BASE_MAPPER_PREFIX_PARAM} + {@link #paramNameSeq}.incrementAndGet()
	 * V: 参数值
	 */
	protected Map<String, Object> paramNameValuePairs = new HashMap<>(16);

	/**
	 * 自增长值，作为 {@link #paramNameValuePairs} 的 key 的后缀
	 */
	protected AtomicInteger paramNameSeq = new AtomicInteger(0);

	/**
	 * SQL 片段生成器
	 */
	private SqlSegmentBuilder sqlSegmentBuilder = new SqlSegmentBuilder();

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public Class<T> getModelClass() {
		return modelClass;
	}

	public void setModelClass(Class<T> modelClass) {
		this.modelClass = modelClass;
	}

	@Override
	public R eq(K column, V value) {
		return addCondition(column, SqlKeyword.EQ, value);
	}

	@Override
	public R eq(Map<K, V> params) {
		params.forEach((k, v) -> addCondition(k, SqlKeyword.EQ, v));
		return typedThis;
	}

	@Override
	public R ne(K column, V value) {
		return addCondition(column, SqlKeyword.NE, value);
	}

	@Override
	public R ne(Map<K, V> params) {
		params.forEach((k, v) -> addCondition(k, SqlKeyword.NE, v));
		return typedThis;
	}

	@Override
	public R gt(K column, V value) {
		return addCondition(column, SqlKeyword.GT, value);
	}

	@Override
	public R gt(Map<K, V> params) {
		params.forEach((k, v) -> addCondition(k, SqlKeyword.GT, v));
		return typedThis;
	}

	@Override
	public R ge(K column, V value) {
		return addCondition(column, SqlKeyword.GE, value);
	}

	@Override
	public R ge(Map<K, V> params) {
		params.forEach(((k, v) -> addCondition(k, SqlKeyword.GE, v)));
		return typedThis;
	}

	@Override
	public R lt(K column, V value) {
		return addCondition(column, SqlKeyword.LT, value);
	}

	@Override
	public R lt(Map<K, V> params) {
		params.forEach((k, v) -> addCondition(k, SqlKeyword.LT, v));
		return typedThis;
	}

	@Override
	public R le(K column, V value) {
		return addCondition(column, SqlKeyword.LE, value);
	}

	@Override
	public R le(Map<K, V> params) {
		params.forEach((k, v) -> addCondition(k, SqlKeyword.LE, v));
		return typedThis;
	}

	@Override
	public R between(K column, V value1, V value2) {
		return addCondition(() -> columnToString(column), SqlKeyword.BETWEEN, () -> formatSqlValue(value1),
				SqlKeyword.AND, () -> formatSqlValue(value2));
	}

	@Override
	public R notBetween(K column, V value1, V value2) {
		return addCondition(() -> columnToString(column), SqlKeyword.NOT, SqlKeyword.BETWEEN,
				() -> formatSqlValue(value1), SqlKeyword.AND, () -> formatSqlValue(value2));
	}

	@Override
	public R isNull(K column) {
		return addCondition(() -> columnToString(column), SqlKeyword.IS_NULL);
	}

	@Override
	public R isNotNull(K column) {
		return addCondition(() -> columnToString(column), SqlKeyword.IS_NOT_NULL);
	}

	@Override
	public R in(K column, V... values) {
		return ArrayUtils.isEmpty(values) ? typedThis
				: addCondition(() -> columnToString(column), SqlKeyword.IN, inExpression(Arrays.asList(values)));
	}

	@Override
	public R notIn(K column, V... values) {
		return ArrayUtils.isEmpty(values) ? typedThis
				: addCondition(() -> columnToString(column), SqlKeyword.NOT, SqlKeyword.IN,
						inExpression(Arrays.asList(values)));
	}

	@Override
	public R like(K column, V value) {
		return addCondition(() -> columnToString(column), SqlKeyword.LIKE,
				() -> StringPool.PERCENT + formatSqlValue(value) + StringPool.PERCENT);
	}

	@Override
	public R notLike(K column, V value) {
		return addCondition(() -> columnToString(column), SqlKeyword.NOT, SqlKeyword.LIKE,
				() -> StringPool.PERCENT + formatSqlValue(value) + StringPool.PERCENT);
	}

	@Override
	public R likeLeft(K column, V value) {
		return addCondition(() -> columnToString(column), SqlKeyword.LIKE,
				() -> StringPool.PERCENT + formatSqlValue(value));
	}

	@Override
	public R likeRight(K column, V value) {
		return addCondition(() -> columnToString(column), SqlKeyword.LIKE,
				() -> formatSqlValue(value) + StringPool.PERCENT);
	}

	@Override
	public R orderBy(boolean isAsc, K... columns) {
		if (ArrayUtils.isEmpty(columns)) {
			return typedThis;
		} else {
			SqlKeyword mode = isAsc ? SqlKeyword.ASC : SqlKeyword.DESC;
			for (int i = 0; i < columns.length; i++) {
				K column = columns[i];
				addCondition(SqlKeyword.ORDER_BY, () -> columnToString(column), mode);
			}
			return typedThis;
		}
	}

	@Override
	public R groupBy(K... columns) {
		return ArrayUtils.isEmpty(columns) ? typedThis
				: addCondition(SqlKeyword.GROUP_BY, () -> Arrays.stream(columns).map(this::columnToString)
						.collect(Collectors.joining(StringPool.SPACE_COMMA_SPACE)));
	}

	@Override
	public R or() {
		return addCondition(SqlKeyword.OR);
	}

	@Override
	public R and() {
		return addCondition(SqlKeyword.AND);
	}

	@Override
	public R exists(String existsSql) {
		return addCondition(SqlKeyword.EXISTS, () -> String.format(PLACE_HOLDER, existsSql));
	}

	@Override
	public R notExists(String existsSql) {
		return addCondition(SqlKeyword.NOT, SqlKeyword.EXISTS, () -> String.format(PLACE_HOLDER, existsSql));
	}

	@Override
	public R having() {
		return addCondition(SqlKeyword.HAVING);
	}

	@Override
	public R having(String havingSql) {
		return addCondition(SqlKeyword.HAVING, () -> havingSql);
	}

	@Override
	public R limit(Integer start, Integer end) {
		return addCondition(SqlKeyword.LIMIT, () -> String.valueOf(start), () -> StringPool.COMMA,
				() -> String.valueOf(end));
	}

	/**
	 * 添加 SQL 片段到容器中
	 * @param column 数据库表字段名称
	 * @param sqlKeyword 连接符
	 * @param value 字段对应的值
	 * @return this
	 */
	protected R addCondition(K column, SqlKeyword sqlKeyword, V value) {
		return addCondition(() -> columnToString(column), sqlKeyword, () -> formatSqlValue(value));
	}

	/**
	 * 添加 SQL 片段到容器中
	 * @param sqlSegments SQL 片段
	 * @return this
	 */
	protected R addCondition(ISqlSegment... sqlSegments) {
		sqlSegmentBuilder.add(sqlSegments);
		return typedThis;
	}

	/**
	 * 缓存 value，并且格式化成 mybatis 的参数格式：#{%s}
	 * @param value 数据库表字段对应的值
	 * @return String
	 */
	protected String formatSqlValue(V value) {
		if (value instanceof Object) {
			String genParamName = BASE_MAPPER_PREFIX_PARAM + this.paramNameSeq.incrementAndGet();
			this.paramNameValuePairs.put(genParamName, value);
			return String.format(BASE_MAPPER_TOKEN, BASE_MAPPER_ALIAS_PARAM, genParamName);
		} else {
			throw new BaseMapperException("not support this value !");
		}
	}

	/**
	 * 将 column 转化成 String
	 * @param column 数据库表字段名称
	 * @return String
	 */
	protected String columnToString(K column) {
		if (column instanceof String) {
			return (String) column;
		} else {
			throw new BaseMapperException("not support this column !");
		}
	}

	/**
	 * 生成 IN 的表达式
	 * @param values
	 * @return ISqlSegment
	 */
	private ISqlSegment inExpression(Collection<V> values) {
		return () -> values.stream().map((value) -> formatSqlValue(value))
				.collect(Collectors.joining(StringPool.COMMA, StringPool.LEFT_BRACKET, StringPool.RIGHT_BRACKET));
	}

	/**
	 * 获取完整的 SQL 语句
	 * <p>包含 where... group by... having... order by... limit...
	 * @return
	 */
	@Override
	public String getSqlSegment() {
		return sqlSegmentBuilder.getSqlSegment();
	}
	
	/**
	 * 获取普通的 sql 语句
	 * <p>只包含 where...
	 * @return
	 */
	public String getNormalSqlSegment() {
		return sqlSegmentBuilder.getNormalSqlSegment();
	}

}
