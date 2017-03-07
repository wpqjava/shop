<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单修改</title>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<form action="order.do?method=update" method="post">
<input type="hidden" name="id" value="${o.id }"/> 
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td>用户名:</td><td colspan="3">${o.user.username }</td>
	</tr>
	<tr>
	<td colspan="4">购买商品:</td>
	</tr>
	<tr>
	<td>商品图片</td><td>商品名称</td><td>商品数量</td><td>商品价格</td>
	</tr>
	<c:forEach items="${o.orderGoodses }" var="og">
	<tr>
	<td><img src="<%=request.getContextPath() %>/upload/${og.goods.img}" width="80" height="80"></td><td><a href="goods.do?method=show&id=${og.goods.id}">${og.goods.name }</a></td>
	<td>${og.num}</td><td>${og.goods.price * og.num }</td>
	</tr>
	</c:forEach>
	<tr>
	<td>商品总价:</td><td colspan="3"><input type="text" name="price" value="${o.price }"/><span class="errorContainer">${errors.price }</span></td>
	</tr>
	<c:forEach items="${addresses }" var="ads" varStatus="a">
	<tr>
	<c:if test="${ads.id eq o.address.id  }"><td colspan="4"><input type="radio" name="aid" value="${ads.id }" checked="checked"/>&nbsp;${ads.name }</td></c:if>
	<c:if test="${ads.id ne o.address.id   }"><td colspan="4"><input type="radio" name="aid" value="${ads.id }" />&nbsp;${ads.name }</td></c:if>
	</tr>
	</c:forEach>
	<tr>
	<td>状态:</td>
	<td colspan="4">
	<c:if test="${o.status eq 0 }"><span class="errorContainer">已取消</span></c:if>
	<c:if test="${o.status eq 1 }"><span class="errorContainer">待付款</span></c:if>
	<c:if test="${o.status eq 2 }"><span class="errorContainer">待发货</span></c:if>
	<c:if test="${o.status eq 3 }"><span class="errorContainer">已发货</span></c:if>
	<c:if test="${o.status eq 4 }"><span class="errorContainer">成功</span></c:if>
	</td>
	</tr>
	<tr>
	<td colspan="4"><input type="submit" value="提交"/>&nbsp;<input type="reset" value="重置"/>
	</tr>
</table>
</form>
</body>
</html>