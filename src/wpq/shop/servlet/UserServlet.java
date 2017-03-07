package wpq.shop.servlet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wpq.shop.dao.IUserDao;
import wpq.shop.model.Auth;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.ShopException;
import wpq.shop.model.ShoppingCart;
import wpq.shop.model.User;
import wpq.shop.util.RequestUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private IUserDao userDao;

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Auth("any")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		boolean flag = RequestUtil.validate(User.class, req);
		if (flag) {
			User u = (User) RequestUtil.setParams(User.class, req);
			try {
				userDao.add(u);
			} catch (ShopException e) {
				return dealWithException(e, req);
			}
			return "redirect:goods.do?method=list";
		} else {
			return "/WEB-INF/user/addinput.jsp";
		}
	}

	@Auth
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean flag = RequestUtil.validate(User.class, req);
		User u = (User) RequestUtil.setParams(User.class, req);
		User ou = userDao.load(id);
		ou.setPassword(u.getPassword());
		ou.setNickname(u.getNickname());
		if (flag) {
			userDao.update(ou);
			return "redirect:goods.do?method=list";
		} else {
			req.setAttribute("u", ou);
			return "user.do?method=updateinput";
		}
	}
	
	@Auth
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		User u = userDao.load(id);
		req.setAttribute("u", u);
		return "/WEB-INF/user/show.jsp";
	}

	public String updateUserType(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		User u = userDao.load(id);
		int type = u.getType();
		if (type == 1) {
			u.setType(0);
		} else {
			u.setType(1);
		}
		userDao.update(u);
		return "redirect:user.do?method=list";
	}
	
	@Auth
	public String updateSelfInput(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("loginUser");
		req.setAttribute("u", u);
		return "/WEB-INF/user/updateinput.jsp";

	}
	
	@Auth
	public String updateinput(HttpServletRequest req, HttpServletResponse resp) {
		User ou = (User) req.getAttribute("u");
		if(ou!=null){
			req.setAttribute("u", ou);
		}else{
			int id = Integer.parseInt(req.getParameter("id"));
			User u = userDao.load(id);
			req.setAttribute("u", u);
		}
		return "/WEB-INF/user/updateinput.jsp";

	}

	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			userDao.delete(id);
		} catch (ShopException e) {
			return dealWithException(e, req);
		}
		return "redirect:user.do?method=list";

	}

	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Page<User> us = userDao.find("");
		req.setAttribute("us", us);
		return "/WEB-INF/user/list.jsp";
	}

	@Auth("any")
	public String addinput(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/user/addinput.jsp";
	}

	@Auth("any")
	public String logininput(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/user/logininput.jsp";
	}
	
	@Auth("any")
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			User lu = userDao.login(username, password);
			req.getSession().setAttribute("loginUser", lu);
			ShoppingCart sc = new ShoppingCart();
			req.getSession().setAttribute("sc", sc);
		} catch (ShopException e) {
			return super.dealWithException(e, req);
		}
		return "goods.do?method=list";
	}
	
	@Auth()
	public String quit(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		return "redirect:user.do?method=list";
	}

}
