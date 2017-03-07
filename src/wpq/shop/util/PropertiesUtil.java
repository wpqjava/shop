package wpq.shop.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties prop = null;
	
	public static Properties getProp(){
		if(prop==null){
			prop = new Properties();
			try {
				prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("dao.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
}
