package component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Validator {
	private Map<String,String> errors = new HashMap<>();
	private boolean hasError;
	protected boolean isNew;

	public Validator(boolean isNew) {
		this.isNew = isNew;
		hasError = false;
	}

	public boolean hasError() {
		return hasError;
	}

	public abstract boolean validate(HttpServletRequest request);
	public abstract Object getObject();

	public void addError(String fieldName, String error) {
		hasError = true;
		errors.put(fieldName,error);
	}

	public Map<String,String> getErrors() {
		return errors;
	}

	protected int toInt(String fieldName, String s) {
		int result;
		try {
			result = Integer.parseInt(s);
			return result;
		} catch (NumberFormatException e) {
			String error = String.format("'%s' must be numeric",fieldName);
			addError(fieldName,error);
			return 0;
		}
	}

	protected long toLong(String fieldName, String s) {
		long result;
		try {
			result = Long.parseLong(s);
			return result;
		} catch (NumberFormatException e) {
			String error = String.format("'%s' must be numeric",fieldName);
			addError(fieldName,error);
			return 0L;
		}
	}

	protected boolean isEmpty(String fieldName, String s) {
		if (s == null || "".equals(s)) {
			String error = String.format("'%s' is required",fieldName);
			addError(fieldName,error);
			return true;
		}
		return false;
	}

	protected Date toDate(String fieldName, String s) {
		Date result;
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			result = sdf.parse(s);
			return result;
		} catch (ParseException e) {
			String error = String.format("Format of '%s' must be '%s'",fieldName,format);
			addError(fieldName,error);
			return null;
		}
	}

}