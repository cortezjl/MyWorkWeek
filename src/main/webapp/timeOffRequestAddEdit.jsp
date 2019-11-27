<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Add/Edit Time Off Request" />

<html>

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
                <div class="col-0 col-sm-4"></div>
                <div class="col-1 col-sm-1 ">
                    <label class="control-label d-inline-block" for="userName">Username</label>
                </div>
                <div class="col-4 col-sm-3">
                    <input type="text" class="form-control d-inline-block" id="userName"
                           name="userName"
                           required="required"
                           value = "${timeOffRequest.userName}"
                           data-error="Please enter the username."
                           required>
                </div>
                <div class="help-block with-errors col-1 col-sm-1"></div>
                <div class="col-7 col-sm-3 "></div>
            </div>

            <div class="row form-group">
                <div class="col-0 col-sm-4"></div>
                <label class="control-label col-1 col-sm-1" for="startDate">Start Date</label>
                <div class="col-4 col-sm-3">
                    <div class="input-group date" data-provide="datepicker">
                        <input type="text" class="form-control" id="startDate" name="startDate"
                               value = "${timeOffRequest.getStartDateForDisplay()}" data-error="Please enter the starting date for time off"
                               required>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-th"></span>
                        </div>
                    </div>
                </div>
                <div class="help-block with-errors col-1 col-sm-1"></div>
                <div class="col-7 col-sm-3"></div>
            </div>

            <div class="row form-group">
                <div class="col-0 col-sm-4"></div>
                <label class="control-label col-1 col-sm-1" for="endDate">End Date</label>
                <div class="col-4 col-sm-3">
                    <div class="input-group date" data-provide="datepicker">
                        <input type="text" class="form-control" id="endDate" name="startDate"
                               value = "${timeOffRequest.getEndDateForDisplay()}" data-error="Please enter the ending date for time off"
                               required>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-th"></span>
                        </div>
                    </div>
                </div>
                <div class="help-block with-errors col-1 col-sm-1"></div>
                <div class="col-7 col-sm-3"></div>
            </div>

            <div class="row form-group">
                <div class="col-0 col-sm-4"></div>
                <label class="control-label col-1 col-sm-1" for="startTime">Start Time</label>
                <div class="col-4 col-sm-3">
                    <div class="input-group" >
                        <input type="time" class="form-control" id="startTime" name="startTime"
                               value = "${timeOffRequest.startTime}" data-error="Please enter the starting time"
                               required>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-th"></span>
                        </div>
                    </div>
                </div>
                <div class="help-block with-errors col-7 col-sm-4"></div>
            </div>

            <div class="row form-group">
                <div class="col-0 col-sm-4"></div>
                <label class="control-label col-1 col-sm-1" for="endTime">End Time</label>
                <div class="col-4 col-sm-3">
                    <div class="input-group" >
                        <input type="time" class="form-control" id="endTime" name="endTime"
                               value = "${timeOffRequest.endTime}" data-error="Please enter the ending time"
                               required>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-th"></span>
                        </div>
                    </div>
                </div>
                <div class="help-block with-errors col-7 col-sm-4"></div>
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
            <button type="submit" class="btn btn-primary col-sm-offset-3"
                    data-disable="true">
                <c:if test="${timeOffRequestAction == 'edit'}">
                    Update
                </c:if>
                <c:if test="${timeOffRequestAction == 'add'}">
                    Add
                </c:if>
            </button>
        </div>
        <div class="col-4 col-sm-5"></div>
    </div>
    </form>
</div>
</div>

</body>

</html>