<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<c:set var="title" value="Add/Edit Time Off Request" />

<html lang="en">
<body>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2 class="text-center">
            <c:if test="${timeOffRequestAction == 'edit'}">
                Edit Time Off Request
            </c:if>
            <c:if test="${timeOffRequestAction == 'add'}">
                Add New Time Off Request
            </c:if>
        </h2><br/>

        <form id="editTimeOffRequestForm" role="form" data-toggle="validator"
              class="form-horizontal"
                <c:if test="${timeOffRequestAction == 'edit'}">
                    action="addEditTimeOffRequestServlet?timeOffRequestAction=${timeOffRequestAction}"
                </c:if>
                <c:if test="${timeOffRequestAction == 'add'}">
                    action="addEditTimeOffRequestServlet?TimeOffRequestAction=${timeOffRequestAction}"
                </c:if>
              onReset="clearForm();
              method="POST">

            <c:if test="${timeOffRequestAction == 'edit'}">
                <input type="hidden"
                       name="id"
                       value="<c:out value='${timeOffRequest.id}' />"
                />
            </c:if>

            <input type="hidden"
                   name="actionToPerform"
                   value="<c:out value='${timeOffRequestAction}' />"
            />


            <div class="row form-group">
                <div class="col-0 col-sm-2"></div>
                <label class="control-label d-inline-block text-right col-2 col-sm-1" for="userName">Username</label>
                <div class="col-10 col-sm-2">
                    <input type="text" class="form-control d-inline-block" id="userName"
                           name="userName"
                            <c:if test="${timeOffRequestAction == 'edit'}">
                                   readonly
                            </c:if>
                           value = "${timeOffRequest.userName}"
                           data-error="Please enter the username.">
                </div>
                <div class="col-0 col-sm-1 "></div>
                <label class="control-label d-inline-block text-right col-2 col-sm-1" for="name">Name</label>
                <div class="col-10 col-sm-2">
                    <input type="text" class="form-control d-inline-block" id="name"
                           name="name"
                           readonly
                           value = "${timeOffRequest.user.getFirstName()} ${timeOffRequest.user.getLastName()}">
                </div>
                <div class="col-0 col-sm-3 "></div>
                <br><br><br>
            </div>

            <div class="row form-group">
                <label class="control-label text-right col-4 col-sm-5" for="startDate">Start Date and Time</label>
                <div class="col-6 col-sm-3">
                    <div class="input-append" >
                        <input data-format="MM/dd/yyyy HH:mm:ss PP" type="datetime-local"
                               id="startDate" name="startDate"
                               value = "${timeOffRequest.startDate}"
                               data-error="Please enter the starting date for time off">
                    </div>
                </div>
                <div class="help-block with-errors col-2 col-sm-4"></div>
                <br><br>
            </div>

            <div class="row form-group">
                <label class="control-label text-right col-4 col-sm-5" for="endDate">Ending Date and time</label>
                <div class="col-6 col-sm-3">
                    <div class="input-append" >
                        <input data-format="MM/dd/yyyy HH:mm:ss PP" type="datetime-local"
                               id="endDate" name="endDate"
                               value = "${timeOffRequest.endDate}"
                               data-error="Please enter the ending date for time off">
                    </div>
                </div>
                <div class="help-block with-errors col-2 col-sm-4"></div>
            </div>

            <div class="row">
                <div class="col-0 col-sm-2"></div>
                <div class="col-12 col-sm-4">
                    <h3 class="text-sm-left">${timeOffRequestUpdateMessage}</h3>
                </div>
                <c:if test="${timeOffRequestUpdateMessage != null}" >
                    <c:remove var="userUpdateMessage"  />
                </c:if>
                <div class="col-0 col-sm-6"></div>
            </div>

            <div class="row">
                <div class="col-4 col-sm-5"></div>
                <div class="col-4 col-sm-2">
                    <button type="submit" class="btn btn-success col-sm-offset-3"
                            data-disable="true">
                        <c:if test="${timeOffRequestAction == 'edit'}">
                            Update
                        </c:if>
                        <c:if test="${timeOffRequestAction == 'add'}">
                            Add
                        </c:if>
                    </button>
                    <button type="reset" class="btn btn-primary col-sm-offset-3">Clear</button>
                    <br><br>
                </div>
                <div class="col-4 col-sm-2"></div>
            </div>
        </form>
    </div>
</div>

</body>
</html>