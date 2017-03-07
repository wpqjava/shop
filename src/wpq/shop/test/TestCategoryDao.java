package wpq.shop.test;

import java.util.List;

import org.junit.Test;

import wpq.shop.dao.ICategoryDao;
import wpq.shop.model.Category;
import wpq.shop.model.ShopDi;

public class TestCategoryDao extends BaseTest  {
	
	private ICategoryDao categoryDao;
	
	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Test
	public void testAdd() {
		Category cg = new Category();
		cg.setName("测试");
		categoryDao.add(cg);
		testList();
	}
	
	@Test
	public void testDelete(){
		categoryDao.delete(4);
		testList();
	}
	@Test
	public void testUpdate(){
		Category cg = new Category();
		cg.setId(1);
		cg.setName("春");
		categoryDao.update(cg);
		cg.setId(2);
		cg.setName("夏");
		categoryDao.update(cg);
		cg.setId(3);
		cg.setName("秋");
		categoryDao.update(cg);
		cg.setId(5);
		cg.setName("冬");
		categoryDao.update(cg);
		testList();
	}
	@Test
	public void testLoad(){
		Category cg = categoryDao.load(1);
		System.out.println(cg);
	}
	@Test
	public void testList(){
		List<Category> ls = categoryDao.list();
		for(Category c:ls){
			System.out.println(c);
		}
	}

}
