<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="shortcut icon" href="/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">yeon HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<h3>
  <span class="material-symbols-outlined">view_list</span> Spring days00
</h3>
<div>
  <xmp class="code">  
    WEB-INF/views/ssmr/upload2.jsp
  </xmp>   
  
  <form action="" method="post" enctype="multipart/form-data">
  	<div><input type="text" name="output" value="hello world!" /></div>
  	<div><input type="file" name="attachList" multiple="multiple" /></div>
	<!--   
	<div><input type="file" name="attach"/></div>
  	<div><input type="file" name="attach"/></div>
  	<div><input type="file" name="attach"/></div>
  	<div><input type="file" name="attach"/></div> 
  	-->
  	<div><input type="submit"/></div>
  	<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
  </form>
  
</div>

<script>
</script>

</body>
</html>