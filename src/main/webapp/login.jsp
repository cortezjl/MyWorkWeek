<%--
  Created by IntelliJ IDEA.
  User: 15635
  Date: 10/22/2019
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lange="en">
<title>Login</title>

<%@include file="head.jsp"%>

<body>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2>My Work Week Login</h2>

        <FORM ACTION="j_security_check" METHOD="POST">
            <br>
            <TABLE>
                <TR><TD>User name: <INPUT TYPE="TEXT" NAME="j_username">
                <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password" >
                <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In" class="btn btn-primary">
            </TABLE>
        </FORM>
        <br><br>
    </div>
</div>

</body>
</html>
