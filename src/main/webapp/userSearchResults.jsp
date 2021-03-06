<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<c:set var="title" value="User Search Results" />
<html lang="en">
<body>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#userTable').DataTable();
    } );
</script>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2>Users</h2>
        <table id="userTable"  class="table table-striped table-bordered display dt-responsive nowrap"  >
            <tr><th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Roles</th>
                <th></th>
            </tr>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userName}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>
                    <c:forEach var="role" items="${user.roles}">
                        ${role.role} </br>
                    </c:forEach>
                </td>
                <td>
                    <a class="edit" title="Edit" data-toggle="tooltip" href="addEditUserServlet?userAction=edit&id=${user.id}"><i class="fa" >&#xf044;</i></a> <!--Font Awesome Edit symbol -->
                </td>
            </tr>
            </c:forEach>
    </div>
</div>

</body>
</html>