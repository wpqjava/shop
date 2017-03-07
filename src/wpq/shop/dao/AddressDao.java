package wpq.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wpq.shop.model.Address;
import wpq.shop.model.ShopDi;
import wpq.shop.model.User;
import wpq.shop.util.DaoUtil;

public class AddressDao extends BaseDao<Address> implements IAddressDao {
	private IUserDao userDao;
	
	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(Address address,int userId) {
		User u = userDao.load(userId);
		address.setUser(u);
		super.add(address);
	}

	@Override
	public void delete(int id) {
		super.delete(Address.class, id);
	}

	@Override
	public void update(Address address) {
		super.update(address);
	}

	@Override
	public Address load(int id) {
		Map<String,Object> params = new HashMap<>();
		params.put("id", id);
		return super.load(Address.class, params);
	}

	@Override
	public List<Address> list(int userId) {
		return super.list(Address.class, userId);
	}

}
