package wpq.shop.model;

public class Address {
	private int id;
	@ValidateForm(type=ValidateType.NOTNULL, errorMsg = "地址不能为空")
	private String name;
	@ValidateForm(type=ValidateType.NUMBER, errorMsg = "电话号码必须为数字")
	private String phone;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", phone=" + phone ;
	}

}
