<html>
<body>

<h2>Employee Registered Successfully!</h2>

<%
    String name = (String) request.getAttribute("name");
%>

<p>Welcome, <b><%= name %></b></p>

<a href="index.jsp">Register Another</a>

</body>
</html>