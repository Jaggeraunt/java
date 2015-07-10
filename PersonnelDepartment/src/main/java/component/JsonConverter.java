package component;

import jdbc.Department;
import jdbc.Employee;
import jdbc.Position;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class JsonConverter {

	public static JSONObject toJSON(Department department) {
		JSONObject result = new JSONObject();
		result.put("id", department.getId());
		result.put("name", department.getName());
		return result;
	}

	public static JSONObject toJSON(Position position) {
		JSONObject result = new JSONObject();
		result.put("id", position.getId());
		result.put("name", position.getName());
		result.put("minSalary", position.getMinSalary());
		result.put("maxSalary", position.getMaxSalary());
		return result;
	}

	public static JSONObject toJSON(Employee employee) {
		JSONObject result = new JSONObject();
		result.put("id", employee.getId());
		result.put("passportId",employee.getPassportId());
		result.put("name", employee.getName());
		result.put("birthday", employee.getBirthday().toString());
		result.put("salary", employee.getSalary());
		result.put("department", toJSON(employee.getDepartment()));
		result.put("position", toJSON(employee.getPosition()));
		return result;
	}

	public static JSONArray toJSON(List<Employee> employeeList) {
		JSONArray result = new JSONArray();
		for (Employee e : employeeList) {
			result.put(toJSON(e));
		}
		return result;
	}

	public static JSONArray toJSON(Map<String,String> errors) {
		JSONArray result = new JSONArray();
		for (Map.Entry<String,String> entry : errors.entrySet()) {
			JSONObject error = new JSONObject();
			error.put("field",entry.getKey());
			error.put("value",entry.getValue());
			result.put(error);
		}
		return result;
	}

}
