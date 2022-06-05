package homework.course.work.Interface;


import homework.course.work.model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addAnEmployee(String firstName, String lastName);

    public Employee deleteAnEmployee(String firstName, String lastName);

    public Employee findAnEmployee(String firstName, String lastName);

    public List<Employee> employeesList();


}
