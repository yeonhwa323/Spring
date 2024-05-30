<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<h1>
	Main Page - ${ user }
</h1>
<P>  The time on the server is ${serverTime}. </P>
<h3>
	<a href="/time">/time</a>
</h3>


