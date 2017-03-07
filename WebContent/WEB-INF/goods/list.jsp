<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<%-- <jsp:include page="inc.jsp"/> --%>
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
		<td colspan="7">
			<form action="goods.do?method=list" method="post">
			<input type="text" name="condition" value="${param.condition }"/>&nbsp;<input type="submit" value="查询" />
			</form>
		</td>
	</tr>
	<tr>
	<td>商品图片</td><td>商品名称</td><td>商品类型</td><td>价格</td><td>库存</td><td>状态</td><td>操作</td>
	</tr>
	<c:forEach items="${ls.datas }" var="ls">
	<tr>
	<td><img src="<%=request.getContextPath() %>/upload/${ls.img}" width="130" height="130"></td><td><a href="goods.do?method=show&id=${ls.id}">${ls.name }</a></td>
	<td>${ls.category.name }</td><td>${ls.price }</td>
	<td>${ls.stock }&nbsp;
	<c:if test="${loginUser.type eq 0 }"><a href="goods.do?method=updatestockinput&id=${ls.id }">修改库存</a></c:if>
	</td>
	<td>
		<c:if test="${ls.status eq 0 }"><span class="errorContainer">下架</span>&nbsp;
		<c:if test="${loginUser.type eq 0 }"><a href="goods.do?method=updatestatus&id=${ls.id }">上架</a></c:if>
		</c:if>
		<c:if test="${ls.status eq 1 }">可购买&nbsp;
		<c:if test="${loginUser.type eq 0 }"><a href="goods.do?method=updatestatus&id=${ls.id }">下架</a></c:if></c:if>
	</td>
	<td><a href="sc.do?method=add&id=${ls.id }">购买</a>&nbsp;<c:if test="${loginUser.type eq 0 }"><a href="goods.do?method=updateinput&id=${ls.id }">修改</a>&nbsp;<a href="goods.do?method=delete&id=${ls.id }">删除</a></c:if></td>
	</tr>
	</c:forEach>
	<tr>
	<td colspan="7">
		<jsp:include page="/inc/pager.jsp">
			<jsp:param value="${ls.totalRecord }" name="tr"/>
			<jsp:param value="goods.do" name="url"/>
			<jsp:param value="method,condition,cid,status" name="params"/>
		</jsp:include>
	</td>
	</tr>
</table>

</body>
</html>