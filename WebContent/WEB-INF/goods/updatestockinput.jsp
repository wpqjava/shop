<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存修改</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/xheditor_lang/zh-cn.js"></script>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<form action="goods.do?method=updatestock" method="post">
<input type="hidden" name="id" value="${g.id }"/>
	<table align="center" width="800" cellpadding="0" cellspacing="0" class="thin-border">
		<tr>
		<td>商品名称:</td><td>${g.name }</td>
		</tr>
		<tr>
		<td>商品库存:</td><td><input type="text" name="stock" value="${g.stock }"/><span class="errorContainer">${errors.stock }</span></td>
		</tr>
		<tr>
		<td colspan="2"><input type="submit" value="修改">&nbsp;<input type="reset" value="重置"></td>
		</tr>
	</table>
</form>
</body>
</html>