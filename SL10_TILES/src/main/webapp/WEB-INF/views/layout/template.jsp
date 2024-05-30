<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/x-icon" href="../images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script> 
<link rel="stylesheet" href="/resources/w3ss/css/flexbox.css"> 
</head>
<body> 
<!-- header 영역 -->
<tiles:insertAttribute name="header" />
<section>
  <!-- nav 영역 -->  
  <tiles:insertAttribute name="nav" />
  <!-- article 영역 -->
  <tiles:insertAttribute name="article" />
</section>
<!-- footer 영역 -->
<tiles:insertAttribute name="footer" />
</body>
</html>