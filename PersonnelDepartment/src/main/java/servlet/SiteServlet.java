package servlet;

import jdbc.Department;
import jdbc.DepartmentDbManager;
import jdbc.Position;
import jdbc.PositionDbManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/site")
public class SiteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			DepartmentDbManager departmentDbManager = new DepartmentDbManager();
			PositionDbManager positionDbManager = new PositionDbManager();

			List<Department> departments = departmentDbManager.getList();
			List<Position> positions = positionDbManager.getList();
			request.setAttribute("departments",departments);
			request.setAttribute("positions",positions);

			request.getRequestDispatcher("index.jsp").forward(request,response);
		} catch (Exception e) {
			throw new ServletException("error",e);
		}
	}

}
