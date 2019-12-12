<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<c:set var="title" value="Index" />
<html lang="en">
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
                        <div>
                            <a href="timeOffRequestSearch.jsp">
                                <img class="img-fluid" id="headerPicture"
                                     src="images/kisspng-computer-icons-calendar-date-iconfinder-calendar-date-event-month-icon-5ab06bc5db9aa6.0629959215215113658995.jpg"
                                     alt="Calendar image">
                            </a>
                        </div>
                        <br> <br>
                        <a class="card-link text-dark" href="timeOffRequestSearch.jsp">View Time Off Request</a>
                        <a class="card-link text-dark" href="addEditTimeOffRequestServlet?timeOffRequestAction=add&">Add Time Off Request</a>
                    </div>
                </div>

                <div class="card bg-info">
                    <div class="card-body text-center">
                        <h4 class="card-title">Scheduling</h4>
                        <div>
                            <a href="schedules.jsp">
                                <img class="img-fluid" src="images/calendar-blue-152-262206.webp"
                                     alt="Calendar image">
                            </a>
                        </div>
                        <br> <br>
                            <a  class="card-link text-dark" href="schedules.jsp">View or Add Schedule</a>

                    </div>
                </div>

                <div class="card bg-info">
                    <div class="card-body text-center">
                        <h4 class="card-title">Users</h4>
                        <div>
                            <a href="userSearch.jsp">
                                <img class="img-fluid" src="images/Users-icon.png"
                                     alt="Users image">
                            </a>
                        </div>
                        <br> <br>
                        <a class="card-link text-dark" href="userSearch.jsp">View Users</a>
                        <a class="card-link text-dark" href="addEditUserServlet?userAction=add&">Add Users</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
