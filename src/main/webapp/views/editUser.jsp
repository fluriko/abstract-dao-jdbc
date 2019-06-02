<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: insania
  Date: 2019-06-02
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<center>
    <h3>Fill the form below to edit user</h3>
    <form method="post">
        login
        <input type="text" minlength="3" maxlength="20" name="login" value="<c:out value="${userToEdit.login}"/>"><br/><br/>
        email
        <input type="email" name="email" value="<c:out value="${userToEdit.email}"/>"><br/><br/>
        password
        <input type="password" minlength="8" maxlength="20" name="password"><br/>
        <button type="submit">Submit</button><br/><br/>
    </form>
    <button onclick="location.href='/users'">Back to users</button><br/><br/>
    <button onclick="location.href='/home'">Bach to home</button><br/><br/>
</center>
</body>
</html>
