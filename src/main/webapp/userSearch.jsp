<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<c:set var="title" value="userSearch" />
<script src="js/userSearch.js"></script>
<html lang="en">
<body>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2 class="text-center">User Search</h2>

        <div class="justify-content-center align-items-center">

            <form name="userSearchForm"
                  action="searchUser"
                  onReset="clearForm();">

                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <p class="font-weight-bold"><br>Search Type: </p>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>

                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
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
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>


                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <br><br>
                        <p class="font-weight-bold">Search Field:</p>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>

                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
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
                    </div>
                </div>


                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <br><br>
                        <p class="font-weight-bold">Search Value:</p>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>

                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <input type="text" class="form-control" name="searchValue" id="searchValue" p>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>


                <div class="row">
                    <div class="col-0 col-md-4"></div>
                    <br>
                    <div class="col-12 col-md-4">
                        <button type="submit" class="btn btn-success mr-sm-4 mt-sm-4 mb-sm-3">Submit</button>
                        <button type="reset" class="btn btn-primary mt-sm-4 mb-sm-3">Clear</button>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>