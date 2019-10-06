<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
        <div class="wrapper">
            <div class="container-fluid">
                <%@include file="header.jsp"%>
                <%@include file="navbar.jsp"%>

                <h2 class="text-center">Welcome to My Work Week</h2>


                <div class="card-columns">
                    <div class="card bg-info">
                        <div class="card-body text-center">
                            <h4 class="card-title">Time Off Requests</h4>
                            <p>
                                <a class="card-link text-dark" href="timeOffRequestSearch.jsp">View Time Off Request</a>
                            </p>
                            <p>
                                <a class="card-link text-dark" href="timeOffRequestAddEdit.jsp">Add Time Off Request</a>
                            </p>
                        </div>
                    </div>
                    <div class="card bg-info">
                        <div class="card-body text-center">
                            <h4 class="card-title">Scheduling</h4>
                            <p>
                                <a  class="card-link text-dark" href="scheduleSearch.jsp">View Schedule</a>
                            </p>
                            <p>
                                <a  class="card-link text-dark"href="scheduleAddEdit.jsp">Add Schedule</a>
                            </p>
                        </div>
                    </div>

                    <div class="card bg-info">
                        <div class="card-body text-center">
                            <h4 class="card-title">Users</h4>
                            <p>
                                <a class="card-link text-dark" href="userSearch.jsp">View Users</a>
                            </p>
                            <p>
                                <a class="card-link text-dark"href="userAddt.jsp">Add Users</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
