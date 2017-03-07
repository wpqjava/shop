package wpq.shop.test;

import static org.junit.Assert.*;

import org.junit.Test;

import wpq.shop.dao.AddressDao;
import wpq.shop.dao.BaseDao;
import wpq.shop.dao.UserDao;

public class TestBase {

	@Test
	public void test() {
		new UserDao();
	}

}
