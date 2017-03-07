<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td>商品图片</td><td>商品名称</td><td>价格</td><td>数量</td><td>操作</td>
	</tr>
	<c:if test="${sc.isEmpty }"><tr>
	<td colspan="5">购物车中没有商品&nbsp;<a href="goods.do?method=list">选择商品</a></td></tr></c:if>
	<c:forEach items="${sc.scMap }" var="sc">
	<c:set var="tp" value="${tp+sc.key.price*sc.value }"></c:set>
	<tr>
	<td><img src="<%=request.getContextPath() %>/upload/${sc.key.img}" width="130" height="130"></td><td><a href="goods.do?method=show&id=${sc.key.id}">${sc.key.name }</a></td>
	<td>${sc.key.price * sc.value }</td>
	<td>
		<form action="sc.do?method=update" method="post">
		<input type="hidden" name="id" value="${sc.key.id }"/>
		<input type="text" name="num" value="${sc.value }"/>
		<input type="submit" value="修改"/>
		</form>
	</td>
	<td><a href="sc.do?method=delete&id=${sc.key.id }">删除</a></td>
	</tr>
	</c:forEach>
	<c:if test="${sc.isEmpty eq false }">
	<tr>
	<td>购物车总价:</td><td colspan="4">${tp }</td>
	</tr>
	<tr>
	<td colspan="5"><a href="order.do?method=addinput">结算</a>&nbsp;<a href="sc.do?method=removeAll">清空</a></td>
	</tr>
	</c:if>
</table>

</body>
</html>