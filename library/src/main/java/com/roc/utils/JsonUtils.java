package com.roc.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.roc.common.DebugLog;

/**
 * Json工具：Json数据和Java对象之间的转换
 * 
 * @author Mr.Zheng
 * @date 2014年7月15日10:24:52
 */
public class JsonUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 将对象序列化为JSON字符串
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	public static String serialize(Object object) {
		Writer write = new StringWriter();
		try {
			objectMapper.writeValue(write, object);
		} catch (JsonGenerationException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
		return write.toString();
	}

	/**
	 * 将JSON字符串反序列化为对象
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String json, Class<T> clazz) {
		Object object = null;
		try {
			object = objectMapper.readValue(json, TypeFactory.rawClass(clazz));
		} catch (JsonParseException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
		return (T) object;
	}

	/**
	 * 将JSON字符串反序列化为对象
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String json, TypeReference<T> typeRef) {
		try {
			return (T) objectMapper.readValue(json, typeRef);
		} catch (JsonParseException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
