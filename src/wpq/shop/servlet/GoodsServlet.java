package wpq.shop.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wpq.shop.dao.ICategoryDao;
import wpq.shop.dao.IGoodsDao;
import wpq.shop.model.Auth;
import wpq.shop.model.Category;
import wpq.shop.model.Goods;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.ShopException;
import wpq.shop.util.DaoUtil;
import wpq.shop.util.RequestUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/goods.do")
public class GoodsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private ICategoryDao categoryDao;
	
	private IGoodsDao goodsDao;
	
	@ShopDi
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Auth("any")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Page<Goods> ls = null;
		String condition = req.getParameter("condition");
		if(condition==null||"".equals(condition.trim())){
			ls = goodsDao.find(0, -1,null);
		}else{
			ls = goodsDao.find(0, -1,condition);
		}
		req.setAttribute("ls", ls);
		return "/WEB-INF/goods/list.jsp";
	}
	
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		boolean flag = RequestUtil.validate(Goods.class, req);
		int cid = Integer.parseInt(req.getParameter("cid"));
		Goods g = (Goods)RequestUtil.setParams(Goods.class, req);
		if(cid==0){
			Map<String,String> errors = (Map<String, String>) req.getAttribute("errors");
			errors.put("cid", "请选择商品类别");
			flag = false;
		}
		if(super.hasErrors())flag = false;
		if(flag){
			byte[] fs = (byte[]) req.getAttribute("fs");
			String fileName = (String)req.getParameter("fileName");
			RequestUtil.upload(fs, fileName);
			goodsDao.add(cid, g);
			return RED+"goods.do?method=list";
		}else{
			return addinput(req, resp);
		}
	}
	
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			goodsDao.delete(id);
		} catch (ShopException e) {
			return super.dealWithException(e, req);
		}
		return RED+"goods.do?method=list";
	}
	
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		boolean flag = RequestUtil.validate(Goods.class, req);
		int cid = Integer.parseInt(req.getParameter("cid"));
		Goods g = (Goods)RequestUtil.setParams(Goods.class, req);
		Goods og = goodsDao.load(Integer.parseInt(req.getParameter("id")));
		og.setName(g.getName());
		og.setPrice(g.getPrice());
		og.setStock(g.getStock());
		og.setIntro(g.getIntro());
		if(cid==0){
			Map<String,String> errors = (Map<String, String>) req.getAttribute("errors");
			errors.put("cid", "请选择商品类别");
			flag = false;
		}
		if(super.hasErrors())flag = false;
		if(flag){
			String img = req.getParameter("img");
			if(img==null||"".equals(img.trim())){
			}else{
				String ofn = og.getImg();
				DaoUtil.deleteFile(ofn);
				byte[] fs = (byte[]) req.getAttribute("fs");
				String fileName = (String)req.getParameter("fileName");
				RequestUtil.upload(fs, fileName);
				og.setImg(fileName);
			}
			goodsDao.update(cid,og);
			return RED+"goods.do?method=list";
		}else{
			og.setCategory(categoryDao.load(cid));
			req.setAttribute("g", og);
			req.setAttribute("lists", categoryDao.list());
			return "/WEB-INF/goods/updateinput.jsp";
		}
	}
	
	public String updateinput(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Goods g = goodsDao.load(id);
		req.setAttribute("lists", categoryDao.list());
		req.setAttribute("g", g);
		return "/WEB-INF/goods/updateinput.jsp";
	}
	
	
	public String addinput(HttpServletRequest req, HttpServletResponse resp) {
		List<Category> lists = categoryDao.list();
		req.setAttribute("lists", lists);
		return "/WEB-INF/goods/addinput.jsp";
	}
	
	public String updatestatus(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Goods g = goodsDao.load(id);
		if(g.getStatus()==1){
			g.setStatus(0);
		}else{
			g.setStatus(1);
		}
		goodsDao.update(g.getCategory().getId(),g);
		return RED+"goods.do?method=list";
	}
	
	public String updatestockinput(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Goods g = goodsDao.load(id);
		req.setAttribute("g", g);
		return "/WEB-INF/goods/updatestockinput.jsp";
	}
	
	public String updatestock(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Goods g = goodsDao.load(id);
			g.setStock(Integer.parseInt(req.getParameter("stock")));
			goodsDao.update(g.getCategory().getId(),g);
		} catch (NumberFormatException e) {
			return super.dealWithException(new ShopException("库存必须是整数"), req);
		}
		return RED+"goods.do?method=list";
	}
	
	@Auth("any")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Goods g = goodsDao.load(id);
		req.setAttribute("g", g);
		return "/WEB-INF/goods/show.jsp";
	}

}
