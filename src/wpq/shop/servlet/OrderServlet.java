package wpq.shop.servlet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wpq.shop.dao.IAddressDao;
import wpq.shop.dao.ICategoryDao;
import wpq.shop.dao.IGoodsDao;
import wpq.shop.dao.IOrderDao;
import wpq.shop.dao.IOrderGoodsDao;
import wpq.shop.model.Address;
import wpq.shop.model.Auth;
import wpq.shop.model.Goods;
import wpq.shop.model.Order;
import wpq.shop.model.OrderGoods;
import wpq.shop.model.Page;
import wpq.shop.model.ShopDi;
import wpq.shop.model.ShopException;
import wpq.shop.model.ShoppingCart;
import wpq.shop.model.User;
import wpq.shop.util.RequestUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/order.do")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private IOrderDao orderDao;
	private IOrderGoodsDao orderGoodsDao;
	private ICategoryDao categoryDao;
	private IGoodsDao goodsDao;
	private IAddressDao addressDao;
	@ShopDi
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}
	@ShopDi
	public void setOrderGoodsDao(IOrderGoodsDao orderGoodsDao) {
		this.orderGoodsDao = orderGoodsDao;
	}

	@ShopDi
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}

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
		int aid = Integer.parseInt(req.getParameter("id"));
		Order o = new Order();
		o.setAddress(addressDao.load(aid));
		o.setBuyDate(new Date());
		User u = (User)req.getSession().getAttribute("loginUser");
		o.setUser(u);
		o.setPrice(Double.parseDouble(req.getParameter("price")));
		orderDao.add(o);
		ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("sc");
		Map<Goods,Integer> scMap = sc.getScMap();
		OrderGoods og = null;
		for(Map.Entry<Goods, Integer> m:scMap.entrySet()){
			og =  new OrderGoods();
			og.setGoods(m.getKey());
			og.setNum(m.getValue());
			og.setOrderId(o.getId());
			orderGoodsDao.add(og);
		}
		return RED+"order.do?method=list&userId="+u.getId();
	}
	
	@Auth
	public String addinput(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/order/addinput.jsp";
	}
	
	@Auth
	public String updateinput(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Order o = orderDao.load(id);
		List<Address> addresses = addressDao.list(o.getUser().getId());
		req.setAttribute("o", o);
		req.setAttribute("addresses", addresses);
		return "/WEB-INF/order/updateinput.jsp";
	}
	
	@Auth
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		int aid = Integer.parseInt(req.getParameter("aid"));
		Order oo = orderDao.load(id);
		oo.setAddress(addressDao.load(aid));
		boolean flag = RequestUtil.validate(Order.class, req);
		if(flag){
			double price = Double.parseDouble(req.getParameter("price"));
			oo.setPrice(price);
			orderDao.update(oo);
			return RED+"order.do?method=list&id="+oo.getUser().getId();
		}else{
			List<Address> addresses = addressDao.list(oo.getUser().getId());
			req.setAttribute("addresses", addresses);
			req.setAttribute("o", oo);
			req.setAttribute("price", req.getParameter("price"));
			return "/WEB-INF/order/updateinput.jsp";
		}
		 
	}
	
	@Auth
	public String updatePay(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Order oo = orderDao.load(id);
		List<OrderGoods> ls = oo.getOrderGoodses();
		try {
			for(OrderGoods og : ls){
				goodsDao.decreseStock(og.getGoods().getId(), og.getNum());
			}
		} catch (ShopException e) {
			return dealWithException(e, req);
		}
		oo.setStatus(2);
		oo.setPayDate(new Date());
		orderDao.update(oo);
		return RED+"order.do?method=list&userId="+oo.getUser().getId();
	}
	
	@Auth
	public String updateSend(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Order oo = orderDao.load(id);
		oo.setStatus(3);
		orderDao.update(oo);
		return RED+"order.do?method=list&userId="+oo.getUser().getId();
	}
	
	@Auth
	public String updateConfirm(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Order oo = orderDao.load(id);
		oo.setStatus(4);
		oo.setConfirmDate(new Date());
		orderDao.update(oo);
		return RED+"order.do?method=list&userId="+oo.getUser().getId();
	}
	
	@Auth
	public String updateCancel(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Order oo = orderDao.load(id);
		oo.setStatus(0);
		orderDao.update(oo);
		return RED+"order.do?method=list&userId="+oo.getUser().getId();
	}
	
	
	@Auth
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		int userId;
		try {
			userId = Integer.parseInt(req.getParameter("userId"));
		} catch (NumberFormatException e) {
			userId = 0;
		}
		User u = (User)req.getSession().getAttribute("loginUser");
		boolean flag = u.getId()==userId;
		if(u.getType()==0) flag =true;
		if(!flag) {
			return dealWithException(new ShopException("您无权操作"), req);
		}else{
			String condition = req.getParameter("condition");
			Page<Order> ls = orderDao.findByUserIdAndCondition(condition, userId);
			req.setAttribute("ls", ls);
			return "/WEB-INF/order/list.jsp";
		}
	}
	
	public String listAll(HttpServletRequest req, HttpServletResponse resp) {
			Page<Order> ls = orderDao.find(null);
			req.setAttribute("ls", ls);
			return "/WEB-INF/order/list.jsp";
	}
	
	
	@Auth
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		Order o = orderDao.load(id);
		System.out.println(o.getBuyDate());
		System.out.println(o.getPayDate());
		System.out.println(o.getConfirmDate());
		req.setAttribute("order", o);
		return "/WEB-INF/order/show.jsp";
	}

}
