package com.example.demo.service;

import java.io.OutputStream;
import java.io.Writer;


public interface JsonConverter {
	
	/**
	 * <b>功能描述：</b>json字符串转换为相应类型<br>
	 */
	public <T> T fromJson(String json, Class<T> resultType);
	
	/**
	 * <b>功能描述：</b>Object转换为json字符串<br>
	 */
	public String toJson(Object value);

	/**
	 * 美化打印的json
	 */
	public String toJsonPretty(Object value);
	
	/**
	 * <b>功能描述：</b>Object转换为json字符串, 写入到流<br>
	 */
	public void toJson(OutputStream out, Object value);
	
	/**
	 * <b>功能描述：</b>Object转换为json字符串, 写入到流<br>
	 */
	public void toJson(Writer out, Object value);
	
}
