<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="deleteEmplModal" tabindex="-1" role="dialog" aria-labelledby="deleteEmplModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content employee-form">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <input type="hidden" id="empl-id">
            </div>
            <div class="modal-body">
                <h4 class="modal-title" id="deleteEmplModalLabel">Are you sure?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                <button type="button" id="employee-delete" class="btn btn-primary">Yes</button>
            </div>
        </div>
    </div>
</div>