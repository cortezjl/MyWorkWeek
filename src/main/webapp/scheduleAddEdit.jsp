<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html lang="en">
<body>
<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2 class="text-center">
            <c:if test="${userAction == 'edit'}">
                Edit Weekly Schedule
            </c:if>
            <c:if test="${userAction == 'add'}">
                Add Weekly Schedule
            </c:if>
        </h2><br/>

        <form:form id="editScheduleForm" role="form" data-toggle="validator"
                   class="form-horizontal"
                    <c:if test="${userAction == 'edit'}">
                        action="addEditTimeOffRequestServlet?timeOffRequestAction=${userAction}"
                    </c:if>
                    <c:if test="${userAction == 'add'}">
                        action="addEditTimeOffRequestServlet?TimeOffRequestAction=${userAction}"
                    </c:if>
                    method="POST">

        <input type="hidden"
               name="actionToPerform"
               value="<c:out value='${userAction}' />"
        />


        <div class="row form-group">
            <label class="control-label d-inline-block text-right col-2 col-sm-4" for="startDate">For week starting</label>
            <div class="col-10 col-sm-1">
                <input type="text" class="form-control d-inline-block" id="startDate"
                       name="startDate" readonly
                <c:if test="${userAction == 'edit'}">
                       readonly
                </c:if>
                       value = "${schedule.getStartDateForDisplay()}"
                       data-error="Please enter the username.">
            </div>
            <div class="col-0 col-sm-1 "></div>
            <label class="control-label d-inline-block text-right col-2 col-sm-1" for="name">Week Ending</label>
            <div class="col-9 col-sm-2">
                <input type="text" class="form-control d-inline-block" id="name"
                       name="name"
                       readonly
                       value = "${schedule.getEndDateForDisplay()}">
            </div>
            <div class="col-1 col-sm-3 ">
                <!-- TODO Add link to displays time off requests for the schedule week in a new window -->
            </div>
            <br><br><br>
        </div>
        <table id="scheduleTable"  class="table table-striped table-bordered display dt-responsive nowrap"  >
            <tr>${tableHeader}</tr>
        <c:forEach var="scheduleDetail" items="${schedule.scheduleDetails}">
                <div class="col-0 col-sm-2"></div>
                <input type="hidden" id="roleId"
                       name="scheduleDetailId"
                       value="${scheduleDetail.id}">
                <tr>
                    <td><input value = "${scheduleDetail.user.getFirstName()} ${scheduleDetail.user.getLastName()}" readonly ></td>
                    <td><input value = "${scheduleDetail.startTime1}" size="8"/></td>
                    <td><input value = "${scheduleDetail.startTime2}" size="8"/></td>
                    <td><input value = "${scheduleDetail.startTime3}" size="8"/></td>
                    <td><input value = "${scheduleDetail.startTime4}" size="8"/></td>
                    <td><input value = "${scheduleDetail.startTime5}" size="8"/></td>
                    <td><input value = "${scheduleDetail.startTime6}" size="8"/></td>
                    <td><input value = "${scheduleDetail.startTime7}" size="8"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input value = "${scheduleDetail.endTime1}" size="8"/></td>
                    <td><input value = "${scheduleDetail.endTime2}" size="8"/></td>
                    <td><input value = "${scheduleDetail.endTime3}" size="8"/></td>
                    <td><input value = "${scheduleDetail.endTime4}" size="8"/></td>
                    <td><input value = "${scheduleDetail.endTime5}" size="8"/></td>
                    <td><input value = "${scheduleDetail.endTime6}" size="8"/></td>
                    <td><input value = "${scheduleDetail.endTime7}" size="8"/></td>
                </tr>
            </c:forEach>
        </table>
        <div class="row">
            <div class="col-0 col-sm-2"></div>
            <div class="col-12 col-sm-4">
                <h3 class="text-sm-left">${schedulepdateMessage}</h3>
            </div>
            <c:if test="${scheduleUpdateMessage != null}" >
                <c:remove var="scheduleUpdateMessage"  />
            </c:if>
            <div class="col-0 col-sm-6"></div>
        </div>

        <div class="row">
            <div class="col-4 col-sm-5"></div>
            <div class="col-4 col-sm-2">
                <button type="submit" class="btn btn-primary col-sm-offset-3"
                        data-disable="true">
                    <c:if test="${schedule != null}">
                        Update
                    </c:if>
                    <c:if test="${schedule == null}">
                        Add
                    </c:if>
                </button>
            </div>
            <div class="col-4 col-sm-5"></div>
        </div>

    </div>
</div>
</body>
</html>