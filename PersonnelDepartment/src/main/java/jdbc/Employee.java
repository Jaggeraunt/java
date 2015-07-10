package jdbc;

import java.util.Date;

public class Employee {

	private int id;
	private long passportId;
	private String name;
	private Date birthday;
	private int salary;
	private Department department;
	private Position position;

	public Employee(int id, long passportId, String name, Date birthday, int salary, Department department, Position position) {
		this.id = id;
		this.passportId = passportId;
		this.name = name;
		this.birthday = birthday;
		this.salary = salary;
		this.department = department;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPassportId() {
		return passportId;
	}

	public void setPassport(long passportId) {
		this.passportId = passportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
