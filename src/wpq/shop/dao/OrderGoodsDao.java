package wpq.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wpq.shop.model.OrderGoods;

public class OrderGoodsDao extends BaseDao<OrderGoods> implements IOrderGoodsDao {

	@Override
	public void add(OrderGoods og) {
		super.add(og);
	}

	@Override
	public void delete(int id) {
		super.delete(OrderGoods.class, id);
	}

	@Override
	public void update(OrderGoods og) {
		super.update(og);
	}

	@Override
	public OrderGoods load(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return super.load(OrderGoods.class, params);
	}

	@Override
	public void deleteByOrderId(int orderId) {
		super.deleteByOrderId(OrderGoods.class, orderId);
	}

	@Override
	public List<OrderGoods> loadByOrderId(int orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return super.loadByOrderId(OrderGoods.class, params);
	}

}
