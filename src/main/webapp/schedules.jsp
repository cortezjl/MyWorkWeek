<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<c:set var="title" value="Schedules" />
<script src="js/schedules.js"></script>
<c:set var="title" value="Schedules" />
<html lang="en">
<body>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2 class="text-center">Schedules</h2>

        <div class="justify-content-center align-items-center">

            <form name="schedulesForm"
                  action="searchSchedule"
                  onReset="clearForm();">

                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <p class="font-weight-bold"><br>Action to perform: </p>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>

                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="searchType" value="findAllSchedules" checked>Find All Schedules
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input mb-sm-3" name="searchType" value="findByStartDate">Find Schedule by Date
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input mb-sm-3" name="searchType" value="addSchedule">Add Schedule
                            </label>
                        </div>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>


                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <br><br>
                        <p class="font-weight-bold">Starting Date (Required to find or add a Schedule)</p>
                    </div>

                    <div class="col-0 col-md-4"></div>
                </div>

                <div class="row">
                    <div class="col-0 col-md-4"></div>

                    <div class="col-12 col-md-4">
                        <input type="date" class="form-control" name="startDate" id="startDate" >
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