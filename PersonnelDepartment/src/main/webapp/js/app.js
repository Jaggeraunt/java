$(document).ready(function() {
    $('.list-group a:first').addClass('active');
    var dep_id = $('.active').attr('dep-id');
    loadWorkers(dep_id);

    $('.list-group-item').click(function(){
        $('.active').toggleClass('active');
        $(this).addClass('active');
        var dep_id = $(this).attr('dep-id');
        loadWorkers(dep_id);
    });

    $('#employee-save').click(function(){
        saveEmployee();
    });

    $('#employee-create').click(function(){
        clearEmplForm();
        $('span.error-text').html('');
        $('.has-error').toggleClass('has-error');
        $('#editEmplModal').modal();
    });

    $('#employee-delete').click(function(){
        id = $('#empl-id').val();
        $.ajax({
            method: 'delete',
            url: '/employee?id=' + id
        });
        var dep_id = $('.active').attr('dep-id');
        loadWorkers(dep_id);
        $('#deleteEmplModal').modal('hide');
    });

    $('#department-create').click(function(){
        clearErrors();
        $('#editDepModal').modal();
    });

    $('#department-save').click(function(){
        saveDepartment();
    });

});

function loadWorkers(dep_id) {

    $.ajax({
        method: 'GET',
        url: '/employee?department_id=' + dep_id,
        success: function(data) {
            render(data);
        }
    });
}

function render(workers) {
    var html = '';
    var body = $('.workers-list');
    $.each(workers, function(index, value){
        html += '<tr empl-id="' + value.id + '">';
        html += '<td>' + value.name + '</td>';
        html += '<td>' + value.position.name + '</td>';
        html += '<td><a onclick="editEmployee(this)" href="#">edit</a></td>';
        html += '<td><a onclick="deleteEmployee(this)" href="#">delete</a></td>';
        html += '</tr>';
    });
    body.html(html);
}

function editEmployee(el) {
    var id = $(el).parent('td').parent('tr').attr('empl-id');
    getEmployee(id);
    $('#editEmplModal').modal();
}

function deleteEmployee(el) {
    var id = $(el).parent('td').parent('tr').attr('empl-id');
    $('#empl-id').val(id);
    $('#deleteEmplModal').modal();
}

function saveEmployee() {
    var form = collectEmplForm();
    clearErrors();

    $.ajax({
        method: 'POST',
        url: '/employee',
        data: form,
        success: function(response) {
            if (response.status == 'ok') {
                $('#editEmplModal').modal('hide');
                var dep_id = $('.active').attr('dep-id');
                loadWorkers(dep_id);
            } else {
                showEmplErrors(response.errors);
            }
        }
    })
}

function getEmployee(id) {
    $.ajax({
        method: 'GET',
        url: '/employee?id=' + id,
        success: function(response) {
            fillEmplForm(response);
        }
    });
}

function collectEmplForm() {
    var form = {
        id: $('#e-id').val(),
        name: $('#e-name').val(),
        passport_id: $('#e-passport').val(),
        birthday: $('#e-birthday').val(),
        salary: $('#e-salary').val(),
        department_id: $('#e-department').val(),
        position_id: $('#e-position').val()
    };
    return form;
}

function fillEmplForm(empl) {
    $('#e-id').val(empl.id);
    $('#e-name').val(empl.name);
    $('#e-passport').val(empl.passportId);
    $('#e-birthday').val(empl.birthday);
    $('#e-salary').val(empl.salary);
    $('#e-department').val(empl.department.id);
    $('#e-position').val(empl.position.id);
}

function clearEmplForm() {
    $('#e-id').val(null);
    $('#e-name').val(null);
    $('#e-passport').val(null);
    $('#e-birthday').val(null);
    $('#e-salary').val(null);
    $('#e-department').val(null);
    $('#e-position').val(null);
}

function showEmplErrors(errors) {
    $(errors).each(function(key, obj){
        var el = $('[field=\'e-' + obj.field + '\'].error-text');
        el.html(obj.value);
        el.parent().addClass('has-error');
    });
}

function clearErrors() {
    $('span.error-text').html('');
    $('.has-error').toggleClass('has-error');
}

function saveDepartment() {
    var name = $('#d-name').val();
    $.ajax({
        method: 'post',
        url: '/department',
        data: {"name" : name},
        success: function(response) {
            if (response.status == 'ok') {
                location.reload();
            } else {
                var el = $('[field=\'d-name\'].error-text');
                el.html(response.errors[0].value);
                el.parent().addClass('has-error');

            }
        }
    });
}

function clearDepForm() {
    $('#d-name').val('');
}