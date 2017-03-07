package wpq.shop.test;

import java.util.List;

import org.junit.Test;

import wpq.shop.dao.BaseDao;
import wpq.shop.dao.IAddressDao;
import wpq.shop.dao.PropertiesDaoFactory;
import wpq.shop.dao.UserDao;
import wpq.shop.model.Address;
import wpq.shop.model.ShopDi;
import wpq.shop.util.DaoUtil;

public class testAddressDao extends BaseTest {
	private IAddressDao ad;

	public IAddressDao getAd() {
		return ad;
	}
	@ShopDi
	public void setAddressDao(IAddressDao ad) {
		this.ad = ad;
	}

	@Test
	public void tessAdd() {
		System.out.println("addressdao是否存在:" + ad);
		Address add = new Address();
		add.setName("杭州市byPropertiesDaoFactory依赖注入");
		add.setPhone("18698575951byPropertiesDaoFactory依赖注入");
		ad.add(add, 1);
	}

	@Test
	public void testDelete() {
		ad.delete(2);
	}

	@Test
	public void tessUpdate() {
		Address add = new Address();
		add.setId(4);
		add.setName("杭州市余杭区临平byDaoUtilandUpdate");
		add.setPhone("18698575951byDaoUtilandUpdae");
		ad.update(add);
	}

	@Test
	public void testLoad() {
		Address add = ad.load(1);
		System.out.println(add.getUser());
	}

	@Test
	public void testList() {
		List<Address> lists = ad.list(1);
		for (Address a : lists) {
			System.out.println(a + "||||" + a.getUser());
		}
	}

	@Test
	public void testOnly() {
		IAddressDao ad1 = (IAddressDao) PropertiesDaoFactory.getInstance().getDao("AddressDao");
		IAddressDao ad2 = (IAddressDao) PropertiesDaoFactory.getInstance().getDao("AddressDao");
		System.out.println(ad1 == ad2);
	}

}
