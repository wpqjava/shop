package wpq.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import wpq.shop.model.Address;
import wpq.shop.model.Page;
import wpq.shop.model.ShopException;
import wpq.shop.model.SystemContext;
import wpq.shop.model.User;
import wpq.shop.util.MybatisUtil;

public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public void add(User user) {
		User u = loadByUsername(user.getUsername());
		if(u!=null)throw new ShopException("用户名已存在");
		super.add(user);
	}

	@Override
	public void delete(int id) {
		List<Address> ad = load(id).getAddresses();
		if(!ad.isEmpty())throw new ShopException("请先清空该用户的地址");
		super.delete(User.class, id);
	}

	@Override
	public void update(User user) {
		//TODO 不存在判断还未做
		super.update(user);
	}

	@Override
	public User load(int id) {
		Map<String,Object> params = new HashMap<>();
		params.put("id", id);
		return super.load(User.class, params);
	}

	@Override
	public Page<User> find(String condition) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(condition!=null&&!"".equals(condition))params.put("name", "%"+condition+"%");//!!!mybatis中模糊查询不需要加' '号
		return super.find(User.class, params);
	}

	@Override
	public User login(String username, String password) {
		User u = loadByUsername(username);
		if(u==null)throw new ShopException("用户名不存在");
		if(!u.getPassword().equals(password))throw new ShopException("密码错误");
		return u;
	}
	
	public User loadByUsername(String username) {
		SqlSession session=null;
		User u =null;
		try {
			session = MybatisUtil.createSqlSession();
			u = (User)session.selectOne(User.class.getName()+".loadByUsername",username);
		} catch (Exception e) {
			session.rollback();
		}finally{
			MybatisUtil.closeSqlSession(session);
		}
		return u;
	}

}
