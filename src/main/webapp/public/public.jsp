<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Public JSP Page</title>
</head>
<body>
	<div class="container">
		<h1>This Should be User Public secured!</h1>
		<p>Hello
		<P>
			name:
			<%=request.getUserPrincipal().getName()%>
		</p>
		<hr>
		Roles <b>Role: <%=request.getUserPrincipal().toString()%></b>
		</p>
		Return
		<UL>
			<LI><a href="../private/">Private Page</a></LI>
			<LI><a href="../public/">Public Page</a></LI>
		</UL>
	</div>
</body>
</html>