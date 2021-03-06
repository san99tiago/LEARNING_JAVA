// EMPLOYEE WITH INHERITANCE FROM PERSON
// Santiago Garcia Arango

package oop2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Employee extends Person implements EmployeesInterface {

	// Create attributes
	private String area;
	private double salary;
	private Date jobEndsOn;

	// Important: a great example of using "static" method and keeping OOP rules...
	// ... because "id" is unique for each object, but idNext must be static...
	// ... to be able to access it for future object references
	private int id;
	private static int idNext = 1;

	// Constructor 1 (for overloading and "extending" arguments handling)
	public Employee(String name, String sex, String area, double salary, int year, int month, int day) {

		// Call superclass (parent) to get its constructor
		super(name, sex);

		this.area = area;
		this.salary = salary;

		// Remark: for GregorianCalendar objects, months start on zero
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		jobEndsOn = calendar.getTime();

		// Good way of having unique "id"s without breaking OOP rules...
		// ...(with static idNext and keeping private attributes for both)
		id = idNext;
		idNext++;
	}

	// Constructor 2 (for overloading and "extending" arguments handling)
	public Employee(String name, String sex, String area, double salary) {

		// Extend main constructor with default date (after that, we change the date)
		this(name, sex, area, salary, 0, 0, 0);

		// Set default job's end date one year after creation (overwrite jobEndsOn)
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.YEAR, 1);
		jobEndsOn = calendar.getTime();

	}

	// Getter for all information (this is an abstract class that we must use)
	@Override
	public String getInfo() {
		return "* Name: " + super.getName() + " (" + super.getSex() + ")\n* ID / Area: " + String.valueOf(id) + " / "
				+ String.valueOf(area) + "\n* Salary: " + String.valueOf(salary) + "\n* Job Ends On: "
				+ String.valueOf(jobEndsOn) + "\n";
	}

	// Setter for applying raise to employee's salary
	public void increseSalary(double percentage) {
		salary = salary * (1 + percentage / 100);
	}

	// Setter for changing area of employee
	public void changeArea(String new_area) {
		area = new_area;
	}

	// Method that must be implemented from the "EmployeesInterface"
	@Override
	public double giveBonus(double bonusAmount) {
		// Create bonus from constant given by the EmployeesInterface and the parameter
		// bonusAmount
		double totalBonus = EmployeesInterface.BASE_BONUS_AMOUNT + bonusAmount;

		// Only show console message, if this object is Employee (if it is boss, it will
		// be created...
		// ... in its class with its unique message (we will overwrite it)
		if (this instanceof Boss) {
			return totalBonus;
		}
		System.out.println("<Employee " + super.getName() + ", got a bonus of " + String.valueOf(totalBonus) + ">");
		return totalBonus;
	}

}
