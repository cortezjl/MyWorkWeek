<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<c:set var="title" value="Schedule Search Results" />
<html lang="en">
<body>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#scheduleTable').DataTable();
    } );
</script>

<div class="wrapper">
    <div class="container-fluid">
        <%@include file="header.jsp"%>
        <%@include file="navbar.jsp"%>
        <h2 class="text-center">Schedules</h2>
        <table id="scheduleTable"  class="table table-striped table-bordered display dt-responsive nowrap"  >
        <tr><th>Schedule Id</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th></th>
        </tr>
        <c:forEach var="schedule" items="${schedules}">
            <tr>
                <td>${schedule.id}</td>
                <td><fmt:parseDate value="${schedule.startDate}" pattern="yyyy-MM-dd"  var="parsedStartDate" type="date" />
                    <fmt:formatDate pattern="MM/dd/yyyy" value="${ parsedStartDate }" type="date"  /></td>
                <td><fmt:parseDate value="${schedule.endDate}" pattern="yyyy-MM-dd"  var="parsedEndDate" type="date" />
                    <fmt:formatDate pattern="MM/dd/yyyy" value="${ parsedEndDate }"  type="date" /></td>
                <td>
                    <a class="edit" title="Edit" data-toggle="tooltip" href="addEditScheduleServlet?userAction=edit&id=${schedule.id}"><i class="fa" >&#xf044;</i></a>  <!--Font Awesome Edit symbol -->
                </td>
            </tr>
        </c:forEach>
    </div>
</div>

</body>
</html>