package wpq.shop.servlet;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wpq.shop.dao.IAddressDao;
import wpq.shop.dao.IUserDao;
import wpq.shop.model.Address;
import wpq.shop.model.Auth;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.ShopException;
import wpq.shop.model.User;
import wpq.shop.util.RequestUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/address.do")
public class AddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private IAddressDao addressDao;
	private IUserDao userDao;

	@ShopDi
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Auth()
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		int userId = Integer.parseInt(req.getParameter("userId"));
		boolean flag = RequestUtil.validate(Address.class, req);
		if (flag) {
			Address ad = (Address) RequestUtil.setParams(Address.class, req);
			try {
				addressDao.add(ad, userId);
			} catch (ShopException e) {
				return dealWithException(e, req);
			}
			return "redirect:user.do?method=show&id="+userId;
		} else {
			return "/WEB-INF/address/addinput.jsp";
		}
	}

	@Auth
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		Address ad = (Address) RequestUtil.setParams(Address.class, req);
		ad.setName(req.getParameter("name"));
		ad.setPhone(req.getParameter("phone"));
		ad.setUser(userDao.load(userId));
		req.setAttribute("address", ad);
		boolean flag = RequestUtil.validate(Address.class, req);
		if (flag) {
			Address oad = addressDao.load(id);
			oad.setName(ad.getName());
			oad.setPhone(ad.getPhone());
			addressDao.update(ad);
			return "redirect:user.do?method=show&id="+userId;
		} else {
			return "/WEB-INF/address/updateinput.jsp";
		}
	}
	
/*	@Auth
	public String updateSelfInput(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("loginUser");
		req.setAttribute("u", u);
		return "/WEB-INF/user/updateinput.jsp";

	}*/
	
	@Auth
	public String updateinput(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Address ad = addressDao.load(id);
		req.setAttribute("address", ad);
		return "/WEB-INF/address/updateinput.jsp";

	}
	
	@Auth
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int userId = ((User)req.getSession().getAttribute("loginUser")).getId();
		int id = Integer.parseInt(req.getParameter("id"));
		addressDao.delete(id);
		return "redirect:user.do?method=show&id="+userId;

	}

	@Auth()
	public String addinput(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/address/addinput.jsp";
	}

}
