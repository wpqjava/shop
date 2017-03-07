<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单添加</title>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<form action="order.do?method=add" method="post">
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td>用户名:</td><td colspan="3">${loginUser.username }</td>
	</tr>
	<tr>
	<td colspan="4">购买商品:</td>
	</tr>
	<tr>
	<td>商品图片</td><td>商品名称</td><td>商品数量</td><td>商品价格</td>
	</tr>
	<c:forEach items="${sc.scMap }" var="sc">
	<c:set var="tp" value="${tp+sc.key.price*sc.value }"></c:set>
	<tr>
	<td><img src="<%=request.getContextPath() %>/upload/${sc.key.img}" width="80" height="80"></td><td><a href="goods.do?method=show&id=${sc.key.id}">${sc.key.name }</a></td>
	<td>${sc.value }</td><td>${sc.key.price * sc.value }</td>
	</tr>
	</c:forEach>
	<tr>
	<td>商品总价:</td><td colspan="3">${tp }</td>
	</tr>
	<c:if test="${empty loginUser.addresses }">
	<tr>
	<td colspan="4">请先添加收货地址&nbsp;<a href="address.do?method=addinput">添加地址</a></td>
	</tr>
	</c:if>
	<c:if test="${not empty loginUser.addresses }">
	<c:forEach items="${loginUser.addresses }" var="ads" varStatus="a">
	<tr>
	<c:if test="${a.index eq 0  }"><td colspan="4"><input type="radio" name="id" value="${ads.id }" checked="checked"/>&nbsp;${ads.name }</td></c:if>
	<c:if test="${a.index ne 0  }"><td colspan="4"><input type="radio" name="id" value="${ads.id }" />&nbsp;${ads.name }</td></c:if>
	</tr>
	</c:forEach>
	</c:if>
	<tr>
	<input type="hidden" name="price" value="${tp }"/>
	<td colspan="4"><input type="submit" value="提交"/>&nbsp;<a href="sc.do?method=show">修改</a></td>
	</tr>
</table>
</form>
</body>
</html>