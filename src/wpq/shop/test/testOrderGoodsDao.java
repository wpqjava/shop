package wpq.shop.test;

import static org.junit.Assert.*;


import org.junit.Test;

import wpq.shop.dao.IGoodsDao;
import wpq.shop.dao.IOrderGoodsDao;
import wpq.shop.model.OrderGoods;
import wpq.shop.model.ShopDi;

public class testOrderGoodsDao extends BaseTest {
	
	private IOrderGoodsDao orderGoodsDao;
	private IGoodsDao goodsDao;
	
	
	@ShopDi
	public void setOrderGoodsDao(IOrderGoodsDao orderGoodsDao) {
		this.orderGoodsDao = orderGoodsDao;
	}
	
	@ShopDi
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	@Test
	public void testAdd() {
		OrderGoods og = new OrderGoods();
		og.setGoods(goodsDao.load(4));
		og.setOrderId(2);
		og.setNum(33);
		orderGoodsDao.add(og);
		//testload();
	}
	
	@Test
	public void testDelete() {
		orderGoodsDao.delete(2);
	}
	
	@Test
	public void testDeleteByOrderId() {
		orderGoodsDao.deleteByOrderId(1);;
	}

	@Test
	public void testUpdate() {
		OrderGoods og = new OrderGoods();
		og.setGoods(goodsDao.load(4));
		og.setNum(333);
		og.setOrderId(1);
		og.setId(1);
		orderGoodsDao.update(og);
		testload();
	}
	
	@Test
	public void testload() {
		OrderGoods og = orderGoodsDao.load(2);
		System.out.println("名称："+og.getGoods().getName()+"类型："+og.getGoods().getCategory().getName());
	}
	
	@Test
	public void testTest(){
		System.out.println("1111111111111111");
	}

}
