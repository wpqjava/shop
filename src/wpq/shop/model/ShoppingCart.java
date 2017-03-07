package wpq.shop.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	private Map<Goods,Integer> scMap;
	private boolean isEmpty;
	


	public boolean getIsEmpty() {
		return isEmpty;
	}


	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}


	public ShoppingCart() {
		scMap = new HashMap<Goods,Integer>();
		isEmpty = true;
	}
	
	

	public Map<Goods, Integer> getScMap() {
		return scMap;
	}


	public void setScMap(Map<Goods, Integer> scMap) {
		this.scMap = scMap;
	}


	public void update(Goods g,int num){
		scMap.put(g, num);
	}
	
}
