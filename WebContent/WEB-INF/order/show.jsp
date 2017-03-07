<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单查询</title>
<style>
a.order_link:link{
	color:#ff0000;
	background: #ffffff;
	padding: 5px;
	
}
</style>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<c:if test="${not empty order.buyDate }">
	<tr>
	<td>购买日期:</td><td colspan="3">
	${order.buyDate }
	</td>
	</tr>
	</c:if>
	<c:if test="${not empty order.payDate }">
	<tr>
	<td>付款日期:</td><td colspan="3">
	${order.payDate }
	</td>
	</tr>
	</c:if>
	<c:if test="${not empty order.confirmDate }">
	<tr>
	<td>确认日期:</td><td colspan="3">
	${order.confirmDate }
	</td>
	</tr>
	</c:if>
	<tr>
	<td>用户名:</td><td colspan="3">${order.user.nickname }</td>
	</tr>
	<tr>
	<td colspan="4">购买的商品:</td>
	</tr>
	<tr>
	<td>商品图片</td><td>商品名称</td><td>商品数量</td><td>商品价格</td>
	</tr>
	<c:forEach items="${order.orderGoodses }" var="og">
	<tr>
	<td><img src="<%=request.getContextPath() %>/upload/${og.goods.img}" width="80" height="80"></td><td><a href="goods.do?method=show&id=${og.goods.id}">${og.goods.name }</a></td>
	<td>${og.num }</td><td>${og.goods.price * og.num }</td>
	</tr>
	</c:forEach>
	<tr>
	<td>商品总价:</td><td colspan="3">${order.price }</td>
	</tr>
	<tr>
	<td>收货地址:</td><td colspan="3">${order.address.name }</td>
	</tr>
	<tr>
	<td>状态:</td>
	<td colspan="3">
	<c:if test="${order.status eq 0 }"><span class="errorContainer">已取消</span></c:if>
	<c:if test="${order.status eq 1 }"><span class="errorContainer">待付款</span></c:if>
	<c:if test="${order.status eq 2 }"><span class="errorContainer">待发货</span></c:if>
	<c:if test="${order.status eq 3 }"><span class="errorContainer">已发货</span></c:if>
	<c:if test="${order.status eq 4 }"><span class="errorContainer">成功</span></c:if>
	</td>
	</tr>
	<c:if test="${ods.status ne 0 }">
		<tr><td colspan="4">
		<c:if test="${order.status eq 1 }"><a href="order.do?method=updatePay&id=${order.id }">付款</a></c:if>
		<c:if test="${order.status eq 2 and loginUser.type eq 0}"><a href="order.do?method=updateSend&id=${order.id }">发货</a></c:if>
		<c:if test="${order.status eq 3 }"><a href="order.do?method=updateConfirm&id=${order.id }">确认收货</a></c:if>
		<c:if test="${order.status eq 4 }"><span class="errorContainer">订单成功</span></c:if>
		&nbsp;<c:if test="${order.status ne 4 }"><a href="order.do?method=updateCancel&id=${order.id }">取消订单</a></c:if>
		&nbsp;<c:if test="${order.status eq 1 }"><a href="order.do?method=updateinput&id=${order.id }">修改订单</a></c:if>
		</td></tr>
	</c:if>
</table>
</body>
</html>