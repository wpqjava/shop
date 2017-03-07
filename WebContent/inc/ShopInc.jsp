<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${not empty loginUser }">
<a href="category.do?method=list">商品类别</a>&nbsp;
<a href="goods.do?method=list">商品列表</a>&nbsp;
<a href="order.do?method=list&userId=${loginUser.id }">已买到的货物</a>&nbsp;
<a href="sc.do?method=show">查看购物车</a>&nbsp;
<c:if test="${loginUser.type eq 0 }">
<a href="goods.do?method=addinput">商品添加</a>&nbsp;</c:if>
</c:if>
<h1 align="center">
<script>
    var tit=document.title;
    document.write(tit);
</script>
</h1>