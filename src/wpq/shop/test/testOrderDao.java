package wpq.shop.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import wpq.shop.dao.IAddressDao;
import wpq.shop.dao.IOrderDao;
import wpq.shop.dao.IOrderGoodsDao;
import wpq.shop.dao.IUserDao;
import wpq.shop.model.Order;
import wpq.shop.model.OrderGoods;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.SystemContext;

public class testOrderDao extends BaseTest{

	private IOrderDao orderDao;
	private IOrderGoodsDao orderGoodsDao;
	private IUserDao userDao;
	private IAddressDao addressDao;
	
	@ShopDi
	public void setOrderGoodsDao(IOrderGoodsDao orderGoodsDao) {
		this.orderGoodsDao = orderGoodsDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@ShopDi
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}


	@ShopDi
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Test
	public void testAdd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Order o = new Order();
		o.setBuyDate(new Date());
		o.setUser(userDao.load(1));
		o.setAddress(addressDao.load(1));
		orderDao.add(o);
	}

	@Test
	public void testDelete() {
		orderDao.delete(5);
	}

	@Test
	public void testUpdate() {
		Order o = orderDao.load(1);
		o.setStatus(0);
		orderDao.update(o);
	}
	
	@Test
	public void testLoad(){
		Order o = orderDao.load(2);
		List<OrderGoods> ls = new ArrayList<OrderGoods>();
		ls = orderGoodsDao.loadByOrderId(2);
		System.out.println("Order对象："+o);
		System.out.println(o.getUser().getNickname()+";"+o.getAddress().getName()+";"+o.getBuyDate());
		for(OrderGoods og:ls){
			System.out.println(og.getGoods().getName()+";"+og.getNum());
		}
	}
	
	@Test
	public void testFind(){
		SystemContext.setPageOffset(0);
		SystemContext.setPageSize(15);
		Page<Order> p = orderDao.findByUserId(1);
		List<Order> ls = p.getDatas();
		for(Order o:ls){
			System.out.println(o.getUser().getNickname()+";"+o.getAddress().getName()+";"+o.getBuyDate());
		}
	}

}
