package wpq.shop.dao;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import wpq.shop.model.Category;
import wpq.shop.model.Goods;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.ShopException;
import wpq.shop.model.SystemContext;
import wpq.shop.util.DaoUtil;

public class GoodsDao extends BaseDao<Goods> implements IGoodsDao {

	private ICategoryDao categoryDao;

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void add(int cid, Goods goods) {
		Category c = categoryDao.load(cid);
		if(c==null)throw new ShopException("要添加的商品类别不存在");
		goods.setCategory(c);
		goods.setStatus(1);
		super.add(goods);
	}

	@Override
	public void delete(int id) {
		Goods g = load(id);
		if(g.getStatus()==1)throw new ShopException("上架的商品不能删除");
		String fileName = g.getImg();
		DaoUtil.deleteFile(fileName);
		super.delete(Goods.class, id);
	}

	@Override
	public void update(int cid,Goods goods) {
		Category c = categoryDao.load(cid);
		goods.setCategory(c);
		super.update(goods);
	}

	@Override
	public Goods load(int id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return super.load(Goods.class, params);
	}

	@Override
	public Page<Goods> find(int cid,int status ,String condition) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(cid!=0){
			params.put("cid", cid);
		}else{
			params.put("cid", null);
		}
		if(status!=-1){
			params.put("status", status);
		}else{
			params.put("status",null);
		}
		if(condition!=null){
			params.put("condition", "%"+condition+"%");
		}else{
			params.put("condition", null);
		}
		return super.find(Goods.class, params);
	}

	@Override
	public void addStock(int id,int num) {
		Goods g = load(id);
		g.setStock(g.getStock()+num);
		update(g);
	}

	@Override
	public void decreseStock(int id,int num) {
		Goods g = load(id);
		if((g.getStock()-num)<0)throw new ShopException("库存不足");
		if((g.getStock()-num)==0){
			g.setStatus(0);
		}
		g.setStock(g.getStock()-num);
		update(g);
	}

}
