package wpq.shop.model;

import java.util.Date;
import java.util.List;

public class Order {
	private int id;
	private Date buyDate;
	private Date payDate;
	private Date confirmDate;
	@ValidateForm(type=ValidateType.NUMBER,errorMsg="价钱必须为数字")
	private double price;
	private int status;//0是取消，1是未付款，2是已付款，3是已发货，4是确认收货
	private User user;
	private Address address;
	private List<OrderGoods> orderGoodses;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<OrderGoods> getOrderGoodses() {
		return orderGoodses;
	}

	public void setOrderGoodses(List<OrderGoods> orderGoodses) {
		this.orderGoodses = orderGoodses;
	}

	//@Override
/*	public String toString() {
		return "Order [id=" + id + ", buyDate=" + buyDate + ", payDate=" + payDate + ", confirmDate=" + confirmDate
				+ ", status=" + status + ", user=" + user.getUsername() + ", address=" + address.getName()
				
				+ "]";
	}*/

	
}
