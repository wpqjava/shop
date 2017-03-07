<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品类别</title>
<style>
span.category{
	color:#fff;
}
a.category_link:link{
	color:#fff;
	text-decoration: none;
	background: #872;
	padding: 5px;
}
a.category_link:HOVER{
	color:#fff;
	text-decoration: none;
	background: #e22;
	padding: 5px;
}
</style>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<table>
<form action="category.do?method=list" method="post">
<input type="text" name="condition" value="${param.condition }"/>&nbsp;<input type="submit" value="查询"/>
</form>
<c:if test="${loginUser.type eq 0 }">
<form  action="category.do?method=addinput" method="post">
&nbsp;<input type="submit" value="添加类别"/>
</form>
</c:if>
</table>

<br/>
	<c:forEach items="${lists }" var="cs">
	<a href="category.do?method=show&id=${cs.id }" class="category_link"><span class="category">${cs.name }</span></a>&nbsp;
	</c:forEach>
</body>
</html>