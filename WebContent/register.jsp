<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
    <link rel="stylesheet" href="./assets/css/Login.css">
</head>
<body>
<div class="box">
    <h2>ArcsoftHotel欢迎您！</h2>
<form method="post" action="register">
    <div class="inputBox">
      <input type="text" name="Id">
      <label>用户名</label>
    </div>
    
    <div class="inputBox">
		<input type="password" name="password">
		<label>密码</label>
    </div>
    
    <div class="inputBox">
        <input type="text" name="passwordAgain">
        <label>请再次输入密码</label>
    </div>
    
    <div class="inputBox">
      <input type="text" name="name">
      <label>姓名</label>
    </div>
    
    <div class="inputBox">
        <input type="tel" name="phone">
        <label>电话</label>
    </div>
    
    <div class="inputBox">
        <input type="text" name="documentnumber">
        <label>身份证ID</label>
    </div>
    
    <div class="inputBox">
        <input type="file" name="face">
        <label>人脸照片</label>
    </div>
  
    <input type="submit" name="" value="注册"> 
</form>
</div>
</body>
</body>
</html>