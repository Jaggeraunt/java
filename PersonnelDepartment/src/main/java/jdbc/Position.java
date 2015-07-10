package jdbc;

public class Position {

	private int id;
	private String name;
	private int minSalary;
	private int maxSalary;

	public Position(int id, String name, int minSalary, int maxSalary) {
		this.id = id;
		this.name = name;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMinSalary() {
		return minSalary;
	}

	public int getMaxSalary() {
		return maxSalary;
	}
}
