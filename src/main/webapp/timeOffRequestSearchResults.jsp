<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>

<c:set var="title" value="Time Off Requests Search Results" />
<html lang="en">
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
                <th>Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th></th>
            </tr>
            <c:forEach var="timeOffRequest" items="${timeOffRequests}">
            <tr>
                <td>${timeOffRequest.userName}</td>
                <td>${timeOffRequest.user.getFirstName()} ${timeOffRequest.user.getLastName()}</td>
                <td><fmt:parseDate value="${ timeOffRequest.startDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />
                    <fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${ parsedStartDateTime }" /></td>
                <td><fmt:parseDate value="${ timeOffRequest.endDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
                    <fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${ parsedEndDateTime }" /></td>
                <td>
                    <c:if test="${timeOffRequestsDisplayOnly == null}" >
                    <a class="edit" title="Edit" data-toggle="tooltip" href="addEditTimeOffRequestServlet?timeOffRequestAction=edit&id=${timeOffRequest.id}"><i class="fa" >&#xf044;</i></a>  <!--Font Awesome Edit symbol -->
                    </c:if>
                    <c:if test="${timeOffRequestsDisplayOnly != null}" >
                        <c:remove var="timeOffRequestsDisplayOnly"  />
                    </c:if>
                </td>
            </tr>
            </c:forEach>
    </div>
</div>

</body>
</html>