package wpq.shop.dao;

import java.util.HashMap;
import java.util.Map;

import wpq.shop.model.Order;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;

public class OrderDao extends BaseDao<Order> implements IOrderDao {
	public IOrderGoodsDao orderGoodsDao;
	
	public IOrderGoodsDao getOrderGoodsDao() {
		return orderGoodsDao;
	}

	@ShopDi
	public void setOrderGoodsDao(IOrderGoodsDao orderGoodsDao) {
		this.orderGoodsDao = orderGoodsDao;
	}

	@Override
	public void add(Order order) {
		super.add(order);
	}

	@Override
	public void delete(int id) {
		orderGoodsDao.deleteByOrderId(id);
		super.delete(Order.class, id);
	}
	

	@Override
	public void update(Order order) {

		super.update(order);
	}

	@Override
	public Order load(int id) {

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return super.load(Order.class, params);
	}

	@Override
	public Page<Order> find(String condition) {
		return findByUserIdAndCondition(condition, 0);
	}
	
	@Override
	public Page<Order> findByUserIdAndCondition(String condition,int userId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		if(condition==null||"".equals(condition)){
			params.put("condition", null);
		}else{
			params.put("condition", "%"+condition+"%");
		}
		return super.find(Order.class, params);
	}
	
	@Override
	public Page<Order> findByUserId(int userId) {
		return findByUserIdAndCondition(null, userId);
	}

}
