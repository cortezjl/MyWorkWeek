<nav class="row navbar navbar-expand-md bg-info navbar-dark">
    <!-- Branding -->
    <a class="navbar-brand" href="#">MyWorkWeek</a>

    <!-- Toggler/collapsibe Button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Navbar links -->
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
          <!--  <li class="nav-item"><a class="nav-link" href="timeOffRequestSearch.jsp">Time Off Requests</a></li> -->
            <!-- Dropdown links for Time Off Requests-->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="timeoffnavbardrop" data-toggle="dropdown">Time Off Requests</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="timeOffRequestSearch.jsp">View Time Off Request</a>
                    <a class="dropdown-item" href="addEditTimeOffRequestServlet?timeOffRequestAction=add&">Add Time Off Request</a>
                </div>
            </li>
            <li class="nav-item"><a class="nav-link" href="schedules.jsp">Scheduling</a></li>
            <!--<li class="nav-item"><a class="nav-link" href="userSearch.jsp">Users</a></li> -->
            <!-- Dropdown links for Users-->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="usernavbardrop" data-toggle="dropdown">Users</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="userSearch.jsp">View/Modify User</a>
                    <a class="dropdown-item" href="addEditUserServlet?userAction=add&">Add User</a>
                </div>
            </li>
        </ul>

        <!-- get user name if user logged into session.  Will use that value to set either login or logout icon
        getSession(false) will check existence of session. If session exists, then it returns the reference of that
        session object, if not, this methods will return null   -->

        <!--if(sessionUserName!=null && request.getSession(false)!=null && session.getAttribute("remoteUser")==null) -->

        <%
            String sessionUserName = request.getRemoteUser();
            session.setAttribute("remoteUser", sessionUserName);
        %>

        <c:if test="${remoteUser == null}">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" ><a class="nav-link" href="loginAction"><i class="fa fa-fw fa-sign-in"></i>Login</a></li><!--uses font awesome sign-in icon -->
            </ul>
        </c:if>

        <c:if test="${remoteUser != null}">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" ><i class="fa fa-fw"></i>Welcome ${remoteUser}</li><!--uses font awesome sign-out icon -->
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" ><a class="nav-link" href="logout"><i class="fa fa-fw fa-sign-out"></i>Logout</a></li><!--uses fixed width font awesome sign-in icon -->
            </ul>
        </c:if>


    </div>   <!-- close Navbar links -->
</nav>