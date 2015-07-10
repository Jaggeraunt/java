<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="editDepModal" tabindex="-1" role="dialog" aria-labelledby="editDepModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content employee-form">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="editDepModalLabel">Add department</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label" for="d-name">Name</label>
                    <span class="error-text" field="d-name"></span>
                    <input type="input" class="form-control" id="d-name" placeholder="Name">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="department-save" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>