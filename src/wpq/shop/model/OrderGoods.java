package wpq.shop.model;

public class OrderGoods {
	private int id;
	private Goods goods;
	private int num;
	private int orderId;
	
	

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "OrderGoods [id=" + id + ", goods=" + goods.getName() + ", num=" + num + ", orderId=" + orderId + "]";
	}

	
	
	
}
