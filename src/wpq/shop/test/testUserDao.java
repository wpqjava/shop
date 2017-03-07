package wpq.shop.test;

import org.junit.Test;

import wpq.shop.dao.IAddressDao;
import wpq.shop.dao.IUserDao;
import wpq.shop.dao.PropertiesDaoFactory;
import wpq.shop.model.Address;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.SystemContext;
import wpq.shop.model.User;
import wpq.shop.util.DaoUtil;

public class testUserDao extends BaseTest {
	public IUserDao ud;

	
	@ShopDi
	public void setUserDao(IUserDao ud) {
		this.ud = ud;
	}

	@Test
	public void testAdd() {
		User user = new User();
		user.setUsername("user19");
		user.setPassword("extend2");
		user.setNickname("extend2");
		ud.add(user);
	}

	@Test
	public void testDelete() {
		ud.delete(5);
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setId(2);
		user.setPassword("2.2");
		user.setNickname("2.2");
		ud.update(user);
	}

	@Test
	public void testLoad() {
		User u = ud.load(1);
		System.out.println(u);
		System.out.println("------------------------------");
		for (Address a : u.getAddresses()) {
			System.out.println(u + "     |||||     " + a);
		}
	}

	@Test
	public void testLogin() {
		User u = ud.login("admin", "admin");
		System.out.println(u);
	}

	@Test
	public void testFind() {
		SystemContext.setPageOffset(1);
		SystemContext.setPageSize(15);
		System.out.println(SystemContext.getPageSize());
	//	SystemContext.setOrder("desc");
	//	SystemContext.setSort("id");
		Page<User> p = ud.find("");
		System.out.println("-------------------------");
		for (User u : p.getDatas()) {
			System.out.println(u);
		}
		System.out.println(p.getTotalRecord());
	}
	
	@Test
	public void testLoadByUsername(){
		User u = ud.login("admin", "admin");
		System.out.println(u.getAddresses());
	}
}
