package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDbManager extends DbManager {

	public List<Department> getList() throws SQLException {
		try (Connection connection = getConnection();
			Statement statement = connection.createStatement()
		){
			ResultSet rs = statement.executeQuery("select id, name from department");
			List<Department> departments = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				departments.add(new Department(id,name));
			}
			return departments;
		}
	}

	public Department getByAttribute(String fieldName, String value) throws SQLException {
		String statementStr;
		if ("name".equals(fieldName)) {
			statementStr = "select id, name from department where name = ?";
		} else {
			statementStr = "select id, name from department where id = ?";
		}

		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(statementStr)
		){
			statement.setString(1,value);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);

				return new Department(id,name);
			}
			return null;
		}
	}

	public void insert(Department department) throws SQLException {
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(
				     "insert into department(name) " +
						     "values(?)"
		     );
		){

			statement.setString(1,department.getName());
			statement.execute();
		}
	}
}
