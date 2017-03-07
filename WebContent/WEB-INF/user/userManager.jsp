<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${not empty loginUser }">
<a href="user.do?method=show&id=${loginUser.id }">个人信息查询</a>&nbsp;
<a href="user.do?method=updateSelfInput">修改个人信息</a>&nbsp;
<c:if test="${loginUser.type eq 0 }">
<a href="user.do?method=list">用户列表</a>&nbsp;</c:if><a href="user.do?method=addinput">新用户注册</a>
</c:if>
<h1 align="center">
<script>
    var tit=document.title;
    document.write(tit);
</script>
</h1>