package wpq.shop.test;

import java.util.List;

import org.junit.Test;

import wpq.shop.dao.ICategoryDao;
import wpq.shop.dao.IGoodsDao;
import wpq.shop.model.Goods;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.SystemContext;

public class testGoodsDao extends BaseTest{

	private IGoodsDao goodsDao;
	private ICategoryDao categoryDao;

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@ShopDi
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	@Test
	public void testAdd() {
		Goods g = new Goods();
		g.setName("good02");
		g.setPrice(100);
		g.setIntro("good02");
		g.setImg("/goods/good02");
		g.setStock(100);
		g.setStatus(0);
		goodsDao.add(1, g);
	}

	@Test
	public void testDelete() {
		goodsDao.delete(3);
	}

	@Test
	public void testUpdate() {
		Goods g = goodsDao.load(3);
		System.out.println(3);
		g.setStatus(0);
	}
	
	@Test
	public void testLoad(){
		Goods g = goodsDao.load(4);
		System.out.println(g);
	}
	
	@Test
	public void testFind(){
		SystemContext.setPageOffset(0);
		SystemContext.setPageSize(15);
		Page<Goods> p = goodsDao.find(0, -1, null);
		List<Goods> ls = p.getDatas();
		for(Goods g:ls){
			System.out.println(g.getId()+" ; 商品名"+g.getName()+" ; 类型:"+g.getCategory().getName());
		}
	}

}
