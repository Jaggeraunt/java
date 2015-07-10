package jdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class EmployeeDbManager extends DbManager {

	public List<Employee> getList(int departmentId) throws SQLException {
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(
				     "select id, passport_id, name, birthday, salary, position_id from employee where department_id = ?"
		     );
		){
			statement.setInt(1, departmentId);
			ResultSet rs = statement.executeQuery();
			List<Employee> employees = new ArrayList<>();

			DepartmentDbManager departmentDbManager = new DepartmentDbManager();
			PositionDbManager positionDbManager = new PositionDbManager();

			while (rs.next()) {
				int id = rs.getInt(1);
				long passportId = rs.getLong(2);
				String name = rs.getString(3);
				Date birthday = rs.getDate(4);
				int salary = rs.getInt(5);
				int positionId = rs.getInt(6);
				Department d = departmentDbManager.getByAttribute("id",String.valueOf(departmentId));
				Position p = positionDbManager.getById(positionId);

				employees.add(new Employee(id,passportId,name,birthday,salary,d,p));
			}
			return employees;
		}
	}

	public Employee getByAttribute(String fieldName, String value) throws SQLException {
		String statementStr;
		if ("passportId".equals(fieldName)) {
			statementStr = "select id, passport_id, name, birthday, salary, department_id, position_id from employee where passport_id = ?";
		} else {
			statementStr = "select id, passport_id, name, birthday, salary, department_id, position_id from employee where id = ?";
		}
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(statementStr);
		){
			statement.setString(1, value);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				DepartmentDbManager departmentDbManager = new DepartmentDbManager();
				PositionDbManager positionDbManager = new PositionDbManager();

				int id = rs.getInt(1);
				long passportId = rs.getLong(2);
				String name = rs.getString(3);
				Date birthday = rs.getDate(4);
				int salary = rs.getInt(5);
				int departmentId = rs.getInt(6);
				int positionId = rs.getInt(7);
				Department d = departmentDbManager.getByAttribute("id",String.valueOf(departmentId));
				Position p = positionDbManager.getById(positionId);

				return new Employee(id,passportId,name,birthday,salary,d,p);
			}
			return null;
		}
	}

	public void insert(Employee e) throws SQLException {
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(
				     "insert into employee(passport_id,name,birthday,salary,department_id,position_id) " +
						     "values(?, ?, ?, ?, ?, ?)"
		     );
		){

			statement.setLong(1,e.getPassportId());
			statement.setString(2, e.getName());
			java.sql.Date birthday = new java.sql.Date(e.getBirthday().getTime());
			statement.setDate(3,birthday);
			statement.setInt(4,e.getSalary());
			statement.setInt(5,e.getDepartment().getId());
			statement.setInt(6,e.getPosition().getId());
			statement.execute();
		}

	}

	public void update(int id, Employee e) throws SQLException {
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(
				     "update employee set passport_id = ?, name = ?, birthday = ?, salary = ?, department_id = ?, position_id = ? where id = ?"
		     );
		){

			statement.setLong(1,e.getPassportId());
			statement.setString(2, e.getName());
			java.sql.Date birthday = new java.sql.Date(e.getBirthday().getTime());
			statement.setDate(3,birthday);
			statement.setInt(4, e.getSalary());
			statement.setInt(5,e.getDepartment().getId());
			statement.setInt(6,e.getPosition().getId());
			statement.setInt(7,id);
			statement.execute();
		}

	}	
	public void delete(int id) throws SQLException {
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement("delete from employee where id = ?");
		){
			statement.setInt(1,id);
			statement.execute();
		}

	}
}