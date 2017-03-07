package wpq.shop.dao;

import java.util.List;

import wpq.shop.model.Category;

public interface ICategoryDao {
	public void add(Category category);
	public void delete(int id);
	public void update(Category category);
	public Category load(int id);
	public List<Category> list();
	public List<Category> list(String name);
}
