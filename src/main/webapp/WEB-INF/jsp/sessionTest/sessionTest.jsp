<%--
  Created by IntelliJ IDEA.
  User: seongjunGhong
  Date: 2023/09/07
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sessionTest</title>
</head>
<body>
    <h1>sessionTest</h1>
    <%
        String visitCounter = String.valueOf(session.getAttribute("visit-counter"));
        if(visitCounter != null) {
    %>
        <p>visit-counter : <%= visitCounter %></p>
    <%
        }
    %>
</body>
</html>
