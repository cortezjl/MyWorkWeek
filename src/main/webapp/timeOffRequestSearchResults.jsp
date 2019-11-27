<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Time Off Requests Search Results" />
<html>
<body>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#timeOffRequestTable').DataTable();
    } );
</script>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2 class="text-center">Time Off Requests</h2>
        <table id="timeOffRequestTable"  class="table table-striped table-bordered display dt-responsive nowrap"  >
            <tr><th>User Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th></th>
            </tr>
            <c:forEach var="timeOffRequest" items="${timeOffRequests}">
            <tr>
                <td>${timeOffRequest.userName}</td>
                <td>${timeOffRequest.startDate}</td>
                <td>${timeOffRequest.endDate}</td>
                <td>${timeOffRequest.startTime}</td>
                <td>${timeOffRequest.endTime}</td>
                <td>
                    <a class="edit" title="Edit" data-toggle="tooltip" href="addEditTimeOffRequestServlet?timeOffRequestAction=edit&id=${timeOffRequest.id}"><i class="fa" >&#xf044;</i></a>  <!--Font Awesome Edit symbol -->
                </td>
            </tr>
            </c:forEach>
    </div>
</div>
</body>
</html>