/**
 * 
 */
package com.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建者：qjt 修改时间：2015-5-28 下午2:43:20 作用：
 */

public class BeanResolveUtils {

	public static Map<String, String> fillParams(Object bean) {
		Map<String, String> params = new HashMap<String, String>();
		int setCount = 0;
		int getCount = 0;
		try {
			List<String> keyList = new ArrayList<String>();
			Class<?> clz = bean.getClass();
			
			Field[] fields = clz.getDeclaredFields();
			
			try {
				for(Field field : fields){
					keyList.add(field.getName());
					Thread.sleep(5);
				}
				Method[] methods = clz.getMethods();
				
				for (Method method : methods) {
					String methodName = method.getName();
					if (methodName.startsWith("set")
							|| methodName.startsWith("get")) {
						if (methodName.startsWith("get") && !methodName.startsWith("getClass")) {
							String value = "";
							if(method.invoke(bean)!=null){
								value = method.invoke(bean).toString();
							}
							for(String str:keyList){
								String name = methodName.substring(3, methodName.length());
								if(name.equalsIgnoreCase(str)){
									params.put(str, value);
								}
							}
							getCount++;
							Thread.sleep(5);
						}else if(methodName.startsWith("set")){
							setCount++;
						}
					}
					Thread.sleep(5);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(setCount != getCount){
				throw new IllegalAccessException();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		return params;
	}

}
