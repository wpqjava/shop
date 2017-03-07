package wpq.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wpq.shop.model.Category;

public class CategoryDao extends BaseDao<Category> implements ICategoryDao {

	@Override
	public void add(Category category) {
		super.add(category);
	}

	@Override
	public void delete(int id) {
		// TODO 判断是否存在商品
		super.delete(Category.class, id);
	}

	@Override
	public void update(Category category) {
		super.update(category);
	}

	@Override
	public Category load(int id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return super.load(Category.class, params);
	}

	@Override
	public List<Category> list() {
		return list(null);
	}

	@Override
	public List<Category> list(String name) {
		Map<String, Object> params = new HashMap<String,Object>();
		if(name==null||"".equals(name.trim())){
			params.put("name", null);
		}else{
			params.put("name", "%"+name+"%");

		}
		return (List<Category>) super.listByCondition(Category.class, params);
	}

}
