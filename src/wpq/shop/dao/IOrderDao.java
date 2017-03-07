package wpq.shop.dao;

import wpq.shop.model.Order;
import wpq.shop.model.Page;

public interface IOrderDao {
	public void add(Order order);
	public void delete(int id);
	public void update(Order order);
	public Order load(int id);
	public Page<Order> find(String condition);
	public Page<Order> findByUserId(int userId);
	public Page<Order> findByUserIdAndCondition(String condition,int userId);
}
