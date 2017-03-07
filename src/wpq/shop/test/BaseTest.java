package wpq.shop.test;

import wpq.shop.util.DaoUtil;

public class BaseTest {
	public BaseTest() {
		DaoUtil.diDao(this);
	}
}
