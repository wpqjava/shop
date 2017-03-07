package wpq.shop.dao;

import wpq.shop.model.Page;
import wpq.shop.model.User;

public interface IUserDao {

	void add(User user);

	void delete(int id);

	void update(User user);

	User load(int id);

	Page<User> find(String condition);

	User login(String username, String password);

}
