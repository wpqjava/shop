package wpq.shop.model;

public class Category {
	private int id;
	@ValidateForm(type=ValidateType.NOTNULL,errorMsg="类别名不能为空")
	private String name;

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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	
}
