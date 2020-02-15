package wang.miansen.roothub.common.beans;

import java.lang.reflect.Field;
import java.util.Arrays;

import wang.miansen.roothub.common.enums.BaseMasterDataEnum;
import wang.miansen.roothub.common.enums.ConverPolicy;
import wang.miansen.roothub.common.enums.ConverType;

/**
 * DO DTO VO 互相转换的描述信息
 * 
 * @author miansen.wang
 * @date 2020-02-09
 * @since 3.0
 */
public class ConverInfo {

	/**
	 * 字段对象
	 */
	private Field field;

	/**
	 * 转换的类型
	 */
	private ConverType type;

	/**
	 * 来源属性名数组
	 */
	private String[] sources;
	
	/**
	 * 目标属性名数组
	 */
	private String[] targets;

	/**
	 * 转换的策略
	 */
	private ConverPolicy policy;

	/**
	 * service bean name
	 */
	private String service;

	/**
	 * 主数据枚举 Class
	 */
	private Class<? extends BaseMasterDataEnum> enumClass;

	public ConverInfo(Field field, ConverType type, String[] targets, ConverPolicy policy) {
		this.field = field;
		this.type = type;
		this.targets = targets;
		this.policy = policy;
	}


	public ConverInfo(Field field, ConverType type, String[] targets, ConverPolicy policy, String service) {
		this.field = field;
		this.type = type;
		this.targets = targets;
		this.policy = policy;
		this.service = service;
	}

	public ConverInfo(Field field, ConverType type, String[] targets, ConverPolicy policy,
			Class<? extends BaseMasterDataEnum> enumClass) {
		this.field = field;
		this.type = type;
		this.targets = targets;
		this.policy = policy;
		this.enumClass = enumClass;
	}

	public ConverInfo(Field field, ConverType type, String[] targets, ConverPolicy policy, String service,
			Class<? extends BaseMasterDataEnum> enumClass) {
		this.field = field;
		this.type = type;
		this.targets = targets;
		this.policy = policy;
		this.service = service;
		this.enumClass = enumClass;
	}


	public Field getField() {
		return field;
	}


	public void setField(Field field) {
		this.field = field;
	}


	public ConverType getType() {
		return type;
	}


	public void setType(ConverType type) {
		this.type = type;
	}

	public String[] getSources() {
		return sources;
	}

	public void setSources(String[] sources) {
		this.sources = sources;
	}

	public String[] getTargets() {
		return targets;
	}

	public void setTargets(String[] targets) {
		this.targets = targets;
	}

	public ConverPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(ConverPolicy policy) {
		this.policy = policy;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public Class<? extends BaseMasterDataEnum> getEnumClass() {
		return enumClass;
	}


	public void setEnumClass(Class<? extends BaseMasterDataEnum> enumClass) {
		this.enumClass = enumClass;
	}


	@Override
	public String toString() {
		return "ConverInfo {field=" + field + ", type=" + type + ", targets=" + Arrays.toString(targets) + ", policy="
				+ policy + ", service=" + service + ", enumClass=" + enumClass + "}";
	}

}
