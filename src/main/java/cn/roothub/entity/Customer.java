package cn.roothub.entity;

import java.io.Serializable;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = -1545195873576249731L;

	private Integer id;
	
	private String name;
	
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
