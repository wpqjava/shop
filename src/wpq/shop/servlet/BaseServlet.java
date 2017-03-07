package wpq.shop.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import wpq.shop.model.Auth;
import wpq.shop.model.ShopException;
import wpq.shop.model.User;
import wpq.shop.util.DaoUtil;

public class BaseServlet extends HttpServlet {
	protected final static String RED = "redirect:";
	public Map<String,String> errors = null;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			errors = new HashMap<String,String>();
			request.setAttribute("errors", errors);
			DaoUtil.diDao(this);
			if(ServletFileUpload.isMultipartContent(request)){
				request = new MultipartWrapper(request);
			}
			String method = request.getParameter("method");
			Method m = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			String path = null;
			int auth = checkAuth(m,request);
			switch (auth) {
			case 0:
				path = (String) m.invoke(this, request,response);
				break;
			case 1:
				path = "redirect:user.do?method=logininput";
				break;
			case 2:
				request.setAttribute("errorMsg", "无权限操作");
				path = "inc/error.jsp";
				break;
			}
			if(path.startsWith(RED)){
				response.sendRedirect(path.substring(RED.length()));
				return;
			}else{
				request.getRequestDispatcher(path).forward(request, response);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean hasErrors(){
		if(errors.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	//返回0为可操作，返回1跳转登录，返回2跳转无权限错误
	private int checkAuth(Method m, HttpServletRequest req) {
		User lu = (User) req.getSession().getAttribute("loginUser");
		if(lu!=null && lu.getType()==0)return 0;
		if(!m.isAnnotationPresent(Auth.class)){
			if(lu==null)return 1;
			if(lu.getType() != 0)return 2;
		}else{
			String value = m.getAnnotation(Auth.class).value();
			if(value.equals("any"))return 0;
			if(value.equals("user")){
				if(lu==null)return 1;
			}
		}
		return 0;
	}
	
	protected String dealWithException(ShopException e, HttpServletRequest req) {
		req.setAttribute("errorMsg", e.getMessage());
		return "/inc/error.jsp";
	}
}
