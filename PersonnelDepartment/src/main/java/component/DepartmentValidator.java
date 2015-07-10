package component;

import jdbc.Department;
import jdbc.DepartmentDbManager;

import javax.servlet.http.HttpServletRequest;

public class DepartmentValidator extends Validator {

	private Department department = null;

	public DepartmentValidator(boolean isNew) {
		super(isNew);
	}

	public boolean validate(HttpServletRequest request) {
		String name = request.getParameter("name");
		DepartmentDbManager manager = new DepartmentDbManager();

		if (!isEmpty("name",name)) {
			try {
				Department d = manager.getByAttribute("name",name);
				if (d != null) {
					addError("name","This name has already been taken");
				}
			} catch (Exception e){}
		}

		int id = 0;

		if (!hasError()) {
			department = new Department(id,name);
		}

		return !hasError();
	}

	public Object getObject() {
		return department;
	}

}
