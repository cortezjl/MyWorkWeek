<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
        <div class="wrapper">
            <div class="container">
                <%@include file="header.jsp"%>
                <%@include file="navbar.jsp"%>

                <h2>Welcome to My Work Week</h2>


                <div class="card-columns">
                    <div class="card bg-info">
                        <div class="card-body text-center">
                            <h4 class="card-title">Time Off Requests</h4>
                            <p class="card-text">
                                <a class="nav-link" href="timeOffRequestSearch.jsp">View Time Off Request</a>
                            </p>
                            <p class="card-text">
                                <a class="nav-link" href="timeOffRequestAddEdit.jsp">Add Time Off Request</a>
                            </p>
                        </div>
                    </div>
                    <div class="card bg-info">
                        <div class="card-body text-center">
                            <h4 class="card-title">Scheduling</h4>
                            <p class="card-text">
                                <a class="nav-link" href="scheduleSearch.jsp">View Schedule</a>
                            </p>
                            <p class="card-text">
                                <a class="nav-link" href="scheduleAddEdit.jsp">Add Schedule</a>
                            </p>
                        </div>
                    </div>
                    <div class="card bg-info">
                        <div class="card-body text-center">
                            <h4 class="card-title">Users</h4>
                            <p class="card-text">
                                <a class="nav-link" href="userSearch.jsp">View User</a>
                            </p>
                            <p class="card-text">
                                <a class="nav-link" href="userAddEdit.jsp">Add User</a>
                            </p>                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
