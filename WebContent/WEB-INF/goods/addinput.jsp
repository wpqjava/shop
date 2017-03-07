<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品添加</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhed/xheditor_lang/zh-cn.js"></script>
</head>
<body>
<jsp:include page="/inc/ShopInc.jsp"/>
<form action="goods.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="method" value="add"/>
	<table align="center" width="800" cellpadding="0" cellspacing="0" class="thin-border">
		<tr>
		<td>商品名称:</td><td><input type="text" name="name" value="${param.name }"/><span class="errorContainer">${errors.name }</span></td>
		</tr>
		<tr>
		<td>商品价格:</td><td><input type="text" name="price" value="${param.price }"/><span class="errorContainer">${errors.price }</span></td>
		</tr>
		<tr>
		<td>商品库存:</td><td><input type="text" name="stock" value="${param.stock }"/><span class="errorContainer">${errors.stock }</span></td>
		</tr>
		<tr>
		<td>商品类别:</td>
		<td>
			<select name="cid">
			<option value="0">请选择</option>
			<c:forEach items="${lists }" var="ls">
			<c:if test="${ls.id eq param.cid }">
			<option value="${ls.id }" selected="selected">${ls.name }</option>
			</c:if>
			<c:if test="${ls.id ne param.cid }">
			<option value="${ls.id }">${ls.name }</option>
			</c:if>
			</c:forEach>
			</select>
			<span class="errorContainer">${errors.cid }</span>
		</td>
		</tr>
		<tr>
		<td>商品图片:</td><td><input type="file" name="img" value="${param.img }"/><span class="errorContainer">${errors.img }</span></td>
		</tr>
		<tr>
		<td colspan="2">商品介绍:</td>
		</tr>
		<tr>
		<td colspan="2"><textarea name="intro" class="xheditor" cols="100" rows="10">${param.intro }</textarea></td>
		</tr>
		<tr>
		<td colspan="2"><input type="submit" value="添加">&nbsp;<input type="reset" value="重置"></td>
		</tr>
	</table>
</form>
</body>
</html>