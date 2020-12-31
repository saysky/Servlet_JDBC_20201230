<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改密码</title>
    <link rel="stylesheet" href="./assets/css/Login.css">
</head>
<body>
<div class="box">
    <h2>ArcsoftHotel欢迎您！</h2>
<form method="post" action="login">
    <div class="inputBox">
      <input type="text" name="username" required="">
      <label>用户名</label>
    </div>
    
    <div class="inputBox">
		<input type="password" name="password" required="">
		<label>密码</label>
    </div>
    
    <div class="inputBox">
		<input type="password" name="newpassword" required="">
		<label>新密码</label>
    </div>
    
    <div class="inputBox">
		<input type="password" name="newpasswordAgain" required="">
		<label>再次输入新密码</label>
    </div>
    <input type="submit" name="" value="确认"> 
</form>
</div>
</body>
</html>