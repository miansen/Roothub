package wang.miansen.roothub.common.beans;

import java.lang.reflect.Field;
import java.util.Arrays;

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
	 * 目标属性名数组
	 */
	private String[] targets;

	/**
	 * 转换的类型
	 */
	private ConverType type;

	/**
	 * 转换的策略
	 */
	private ConverPolicy strategy;

	/**
	 * service bean name
	 */
	private String service;

	public ConverInfo(Field field, String[] targets, ConverType type, ConverPolicy strategy) {
		this.field = field;
		this.targets = targets;
		this.type = type;
		this.strategy = strategy;
	}


	public ConverInfo(Field field, String[] targets, ConverType type, ConverPolicy strategy, String service) {
		this.field = field;
		this.targets = targets;
		this.type = type;
		this.strategy = strategy;
		this.service = service;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String[] getTargets() {
		return targets;
	}

	public void setTargets(String[] targets) {
		this.targets = targets;
	}

	public ConverType getType() {
		return type;
	}

	public void setType(ConverType type) {
		this.type = type;
	}

	public ConverPolicy getStrategy() {
		return strategy;
	}

	public void setStrategy(ConverPolicy strategy) {
		this.strategy = strategy;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "ConverInfo {field=" + field + ", targets=" + Arrays.toString(targets) + ", type=" + type + ", strategy="
				+ strategy + ", service=" + service + "}";
	}

}
