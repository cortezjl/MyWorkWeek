<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2>User Search</h2>

        <form name="userSearchForm"
              action="searchUser"
              onReset="clearForm();">

            <div>
                <p class="font-weight-bold"><br>Search Type: </p>
            </div>

            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="searchType" value="allUsers" checked>All Users
                </label>
            </div>
            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="searchType" value="activeUsers">Active Users
                </label>
            </div>
            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input mb-sm-3" name="searchType" value="qualifyingUsers">By Search Value
                </label>
            </div>

            <div>
                <p class="mt-sm-3 font-weight-bold">
                    <br>
                    Search Field:
                </p>
            </div>

            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="searchField" value="firstName">First Name
                </label>
            </div>
            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="searchField" value="lastName">Last Name
                </label>
            </div>
            <div class="form-check-inline">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="searchField" value="userName">User Name
                </label>
            </div>

            <div>
                <p class="mt-sm-3 font-weight-bold">
                    <br>
                    Search Value:
                </p>
            </div>
            <div class="mt-sm-3">
                <input type="text" size="25" class="form-control" name="searchValue" id="searchValue" p>
            </div>
            <div>
                <p class="mt-sm-9"></p>
            </div>
            <button type="submit" class="btn btn-success mr-sm-4 mt-sm-4 mb-sm-3">Submit</button>
            <button type="reset" class="btn btn-primary mt-sm-4 mb-sm-3">Clear</button>

        </form>
    </div>
</div>
</body>
</html>