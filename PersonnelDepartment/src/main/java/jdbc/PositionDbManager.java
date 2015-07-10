package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDbManager extends DbManager {

	public List<Position> getList() throws SQLException {
		try (Connection connection = getConnection();
		     Statement statement = connection.createStatement()
		){
			ResultSet rs = statement.executeQuery("select id, name, min_salary, max_salary from position");
			List<Position> positions = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int minSalary = rs.getInt(3);
				int maxSalary = rs.getInt(4);
				positions.add(new Position(id,name,minSalary,maxSalary));
			}
			return positions;
		}
	}

	public Position getById(int id) throws SQLException {
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(
				     "select name, min_salary, max_salary from position where id = ?"
		     );
		){
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String name = rs.getString(1);
				int minSalary = rs.getInt(2);
				int maxSalary = rs.getInt(3);
				return new Position(id,name,minSalary,maxSalary);
			}
			return null;
		}
	}


}
