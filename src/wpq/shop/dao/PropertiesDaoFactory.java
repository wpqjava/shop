package wpq.shop.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import wpq.shop.util.PropertiesUtil;

public class PropertiesDaoFactory  {
	private static PropertiesDaoFactory f = new PropertiesDaoFactory();
	private static Map<String,Object> maps = new HashMap<String,Object>();
	private PropertiesDaoFactory() {
	}

	public static PropertiesDaoFactory getInstance(){
		return f;
	}
	
	public Object getDao(String name) {
		try{
			if(maps.containsKey(name))return maps.get(name);
			Properties prop = PropertiesUtil.getProp();
			String n = prop.getProperty(name);
			Object obj = Class.forName(n).newInstance();
			maps.put(name, obj);
			return obj;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
