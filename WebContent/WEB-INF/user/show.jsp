<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户个人信息</title>
</head>
<body>
<jsp:include page="userManager.jsp"/>
<table align="center" width="800" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td>用户名:${u.username }</td>
	</tr>
	<tr>
	<td>用户昵称:${u.nickname }</td>
	</tr>
	<tr>
	<td>用户地址:<c:if test="${loginUser.id eq u.id }"><a href="address.do?method=addinput&id=${u.id }">添加地址</a></c:if></td>
	</tr>
	<c:forEach items="${u.addresses }" var="ads">
		<tr>
		<td>${ads.name }&nbsp;${ads.phone }&nbsp;
		<c:if test="${loginUser.id eq u.id or loginUser.type eq 0 }">
		<a href="address.do?method=updateinput&id=${ads.id }">修改</a>&nbsp;<a href="address.do?method=delete&id=${ads.id }">删除</a></td>
		</c:if>
		</tr>
	</c:forEach>
</table>
</body>
</html>