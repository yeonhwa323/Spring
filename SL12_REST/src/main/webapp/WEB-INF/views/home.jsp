<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! - ${ user }
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3>
	<a href="/time">/time</a><br>
	<a href="/scott/getText">/scott/getText</a><br>
	<a href="/scott/getText2">/scott/getText2</a><br>
	<a href="/scott/employees">/scott/employees</a><br>
	<a href="/scott/employees2">/scott/employees2</a><br>
	<a href="/scott/employees3">/scott/employees3</a><br>
	<br>	
	<a href="/scott/employee/7369">/scott/employee/7369</a><br>
	<br>
	<a href="/scott/ticket">/scott/ticket</a><br>
	<br>
	<a href="/scott/idCheck">/scott/idCheck</a><br>
</h3>

</body>
</html>
