package wpq.shop.dao;

import wpq.shop.model.Goods;
import wpq.shop.model.Page;

public interface IGoodsDao {
	public void add(int cid,Goods goods);
	
	public void delete(int id);
	
	public void update(int cid,Goods goods);
	
	public Goods load(int id);
	
	public Page<Goods> find(int cid,int status,String condition);
	
	public void addStock(int id,int num);
	
	public void decreseStock(int id,int num);
}
