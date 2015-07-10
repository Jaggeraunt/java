<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="editEmplModal" tabindex="-1" role="dialog" aria-labelledby="editEmplModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content employee-form">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="editEmplModalLabel">Edit employee</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label" for="e-name">Name</label>
                    <span class="error-text" field="e-name"></span>
                    <input type="input" class="form-control" id="e-name" placeholder="Name">
                </div>
                <div class="form-group">
                    <label class="control-label" for="e-passport">Passport</label>
                    <span class="error-text" field="e-passportId"></span>
                    <input type="input" class="form-control" id="e-passport" placeholder="Passport ID">
                </div>
                <div class="form-group">
                    <label class="control-label" for="e-birthday">Birthday</label>
                    <span class="error-text" field="e-birthday"></span>
                    <input type="input" class="form-control" id="e-birthday" placeholder="yyyy-mm-dd">
                </div>
                <div class="form-group">
                    <label class="control-label" for="e-salary">Salary</label>
                    <span class="error-text" field="e-salary"></span>
                    <input type="input" class="form-control" id="e-salary" placeholder="Salary">
                </div>
                <div class="form-group">
                    <label class="control-label" for="e-department">Department</label>
                    <span class="error-text" field="e-departmentId"></span>
                    <select class="form-control" id="e-department">
                        <c:forEach var="d" items="${requestScope.departments}">
                            <option value="<c:out value="${d.id}"/>">
                                <c:out value="${d.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="control-label" for="e-position">Position</label>
                    <span class="error-text" field="e-positionId"></span>
                    <select class="form-control" id="e-position">
                        <c:forEach var="p" items="${requestScope.positions}">
                            <option value="<c:out value="${p.id}"/>">
                                <c:out value="${p.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="hidden" id="e-id">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="employee-save" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>