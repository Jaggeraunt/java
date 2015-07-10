package servlet;

import component.JsonConverter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RestServlet extends HttpServlet {

	protected void send(HttpServletResponse response, JSONObject object) throws IOException {
		response.setContentType("application/json");
		response.getWriter().print(object);
	}

	protected void send(HttpServletResponse response, JSONArray object) throws IOException {
		response.setContentType("application/json");
		response.getWriter().print(object);
	}

	protected void sendOk(HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		result.put("status","ok");
		send(response,result);
	}

	protected void sendError(HttpServletResponse response, Map<String,String> errors) throws IOException {
		JSONObject result = new JSONObject();
		JSONArray errorsArray = JsonConverter.toJSON(errors);
		result.put("status","hasError");
		result.put("errors",errorsArray);
		send(response,result);
	}

}
