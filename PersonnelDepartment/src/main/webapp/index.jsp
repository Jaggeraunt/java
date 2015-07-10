<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Personnel Department</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/app.js"></script>
</head>
<body>

<h1 class="header">Personnel department</h1>

<div class="department">
    <div class="department-content">
        <div class="list-group">
            <c:forEach var="d" items="${requestScope.departments}">
                <a href="#" class="list-group-item" dep-id="<c:out value="${d.id}"/>">
                    <span class="list-group-item-heading"><c:out value="${d.name}"/></span>
                </a>
            </c:forEach>
        </div>
        <a href="#" id="department-create" class="btn btn-primary">Add department</a>
    </div>
    <br/><br/>
</div>

<div class="workers">
    <jsp:include page="parts/employeeList.jsp"></jsp:include>
    <a href="#" id="employee-create" class="btn btn-primary pull-right">Add employee</a>
</div>

<!-- modal -->
<jsp:include page="parts/employeeEdit.jsp"></jsp:include>
<jsp:include page="parts/employeeDelete.jsp"></jsp:include>
<jsp:include page="parts/departmentEdit.jsp"></jsp:include>

</body>
</html>