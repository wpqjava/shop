package wpq.shop.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import wpq.shop.model.ValidateForm;
import wpq.shop.model.ValidateType;

public class RequestUtil {
	
	public static final String UPLOADPATH = "D:\\CodeSoftware\\STS\\workspace\\shop03\\WebContent";
	public static final String[] ALLOWEDExt = new String[]{"jpg","bmp"};
	
	public static boolean validate(Class clz,HttpServletRequest req){
		Field[] fs = clz.getDeclaredFields();
		boolean flag = true;
		Map<String, String> errors = (Map<String, String>) req.getAttribute("errors");
		for(Field f:fs){
			if(f.isAnnotationPresent(ValidateForm.class)){
				ValidateType type = f.getAnnotation(ValidateForm.class).type();
				if(type==ValidateType.NOTNULL){
					boolean b = validateNotNull(f.getName(),req);
					if(!b){
						errors.put(f.getName(), f.getAnnotation(ValidateForm.class).errorMsg());
						flag = false;
					}
				}else if(type==ValidateType.LENGTH){
					boolean b = validateLength(f.getName(),f.getAnnotation(ValidateForm.class).value(),req);
					if(!b){
						errors.put(f.getName(), f.getAnnotation(ValidateForm.class).errorMsg());
						flag = false;
					}
				}else if(type==ValidateType.NUMBER){
					boolean b = validateNumber(f.getName(),req);
					if(!b){
						errors.put(f.getName(), f.getAnnotation(ValidateForm.class).errorMsg());
						flag = false;
					}
				}
			}
		}
		return flag;
	}


	private static boolean validateNumber(String name, HttpServletRequest req) {
		try {
			Double.parseDouble(req.getParameter(name));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}


	private static boolean validateLength(String name,int value ,HttpServletRequest req) {
		String str = req.getParameter(name);
		if(str.length() < value){
			return false;
		}
		return true;
	}


	private static boolean validateNotNull(String name, HttpServletRequest req) {
		if(!req.getParameterMap().containsKey(name))return true;
		String str = req.getParameter(name);
		if(str==null||"".equals(str.trim())){
			return false;
		}
		return true;
	}


	public static Object setParams(Class clz,HttpServletRequest req){
		Map<String,String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		Object o = null;
		try {
			o = clz.newInstance();
			for(String key:keys){
				String[] v = params.get(key);
				if(v.length>1){
					BeanUtils.copyProperty(o, key, v);
				}else{
					BeanUtils.copyProperty(o, key, v[0]);
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static void upload(byte[] fs,String fileName){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(UPLOADPATH+"/upload/"+fileName);
			fos.write(fs,0,fs.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fos!=null)fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
