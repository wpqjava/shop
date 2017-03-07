package wpq.shop.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wpq.shop.dao.ICategoryDao;
import wpq.shop.model.Auth;
import wpq.shop.model.Category;
import wpq.shop.model.ShopDi;
import wpq.shop.model.ShopException;
import wpq.shop.util.RequestUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/category.do")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ICategoryDao categoryDao;
	
	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Auth("any")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		List<Category> lists = new ArrayList<Category>();
		String condition = req.getParameter("condition");
		if(condition==null||"".equals(condition.trim())){
			lists = categoryDao.list();
		}else{
			lists = categoryDao.list(condition);
		}
		req.setAttribute("lists", lists);
		return "/WEB-INF/category/list.jsp";
	}
	
	@Auth("any")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		Category c = categoryDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("c", c);
		return "/WEB-INF/category/show.jsp";
	}
	
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		categoryDao.delete(id);
		return "redirect:category.do?method=list";
	}
	
	public String updateinput(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Category c = categoryDao.load(id);
		req.setAttribute("c", c);
		return "/WEB-INF/category/updateinput.jsp";
	}
	
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean flag = RequestUtil.validate(Category.class, req);
		Category c = (Category) RequestUtil.setParams(Category.class, req);
		if(flag){
			categoryDao.update(c);
			return "redirect:category.do?method=list";
		}else{
			req.setAttribute("c", c);
			return "/WEB-INF/category/updateinput.jsp";
		}
	}
	
	public String addinput(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/category/addinput.jsp";
	}
	
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		boolean flag = RequestUtil.validate(Category.class, req);
		Category c = (Category) RequestUtil.setParams(Category.class, req);
		if(flag){
			categoryDao.add(c);
			return "redirect:category.do?method=list";
		}else{
			req.setAttribute("c", c);
			return "/WEB-INF/category/addinput.jsp";
		}
	}

}
