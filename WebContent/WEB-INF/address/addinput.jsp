<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地址添加</title>
</head>
<body>
<form action="address.do?method=add" method="post">
<input type="hidden" name="userId" value="${loginUser.id }"/>
	<table align="center" width="800" cellpadding="0" cellspacing="0" class="thin-border">
		<tr>
		<td>用户名:</td><td>${loginUser.username }</td>
		</tr>
		<tr>
		<td>用户昵称:</td><td>${loginUser.nickname }</td>
		</tr>
		<tr>
		<td>联系电话:</td><td><input type="text" name="phone" size="80"/>&nbsp;<span class="errorContainer">${errors.phone }</span></td>
		</tr>
		<tr>
		<td>详细地址:</td><td><input type="text" name="name" size="80"/>&nbsp;<span class="errorContainer">${errors.name }</span></td>
		</tr>
		<tr>
		<td colspan="2"><input type="submit" value="添加">&nbsp;<input type="reset" value="重置"></td>
		</tr>
	</table>
</form>
</body>
</html>