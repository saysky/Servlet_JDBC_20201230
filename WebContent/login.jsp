<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登陆</title>
    <link rel="stylesheet" href="./assets/css/Login.css">
</head>
<body>
<div class="box">
    <h2>ArcsoftHotel欢迎您！</h2>
<form method="post" action="login">
    <div class="inputBox">
      <input type="text" name="Id">
      <label>用户名</label>
    </div>
    
    <div class="inputBox">
		<input type="password" name="password">
		<label>密码</label>
    </div>
    <input type="submit" name="" value="登录"> 
</form>
</div>
</body>
</html>