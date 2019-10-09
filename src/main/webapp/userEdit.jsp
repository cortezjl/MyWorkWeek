<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Edit User" />

<html>
    <body>
        <div class="wrapper">
            <div class="container-fluid">
                <%@include file="header.jsp"%>
                <%@include file="navbar.jsp"%>
                <h2 class="text-center">Edit User</h2><br/>

                    <form id="editUserForm" role="form" data-toggle="validator"
                          class="form-horizontal"
                          action="editUserServlet"
                          method="POST">

                        <input type="hidden" id="id"
                               name="id"
                               value = ${user.id}>

                        <div class="row form-group">
                            <div class="col-0 col-sm-3"></div>
                            <div class="col-1 col-sm-1 ">
                                <label class="control-label d-inline-block" for="firstName">First Name</label>
                            </div>
                            <div class="col-11 col-sm-5">
                                <input type="text" class="form-control d-inline-block" id="firstName"
                                       name="firstName"
                                       value = "${user.firstName}"
                                       data-error="Please enter the user's first name." required>
                            </div>
                            <div class="help-block with-errors col-0 col-md-3"></div>
                        </div>

                        <div class="row form-group">
                            <div class="col-0 col-sm-3"></div>
                            <div class="col-1 col-sm-1 ">
                                <label class="control-label d-inline-block" for="lastName">Last Name</label>
                            </div>
                            <div class="col-11 col-sm-5">
                                <input type="text" class="form-control d-inline-block" id="lastName"
                                       name="lastName"
                                       value = "${user.lastName}"
                                       data-error="Please enter the user's last name."
                                       required>
                            </div>
                            <div class="help-block with-errors col-0 col-md-3"></div>
                        </div>

                        <div class="row form-group">
                            <div class="col-0 col-sm-3"></div>
                            <div class="col-1 col-sm-1 ">
                                <label class="control-label d-inline-block" for="userName">Username</label>
                            </div>
                            <div class="col-11 col-sm-5">
                                <input type="text" class="form-control d-inline-block" id="userName"
                                       name="userName"
                                       value = "${user.userName}"
                                       data-error="Please enter the username."
                                       required>
                            </div>
                            <div class="help-block with-errors col-0 col-md-3"></div>
                        </div>

                        <div class="row form-group">
                            <div class="col-0 col-sm-3"></div>
                            <label class="control-label col-1 col-sm-1" for="dateOfBirth">Date Of Birth</label>
                            <div class="col-11 col-sm-5">
                                <div class="input-group date" data-provide="datepicker">
                                    <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth"
                                           value = "${user.getDateOfBirthForDisplay()}" data-error="Please enter the user's date of birth"
                                           required>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                                <div class="help-block with-errors col-0 col-sm-3"></div>
                            </div>
                        </div>









                        <div class="row form-group">
                            <div class="col-0 col-sm-3"></div>
                            <div class="col-1 col-sm-1 ">
                                <label class="control-label col-sm-2" for="userRoles">Role(s)</label>
                            </div>
                            <c:forEach var="role" items="${user.roles}">
                                <div class="col-11 col-sm-5">
                                    <input type="text" class="form-control" id="userRoles"
                                           name="usersRole"
                                           value = "${role.role}"
                                           data-error="Please enter the username."
                                           required>
                                </div>
                            </c:forEach>
                            <div class="help-block with-errors col-0 col-md-3"></div>
                        </div>


                        <div class="row">
                            <c:if test="${userUpdateMessage != null}" >
                                <c:remove var="userUpdateMessage" scope="session" />
                            </c:if>
                        </div>

                        <div class="row">
                            <div class="col-4 col-sm-5"></div>
                            <div class="col-4 col-sm-2">
                                <button type="submit" class="btn btn-primary col-sm-offset-3"
                                        data-disable="true">Update
                                </button>
                            </div>
                            <div class="col-4 col-sm-5"></div>
                        </div>
                    </form>
            </div>
        </div>
    </body>
</html>