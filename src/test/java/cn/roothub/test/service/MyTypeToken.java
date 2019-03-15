package cn.roothub.test.service;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import cn.roothub.entity.SystemConfig;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-03-15
 */
public class MyTypeToken extends TypeToken<SystemConfig> {

	public static void main(String[] args) {
		MyTypeToken myTypeToken = new MyTypeToken();
		Type type2 = myTypeToken.getType();
		System.out.println(type2);
	}
}
