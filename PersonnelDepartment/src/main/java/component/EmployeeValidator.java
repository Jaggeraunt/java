package component;

import jdbc.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class EmployeeValidator extends Validator {

	private Employee employee = null;

	public EmployeeValidator(boolean isNew) {
		super(isNew);
	}

	@Override
	public boolean validate(HttpServletRequest request) {
		String name = request.getParameter("name");
		String idStr = request.getParameter("id");
		String passportIdStr = request.getParameter("passport_id");
		String birthdayStr = request.getParameter("birthday");
		String salaryStr = request.getParameter("salary");
		String departmentIdStr = request.getParameter("department_id");
		String positionIdStr = request.getParameter("position_id");

		EmployeeDbManager employeeDbManager = new EmployeeDbManager();
		PositionDbManager positionDbManager = new PositionDbManager();
		DepartmentDbManager departmentDbManager = new DepartmentDbManager();

		isEmpty("name", name);

		Date birthday = null;
		if (!isEmpty("birthday",birthdayStr)) {
			birthday = toDate("birthday",birthdayStr);
		}

		int id = 0;
		if (!isNew && !isEmpty("id",idStr)) {
			id = toInt("id",idStr);
		}

		int departmentId = 0;
		Department department = null;
		if (!isEmpty("departmentId",departmentIdStr)) {
			departmentId = toInt("departmentId",departmentIdStr);
			if (departmentId != 0) {
				try {
					department = departmentDbManager.getByAttribute("id",departmentIdStr);
				} catch(Exception e){}
			}
		}

		int positionId = 0;
		Position position = null;
		if (!isEmpty("positionId",positionIdStr)) {
			positionId = toInt("positionId",positionIdStr);
			if (positionId != 0) {
				try {
					position = positionDbManager.getById(positionId);
				} catch (Exception e){}
			}
		}

		long passportId = 0;
		if (!isEmpty("passportId",passportIdStr)) {
			passportId = toLong("passportId",passportIdStr);
			try {
				Employee e = employeeDbManager.getByAttribute("passportId",passportIdStr);
				if (e != null) {
					if (isNew || e.getId() != id) {
						addError("passportId","This passport ID has already been taken");
					}
				}
			} catch (Exception e){}
		}

		int salary = 0;
		if (!isEmpty("salary",salaryStr)) {
			salary = toInt("salary",salaryStr);
			if (salary != 0 && position != null) {
				try {
					if (salary < position.getMinSalary() || salary > position.getMaxSalary()) {
						String error = String.format("For this position salary can be from %d to %d",position.getMinSalary(),position.getMaxSalary());
						addError("salary",error);
					}
				} catch (Exception e){}
			}
		}

		if (!hasError()) {
			employee = new Employee(id,passportId,name,birthday,salary,department,position);
		}

		return !hasError();
	}

	public Object getObject() {
		return employee;
	}

}