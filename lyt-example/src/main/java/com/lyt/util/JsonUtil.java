package com.lyt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	
	/**
	 * 将数据转换为json数据进行统一处理，比如数据加密，数据加标记
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		return JSON.toJSONString(obj);
	}
	/**
	 * 将数据转换为json数据进行统一处理，比如数据加密，数据加标记(防止值为null 序列化后没有值)
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj, SerializerFeature s){
		return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 将json数据转换为JSONObject对象
	 * @see com.alibaba.fastjson.JSONObject
	 * @param json
	 * @return
	 */
	public static Object parseJson(String json){
		Object o = null;
		try {
			o = JSON.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o ;
	}
	
	/**
	 * 将json数据转换为指定的java对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> T parseJson(String json, Class<T> clazz){
		T o = null;
		try {
			o = JSON.parseObject(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	/**
	 * 将json数据转换为集合
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> parseJsonStr(String jsonStr, Class<T> clazz) {
		List<T> list = null;
		if(StringUtils.isNotBlank(jsonStr)){
			if(jsonStr.startsWith("{") && jsonStr.endsWith("}")){
				list = new ArrayList<T>();
				T t = JSON.parseObject(jsonStr,clazz);
				list.add(t);
			} else if (jsonStr.startsWith("[") && jsonStr.endsWith("]")){
				list = JSON.parseArray(jsonStr,clazz);
			}
		}
		return list;
	}

}
