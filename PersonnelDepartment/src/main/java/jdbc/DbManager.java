package jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DbManager {

	protected Connection getConnection() throws SQLException {
		try {
			Context initialContext = new InitialContext();
			DataSource datasource =
					(DataSource) initialContext.lookup("java:comp/env/jdbc/PersonnelDB");
			if (datasource != null) {
				return datasource.getConnection();
			}
		} catch (NamingException ex) {
			throw new SQLException("Cannot find datasource", ex);
		}
		return null;
	}

}
