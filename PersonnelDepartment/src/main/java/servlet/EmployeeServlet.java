package servlet;

import component.EmployeeValidator;
import component.JsonConverter;
import component.Validator;
import jdbc.Employee;
import jdbc.EmployeeDbManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends RestServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EmployeeDbManager manager = new EmployeeDbManager();
			String idStr = request.getParameter("id");
			String depIdStr = request.getParameter("department_id");

			if (depIdStr != null) {

				int departmentId = Integer.parseInt(depIdStr);
				List<Employee> employees = manager.getList(departmentId);
				JSONArray result = JsonConverter.toJSON(employees);
				send(response,result);

			} else if (idStr != null) {

				Employee employee = manager.getByAttribute("id", idStr);
				if (employee != null) {
					JSONObject result = JsonConverter.toJSON(employee);
					send(response,result);
				}

			} else {
				response.sendError(404,"wrong parameter");
			}
		} catch (NumberFormatException e) {
			response.sendError(404,"wrong parameter");
		} catch (SQLException e) {
			throw new ServletException("sql error",e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			String id = request.getParameter("id");
			Validator validator;
			EmployeeDbManager manager = new EmployeeDbManager();

			if (id == null || "".equals(id)) {

				validator = new EmployeeValidator(true);
				if (validator.validate(request)) {
					manager.insert((Employee)validator.getObject());
					sendOk(response);
				} else {
					sendError(response,validator.getErrors());
				}

			} else {

				validator = new EmployeeValidator(false);
				if (validator.validate(request)) {
					int idInt = Integer.parseInt(id);
					manager.update(idInt,(Employee)validator.getObject());
					sendOk(response);
				} else {
					sendError(response,validator.getErrors());
				}

			}
		} catch (Exception e) {}
	}


	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDbManager manager = new EmployeeDbManager();
			manager.delete(id);
			sendOk(response);
		} catch (Exception e){}
	}

}