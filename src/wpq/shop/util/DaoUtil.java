package wpq.shop.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.util.Properties;

import wpq.shop.dao.PropertiesDaoFactory;
import wpq.shop.model.ShopDi;


public class DaoUtil {
	private static Properties prop = PropertiesUtil.getProp();
	
	public static void diDao00(Object obj){
		Method[] ms = obj.getClass().getDeclaredMethods();
		for(Method m:ms){
			if(m.getName().startsWith("set")){
				String mm = m.getName().substring(3);
				Object o = PropertiesDaoFactory.getInstance().getDao(mm);
				try {
					m.invoke(obj, o);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void diDao(Object obj){
		Method[] ms = obj.getClass().getDeclaredMethods();
		for(Method m:ms){
			if(m.isAnnotationPresent(ShopDi.class)){
				String v = m.getAnnotation(ShopDi.class).value();
				if(v==null||"".equals(v.trim()))v = m.getName().substring(3).substring(0,1).toLowerCase()+ m.getName().substring(4);
				try {
					m.invoke(obj, PropertiesDaoFactory.getInstance().getDao(v));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
/*	public static IDaoFactory createDaoFactory(){
		String factory = prop.getProperty("factory");
		IDaoFactory f = null;
		try {
			@SuppressWarnings("rawtypes")
			Class clz = Class.forName(factory);
			String mm = "getInstance";
			@SuppressWarnings("unchecked")
			Method m = clz.getMethod(mm);
			f = (IDaoFactory)m.invoke(clz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return f;
	}*/
	
	public static void deleteFile(String fileName){
		File f = new File(RequestUtil.UPLOADPATH+"\\upload\\"+fileName);
		f.delete();
	}
}
