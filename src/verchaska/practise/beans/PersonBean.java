package verchaska.practise.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonBean {
	private String name;
	private int salary;
	@Override
	public String toString() {
		return "name=" + name + ", salary=" + salary + ", date of joining=" + new SimpleDateFormat("dd/MM/yyyy").format(dateOfJoining);
	}
	private Date dateOfJoining;
	
	public PersonBean(String name, int salary, Date dateOfJoining) {
		super();
		this.name = name;
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
	}
	public PersonBean()
	{
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

}
