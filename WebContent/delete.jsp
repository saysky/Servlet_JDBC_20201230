<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注销</title>
    <link rel="stylesheet" href="./assets/css/Login.css">
</head>
<body>
<div class="box">
    <h2>ArcsoftHotel劝您谨慎！</h2>
<form method="post" action="delete">
    <div class="inputBox">
      <input type="text" name="username" required="">
      <label>请输入要删除的用户名</label>
    </div>
    
    <div class="inputBox">
		<input type="password" name="password" required="">
		<label>请输入密码</label>
    </div>
    <input type="submit" name="" value="确认注销"> 
</form>
</div>
</body>
</html>