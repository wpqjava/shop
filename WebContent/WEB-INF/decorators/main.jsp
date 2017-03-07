<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title/></title>
<decorator:head/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>
<c:if test="${not empty loginUser }">
欢迎&nbsp;${loginUser.nickname }&nbsp;使用商城管理系统&nbsp;
<a href="user.do?method=show&id=${loginUser.id }">用户管理</a>&nbsp;<a href="goods.do?method=list">商品管理</a>&nbsp;
<c:if test="${loginUser.type eq 0 }"><a href="order.do?method=listAll">全局订单管理</a>&nbsp;</c:if>
<a href="user.do?method=quit">退出系统</a>
<hr/>
</c:if>
<c:if test="${ empty loginUser }">
欢迎来到商城管理系统&nbsp;
<a href="user.do?method=logininput">用户登录</a>&nbsp;<a href="user.do?method=addinput">用户注册</a>&nbsp;<a href="goods.do?method=list">商品列表</a>
<hr/>
</c:if>
<decorator:body/>
<hr/>
<div align="center">
copyRight@2016-2020
</div>
</body>
</html>