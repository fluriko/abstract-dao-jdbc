<%--
  Created by IntelliJ IDEA.
  User: insania
  Date: 2019-06-02
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<center>
    <h3>Fill the form below to add new user</h3>
    <form method="post" >
        login
        <input type="text" minlength="3" maxlength="20" name="login"><br/><br/>
        email
        <input type="email" name="email"><br/><br/>
        password
        <input type="password" minlength="8" maxlength="20" name="password"><br/><br/><br/>
        <button type="submit">Submit</button><br/><br/><br/>
    </form>
    <button onclick="location.href='/users'">back to users</button><br/><br/>
    <button onclick="location.href='/home'">back to home </button>
</center>
</body>
</html>
