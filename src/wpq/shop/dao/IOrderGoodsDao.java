package wpq.shop.dao;

import java.util.List;

import wpq.shop.model.OrderGoods;

public interface IOrderGoodsDao {
	public void add(OrderGoods og);
	
	public void delete(int id);
	public void deleteByOrderId(int orderId);
	public void update(OrderGoods og);
	
	public OrderGoods load(int id);
	public List<OrderGoods> loadByOrderId(int orderId);
}
