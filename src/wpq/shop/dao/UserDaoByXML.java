package wpq.shop.dao;

import wpq.shop.model.Page;
import wpq.shop.model.User;

public class UserDaoByXML implements IUserDao {

	@Override
	public void add(User user) {
		System.out.println("Xml");
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> find(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
