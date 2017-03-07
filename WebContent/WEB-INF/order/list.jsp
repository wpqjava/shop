<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单列表</title>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td colspan="7">
		<form action="order.do?method=list" method="post">
		<input type="hidden" name="userId" value="${param.userId }">
		<input type="text" name="condition" value="${param.condition }"/>&nbsp;<input type="submit" value="查询" />
			</form>
		</td>
	</tr>
</table><br/>
<c:forEach items="${ls.datas }" var="ods">
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td>订单编号</td><td colspan="4">${ods.id }&nbsp;<a href="order.do?method=show&id=${ods.id }">订单详情</a></td>
	</tr>
	<c:if test="${loginUser.type eq 0 }">
	<tr>
	<td>订单用户</td><td colspan="4">${ods.user.nickname }</td>
	</tr>
	</c:if>
	<tr>
	<td>商品图片</td><td>商品名称</td><td>商品类型</td><td>数量</td><td>价格</td>
	</tr>
	<c:forEach items="${ods.orderGoodses }" var="og">
	<tr>
	<td><img src="<%=request.getContextPath() %>/upload/${og.goods.img}" width="130" height="130"></td><td><a href="goods.do?method=show&id=${og.goods.id}">${og.goods.name }</a></td>
	<td>${og.goods.category.name }</td><td>${og.num }</td><td>${og.goods.price*og.num }</td>
	</tr>
	</c:forEach>
	<tr>
	<td>订单总价</td><td colspan="4">${ods.price }</td>
	</tr>
	<tr>
	<td>状态:</td>
	<td colspan="4">
	<c:if test="${ods.status eq 0 }"><span class="errorContainer">已取消</span></c:if>
	<c:if test="${ods.status eq 1 }"><span class="errorContainer">待付款</span></c:if>
	<c:if test="${ods.status eq 2 }"><span class="errorContainer">待发货</span></c:if>
	<c:if test="${ods.status eq 3 }"><span class="errorContainer">已发货</span></c:if>
	<c:if test="${ods.status eq 4 }"><span class="errorContainer">成功</span></c:if>
	</td>
	</tr>
	<c:if test="${ods.status ne 0 }">
		<tr><td colspan="5">
		<c:if test="${ods.status eq 1 }"><a href="order.do?method=updatePay&id=${ods.id }">付款</a></c:if>
		<c:if test="${ods.status eq 2 and loginUser.type eq 0}"><a href="order.do?method=updateSend&id=${ods.id }">发货</a></c:if>
		<c:if test="${ods.status eq 3 }"><a href="order.do?method=updateConfirm&id=${ods.id }">确认收货</a></c:if>
		<c:if test="${ods.status eq 4 }"><span class="errorContainer">订单成功</span></c:if>
		&nbsp;<c:if test="${ods.status ne 4 }"><a href="order.do?method=updateCancel&id=${ods.id }">取消订单</a></c:if>
		&nbsp;<c:if test="${ods.status eq 1 }"><a href="order.do?method=updateinput&id=${ods.id }">修改订单</a></c:if>
		</td></tr>
	</c:if>
	<tr>
<%-- 	<td colspan="7">
		<jsp:include page="/inc/pager.jsp">
			<jsp:param value="${ls.totalRecord }" name="tr"/>
			<jsp:param value="order.do" name="url"/>
			<jsp:param value="method,condition,status" name="params"/>
		</jsp:include>
	</td> --%>
	</tr>
</table>
<br/>
</c:forEach>

</body>
</html>