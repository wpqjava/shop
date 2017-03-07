package wpq.shop.servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wpq.shop.dao.ICategoryDao;
import wpq.shop.dao.IGoodsDao;
import wpq.shop.model.Auth;
import wpq.shop.model.Goods;
import wpq.shop.model.ShopDi;
import wpq.shop.model.ShopException;
import wpq.shop.model.ShoppingCart;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/sc.do")
public class ShoppingCartServlet extends BaseServlet {
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
	
	@Auth
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Goods g = goodsDao.load(id);
		int stock = g.getStock();
		if(stock<1) return dealWithException(new ShopException("商品库存不足"), req);
		ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("sc");
		Map<Goods,Integer> scMap = sc.getScMap();
		if(scMap.containsKey(g)){
			int num = scMap.get(g)+1;
			if(num>stock)return dealWithException(new ShopException("商品库存不足"), req);
			sc.setIsEmpty(false);
			scMap.put(g, scMap.get(g)+1);
		}else{
			sc.setIsEmpty(false);
			scMap.put(goodsDao.load(id), 1);
		}
		return RED+"sc.do?method=show";
	}
	
	
	@Auth
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			int num = Integer.parseInt(req.getParameter("num"));
			if(num<=0)throw new ShopException("商品数量不正确请填写一个正整数");
			Goods g = goodsDao.load(id);
			int stock = g.getStock();
			ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("sc");
			Map<Goods,Integer> scMap = sc.getScMap();
			if(num>stock)return dealWithException(new ShopException("商品库存不足"), req);
			scMap.put(g, num);
			return RED+"sc.do?method=show";
		} catch (NumberFormatException e) {
			return super.dealWithException(new ShopException("数量必须为数字"), req);
		} catch (ShopException e){
			return super.dealWithException(e, req);
		}
	}
	
	@Auth
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
			int id = Integer.parseInt(req.getParameter("id"));
			Goods g = goodsDao.load(id);
			ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("sc");
			Map<Goods,Integer> scMap = sc.getScMap();
			scMap.remove(g);
			if(scMap.isEmpty())sc.setIsEmpty(true);
			return RED+"sc.do?method=show";
	}
	
	@Auth
	public String removeAll(HttpServletRequest req, HttpServletResponse resp) {
			ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("sc");
			Map<Goods,Integer> scMap = sc.getScMap();
			scMap.clear();
			sc.setIsEmpty(true);
			return RED+"sc.do?method=show";
	}
	
	
	@Auth()
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/shoppingCart/show.jsp";
	}

}
