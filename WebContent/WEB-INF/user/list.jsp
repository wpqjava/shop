<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
<jsp:include page="userManager.jsp"/>
<table	width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
	<td>id</td><td>用户名</td><td>密码</td><td>昵称</td><td>类型</td><td>操作</td>
	</tr>
	<c:forEach items="${us.datas }" var="u">
	<tr>
	<td>${u.id }</td><td>${u.username }</td><td>${u.password }</td><td><a href="user.do?method=show&id=${u.id }">${u.nickname }</a></td>
	<td><c:if test="${u.type eq 0 }">管理员</c:if><c:if test="${u.type eq 1 }">用户</c:if>&nbsp;<a href="user.do?method=updateUserType&id=${u.id }">变更</a></td>
	<td><a href="user.do?method=updateinput&id=${u.id }">修改</a>&nbsp;<a href="user.do?method=delete&id=${u.id }">删除</a></td>
	</tr>
	</c:forEach>
	<tr>
	<td colspan="6">
		<jsp:include page="/inc/pager.jsp">
			<jsp:param value="${us.totalRecord }" name="tr"/>
			<jsp:param value="user.do" name="url"/>
			<jsp:param value="method" name="params"/>
		</jsp:include>
	</td>
	</tr>
</table>
</body>
</html>