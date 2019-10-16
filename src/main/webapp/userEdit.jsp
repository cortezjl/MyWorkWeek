<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Edit User" />

<html>

<body>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2 class="text-center">
            <c:if test="${user != null}">
                Edit User
            </c:if>
            <c:if test="${user == null}">
                Add New User
            </c:if>
        </h2><br/>

            <form id="editUserForm" role="form" data-toggle="validator"
                  class="form-horizontal"
                  action="editUserServlet"
                  method="POST">

                <input type="hidden" id="id"
                       name="id"
                       value = ${user.id}>

                <div class="row form-group">
                    <div class="col-0 col-sm-1"></div>

                    <div class="col-1 col-sm-1 ">
                        <label class="d-inline-block text-sm-right" for="firstName">First Name</label>
                    </div>
                    <div class="col-4 col-sm-3">
                        <input type="text" class="form-control d-inline-block" id="firstName"
                               name="firstName"
                               value = "${user.firstName}"
                               data-error="Please enter the user's first name." required>
                    </div>

                    <div class="help-block with-errors col-1 col-sm-1"></div>

                    <div class="col-1 col-sm-1 ">
                        <label class="d-inline-block text-sm-right" for="lastName">Last Name</label>
                    </div>
                    <div class="col-4 col-sm-3">
                        <input type="text" class="form-control d-inline-block" id="lastName"
                               name="lastName"
                               value = "${user.lastName}"
                               data-error="Please enter the user's last name."
                               required>
                    </div>
                    <div class="help-block with-errors col-1 col-sm-1"></div>
                </div>

                <div class="row form-group">
                    <div class="col-0 col-sm-1"></div>
                    <div class="col-1 col-sm-1 ">
                        <label class="control-label d-inline-block" for="userName">Username</label>
                    </div>
                    <div class="col-4 col-sm-3">
                        <input type="text" class="form-control d-inline-block" id="userName"
                               name="userName"
                               value = "${user.userName}"
                               data-error="Please enter the username."
                               required>
                    </div>
                    <div class="help-block with-errors col-1 col-sm-1"></div>
                    <div class="col-1 col-sm-1 ">
                        <label class="control-label d-inline-block" for="password">Password</label>
                    </div>
                    <div class="col-3 col-sm-3">
                        <input type="text" class="form-control d-inline-block" id="password"
                               name="password"
                               value = "${user.password}"
                               data-error="Please enter the users password."
                               required>
                    </div>
                    <div class="help-block with-errors col-1 col-sm-2"></div>
                </div>


                <div class="row form-group">
                    <div class="col-0 col-sm-1"></div>
                    <label class="control-label col-1 col-sm-1" for="startDate">Start Date</label>
                    <div class="col-14 col-sm-3">
                        <div class="input-group date" data-provide="datepicker">
                            <input type="text" class="form-control" id="startDate" name="startDate"
                                   value = "${user.getStartDateForDisplay()}" data-error="Please enter the user's start date"
                                   required>
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                    </div>
                    <div class="help-block with-errors col-1 col-sm-1"></div>
                    <label class="control-label col-1 col-sm-1" for="startDate">End Date</label>
                    <div class="col-4 col-sm-3">
                        <div class="input-group date" data-provide="datepicker">
                            <input type="text" class="form-control" id="endDate" name="endDate"
                                   value = "${user.getEndDateForDisplay()}" data-error="Please enter the user's end date"
                                   required>
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                    </div>
                    <div class="help-block with-errors col-1 col-sm-4"></div>
                </div>

                <div class="row form-group">
                    <div class="col-0 col-sm-1"></div>
                    <label class="control-label col-1 col-sm-1" for="dateOfBirth">Date Of Birth</label>
                    <div class="col-10 col-sm-3">
                        <div class="input-group date" data-provide="datepicker">
                            <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth"
                                   value = "${user.getDateOfBirthForDisplay()}" data-error="Please enter the user's date of birth"
                                   required>
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-th"></span>
                            </div>
                        </div>
                    </div>
                    <div class="help-block with-errors col-1 col-sm-8"></div>
                </div>


                <div class="row form-group">
                    <div class="col-0 col-sm-1"></div>
                    <div class="col-1 col-sm-1 ">
                        <label for="roleSelect">Role(s):</label>
                    </div>
                    <div class="col-10 col-sm-10"></div>
                </div>

                <div class="row form-group">
                    <c:forEach var="role" items="${user.roles}">
                        <div class="col-0 col-sm-2"></div>
                        <input type="hidden" id="roleId"
                               name="roleId"
                               value="${role.id}">

                        <div class="col-11 col-sm-5">
                            <select class="form-control" id="roleSelect" name="roleName" >
                                <c:forEach var="roleListOption" items="${roleList}">
                                    <option value="${roleListOption}" ${role.role == roleListOption ? 'selected="selected"' : ''}>${roleListOption}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-1 col-sm-5"></div>
                    </c:forEach>
                </div>

                <div class="row">
                    <div class="col-0 col-sm-2"></div>
                    <div class="col-12 col-sm-4">
                        <h3 class="text-sm-left">${userUpdateMessage}</h3>
                    </div>
                    <c:if test="${userUpdateMessage != null}" >
                        <c:remove var="userUpdateMessage"  />
                    </c:if>
                    <div class="col-0 col-sm-6"></div>
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