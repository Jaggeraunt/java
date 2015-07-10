package servlet;

import component.DepartmentValidator;
import component.Validator;
import jdbc.Department;
import jdbc.DepartmentDbManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/department")
public class DepartmentServlet extends RestServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		DepartmentDbManager manager = new DepartmentDbManager();
		Validator validator = new DepartmentValidator(true);

		try {
			if (validator.validate(request)) {
				manager.insert((Department)validator.getObject());
				sendOk(response);
			} else {
				sendError(response,validator.getErrors());
			}
		} catch (Exception e){}

	}

}
