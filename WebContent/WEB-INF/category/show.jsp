<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品类别信息</title>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<table align="center" width="800" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td>类别ID:${c.id }</td>
	</tr>
	<tr>
	<td>类别名称:${c.name }</td>
	</tr>
	<c:if test="${loginUser.type eq 0 }">
	<tr>
	<td>操作:<a href="category.do?method=updateinput&id=${c.id }">修改</a>&nbsp;<a href="category.do?method=delete&id=${c.id }">删除</a>&nbsp;</td>
	</tr>
	</c:if>
	
</table>
</body>
</html>