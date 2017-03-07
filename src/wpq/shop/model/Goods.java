package wpq.shop.model;

public class Goods {
	private int id;
	@ValidateForm(type=ValidateType.NOTNULL,errorMsg="商品名称不能为空")
	private String name;
	@ValidateForm(type=ValidateType.NUMBER,errorMsg="商品价格必须为数字")
	private double price;
	private String intro;
	private String img;
	@ValidateForm(type=ValidateType.NUMBER,errorMsg="商品库存必须为数字")
	private int stock;
	/**
	 * return 1为上架，0为下架
	 */
	private int status;//1为上架，0为下架
	private Category category;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intor) {
		this.intro = intor;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", intor=" + intro + ", img=" + img
				+ ", stock=" + stock + ", status=" + status + ", category=" + category.getName() + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(((Goods)obj).getId()==this.id)return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 117;
	}

}
