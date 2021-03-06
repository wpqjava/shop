<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/xheditor_lang/zh-cn.js"></script>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
		<table align="center" width="800" cellpadding="0" cellspacing="0" class="thin-border">
		<tr>
		<td>商品图片:</td><td><img src="<%=request.getContextPath() %>/upload/${g.img}" width="130" height="130"></td>
		</tr>
		<tr>
		<td>商品名称:</td><td>${g.name }</td>
		</tr>
		<tr>
		<td>商品类别:</td><td>${g.category.name }</td>
		</tr>
		<tr>
		<td>商品价格:</td><td>${g.price }</td>
		</tr>
		<tr>
		<td>商品库存:</td><td>${g.stock }</td>
		</tr>
		<tr>
		<td colspan="2""><c:if test="${g.status eq 1 }"><a href="">加入购物车</a></c:if><c:if test="${g.status eq 0 }"><span class="errorContainer">该商品已下架</span></c:if></td>
		</tr>
		<tr>
		<td colspan="2">商品介绍:</td>
		</tr>
		<tr>
		<td colspan="2"><textarea name="intro" class="xheditor" cols="100" rows="10">${g.intro }</textarea></td>
		</tr>
	</table>
</form>
</body>
</html>